import java.net.*;
import java.util.*;

public class DSender {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message and press enter to send");
        String str = s.nextLine();

        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);
        ds.send(dp);
        ds.close();
        System.out.println("Message sent successfully");
        s.close();
    }    
}
