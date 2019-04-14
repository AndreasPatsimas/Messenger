/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import daoImpl.MessageDaoImpl;
import helpers.ExistMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import messenger.Database;

/**
 *
 * @author andre
 */
public class DeleterOperations extends EditorOperations {
    public void deleterMenus(int i) {
        System.out.println("Deleter Menu");
        Scanner sc = new Scanner(System.in);
        boolean z = true;
        while (z == true) {
            System.out.println("Make a choice");
            System.out.println("1) Log in as a deleter user");
            System.out.println("2) Log in as a simple user");
            System.out.println("3) Back to main menu");
            try {
                int x = sc.nextInt();
                switch (x) {
                    case 1:
                        deleterMenu(i);
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
        
        protected void deleterMenu(int i){
        System.out.println("Deleter Menu");
        Scanner sc=new Scanner(System.in);
        boolean z=true;
        while(z==true){
            System.out.println("Make a choice");
            System.out.println("1) View messages");
            System.out.println("2) Edit a message");
            System.out.println("3) Delete a message");
            System.out.println("4) Back to Main menu");
            try{
                int x=sc.nextInt();
                switch(x){
                    case 1:
                       viewMessages();
                       break;
                    case 2:
                        editMessage();
                        break;
                    case 3:
                        deleteMessage();
                        break;
                    case 4:
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
        
    private void deleteMessage() {
        Scanner sc=new Scanner(System.in);
        try{
            Database db=new Database();
            System.out.println("Give id of message you want to delete");
            int id=sc.nextInt();
            id=ExistMessage.checkId(id);
            if(id!=-1){
                MessageDaoImpl messages=new MessageDaoImpl(db);
                messages.deleteMessage(id,"message");
                messages.deleteMessage(id,"receive_message");
            }
        }
        catch(Exception e){
            sc.next();
            System.out.println("Invalid option");
        }
    }
}
