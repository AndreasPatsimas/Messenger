/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Scanner;

/**
 *
 * @author andre
 */
public class ExistRole {
    
    public static String assignRole(String role) {
        Scanner sc = new Scanner(System.in);
        boolean z = true;
        while (z == true) {
            if ("viewer".equals(role) || "editor".equals(role)
                    || "deleter".equals(role) || "simple user".equals(role)) {
                z = false;
            } else if ("administrator".equals(role)) {
                System.out.println("You cannot create another administrator.");
                role = sc.nextLine();
            } else {
                System.out.println("It doesn't exist that role:" + role);
                System.out.println("The possible choices are:");
                System.out.println("- simple user");
                System.out.println("- viewer");
                System.out.println("- editor");
                System.out.println("- deleter");
                System.out.println("Make a right choice, please.");
                role = sc.nextLine();
            }
        }
        return role;
    }
    
}
