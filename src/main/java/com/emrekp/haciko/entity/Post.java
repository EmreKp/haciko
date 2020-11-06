package com.emrekp.haciko.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nick;
    private String message;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Category category;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;

        return this;
    }

    public String getNick() {
        return nick;
    }

    public Post setNick(String nick) {
        this.nick = nick;

        return this;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public Post setMessage(String message) {
        this.message = message;

        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
