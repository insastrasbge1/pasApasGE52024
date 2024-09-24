/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.partage;

import fr.insa.beuvron.pasapas.Utils;

/**
 *
 * @author fdebertranddeb01
 */
public class IncrementV2 {
    
    public static final long MAX = 1000000;
    
    public static long compteur = 0;
    
    public static void gogogo() {
        Thread t1 = new Thread(() -> {
            synchronized (IncrementV2.class) {
            for(long i = 0 ; i < MAX ; i ++) {
                compteur ++;
            }
        }});
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (IncrementV2.class) {
            for(long i = 0 ; i < MAX ; i ++) {
                compteur++;
            }
        }});
        t2.start();
        Utils.joinSansInterrupt(t1);
        Utils.joinSansInterrupt(t2);
        System.out.println("valeur finale : " + compteur);
        
    }
    
    public static void main(String[] args) {
        gogogo();
    }
    
}
