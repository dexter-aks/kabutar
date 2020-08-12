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

    @Query(value = "select m.content from Message m where m.sender.id = :senderId")
    List<String> findBySenderId(Long senderId);

    @Query(value = "select m.content from Message m where m.receiver.id = :receiverId")
    List<String> findByReceiverId(Long receiverId);

    @Query(value = "select m.content from Message m where m.receiver.id = :receiverId and m.sender.id = :senderId")
    List<String> findByReceiverSenderId(Long receiverId, Long senderId);

}
