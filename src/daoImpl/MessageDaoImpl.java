/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.MessageDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import messenger.Database;
import messenger.Message;

/**
 *
 * @author andre
 */
public class MessageDaoImpl implements MessageDao {
    private Database db;

    public MessageDaoImpl(Database db) {
        this.db = new Database();
    }
    
        public ArrayList getMessages(String query){
        ArrayList<Message> messages=new ArrayList<>();
        try {
            Connection con=db.getConnected();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Message message=new Message(rs.getInt(1),rs.getDate(2), 
                        rs.getString(3), rs.getString(4), 
                        rs.getString(5));
                messages.add(message);
            }
            st.close();
            con.close();
            return messages;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void createMessage(String table,String sender,String receiver,String message){
        Connection con;
        try {
            con = db.getConnected();
            PreparedStatement pst=con.prepareStatement("insert into "+table
                    + " values(default,now(),?,?,?)");
            pst.setString(1, sender);
            pst.setString(2, receiver);
            pst.setString(3, message);
            int k=pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
    
    public void editMessage(int id,String message){
        Connection con;
        try {
            con = db.getConnected();
            PreparedStatement pst=con.prepareStatement("update message set "
                    + "date_of_submission=now(),"
                    + " message_data=? where id="+id);
            pst.setString(1, message);
            int k=pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
    public void deleteMessage(int id,String table){
        Connection con;
        try {
            con = db.getConnected();
            PreparedStatement pst=con.prepareStatement("delete from " +table 
                    + " where id="+id);
            int k=pst.executeUpdate();
            if(table=="message"){
                pst=con.prepareStatement("delete from receive_message where "
                        + "id="+id);
                int kj=pst.executeUpdate();
            }
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
}
