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
public class Main2 {

    public static void main(String[] args) {
        Thread tpair = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i = i + 2) {
                    System.out.println(i);
                    Utils.sleepSansInterrupt(100);
                }
            }
        });
        tpair.start();
        Thread timpair = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i = i + 2) {
                    System.out.println(i);
                    Utils.sleepSansInterrupt(100);
                }
            }
        });
        timpair.start();
        Utils.joinSansInterrupt(tpair);
        Utils.joinSansInterrupt(timpair);
        System.out.println("fini");
    }

}
