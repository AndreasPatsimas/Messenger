/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author andre
 */
public class Message {
    private int id;
    private Date date;
    private String sender;
    private String receiver;
    private String messageData;

    public Message() {
    }

    public Message(int id,Date date, String sender, String receiver, 
            String messageData) {
        this.id = id;
        this.date=date;
        this.sender = sender;
        this.receiver = receiver;
        this.messageData = messageData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDate(){
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", date=" + date + ", sender=" +
                sender + ", receiver=" + receiver + ", messageData=" 
                + messageData + '}';
    }
    
}
