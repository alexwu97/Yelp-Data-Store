package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;

public class ServerThreadTask implements Runnable {
    private Socket clientSocket;

    public ServerThreadTask(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try(
                PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            String clientRequest;
            YelpDBController yelpDBController = new YelpDBController();
            while((clientRequest = serverInput.readLine()) != null){
                serverOutput.println(yelpDBController.handleRequest(clientRequest));
            }
            clientSocket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
