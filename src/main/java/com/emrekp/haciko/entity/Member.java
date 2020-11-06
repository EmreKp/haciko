package com.emrekp.haciko.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nick;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public Member setId(Long id) {
        this.id = id;

        return this;
    }

    public String getNick() {
        return nick;
    }

    public Member setNick(String nick) {
        this.nick = nick;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public Member setPassword(String password) {
        this.password = password;

        return this;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Member setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Member setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;

        return this;
    }
}
