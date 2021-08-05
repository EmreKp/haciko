package com.emrekp.haciko.controller;

import com.emrekp.haciko.dto.PollDto;
import com.emrekp.haciko.entity.Poll;
import com.emrekp.haciko.entity.PollChoice;
import com.emrekp.haciko.service.PollService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/polls")
public class PollController {

  private final PollService service;

  @Autowired
  public PollController(PollService service) {
    this.service = service;
  }

  @GetMapping("/new")
  public String addPollPage(Model model) {
    // Add poll with two choices at start.
    model.addAttribute("poll", new PollDto().addChoice(new PollChoice()).addChoice(new PollChoice()));

    return "poll_new";
  }

  @PostMapping("/new")
  public String addPoll(@ModelAttribute("poll") PollDto pollDto, RedirectAttributes redirectAttr) {
    Poll poll;

    try {
      poll = service.convertAndSavePoll(pollDto);
    } catch (Exception ex) {
      redirectAttr.addFlashAttribute("error", ex.getMessage());

      return "redirect:/polls/new";
    }

    return String.format("redirect:/polls/%d", poll.getId());
  }

  @GetMapping("/{id}")
  public String getPoll(@PathVariable Long id, Model model, HttpSession session) {
    Poll poll = service.getPoll(id);
    model.addAttribute("poll", poll);

    ArrayList<Long> selectedChoices = (ArrayList<Long>) session.getAttribute("selectedChoices");

    Long selectedChoice = null;
    if (selectedChoices != null) {
      selectedChoice = poll.getChoices().stream()
          .filter(choice -> selectedChoices.contains(choice.getId())).findFirst()
          .map(PollChoice::getId).orElse(null);
    }

    model.addAttribute("selectedChoice", selectedChoice);
    model.addAttribute(
        "totalVotes",
        poll.getChoices().stream().mapToInt(PollChoice::getVoteCount).sum()
    );
    model.addAttribute(
        "isExpired",
        poll.getExpiresAt() != null && poll.getExpiresAt().isBefore(LocalDateTime.now())
    );

    return "poll";
  }

  @PostMapping("/{id}")
  public String vote(@RequestParam Long choiceId, HttpSession session) {
    PollChoice choice = service.vote(choiceId);

    ArrayList<Long> selectedChoices = (ArrayList<Long>) session.getAttribute("selectedChoices");

    if (selectedChoices == null) {
      selectedChoices = new ArrayList<>();
    }

    selectedChoices.add(choice.getId());

    session.setAttribute("selectedChoices", selectedChoices);

    service.publishVote(choice);

    return String.format("redirect:/polls/%d", choice.getPoll().getId());
  }

  @SendTo("/topic/votes") // We don't need MessageMapping for now, because endpoint called directly.
  public Long voteTopic(PollChoice choice) {
    return choice.getId();
  }
}
