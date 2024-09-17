/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.pairV1;

/**
 *
 * @author fdebertranddeb01
 */
public class AffPair {

    public static void affPair(int n) {
        for (int i = 0; i < n; i = i + 2) {
            System.out.println(i);
        }
    }
    
    public static void main(String[] args) {
        affPair(10);
    }

}
