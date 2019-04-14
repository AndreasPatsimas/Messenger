/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import messenger.Database;
import messenger.User;

/**
 *
 * @author andre
 */
public class UserDaoImpl implements UserDao {
    
    private Database db;

    public UserDaoImpl(Database db) {
        this.db = new Database();
    }
    
    public ArrayList<User> getUsers(String query){
        ArrayList<User> users=new ArrayList<>();
        try {
            Connection con=db.getConnected();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                User user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDate(5));
                users.add(user);
            }
            st.close();
            con.close();
            return users;
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void createUser(String username,String password,String role){
        try {
            Connection con=db.getConnected();
            PreparedStatement pst = con.prepareStatement("insert into user "
                    + "values(default,?,?,?,now())");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            int k = pst.executeUpdate();
            pst.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void editUser(int id,String username,String password,String role){
        try {
            Connection con=db.getConnected();
            PreparedStatement pst = con.prepareStatement("update user set "
                    + "date_of_creation=now(),"
                    + " username=?, password=?, role=? where id="+id);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            int k = pst.executeUpdate();
            pst.close();
            con.close();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteUser(int id){
        Connection con;
        try {
            con = db.getConnected();
            String delete = "delete from user where id=?";
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setInt(1, id);
            int k = pst.executeUpdate();
            pst.close();
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
