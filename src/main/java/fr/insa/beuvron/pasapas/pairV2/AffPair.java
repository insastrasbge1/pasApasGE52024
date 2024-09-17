/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.pairV2;

/**
 *
 * @author fdebertranddeb01
 */
public class AffPair {

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

    public void affPair() {
        for (int i = 0; i < this.max; i = i + 2) {
            System.out.println(i);
        }

    }
    
    public static void main(String[] args) {
        AffPair p = new AffPair(10);
        p.affPair();
    }

}
