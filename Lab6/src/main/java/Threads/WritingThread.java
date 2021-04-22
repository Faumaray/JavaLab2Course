package Threads;

import java.util.Random;

import FactoryArea.Factorable;

public class WritingThread extends Thread {
    private Factorable f;

    public WritingThread(Factorable f) {
        this.f = f;
    }

    @Override
    public void run() {
        if (f == null) {
            System.out.println("операция невозможна: объект не задан");
            return;
        }
        Random rand = new Random();
        int output;
        double defect;
        for (int index = 0; index < f.getNumberOfEls(); index++) {
                output= rand.nextInt(10000);
                defect = rand.nextDouble();
                f.setEl(index, output);
                f.setDefectOfEl(index, defect);
                System.out.println("WRITE " + output + " to   position " + index
                + " with % of defect " + defect);
        }
    }
}