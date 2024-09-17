/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.pairV3;

import fr.insa.beuvron.pasapas.Utils;

/**
 *
 * @author fdebertranddeb01
 */
public class AffImpair extends Thread{

    private int max;

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
            System.out.println(i);
          Utils.sleepSansInterrupt(200);
          }

    }
    
    public static void main(String[] args) {
        AffImpair p = new AffImpair(10);
        p.start();
    }

}
