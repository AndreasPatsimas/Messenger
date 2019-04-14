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
import operations.AdminOperations;
import operations.DeleterOperations;
import operations.EditorOperations;
import operations.UserOperations;
import operations.ViewerOperations;

/**
 *
 * @author andre
 */
public class Menu {

    public Menu() {
    }
    
    
    public void runMenu() throws SQLException  {
        printHeaders();
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getInput();
            exit = makeChoice(choice);
        }
    }

    private static void printHeaders() {
        System.out.println("+-------------------------------+");
        System.out.println("|      Welcome to our           |");
        System.out.println("|      Messenger's Menu         |");
        System.out.println("+-------------------------------+");
    }

    private static void printMenu() {
        System.out.println("Please make a selection...");
        System.out.println("1) Log in");
        System.out.println("2) Register");
        System.out.println("3) Exit");
    }

    private static int getInput() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice < 1 || choice > 3) {
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                sc.next();
                System.out.println("Invalid choice...");
            }
            if (choice < 1 || choice > 3) {
                System.out.println("Μake the right choice...");
            }
        }
        return choice;
    }
    
    private static boolean makeChoice(int choice) throws SQLException  {
        int i = -1;
        List<User> userList=new ArrayList<>();
        switch (choice) {
            case 1:
                Login log=new Login();
                i = log.login();
                Database db=new Database();
                UserDaoImpl users= new UserDaoImpl(db);
                if(i!=-1){
                    userList=users.getUsers("select * from user where id="+i);
                    String role = userList.get(0).getRole();
                    switch (role) {
                        case "simple user":
                            UserOperations user=new UserOperations();
                            user.userMenu(i);
                            break;
                        case "viewer":
                            ViewerOperations viewer=new ViewerOperations();
                            viewer.viewerMenus(i);
                            break;
                        case "editor":
                            EditorOperations editor=new EditorOperations();
                            editor.editorMenus(i);
                            break;
                        case "deleter":
                            DeleterOperations deleter=new DeleterOperations();
                            deleter.deleterMenus(i);
                            break;
                        case "administrator":
                            AdminOperations admin=new AdminOperations();
                            admin.adminMenus(i);
                            break;
                    }
                    break;
                }
                else{
                    break;
                }
            case 2:
                List<String> usernames = new ArrayList<>();
                List<String> passwords = new ArrayList<>();
                Scanner sc = new Scanner(System.in);
                boolean z = true;
                try{
                    db=new Database();
                    users=new UserDaoImpl(db);
                    userList=users.getUsers("select * from user");
                    for(int j=0;j<userList.size();j++){
                        usernames.add(userList.get(j).getUsername());
                        passwords.add(userList.get(j).getPassword());
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

                    users.createUser(username, password,"simple user");
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case 3:
                System.out.println("Τhank you for using messenger's application");
                break;
        }
        if (choice!= 3) {
            return false;
        }

        return true;
    }
}
