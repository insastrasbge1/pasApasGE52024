/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.sockets.echo;

import fr.insa.beuvron.pasapas.sockets.INetAdressUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author fdebertranddeb01
 */
public class Server {
    
    public static final int PORT = 50001;
    
    public static void sansThread() {
        try {
            Inet4Address adr = INetAdressUtil.premiereAdresseNonLoopback();
            System.out.println("ip serveur : " + adr.getHostAddress());
            System.out.println("port serveur : " + PORT);
            ServerSocket ss = new ServerSocket(PORT, 10, adr);
            Socket soc = ss.accept();
            try( BufferedReader in = new BufferedReader(
                    new InputStreamReader(soc.getInputStream(),StandardCharsets.UTF_8))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("reçu : " + line);
                }
            }
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }
    
    public static void main(String[] args) {
        sansThread();
    }
    
}
