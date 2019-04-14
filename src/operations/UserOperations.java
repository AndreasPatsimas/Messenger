/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import daoImpl.MessageDaoImpl;
import daoImpl.UserDaoImpl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import messenger.Database;
import messenger.File;
import messenger.Message;
import messenger.User;

/**
 *
 * @author andre
 */
public class UserOperations {
    public void userMenu(int i){
        System.out.println("User Menu");
        Scanner sc=new Scanner(System.in);
        boolean z=true;
        while(z==true){
            System.out.println("Make a choice");
            System.out.println("1) Inbox");
            System.out.println("2) Outbox");
            System.out.println("3) Type a message");
            System.out.println("4) Delete My Account");
            System.out.println("5) Back to main menu");
            try{
                int x=sc.nextInt();
                switch(x){
                    case 1:
                       Box(i,"Inbox","incoming","receive_message","receiver");
                       break;
                    case 2:
                       Box(i,"Outbox","sent","message","sender");
                       break;
                    case 3:
                        typeMessage(i);
                        break;
                    case 4:
                        z=deleteMyAccount(i);
                        break;
                    case 5:
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
        
    private List<Integer> viewMessages(int i,String table,String user) {
        List<Message> messagesList=new ArrayList<>();
        List<User> userList=new ArrayList<>();
        List<Integer> idList=new ArrayList<>();
        String username="";
        try{
            Database db =new Database();
            MessageDaoImpl messages=new MessageDaoImpl(db);
            UserDaoImpl users=new UserDaoImpl(db);
            userList=users.getUsers("select * from user where id="+i);
            username=userList.get(0).getUsername();
            messagesList=messages.getMessages("select * from "
                    + table+ " where "+user+"="+"'"+username+"'");
            for(int j=0;j<messagesList.size();j++){
                idList.add(messagesList.get(j).getId());
                System.out.println(messagesList.get(j));
            }
        }
        catch(Throwable t){
            t.printStackTrace();
        }
        return idList;
    }

    
    
    
    private void typeMessage(int i){
        boolean z=true;
        String receiver="";
        int idmes=-1;
        List<String> receiversList= new ArrayList <>();
        List<User> userList= new ArrayList <>();
        List<Integer> idmesList= new ArrayList <>();
        List<Message> mesList= new ArrayList <>();
        Scanner sc=new Scanner(System.in);
        try{
            Database db=new Database();
            UserDaoImpl users=new UserDaoImpl(db);
            MessageDaoImpl messages=new MessageDaoImpl(db);
            userList=users.getUsers("select * from user where id="+i);
            String sender=userList.get(0).getUsername();
            System.out.println("Choose the receiver from the users below.");
            userList=users.getUsers("select * from user");
            for(int j=0;j<userList.size();j++){
                receiversList.add(userList.get(j).getUsername());
            }
            System.out.println(receiversList);
            while(z==true){
                receiver=sc.nextLine();
                if(receiversList.contains(receiver)){
                    z=false;
                }
                
                else{
                    System.out.println("User with username "+receiver
                            +" does not exist.");
                    System.out.println("Give an existing username");
                }
            }
            System.out.println("Type the message you want");
            String message=sc.nextLine();
            messages.createMessage("message",sender, receiver, message);
            messages.createMessage("receive_message",sender, receiver, message);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date dates = new Date();
            String date=dateFormat.format(dates);
            mesList=messages.getMessages("select * from message");
            for(int j=0; j<mesList.size();j++){
                idmesList.add(mesList.get(j).getId());
            }
            Collections.reverse(idmesList);
            idmes=idmesList.get(0);
            String mes="id:"+idmes+" /date of submission:"+date+
                    " /sender:"+sender+" "
                    + "/receiver:"+receiver+" /message:"+message;
            File file=new File();
            file.writeFile("messages",mes);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void deleteMyMessages(int i,String table,String user) {
        Scanner sc=new Scanner(System.in);
        List<Integer> list=new ArrayList<>();
        try{
            Database db=new Database();
            list=viewMessages(i,table,user);
            System.out.println("Give id of message you want to delete");
            int id=sc.nextInt();
            if(list.contains(id)){
                MessageDaoImpl messages=new MessageDaoImpl(db);
                messages.deleteMessage(id,table);
            }
            else{
                System.out.println("Wrong id. Please try again.");
            }
        }
        catch(Exception e){
            sc.next();
            System.out.println("Invalid option");
        }
    }
    
    private void Box(int i,String box,String inout,String table,String user){
        System.out.println(box+" Menu");
        Scanner sc=new Scanner(System.in);
        boolean z=true;
        while(z==true){
            System.out.println("Make a choice");
            System.out.println("1) View "+inout+" messages");
            System.out.println("2) Delete "+inout+" messages");
            System.out.println("3) Back");
            try{
                int x=sc.nextInt();
                switch(x){
                    case 1:
                       viewMessages(i,table,user);
                       break;
                    case 2:
                       deleteMyMessages(i,table,user);
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
    
    private boolean deleteMyAccount(int i){
        Scanner sc = new Scanner(System.in);
        boolean z=true;
        List<User> userList=new ArrayList<>();
        try {
            Database db=new Database();
            UserDaoImpl users=new UserDaoImpl(db);
            userList=users.getUsers("select * from user where id="+i);
            String role=userList.get(0).getRole();
            if("simple user".equals(role)){
                System.out.println("Are you sure that you want to delete yor account?");
                System.out.println("---------------(Y/N)");
                String choice=sc.nextLine();
                while(z==true){
                    if("Y".equals(choice)||"N".equals(choice)){
                        z=false;
                        if("Y".equals(choice)){
                            users.deleteUser(i);
                            return false;
                        }
                    }
                    else{
                        System.out.println("Press Y or N");
                        choice=sc.nextLine();
                    }
                }
            }
            else{
                System.out.println("You cannot delete your account because you "
                        + "have a role.");
                System.out.println("Only Administrator can delete your account");
            }
        } 
        catch (Exception e) {
            sc.next();
            System.out.println("Invalid option");
        }
        return true;
    }
}
