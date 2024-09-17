/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas;

/**
 *
 * @author fdebertranddeb01
 */
public class Rationnel {
    
    private long num;
    private long denom;
    
    public Rationnel(long num,long denom) {
        this.num = num;
        if (denom == 0) {
            throw new Error("dénominateur nul");
        }
        this.denom = denom;
    }
    
    public Rationnel add(Rationnel r2) {
        long n = this.num * r2.denom + r2.num * this.denom;
        long d = this.denom * r2.denom;
        return new Rationnel(n,d).reduire();
    }
    
    public Rationnel oppose() {
        return new Rationnel(-this.num,this.denom);
    }
    
    public Rationnel(long num) {
        this(num,1);
    }
    
    public static long pgcd(long u,long v) {
        u = Math.abs(u);
        v = Math.abs(v);
        while (v != 0) {
            long temp = u;
            u = v ;
            v = temp % v;
        }
        return u;
    }
    
    public void reduireSurPlace() {
        long p = pgcd(this.num,this.denom);
        this.num = this.num/p;
        this.denom = this.denom/p;
    }
    
    public Rationnel reduire() {
        long p = pgcd(this.num,this.denom);
        return new Rationnel(this.num/p,this.denom/p);
    }
    
    
    
    public String toString() {
        return this.num + "/" + this.denom;
    }
    
    public static void main(String[] args) {
        Rationnel r1 = new Rationnel(24,36);
        Rationnel r2 = r1.reduire();
        System.out.println("r1 = " + r1 + "\nr2 = " + r2);
    }
    

    /**
     * @return the num
     */
    public long getNum() {
        return num;
    }

    /**
     * @return the denom
     */
    public long getDenom() {
        return denom;
    }
    
    
    
}
