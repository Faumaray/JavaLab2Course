package Threads;

public class ReadingRunnableThread implements Runnable 
{
    private FactorableSynchronizer fsyncher;
    public ReadingRunnableThread(FactorableSynchronizer fsyncher) 
    {
        this.fsyncher = fsyncher;
    }   

    @Override
    public void run() {
        try {
            for(int index=0; index < fsyncher.getFacNumOfEls(); index++)
            {
                fsyncher.read();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
