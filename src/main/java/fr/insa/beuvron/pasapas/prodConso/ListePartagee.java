/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.prodConso;

import fr.insa.beuvron.pasapas.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fdebertranddeb01
 */
public class ListePartagee {

    public static final int DEFAUT_TAILLE_MAX = 5;
    private static long curObjet = 1;

    private final LinkedList<Long> objets;
    private int tailleMax;

    public ListePartagee() {
        this(DEFAUT_TAILLE_MAX);
    }

    public ListePartagee(int tailleMax) {
        this.tailleMax = tailleMax;
        this.objets = new LinkedList<>();
    }

    public long addObject() {
        synchronized (this.objets) {
            while (this.objets.size() >= this.tailleMax) {
                try {
                    this.objets.wait();
                } catch (InterruptedException ex) {
                    throw new Error("unexpected interrupt", ex);
                }
            }
            long obj = nextObj();
            this.objets.offer(obj);
            this.objets.notify();
            return obj;
        }
    }

    public long litObject() {
        synchronized (this.objets) {
            while (this.objets.size() <= 0) {
                try {
                    this.objets.wait();
                } catch (InterruptedException ex) {
                    throw new Error("unexpected interrupt", ex);
                }
            }
            long res = this.objets.poll();
            this.objets.notify();
            return res;
        }
    }
    
    public int nbrObjInList() {
        return this.objets.size();
    }

    public static synchronized long nextObj() {
        return curObjet++;
    }

    public class Producteur extends Thread {

        private String name;
        private long tempoProd;

        public Producteur(String name, long tempoConso) {
            this.name = name;
            this.tempoProd = tempoConso;
        }

        @Override
        public void run() {
            while (true) {
                Utils.sleepSansInterrupt((long) (this.tempoProd * (1 + Math.random())));
                long added = addObject();
                System.out.println("Prod " + this.name + " --> " + added 
                        + " ; (" + nbrObjInList() + " in list)");
            }

        }
    }

    public class Consomateur extends Thread {

        private String name;
        private long tempoConso;

        public Consomateur(String name, long tempoConso) {
            this.name = name;
            this.tempoConso = tempoConso;
        }

        @Override
        public void run() {
            while (true) {
                long obj = litObject();
                System.out.println("Conso " + this.name + " <-- " + obj 
                        + " ; (" + nbrObjInList() + " in list)");
                Utils.sleepSansInterrupt((long) (this.tempoConso * (1 + Math.random())));
            }

        }
    }

    public static void test(int nbrProd, long tempoProd, int nbrConso, long tempoConso) {
        ListePartagee partage = new ListePartagee();
        for (int i = 0; i < nbrProd; i++) {
            partage.new Producteur("P" + i, tempoProd).start();
        }
        for (int i = 0; i < nbrConso; i++) {
            partage.new Consomateur("C" + i, tempoConso).start();
        }
    }

    public static void main(String[] args) {
//        test(1, 500, 1, 1000);
//        test(1, 1000, 1, 500);
        test(5,500,10,1000);
        
    }

}
