// import GetDate;
import java.io.IOException;
import java.io.PrintWriter; 
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 * A TCP server that runs on port 9090. When a client connects, it sends the client the
 * current date and time, then closes the connection with that client. This is arguably
 * just about the simplest server you can write.
 */
public class DateServer implements GetDate {
    public String getTime() throws RemoteException {
        // System.out.println("Current time at the server: " + new Date().toString());
        return Long.toString(System.nanoTime());
        //return new Date().toString();
    }
    public DateServer() throws RemoteException {} 

    public static void main(String[] args) {
        try {
            DateServer obj = new DateServer();
            GetDate stub = (GetDate) UnicastRemoteObject.exportObject(obj, 0);
            Registry reg = LocateRegistry.getRegistry();
            reg.bind("rmi:/192.168.1.64:1099/DateServer", stub);
            System.out.println("Server is Ready!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}