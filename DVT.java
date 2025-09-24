import java.util.*;

public class DVT {
    public static void main(String[] args) {
        int dist[][] = new int[20][20];
        int from[][] = new int[20][20];
        int costmat[][] = new int[20][20];
        int i, j, k, nodes;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        nodes = sc.nextInt();
        System.out.println("Enter the cost matrix: ");
        for (i = 1; i <=nodes; i++) {
            for (j = 1; j <=nodes; j++) {
                costmat[i][j] = sc.nextInt();
                costmat[i][i] = 0; // Distance to self is zero
                dist[i][j] = costmat[i][j];
                from[i][j] = j;
            }
        }
        for (i = 1; i <= nodes; i++) {
            for (j = 1; j <= nodes; j++) {
                for (k = 1; k <= nodes; k++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        from[i][j] = k;
                    }
                }
            }
        }
        for (i = 1; i <= nodes; i++) {
            System.out.println("From router node: " + i);
            System.out.print("Destination node\tNext Hop\tDistance\n");
            for (j = 1; j <= nodes; j++) {
                System.out.print(j + "\t\t\t" + from[i][j] + "\t\t" + dist[i][j] + "\n");
            }
            System.out.println();
        }
        sc.close();
    }
}
