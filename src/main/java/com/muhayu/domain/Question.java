package com.muhayu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;
    private String title;
    private String contents;

    public Question(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
