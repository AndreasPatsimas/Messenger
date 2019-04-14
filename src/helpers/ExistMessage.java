/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

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
public class ExistMessage {
    public static int checkId(int id) {
        boolean z=true;
        List<Integer> listId=new ArrayList<>();
        List<Message> list=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        try{
            Database db=new Database();
            MessageDaoImpl message=new MessageDaoImpl(db);
            list=message.getMessages("select * from message");
            for(int i=0; i<list.size();i++){
                listId.add(list.get(i).getId());
            }
            while(z==true){
                if(listId.contains(id)){
                    z=false;
                }
                else{
                    System.out.println("An id="+id+" doesn't exist.");
                    System.out.println("Please give an existing id");
                    id=sc.nextInt();
                }
            }
        }
        catch(Exception e){
            sc.next();
            System.out.println("Invalid option");
            return -1;
        }
        return id;
    }
}
