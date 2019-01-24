import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class DateServer implements GetDate {
    public Long getTime() throws RemoteException {
        return new Long(System.currentTimeMillis());
    }
    public DateServer() throws RemoteException {}

    public static void main(String[] args) {
        try {
            String serverName = "DateServer";
            DateServer obj = new DateServer();
            GetDate stub = (GetDate) UnicastRemoteObject.exportObject(obj, 0);
            Registry reg = LocateRegistry.getRegistry();
            reg.bind(serverName, stub);
            System.out.println("Server is Ready!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
