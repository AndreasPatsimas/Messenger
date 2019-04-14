/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author andre
 */
public interface MessageDao {
    public ArrayList getMessages(String query);
    public void createMessage(String table,String sender,String receiver,String message);
    public void editMessage(int id,String message);
    public void deleteMessage(int id,String table);
}
