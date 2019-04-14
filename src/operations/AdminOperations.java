/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import daoImpl.UserDaoImpl;
import helpers.ExistRole;
import helpers.ExistUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import messenger.Database;
import messenger.User;

/**
 *
 * @author andre
 */
public class AdminOperations extends DeleterOperations{
    public void adminMenus(int i) {
        System.out.println("Administrator Menu");
        Scanner sc = new Scanner(System.in);
        boolean z = true;
        while (z == true) {
            System.out.println("Make a choice");
            System.out.println("1) Log in as an administrator");
            System.out.println("2) Log in as a deleter user");
            System.out.println("3) Log in as a simple user");
            System.out.println("4) Back to main menu");
            try {
                int x = sc.nextInt();
                switch (x) {
                    case 1:
                        adminMenu();
                        break;
                    case 2:
                        deleterMenu(i);
                        break;
                    case 3:
                        userMenu(i);
                        break;
                    case 4:
                        z = false;
                        break;
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Invalid option");
            }
        }
    }
    
    private void adminMenu() {
        System.out.println("Administrator Menu");
        Scanner sc = new Scanner(System.in);
        boolean z = true;
        while (z == true) {
            System.out.println("Make a choice");
            System.out.println("1) View users");
            System.out.println("2) Edit a user");
            System.out.println("3) Delete a user");
            System.out.println("4) Create a user");
            System.out.println("5) Back to Main menu");
            try {
                int x = sc.nextInt();
                switch (x) {
                    case 1:
                        viewUser();
                        break;
                    case 2:
                        editUser();
                        break;
                    case 3:
                        deleteUser();
                        break;
                    case 4:
                        createUser();
                        break;
                    case 5:
                        z = false;
                        break;
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Invalid option");
            }
        }
    }
    
    private void viewUser(){
        try{
            List<User> list=new ArrayList<>();
            Database db=new Database();
            UserDaoImpl users=new UserDaoImpl(db);
            list=users.getUsers("select * from user");
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void editUser(){
        List<String> usernames = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        List<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Scanner srol=new Scanner(System.in);
        boolean z = true;
        try{
            Database db=new Database();
            System.out.println("Give id of user you want to edit");
            int id = sc.nextInt();
            while (id == 1) {
                System.out.println("Administrator cannot be edited");
                System.out.println("Give another id");
                id = sc.nextInt();
                id = ExistUser.checkId(id);
            }
            id = ExistUser.checkId(id);
            if(id!=-1){
                UserDaoImpl users=new UserDaoImpl(db);
                userList=users.getUsers("select * from user");
                for(int i=0;i<userList.size();i++){
                    usernames.add(userList.get(i).getUsername());
                    passwords.add(userList.get(i).getPassword());
                }
                userList=users.getUsers("select * from user where id="+id);
                list.add(userList.get(0).getUsername());
                System.out.println("Edit the user.");
                System.out.println("Change username");
                String username = sc.next();
                while (z == true) {
                    if (usernames.contains(username)&&!list.get(0).equals(username)) {
                        System.out.println("The username, you typed, exists already.");
                        System.out.println("Please, give another username.");
                        username = sc.next();
                    } else {
                        z = false;
                    }
                }
                list.add(userList.get(0).getPassword());
                z = true;
                System.out.println("Change password");
                String password = sc.next();
                while (z == true) {
                    if (passwords.contains(password)&&!list.get(1).equals(password)) {
                        System.out.println("The password, you typed, exists already.");
                        System.out.println("Please, give another password.");
                        password = sc.next();
                    } else {
                        z = false;
                    }
                }
                System.out.println("Change role");
                String role = srol.nextLine();
                role = ExistRole.assignRole(role);
                users.editUser(id, username, password, role);
            }
        }
        catch(Exception e){
            sc.next();
            System.out.println("Invalid option");
        }
    }
    private void deleteUser(){
        Scanner sc = new Scanner(System.in);
        try {
            Database db=new Database();
            System.out.println("Give id of user you want to delete");
            int id = sc.nextInt();
            while (id == 1) {
                System.out.println("Administrator cannot be deleted");
                System.out.println("Give another id");
                id = sc.nextInt();
                id = ExistUser.checkId(id);
            }
            
            id = ExistUser.checkId(id);
            if(id!=-1){
                UserDaoImpl users=new UserDaoImpl(db);
                users.deleteUser(id);
            }
        } 
        catch (Exception e) {
            sc.next();
            System.out.println("Invalid option");
        }
    }
    private void createUser(){
        List<User> userList = new ArrayList<>();
        List<String> usernames = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Scanner srol=new Scanner(System.in);
        boolean z = true;
        try{
            Database db=new Database();
            UserDaoImpl users=new UserDaoImpl(db);
            userList=users.getUsers("select * from user");
            for(int i=0;i<userList.size();i++){
                usernames.add(userList.get(i).getUsername());
                passwords.add(userList.get(i).getPassword());
            }
            System.out.println("Give username");
            String username = sc.next();
            while (z == true) {
                if (usernames.contains(username)) {
                    System.out.println("The username, you typed, exists already.");
                    System.out.println("Please, give another username.");
                    username = sc.next();
                } else {
                    z = false;
                }
            }
            z = true;
            System.out.println("Give password");
            String password = sc.next();
            while (z == true) {
                if (passwords.contains(password)) {
                    System.out.println("The password, you typed, exists already.");
                    System.out.println("Please, give another password.");
                    password = sc.next();
                } else {
                    z = false;
                }
            }
            System.out.println("Give role");
            String role = srol.nextLine();
            role = ExistRole.assignRole(role);
            users.createUser(username, password, role);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
