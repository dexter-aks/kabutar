package com.dexter.kabutar.model;

import java.util.Objects;

public class MessageInfo {

    private final String content;
    private final String senderNickName;
    private final String receiverNickName;

    public MessageInfo(String content, String senderNickName, String receiverNickName) {
        this.content = content;
        this.senderNickName = senderNickName;
        this.receiverNickName = receiverNickName;
    }

    public String getContent() {
        return content;
    }

    public String getSenderNickName() {
        return senderNickName;
    }

    public String getReceiverNickName() {
        return receiverNickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageInfo)) return false;
        MessageInfo that = (MessageInfo) o;
        return content.equals(that.content) &&
                senderNickName.equals(that.senderNickName) &&
                receiverNickName.equals(that.receiverNickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, senderNickName, receiverNickName);
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "content='" + content + '\'' +
                ", senderNickName='" + senderNickName + '\'' +
                ", receiverNickName='" + receiverNickName + '\'' +
                '}';
    }
}
