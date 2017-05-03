package com.questionnaire.area.reference.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Entity
@Table(name = "answer_references")
public class AnswerReference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "url", nullable = false)
    private String url;

    public AnswerReference() {
        super();
    }

    public AnswerReference(String url) {
        this.setUrl(url);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}