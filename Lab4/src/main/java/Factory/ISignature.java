package Factory;

import java.io.OutputStream;
import java.io.Writer;

public interface ISignature 
{
    public void setoutput(int ... values);
    public int[] getoutput();
    public int getEllOfOutput(int index);
    public double[] getdefect();
    public double getEllOfDefect(int index);
    public int getNumOfDefect();
    public int getNumOfOutput();
    public void setdefect(double ... values);
    public void setRate(int value);
    public int getRate();
    public void setName(String newname);
    public String getName();
    public int excess();
    public int usefulexcess();
    void outputAsBytes(OutputStream out); // запись в байтовый поток
    
    void writeAsText(Writer out); // запись в символьный поток
}
