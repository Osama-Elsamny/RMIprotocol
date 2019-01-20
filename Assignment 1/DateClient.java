// import GetDate;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/**
 * A command line client for the date server. It prompts you, at the console, to enter
 * the IP address of a server, then displays the response from the server on success,
 * otherwise it crashes and dumps the exception trace.
 */
public class DateClient {
    private DateClient() {}
    public static void main(String[] args) throws IOException {
        String address = args[0] == null ? null : args[0];  
        System.out.println("Connecting to server with Address: " + address);
        try {
            Registry reg = LocateRegistry.getRegistry(address);
            GetDate stub = (GetDate) reg.lookup("DateServer");
            // Start timer 
            long start = System.nanoTime();
            String theResult = stub.getTime();
            long difference = System.nanoTime() - start;
            // End Timer
            System.out.println("The Clock at the server is: " + theResult + " (ns) and the diff is " + difference + " (ns).");
            System.out.println("Synchronized clock is: " +  (Long.parseLong(theResult) - (difference/2)) + " (ns).");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}