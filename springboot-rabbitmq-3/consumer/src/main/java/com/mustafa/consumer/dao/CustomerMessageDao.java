package com.mustafa.consumer.dao;

import java.util.Date;

public class CustomerMessageDao {
    //-------------------------------------------------------------------Variables
    private String messageId;
    private String message;
    private Date messageDate;

    //-------------------------------------------------------------------Constructor
    public CustomerMessageDao() {
    }

    public CustomerMessageDao(String messageId, String message, Date messageDate) {
        this.messageId = messageId;
        this.message = message;
        this.messageDate = messageDate;
    }

    //-------------------------------------------------------------------Getter-Setter
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}

