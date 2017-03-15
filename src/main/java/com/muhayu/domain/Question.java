package com.muhayu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by hyecheon on 2017. 3. 12..
 */
@Log4j
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

    @OneToMany(mappedBy = "question")
    @OrderBy("id ASC ")
    private List<Answer> answers;

    @Lob
    private String contents;

    private LocalDateTime createDate;

    public Question(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public String getFormattedCreateDate() {
        if (createDate == null) {
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy . MM . dd HH:ss"));
    }

    public void update(Question question) {
        this.title = question.getTitle();
        this.contents = question.getContents();
    }

    public boolean isSameWriter(User loginUser) {

        return loginUser.equals(writer);
    }
}
