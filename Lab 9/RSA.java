import java.util.*;

public class RSA {
    static int mult(int x, int y, int n) {
        int k = 1;
        for(int i = 1; i <= y; i++) {
            k = (k * x) % n;
        }
        return k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String msg1;
        int[] pt = new int[100];
        int[] ct = new int[100];
        int n, d = 0, e, Z, p, q;

        System.out.println("Enter two prime numbers p and q:");
        p = sc.nextInt();
        q = sc.nextInt();

        n = p * q;
        Z = (p - 1) * (q - 1);

        System.out.println("Enter value of e:");
        e = sc.nextInt();
        sc.nextLine();  // clear buffer

        System.out.println("Enter the message:");
        msg1 = sc.nextLine();

        char[] msg = msg1.toCharArray();
        int len = msg1.length();

        for(int i = 0; i < len; i++) {
            pt[i] = msg[i];
        }

        // compute d
        for(int k = 1; k < Z; k++) {
            if(((e * k) % Z) == 1) {
                d = k;
                break;
            }
        }

        System.out.println("p = " + p + " q = " + q + " n = " + n + " Z = " + Z + " e = " + e + " d = " + d);

        System.out.println("Cipher Text:");
        for(int i = 0; i < len; i++) {
            ct[i] = mult(pt[i], e, n);
            System.out.print("\t" + ct[i]);
        }

        System.out.println("\nPlain Text:");
        for(int i = 0; i < len; i++) {
            pt[i] = mult(ct[i], d, n);
            System.out.print((char)pt[i]);
        }
    }
}
