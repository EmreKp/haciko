package com.emrekp.haciko.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "polls")
public class Poll {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String question;
  private LocalDateTime expiresAt;

  @OneToMany(mappedBy = "poll")
  private List<PollChoice> choices;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public Long getId() {
    return id;
  }

  public Poll setId(Long id) {
    this.id = id;

    return this;
  }

  public String getQuestion() {
    return question;
  }

  public Poll setQuestion(String question) {
    this.question = question;

    return this;
  }

  public LocalDateTime getExpiresAt() {
    return expiresAt;
  }

  public Poll setExpiresAt(LocalDateTime expiresAt) {
    this.expiresAt = expiresAt;

    return this;
  }

  public List<PollChoice> getChoices() {
    return choices;
  }

  public Poll setChoices(List<PollChoice> choices) {
    this.choices = choices;

    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public Poll setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;

    return this;
  }
}
