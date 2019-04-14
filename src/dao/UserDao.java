/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import messenger.User;

/**
 *
 * @author andre
 */
public interface UserDao {
    public ArrayList<User> getUsers(String query);
    public void createUser(String username,String password,String role);
    public void editUser(int id,String username,String password,String role);
    public void deleteUser(int id);
}
