package com.dexter.kabutar.dao;

import com.dexter.kabutar.domain.Message;
import com.dexter.kabutar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Override
    <S extends Message> S save(S entity);

    @Override
    List<Message> findAll();

//    @Query(value = "select m.content from Message m where m.sender =: senderId")
    List<Message> findBySenderId(Long senderId);
}
