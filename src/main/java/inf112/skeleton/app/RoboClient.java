package inf112.skeleton.app;

import inf112.skeleton.app.gameLogic.Player;

import java.io.*;
import java.net.Socket;

public class RoboClient {

    private Player clientPlayer;
    private Socket roboClient;
    private DataInputStream in;
    private DataOutputStream out;

    /**
     * Constructor for opening a client and connecting it to a server
     * @param ip The IP you want to connect to e.g "localhost"
     * @param port The port you want to connect to e.g 8000
     */
    public RoboClient( String ip, int port){
        try {
            System.out.println("Attempting connection to " + ip + " on port " + port);
            roboClient = new Socket(ip, port);
            System.out.println("Connection accepted! Do some shit.");

            out = new DataOutputStream(roboClient.getOutputStream());
            in = new DataInputStream(roboClient.getInputStream());

            out.writeUTF("Schmell from client");
            System.out.println("The server sends " + in.readUTF());
            roboClient.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
