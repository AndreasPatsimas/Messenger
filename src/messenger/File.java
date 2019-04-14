/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author andre
 */
public class File {
    
    public  void writeFile(String nameFile,String message){
        try(FileWriter fw = new FileWriter(nameFile+".txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            out.println(message);

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void createFile(String fileName){
        try {
	     java.io.File file = new java.io.File(fileName+".txt");
	     
             boolean fvar = file.createNewFile();
	     if (fvar){
	          System.out.println("File has been created successfully");
	     }
	     else{
	          System.out.println("File already present at the specified location");
	     }
    	} 
        catch (IOException e) {
    		System.out.println("Exception Occurred:");
	        e.printStackTrace();
        }
    }

    
    
}
