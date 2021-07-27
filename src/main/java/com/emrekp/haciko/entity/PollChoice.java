package com.emrekp.haciko.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "pollChoices")
public class PollChoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;
  private Integer voteCount = 0;

  @ManyToOne
  private Poll poll;

  public Long getId() {
    return id;
  }

  public PollChoice setId(Long id) {
    this.id = id;

    return this;
  }

  public String getText() {
    return text;
  }

  public PollChoice setText(String text) {
    this.text = text;

    return this;
  }

  public Integer getVoteCount() {
    return voteCount;
  }

  public PollChoice setVoteCount(Integer voteCount) {
    this.voteCount = voteCount;

    return this;
  }

  public Poll getPoll() {
    return poll;
  }

  public PollChoice setPoll(Poll poll) {
    this.poll = poll;

    return this;
  }
}
