import java.util.Scanner;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.rmi.registry.LocateRegistry;

public class DateClient {
    private DateClient() {}
    public static void main(String[] args) throws IOException {
        String address = args.length == 0 ? null : args[0];  
        if(address != null) {
            System.out.println("Connecting to server with Address: " + address);
        }
        try {
            Registry reg = LocateRegistry.getRegistry(address);
            GetDate stub = (GetDate) reg.lookup("DateServer");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            // Start timer 
            Long start = System.currentTimeMillis();
            Long theResult = stub.getTime();
            Long difference = System.currentTimeMillis() - start;
            // End Timer
            System.out.println("The Clock at the server is: " + sdf.format(new Date(theResult)) + " and the diff is " + difference/2 + " (ms).");
            System.out.println("Synchronized clock is: " +  sdf.format(new Date(theResult + (difference/2))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}