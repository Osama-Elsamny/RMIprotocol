import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GetDate extends Remote {
    String getTime() throws RemoteException;
}