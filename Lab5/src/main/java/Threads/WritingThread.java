package Threads;

import Factory.Factorable;

public class WritingThread extends Thread {
    private Factorable f;

    public WritingThread(Factorable f) {
        this.f = f;
    }

    @Override
    public void run() {
        if f == null) {
            System.out.println("операция невозможна: объект не задан");
            return;
        }

        int output;
        double defect;
        for (int index = 0; index < f.getNumberOfEls(); index++) {
                output= Testing.getRandOutput();
                defect = Testing.getRandDefect();
                f.setEl(index, output);
                f.setDefectOfEl(index, defect);
                System.out.println("WRITE " + output + " to   position " + index
                + " with % of defect " + defect);
        }
    }
}