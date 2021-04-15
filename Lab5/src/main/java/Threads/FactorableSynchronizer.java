package Threads;
import Factory.Factorable;

public class FactorableSynchronizer {
    private final Factorable f;
    private volatile int currIndex = 0;
    private volatile boolean isElSet = false;

    public FactorableSynchronizer(Factorable f)
    {
        this.f = f;
    }

    void write(int val, double dval) throws InterruptedException
    {
        synchronized (f){
            if(!canWrite())
            {
                throw new InterruptedException();
            }
            while(isElSet)
            {
                f.wait();
            }

            f.setEl(currIndex, val);
            f.setDefectOfEl(currIndex, dval);
            isElSet = true;
            System.out.println("WRITE "+ val + " to position "+ currIndex
            + " with % of defect " + dval);

            f.notifyAll();
        }
    }

    private boolean canWrite()
    {
        return (!isElSet && currIndex < f.getNumberOfEls() || 
        (isElSet && currIndex < f.getNumberOfEls() - 1));
    }

    void read() throws InterruptedException
    {
        int val;
        double dval;
        synchronized (f) {
            if (!canRead()) {
                throw new InterruptedException();
            }
            while (!isElSet) {
                f.wait();
            }
            val = f.getEl(currIndex);
            dval = f.getDefect(currIndex);
            isElSet = false;
            System.out.println("READ  " + val + " from position " + currIndex
            + " with % of defect " + dval);
            currIndex++;

            f.notifyAll();
        }
    }

    private boolean canRead()
    {
        return currIndex < f.getNumberOfEls();
    }

    int getFacNumOfEls()
    {
        return f.getNumberOfEls();
    }
}
