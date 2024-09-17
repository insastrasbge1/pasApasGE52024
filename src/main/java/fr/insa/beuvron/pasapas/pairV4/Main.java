/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.pairV4;

import fr.insa.beuvron.pasapas.Utils;

/**
 *
 * @author fdebertranddeb01
 */
public class Main {
    
    public static void main(String[] args) {
        AffPair pair = new AffPair(10);
        AffImpair impair = new AffImpair(10);
        pair.setImpair(impair);
        impair.setPair(pair);
        pair.start();
        impair.start();
        
        Utils.joinSansInterrupt(pair);
        Utils.joinSansInterrupt(impair);
        System.out.println("fini !!!");
    }
    
}
