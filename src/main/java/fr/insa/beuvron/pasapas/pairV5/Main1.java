/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.pairV5;

import fr.insa.beuvron.pasapas.Utils;

/**
 *
 * @author fdebertranddeb01
 */
public class Main1 {
    
    public static class AffPair implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i = i + 2) {
                System.out.println(i);
                Utils.sleepSansInterrupt(100);
            }
        }
    }

    public static class AffImpair implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i < 10; i = i + 2) {
                System.out.println(i);
                Utils.sleepSansInterrupt(100);
            }
        }
    }

    public static void main(String[] args) {
        AffPair pair = new AffPair();
        Thread tpair = new Thread(pair);
        tpair.start();
        AffImpair impair = new AffImpair();
        Thread timpair = new Thread(impair);
        timpair.start();
        Utils.joinSansInterrupt(tpair);
        Utils.joinSansInterrupt(timpair);
        System.out.println("fini");
    }
    
}
