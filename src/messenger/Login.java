/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import daoImpl.UserDaoImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Login {

    public Login() {
    }
    
    
    public int login() throws SQLException {  
        List<Integer> idList=  new ArrayList<>();
        List<String>  usernameList= new ArrayList<>();
        List<String>  passwordList= new ArrayList<>();
        List<User>  userList= new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        Database db=new Database();
        UserDaoImpl users=new UserDaoImpl(db);
        Scanner cs=new Scanner(System.in);
        try{
            System.out.println("Please enter your username...");
            String username=cs.nextLine();
            userList=users.getUsers("select * from user where username="+"'"+username+"'");
            idList.add(userList.get(0).getId());
            usernameList.add(userList.get(0).getUsername());
            System.out.println("Please enter your password...");
            String password=cs.nextLine();
            passwordList.add(userList.get(0).getPassword());
            if((usernameList.get(0).equals(username))
                    &&(passwordList.get(0).equals(password))){
                return idList.get(0);
            }
            else{
                System.out.println("Invalid username or password. Please "
                        + "try again...");
            }
        }
        catch(Exception e){
            System.out.println("Invalid username...");
        } 
        return -1;
    }
    
}
