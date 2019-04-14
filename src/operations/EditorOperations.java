/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import daoImpl.MessageDaoImpl;
import helpers.ExistMessage;
import java.util.Scanner;
import messenger.Database;

/**
 *
 * @author andre
 */
public class EditorOperations extends ViewerOperations {
    public void editorMenus(int i) {
        System.out.println("Editor Menu");
        Scanner sc = new Scanner(System.in);
        boolean z = true;
        while (z == true) {
            System.out.println("Make a choice");
            System.out.println("1) Log in as an editor user");
            System.out.println("2) Log in as a simple user");
            System.out.println("3) Back to main menu");
            try {
                int x = sc.nextInt();
                switch (x) {
                    case 1:
                        editorMenu(i);
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
        
        protected void editorMenu(int i) {
        System.out.println("Editor Menu");
        Scanner sc=new Scanner(System.in);
        boolean z=true;
        while(z==true){
            System.out.println("Make a choice");
            System.out.println("1) View messages");
            System.out.println("2) Edit a message");
            System.out.println("3) Back to Main menu");
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
        
    protected void editMessage() {
        Database db=new Database();
        Scanner sc=new Scanner (System.in);
        Scanner cs=new Scanner (System.in);
        try{
            MessageDaoImpl editMessage=new MessageDaoImpl (db);
            System.out.println("Give id of message you want to edit");
            int id=sc.nextInt();
            id=ExistMessage.checkId(id);
            if(id!=-1){
                System.out.println("Edit the message");
                String message=cs.nextLine();
                editMessage.editMessage(id, message);
            }
        }
        catch(Exception e){
            sc.next();
            System.out.println("Invalid option");
        }
    }
}
