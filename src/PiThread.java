import java.util.concurrent.CountDownLatch;

public class PiThread extends Thread{
    double i=0, n=0;
    long lim = (long) 1e8;
    double cur = 1.0f;
    CountDownLatch latch;
    public PiThread(int i, int n, CountDownLatch latch){
        this.i = (double) i;
        this.n = (double) n;
        this.latch = latch;
    }
    public void calc(){
        cur *= ((2.0f*i)/((2.0f*i)-1.0f))*((2.0f*i)/((2.0f*i)+1.0f));
        i+=n;
    }
    public double get_cur(){
        return cur;
    }
    @Override
    public void run(){
        while(i<lim) calc();
        latch.countDown();
    }
}