package Threads;

import java.util.Random;

public class WritingRunnableThread implements Runnable {
    private FactorableSynchronizer fsyncher;

    public WritingRunnableThread(FactorableSynchronizer fsyncher) {
        this.fsyncher = fsyncher;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            int val;
            double dval;
            for (int index = 0; index < fsyncher.getFacNumOfEls(); index++) {
                val = rand.nextInt(1000);
                dval = rand.nextDouble();
                fsyncher.write(val, dval);
            }
        } catch (InterruptedException exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
}