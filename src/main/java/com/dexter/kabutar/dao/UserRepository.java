package com.dexter.kabutar.dao;

import com.dexter.kabutar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.nickName = :nickName")
    User findByNickName(String nickName);
}
