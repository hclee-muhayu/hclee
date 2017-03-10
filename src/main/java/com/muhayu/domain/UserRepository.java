package com.muhayu.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hyecheon on 2017. 3. 11..
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
