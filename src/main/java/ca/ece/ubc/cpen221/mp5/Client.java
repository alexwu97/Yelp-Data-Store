package ca.ece.ubc.cpen221.mp5;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Client {
    public final static int PORT_NUMBER = 4948;
    public final static String HOSTNAME = "localhost";
    private Socket clientSocket;
    private PrintWriter clientOutput;
    private BufferedReader clientInput;

    public Client(Socket clientSocket){
        try{
            this.clientSocket = clientSocket;
            this.clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            this.clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendRequest(String request){
        clientOutput.println(request);
    }

    public void receiveResponse() throws IOException {
        System.out.println(clientInput.readLine());
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(new Socket(HOSTNAME, PORT_NUMBER));
        Client client2 = new Client(new Socket(HOSTNAME, PORT_NUMBER));
        try{
            client2.sendRequest("ADDRESTAURANT {\"name\": \"resA\", \"open\": false, \"latitude\": 1.23456, \"longitude\": 2.34567}");
            client2.receiveResponse();
            client.sendRequest("ADDUSER {\"name\": \"A. Marziali\"}");
            client.receiveResponse();
            client2.sendRequest("ADDUSER {\"review_count\": 5, \"name\": \"Ali G.\"}");
            client2.receiveResponse();
            client2.sendRequest("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
            client2.receiveResponse();
            client.sendRequest("ADDUSER {\"name\": \"Slim Shady\", \"review_count\": 5}");
            client.receiveResponse();
            client.sendRequest("PREDICT QScfKdcxsa7t5qfE0Ev0Cw gclB3ED6uk6viWlolSb_uA");
            client.receiveResponse();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
