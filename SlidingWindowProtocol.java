import java.util.Scanner;
import java.util.Random;

public class SlidingWindowProtocol {
    static int windowSize, totalFrames, sendBase, nextSeqNum;
    static boolean[] acknowledged;

    static void sendFrame(int frameNumber) {
        System.out.println("Sending frame: " + frameNumber);
    }

    static int receiveAck() {
        Random rand = new Random();
        if(rand.nextInt(100) < 90) {
            int ackFrame = sendBase + rand.nextInt(windowSize);
            System.out.println("Acknowledgement received for frame: " + ackFrame);
            return ackFrame;
        }  else {
            System.out.println("Acknowledgement lost for frame: " + sendBase);
            return -1; // Simulate lost ACK
        }
    }

    static void slideWindow(int ackFrame) {
        while(sendBase <= ackFrame && sendBase < totalFrames) {
            acknowledged[sendBase] = true;
            System.out.println("Frame: " + sendBase + "acknowledged.");
            sendBase++;
        }
    }

    static void runSlidingWindow() {   
        while(sendBase < totalFrames) {
            while(nextSeqNum < sendBase + windowSize && nextSeqNum < totalFrames) {
                sendFrame(nextSeqNum);
                nextSeqNum++;
            }

            int ackFrame = receiveAck();
            if(ackFrame != -1) {
                slideWindow(ackFrame);
            } else {
                System.out.println("Timeout! Resending frames.");
            }

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter window size: ");
        windowSize = sc.nextInt();
        System.out.print("Enter total number of frames: ");
        totalFrames = sc.nextInt();

        acknowledged = new boolean[totalFrames];
        sendBase = 0;
        nextSeqNum = 0;

        runSlidingWindow();
        sc.close();
    }

}