package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Poll;
import com.emrekp.haciko.entity.PollChoice;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PollRepository {

  private final PollCrudRepository pollCrudRepository;
  private final PollChoiceCrudRepository choiceCrudRepository;

  @Autowired
  public PollRepository(
      PollCrudRepository pollCrudRepository,
      PollChoiceCrudRepository choiceCrudRepository
  ) {
    this.pollCrudRepository = pollCrudRepository;
    this.choiceCrudRepository = choiceCrudRepository;
  }

  public Poll find(long id) {
    return pollCrudRepository.findById(id).orElse(null);
  }

  public Poll save(Poll poll) {
    Poll savedPoll = pollCrudRepository.save(poll);

    // TODO: Optimize it.
    choiceCrudRepository.saveAll(
        poll.getChoices().stream()
            .map(choice -> choice.setPoll(savedPoll))
            .collect(Collectors.toList())
    );

    return savedPoll;
  }

  public PollChoice findChoice(long id) {
    return choiceCrudRepository.findById(id).orElse(null);
  }

  public PollChoice saveChoice(PollChoice choice) {
    return choiceCrudRepository.save(choice);
  }
}
