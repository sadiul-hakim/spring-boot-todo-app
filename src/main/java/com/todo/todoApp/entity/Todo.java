package com.todo.todoApp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Todo implements Serializable {

    private static final Long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Boolean complete=false;


    public Todo() {
    }

    public Todo(Long id, String content, Boolean complete) {
        this.id = id;
        this.content = content;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }



    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", complete=" + complete +
                '}';
    }
}
