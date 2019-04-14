/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import daoImpl.UserDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import messenger.Database;
import messenger.User;

/**
 *
 * @author andre
 */
public class ExistUser {
    
    public static int checkId(int id){
        boolean z=true;
        Database db=new Database();
        List<User> list=new ArrayList<>();
        List<Integer> idList=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        UserDaoImpl users=new UserDaoImpl(db);
        list=users.getUsers("select * from user");
        for(int i=0;i<list.size();i++){
            idList.add(list.get(i).getId());
        }
        try{
            while(z==true){
                if(idList.contains(id)){
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
