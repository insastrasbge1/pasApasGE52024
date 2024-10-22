/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.prodConso;

import fr.insa.beuvron.pasapas.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author fdebertranddeb01
 */
public class ListePartageeMaxObj {
    
    /**
     * Pour contourner un problème pénible.
     * <ul>
     *   <li> on ne peut pas synchroniser sur un boolean (type prédéfini)</li>
     *   <li> on n'est pas sûr de synchroniser sur un Boolean car suivant
     *        l'implémentation, plusieurs Boolean peuvent être == et donc
     *        avoir le même verrou
     *   </li>
     * </ul>
     */
    public static class MyBool{
        private boolean val;

        public MyBool(boolean val) {
            this.val = val;
        }

        /**
         * @return the val
         */
        public boolean getVal() {
            return val;
        }

        /**
         * @param val the val to set
         */
        public void setVal(boolean val) {
            this.val = val;
        }
        
        
    }

    public static final int DEFAUT_TAILLE_MAX = 5;
    public static final int DEFAUT_NBR_TOT_OBJETS = 20;

    /**
     * les objets générés sont des entier.
     * <p>
     * dans la version sans max, le compteur d'objet était static donc commun si
     * l'on avait plusieurs ListePartagee. Comme ici, on veut éventuellement
     * préciser le max d'objet pour chaque liste, il est assez logique de faire
     * un compteur pour chaque liste.
     * </p>
     */
    private long curObjet = 1;

    private final LinkedList<Long> objets;
    private int tailleMax;
    private int nbrTotalObjetsAProduire;
    /** garder les producteurs n'est pas indispensable. */
    private List<Producteur> lesProds;
    /** garder les consomateurs est indispensable pour gérer leur arrêt
     * à la fin de la production
     */
    private List<Consomateur> lesConsos;
    
    private final MyBool doitFinir;

    public ListePartageeMaxObj() {
        this(DEFAUT_TAILLE_MAX, DEFAUT_NBR_TOT_OBJETS);
    }

    public ListePartageeMaxObj(int tailleMax, int nbrTotalObjetsAProduire) {
        this.tailleMax = tailleMax;
        this.nbrTotalObjetsAProduire = nbrTotalObjetsAProduire;
        this.objets = new LinkedList<>();
        this.doitFinir = new MyBool(false);
        this.lesProds = new ArrayList<>();
        this.lesConsos = new ArrayList<>();
    }

    public long addObject() throws NoMoreObjects {
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

    public static class NoMoreObjects extends Exception {
    }

    public synchronized long nextObj() throws NoMoreObjects {
        if (this.curObjet > this.nbrTotalObjetsAProduire) {
            throw new NoMoreObjects();
        }
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
            try {
                while (true) {
                    Utils.sleepSansInterrupt((long) (this.tempoProd * (1 + Math.random())));
                    long added = addObject();
                    System.out.println("Prod " + this.name + " --> " + added
                            + " ; (" + nbrObjInList() + " in list)");
                }
            } catch (NoMoreObjects ex) {
                System.out.println(this.name + " : ARRET car plus d'objets");
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
    
    public class Moniteur extends Thread {
        @Override
        public void run() {
            synchronized (doitFinir) {
                while (! doitFinir.getVal()) {
                    try {
                        doitFinir.wait();
                    } catch (InterruptedException ex) {
                        throw new Error("unexpected interrupt",ex);
                    }
                }
            }
        }
    }

    public static void test(int nbrProd, long tempoProd, int nbrConso, long tempoConso) {
        ListePartageeMaxObj partage = new ListePartageeMaxObj();
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
        test(5, 500, 10, 1000);

    }

}
