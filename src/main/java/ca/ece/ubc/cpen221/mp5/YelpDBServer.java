package ca.ece.ubc.cpen221.mp5;

import java.net.ServerSocket;

public class YelpDBServer {
    public final static int PORT_NUMBER = 4948;
    private ServerSocket serverSocket;

    public YelpDBServer(){
        try{
            this.serverSocket = new ServerSocket(PORT_NUMBER);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args){
        YelpDBServer server = new YelpDBServer();
        boolean connectionListening = true;
        try{
            while(connectionListening){
                Thread newThread = new Thread(new ServerThreadTask(server.serverSocket.accept()));
                newThread.start();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
