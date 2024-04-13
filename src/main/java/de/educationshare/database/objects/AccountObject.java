package de.educationshare.database.objects;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "accounts")
public class AccountObject {
    public AccountObject() {}

    public AccountObject(String username, String password, boolean isTeacher, Date createdAt) {
        this.username = username;
        this.password = password;
        this.isTeacher = isTeacher;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "isTeacher")
    private boolean isTeacher;

    @Column(name = "createdAt")
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }
}