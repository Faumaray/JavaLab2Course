package FactoryArea;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MonthsIterator implements Iterator<ArrayList<Number>> 
{
    private int[] output;
    private double[] defect;
    private int currPos;
    private ArrayList<Number> out;

    public MonthsIterator(int[] output, double[] defect) {
        this.output = output;
        this.defect = defect;
        this.out = new ArrayList<Number>(2);
        out.add(null);
        out.add(null);
        currPos = 0;
    }

    @Override
    public boolean hasNext() {
        return currPos < output.length;
    }

    @Override
    public ArrayList<Number> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
            out.set(1, defect[currPos]);
            out.set(0,output[currPos]);
            currPos++;

        return out;
    }
}
