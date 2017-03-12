package com.muhayu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hyecheon on 2017. 3. 12..
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue
    private long id;
    private String userId;
    private String title;
    private String contents;

    public Question(String userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }
}
