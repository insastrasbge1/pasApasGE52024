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
public class IncrementV3 {

    public static final long MAX = 1000000;

    public static long compteur = 0;

    public static void gogogo() {
        long curTime = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < MAX; i++) {
                synchronized (IncrementV3.class) {
                    compteur++;
                }
            }
        });
       Thread t2 = new Thread(() -> {
            for (long i = 0; i < MAX; i++) {
                synchronized (IncrementV3.class) {
                    compteur++;
                }
            }
        });
        t2.start();
        Utils.joinSansInterrupt(t1);
        Utils.joinSansInterrupt(t2);
        System.out.println("valeur finale : " + compteur);
        System.out.println("temps exec : " + (System.currentTimeMillis() - curTime));
    }

    public static void main(String[] args) {
        gogogo();
    }

}
