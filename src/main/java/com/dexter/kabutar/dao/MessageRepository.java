package com.dexter.kabutar.dao;

import com.dexter.kabutar.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select m.content from Message m where m.sender.nickName = :senderNickName")
    List<String> findBySenderNickName(String senderNickName);

    @Query(value = "select m.content from Message m where m.receiver.nickName = :receiverNickName")
    List<String> findByReceiverNickName(String receiverNickName);

    @Query(value = "select m.content from Message m where m.receiver.nickName = :receiverNickName and m.sender.nickName = :senderNickName")
    List<String> findByReceiverSenderNickName(String receiverNickName, String senderNickName);

}
