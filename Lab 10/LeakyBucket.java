import java.util.*;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, size, nop, opr, temp = 0;
        int[] datarate = new int[100];
        System.out.print("Enter the size of the bucket: ");
        size = sc.nextInt();
        System.out.print("Enter the number of packets: ");
        nop = sc.nextInt();
        System.out.print("Enter the output rate: ");
        opr = sc.nextInt();
        System.out.println("Enter the data rates of the packets:");
        for (i = 0; i < nop; i++) {
            datarate[i] = sc.nextInt();
        }
        for (i = 0; i < nop; i++) {
            if(datarate[i] > size) {
                System.out.println("Bucket outflow for packet " + (i + 1) + " with data rate " + datarate[i] + " is dropped (exceeds bucket size).");
            } else {
                temp = datarate[i];
                while(temp > opr) {
                    System.out.println("Packet transmitted " + opr + ".");
                    temp -= opr;
                }
                if(temp > 0) {
                    System.out.println("Packet transmitted " + temp + ".");
            }
        }
        sc.close();
    }
}
