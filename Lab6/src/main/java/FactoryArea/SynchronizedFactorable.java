package FactoryArea;

import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

public class SynchronizedFactorable implements Factorable {
    private final Factorable f;

    public SynchronizedFactorable(Factorable f)
    {
        this.f = f;
    }
    @Override
    public synchronized int getNumberOfEls() {
        return f.getNumberOfEls();
    }

    @Override
    public synchronized String getName() {
        return f.getName();
    }

    @Override
    public synchronized void setName(String newName) {
        f.setName(newName);
    }

    @Override
    public synchronized int getRate() {
        return f.getRate();
    }

    @Override
    public synchronized void setRate(int num) {
        f.setRate(num);
    }

    @Override
    public synchronized int getEl(int index) {
        return f.getEl(index);
    }

    @Override
    public synchronized void setEl(int index, int value) 
    {
        f.setEl(index, value);
    }

    @Override
    public synchronized double getDefect(int index) {
        return f.getDefect(index);
    }

    @Override
    public synchronized void setDefectOfEl(int index, double value) 
    {
        f.setDefectOfEl(index, value);
    }
    @Override
    public synchronized int excess() {
        return f.excess();
    }
    @Override
    public synchronized int usefulexcess() {
        return f.usefulexcess();
    }
    @Override
    public synchronized void outputAsBytes(OutputStream out) {
        f.outputAsBytes(out); 
    }
    @Override
    public synchronized void writeAsText(Writer out) {
        f.writeAsText(out);
    }

    @Override
    public Iterator<ArrayList<Number>> iterator() {
        return f.iterator();
    }


    @Override
    public String toString() {
        return f.toString();
    }
    
}
