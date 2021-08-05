package com.emrekp.haciko.service;

import com.emrekp.haciko.dto.PollDto;
import com.emrekp.haciko.entity.Poll;
import com.emrekp.haciko.entity.PollChoice;
import com.emrekp.haciko.repo.PollRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PollService {

  private static final int CHOICE_MIN_LIMIT = 2;
  private static final int CHOICE_MAX_LIMIT = 7;

  @Value("${poll.page-hacking-exception-message}")
  private String PAGE_HACK_EXCEPTION_MESSAGE;

  private final PollRepository repository;
  private final SimpMessagingTemplate messagingTemplate;

  @Autowired
  public PollService(PollRepository repository, SimpMessagingTemplate messagingTemplate) {
    this.repository = repository;
    this.messagingTemplate = messagingTemplate;
  }

  public Poll getPoll(Long id) {
    return repository.find(id);
  }

  public PollChoice vote(Long id) {
    PollChoice choice = repository.findChoice(id);

    choice.setVoteCount(choice.getVoteCount() + 1);

    return repository.saveChoice(choice);
  }

  public Poll convertAndSavePoll(PollDto pollDto) {
    int choiceCount = pollDto.getChoices().size();
    if (choiceCount < CHOICE_MIN_LIMIT || choiceCount > CHOICE_MAX_LIMIT) {
      throw new RuntimeException(PAGE_HACK_EXCEPTION_MESSAGE);
    }

    LocalDateTime expireTime = null;
    Integer expireSeconds = pollDto.getExpireInterval();
    if (expireSeconds != null && expireSeconds != -1) {
      expireTime = LocalDateTime.now().plusSeconds(expireSeconds);
    }

    // Convert DTO to plain Poll object.
    Poll poll = new Poll()
        .setQuestion(pollDto.getQuestion())
        .setChoices(pollDto.getChoices())
        .setExpiresAt(expireTime);

    return repository.save(poll);
  }

  public void publishVote(PollChoice choice) {
    messagingTemplate.convertAndSend("/topic/votes", choice.getId());
  }
}
