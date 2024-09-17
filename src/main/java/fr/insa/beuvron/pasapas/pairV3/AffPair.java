/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.pairV3;

import fr.insa.beuvron.pasapas.Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fdebertranddeb01
 */
public class AffPair extends Thread{

    private int max;

    public AffPair(int max) {
        this.max = max;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.max; i = i + 2) {
            System.out.println(i);
            Utils.sleepSansInterrupt(100);
        }

    }
    
    public static void main(String[] args) {
        AffPair p = new AffPair(10);
        p.start();
    }

}
