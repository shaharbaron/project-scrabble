package test;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.Provider;

public class MyServer {
    private int port;
    private ClientHandler ch;
    private volatile boolean stop;

	public MyServer (int port, ClientHandler ch){
        this.port = port;
        this.ch = ch;
        stop = false;
    }

   private void runServer() throws Exception{
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        while (!stop){
            try{
                Socket aClient = server.accept();
                try{
                    ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                    aClient.getInputStream().close();
                    aClient.getOutputStream().close();
                    aClient.close();
                }catch(IOException e){}
            }catch(SocketTimeoutException e){}
        }
        server.close();
    }

    public void start(){
        new Thread(()-> {
            try {
                runServer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void close(){
        stop = true;
    }
}
