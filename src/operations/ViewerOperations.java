/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import daoImpl.MessageDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import messenger.Database;
import messenger.Message;

/**
 *
 * @author andre
 */
public class ViewerOperations extends UserOperations {
    public  void viewerMenus(int i) {
        System.out.println("Viewer Menu");
        Scanner sc = new Scanner(System.in);
        boolean z = true;
        while (z == true) {
            System.out.println("Make a choice");
            System.out.println("1) Log in as a viewer user");
            System.out.println("2) Log in as a simple user");
            System.out.println("3) Back to main menu");
            try {
                int x = sc.nextInt();
                switch (x) {
                    case 1:
                        viewerMenu(i);
                        break;
                    case 2:
                        userMenu(i);
                        break;
                    case 3:
                        z = false;
                        break;
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Invalid option");
            }
        }
    }
    
        private void viewerMenu(int i) {
        System.out.println("Viewer Menu");
        Scanner sc=new Scanner(System.in);
        boolean z=true;
        while(z==true){
            System.out.println("Make a choice");
            System.out.println("1) View messages");
            System.out.println("2) Back to Main menu");
            try{
                int x=sc.nextInt();
                switch(x){
                    case 1:
                       viewMessages();
                       break;
                    case 2:
                        z=false;
                        break;
                }
            }
            catch(Exception e){
                 sc.next();
                 System.out.println("Invalid option");
            }
        }
    }
        
    protected void viewMessages() {
        List<Message> messageList=new ArrayList<>();
        try{
            Database db=new Database();
            MessageDaoImpl messages=new MessageDaoImpl(db);
            messageList=messages.getMessages("select * from message");
            for(int i=0;i<messageList.size();i++){
                System.out.println(messageList.get(i));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
