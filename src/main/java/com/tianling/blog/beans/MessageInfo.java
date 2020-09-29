package com.tianling.blog.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
public class MessageInfo extends Model<MessageInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    private String messageContent;

    private LocalDateTime messageTime;

    private String messageName;

    private String messageMark;


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageMark() {
        return messageMark;
    }

    public void setMessageMark(String messageMark) {
        this.messageMark = messageMark;
    }

    @Override
    protected Serializable pkVal() {
        return this.messageId;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
        "messageId=" + messageId +
        ", messageContent=" + messageContent +
        ", messageTime=" + messageTime +
        ", messageName=" + messageName +
        ", messageMark=" + messageMark +
        "}";
    }
}
