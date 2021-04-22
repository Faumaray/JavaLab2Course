package FactoryArea;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

public class UnmodifiableFactorable implements Factorable
{
    private final Factorable f;

    public UnmodifiableFactorable(Factorable f) {
        this.f = f;
    }

    @Override
    public void setEl(int index, int value) {
        throw new UnsupportedOperationException("неподдерживаемая операция: невозможно изменить значение");    
    }

    @Override
    public void setDefectOfEl(int index, double value) {
        throw new UnsupportedOperationException("неподдерживаемая операция: невозможно изменить значение");  
        
    }

    @Override
    public void setRate(int value) {
        throw new UnsupportedOperationException("неподдерживаемая операция: невозможно изменить значение");  
    }

    @Override
    public void setName(String newname) {
        throw new UnsupportedOperationException("неподдерживаемая операция: невозможно изменить имя");  
        
    }

    @Override
    public int getNumberOfEls() {
        return f.getNumberOfEls();
    }

    @Override
    public int getEl(int index) {
        return f.getEl(index);
    }

    @Override
    public double getDefect(int index) {
        return f.getDefect(index);
    }

    @Override
    public int getRate() {
        return f.getRate();
    }

    @Override
    public String getName() {
        return f.getName();
    }

    @Override
    public int excess() {
        return f.excess();
    }

    @Override
    public int usefulexcess() {
        return f.usefulexcess();
    }

    @Override
    public void outputAsBytes(OutputStream out) {
        f.outputAsBytes(out);
    }

    @Override
    public void writeAsText(Writer out) {
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
