package com.muhayu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hyecheon on 2017. 3. 7..
 */

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String userId;
    private String password;
    private String email;
    private String name;

    public boolean matchUserPassword(String password) {
        return password.equals(this.password);
    }
}
