package Threads;

import FactoryArea.Factorable;

public class ReadingThread extends Thread{
    private Factorable f;

    public ReadingThread(Factorable f)
    {
        this.f = f;
    }

    @Override
    public void run() {
        if(f == null)
        {
            System.err.println("Объект не задан");
            return;
        }
        for(int index=0; index< f.getNumberOfEls(); index++)
        {
            System.out.println("READ" + f.getEl(index) + " from position "
            + index + " with % of defect " + f.getDefect(index));
        }
    }
    
}
