import java.util.Scanner;

public class CRC {
    static int data[], cs[];
    static int g[] = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1}; // x^14 + x^10 + x^8 + x^7 + x^5 + x^4 + 1
    static int n, i, e, c, pos;
    static int N = 17;
    static void xor() {
        for(c=0; c<N; c++)
            cs[c] = ((cs[c] == g[c]) ? 0 : 1);
    }
    static void crc() {
        for(i=0; i<N; i++)
            cs[i] = data[i];
        do {
            if(cs[0] == 1)
                xor();
            for(c=0; c<N-1; c++)
                cs[c] = cs[c+1];
            cs[c] = data[i++];
        } while(i <= n+N-1);
    }

    public static void main(String[] args) {
        cs = new int[100];
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of data bits: ");
        n = in.nextInt();
        data = new int[100];
        System.out.println("Enter the data bits: ");
        for(int i = 0; i < n; i++)
            data[i] = in.nextInt();
        System.out.println("\n\nCRC Divisor: ");
        for(int i = 0; i < N; i++)
            System.out.print(g[i]);
        for(int i = n; i < n+N-1; i++)
            data[i] = 0;
        System.out.println("\n\nModified data is: ");
        for(int i = 0; i < n+N-1; i++)
            System.out.print(data[i]);
        crc();
        System.out.println("\n\nCRC Checksum is: ");
        for(i = 0; i < N; i++)
            System.out.print(cs[i]);
        for(i = n; i < n+N-1; i++)
            data[i] = cs[i-n];
        System.out.println("\n\nFinal codeword is: ");
        for(int i = 0; i < n+N-1; i++)
            System.out.print(data[i]);
        System.out.println("\n\nTest Error Detection 0(yes) 1(no)?: ");
        e = in.nextInt();
        if(e == 0) {
            System.out.println("Enter the position where error is to be introduced: ");
            pos = in.nextInt();
            data[pos] = (data[pos] == 0) ? 1 : 0;
            System.out.println("Erroneous data is: ");
            for(int i = 0; i < n+N-1; i++)
                System.out.print(data[i]);
            }
        crc();
        System.out.println("\n\nReceiver Checksum: ");
        for(int i = 0; i < N-1; i++) {
            if(cs[i]!=0) {
                System.out.println("Error received in codeword");
                System.exit(0);
            }
        }
        System.out.println("No error received in codeword");
    }
}