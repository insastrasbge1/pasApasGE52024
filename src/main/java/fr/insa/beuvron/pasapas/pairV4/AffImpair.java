/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.pairV4;

import fr.insa.beuvron.pasapas.Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fdebertranddeb01
 */
public class AffImpair extends Thread {

    private int max;
    private AffPair pair;

    public AffImpair(int max) {
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
        for (int i = 1; i < this.max; i = i + 2) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException ex) {
                System.out.println("impair se réveille");
            }
            Utils.sleepSansInterrupt(200);
            System.out.println(i);
            this.pair.interrupt();
        }

    }

    public static void main(String[] args) {
        AffImpair p = new AffImpair(10);
        p.start();
    }

    /**
     * @return the pair
     */
    public AffPair getPair() {
        return pair;
    }

    /**
     * @param pair the pair to set
     */
    public void setPair(AffPair pair) {
        this.pair = pair;
    }

}
