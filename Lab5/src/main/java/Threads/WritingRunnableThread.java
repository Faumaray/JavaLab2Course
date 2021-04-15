package Threads;

public class WritingRunnableThread implements Runnable {
    private FactorableSynchronizer fsyncher;

    public WritingRunnableThread(FactorableSynchronizer fsyncher) {
        this.fsyncher = fsyncher;
    }

    @Override
    public void run() {
        try {
            int val;
            double dval;
            for (int index = 0; index < fsyncher.getFacNumOfEls(); index++) {
                val = Testing.getRandOutput();
                dval = Testing.getRandDefect();
                fsyncher.write(val, dval);
            }
        } catch (InterruptedException exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
}