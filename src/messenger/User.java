/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;


import java.util.Date;


/**
 *
 * @author andre
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String role;
    private Date date;
   

    public User() {
    }
    
    public User(int id, String username, String password, String role,Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.date=date;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    

    @Override
    public String toString() {
        return "{" + "id=" + id + ", username=" + username + ", password=" +
                password + ", role=" + role + ", date=" + date + '}';
    }
    
        
}
