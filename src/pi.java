import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.CountDownLatch;

public class pi {
    public static void main(String[] args) throws InterruptedException, IOException{
        Writer wr = new FileWriter("output.txt");
        for(int n=1;n<=500;n+=1){
            PiThread v[] = new PiThread[n];
            CountDownLatch latch = new CountDownLatch(n);
            long start = System.nanoTime();
            for(int i=0;i<n;i++){
                v[i] = new PiThread(i+1, n, latch);
                v[i].start();
            }
            latch.await();
            long end = System.nanoTime();
            double pi = 2.0;
            for(int i=0;i<n;i++){
                pi *= v[i].cur;
            }
            wr.write(String.valueOf(n)+","+String.valueOf(end-start)+"\n");
        }
        wr.close();
    }
}
