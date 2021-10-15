package com.emrekp.haciko.dto;

import com.emrekp.haciko.entity.PollChoice;
import java.util.ArrayList;
import java.util.List;

public class PollDto {

  private String question;
  private List<PollChoice> choices;
  private Integer expireInterval;

  public String getQuestion() {
    return question;
  }

  public PollDto setQuestion(String question) {
    this.question = question;

    return this;
  }

  public List<PollChoice> getChoices() {
    return choices;
  }

  public PollDto setChoices(List<PollChoice> choices) {
    this.choices = choices;

    return this;
  }

  public PollDto addChoice(PollChoice choice) {
    if (this.choices == null) {
      this.choices = new ArrayList<>();
    }

    this.choices.add(choice);

    return this;
  }

  public Integer getExpireInterval() {
    return expireInterval;
  }

  public PollDto setExpireInterval(Integer expireInterval) {
    this.expireInterval = expireInterval;

    return this;
  }
}
