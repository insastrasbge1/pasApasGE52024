/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas;

/**
 *
 * @author fdebertranddeb01
 */
public class Utils {
    
    public static void sleepSansInterrupt(long duree) {
        try {
            Thread.sleep(duree);
        } catch (InterruptedException ex) {
            throw new Error("impossible",ex);
        }
    }
    
    public static void joinSansInterrupt(Thread aAttendre) {
        try {
            aAttendre.join();
        } catch (InterruptedException ex) {
            throw new Error("impossible",ex);
        }
    }
    
}
