/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.projectkontak.view;

/**
 *
 * @author Putri Ayu 
 */
public class Projectkontak {
    
    public static void main(String[] args) {
        //jdbc:mysql://localhost:3306/aplikasi_kontak?zeroDateTimeBehavior=CONVERT_TO_NULL [root on Default schema]
        new Login().setVisible(true);
    }
}
