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
public class AffPair extends Thread {

    private int max;
    private AffImpair impair;

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
            this.impair.interrupt();
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException ex) {
                System.out.println("pair se réveille");
            }
        }

    }

    public static void main(String[] args) {
        AffPair p = new AffPair(10);
        p.start();
    }

    /**
     * @return the impair
     */
    public AffImpair getImpair() {
        return impair;
    }

    /**
     * @param impair the impair to set
     */
    public void setImpair(AffImpair impair) {
        this.impair = impair;
    }

}
