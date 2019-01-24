import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface GetDate extends Remote {
    Long getTime() throws RemoteException;
}