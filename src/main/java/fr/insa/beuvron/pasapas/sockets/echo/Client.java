/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.pasapas.sockets.echo;

import fr.insa.beuvron.utils.ConsoleFdB;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fdebertranddeb01
 */
public class Client {
    
    public static void clientSansThread() {
        try {
            String adr = ConsoleFdB.entreeString("ip du serveur : ");
            int port = Server.PORT;
            Socket soc = new Socket(adr, port);
            try (OutputStreamWriter out = new OutputStreamWriter(
                    soc.getOutputStream(), StandardCharsets.UTF_8)) {
                String mess = "";
                while (! mess.equalsIgnoreCase("FIN")) {
                    mess = ConsoleFdB.entreeString("message (FIN pour finir) :");
                    out.write(mess+"\n");
                    out.flush();                
                }
            }
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }
    
    public static void main(String[] args) {
        clientSansThread();
    }
    
}
