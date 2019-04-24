package inf112.skeleton.app;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.game.RoboRallyGame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class RoboServer extends Thread {

    private ServerSocket roboServer;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ProgramCardDeck serverDeck;
    private Board serverBoard;

    /**
     * Constructor for creating server
     * @param port The port to run the server on
     * @throws IOException
     */
    public RoboServer (int port, String path) throws IOException {
        roboServer = new ServerSocket(port);
        roboServer.setSoTimeout(100000);

        //The server card stack
        serverDeck = new ProgramCardDeck();

        // The server board
        serverBoard = new Board("serverBoard" ,"path");
    }

    /**
     * Method for running the server
     */
    public void runServer() {
        while (true) {
            try {

                System.out.println("Waiting for client");
                Socket roboSocket = roboServer.accept();
                System.out.println("Client connected");

                //in = new DataInputStream(roboSocket.getInputStream());
                //out = new DataOutputStream(roboSocket.getOutputStream());
                ois = new ObjectInputStream(roboSocket.getInputStream());
                oos = new ObjectOutputStream(roboSocket.getOutputStream());

                Boolean gameOver = false;
                while (!gameOver) {
                    // Write server deck and board to client(s)
                    oos.writeObject(serverDeck);
                    oos.writeObject(serverBoard);

                    Boolean roundsOver = false;
                    while (!roundsOver) {
                        serverDeck = (ProgramCardDeck)ois.readObject();
                        serverBoard = (Board)ois.readObject();
                        roundsOver = true;
                    }
                    //Perform game logic
                    gameOver = true;
                }

                //System.out.println(in.readUTF());

                //out.writeUTF("This be from dat server fam.");

                Board serverBoardzz = (Board)ois.readObject();


                System.out.println(serverBoardzz);
                System.out.println(serverBoardzz.getBoardHeight());
                System.out.println(serverBoardzz.getCellAt(1,1).getPiecesInCell());

                roboServer.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out.");
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }

            catch (ClassNotFoundException c) {
                c.printStackTrace();
                break;
            }

        }
    }
    public static void main (String[] args) throws IOException {

        RoboServer roboServer = new RoboServer(8000, "dankboard.json");
        roboServer.runServer();

    }
}
