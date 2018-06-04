package com.gabi.learnings.springBootMicroservices.user;

import java.util.Date;

public class Post {

    private Integer id;
    private Integer user_id;
    private Date date;
    private String subject;
    private String details;

    public Post(){

    }
    public Post(int id, int user_id, Date date, String subject, String details) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.subject = subject;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", date=" + date +
                ", subject='" + subject + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
