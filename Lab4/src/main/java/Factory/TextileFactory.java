package Factory;

import java.util.Arrays;
import java.io.*;

public class TextileFactory implements ISignature
{
    int[] output;
    double[] defect;
    String name;
    int Rate;

    public TextileFactory()
    {
        this.Rate = 0;
        this.defect = null;
        this.output = null;
        this.name = null;
    }
    public TextileFactory(String factoryname,int rate,int[] outvalues , double[] defvalues)
    {
        boolean acc = true;
        if(rate < 0)
        throw new IllegalArgumentException("Значение не может быть меньше 0");
        else
        this.Rate = rate;
        for(int value : outvalues)
        {if (value<0){acc = false;}}
        if(acc == false)
        throw new IllegalArgumentException("Значение не может быть меньше 0");
        else
        this.output = outvalues;
        for(double value : defvalues)
        if (value<0 || value > 1){acc = false;}
        if(acc == false)
        throw new IllegalArgumentException("Значение не может быть меньше 0 или больше 1");
        else
        this.defect = Arrays.copyOf(defvalues, output.length);
        if(factoryname.equals("") || factoryname.equals(" "))
        throw new IllegalArgumentException("Новые имя не может быть быть пустым");
        else
        this.name = factoryname;
    }

    @Override
    public int excess()
    {
        int sum =0;
        for (int value : output) 
        {
            sum += value;
        }
        return (sum-(Rate*output.length));
    }
    @Override
    public int usefulexcess()
    {
        int useful=0;
        for(int i=0; i<output.length;i++)
        {
            useful += output[i] - (int)(output[i]*defect[i]);
        }
        return (useful - (Rate*output.length));
    }
    @Override
    public void setoutput(int... values) 
    {   
        boolean acc = true;
        for(int value : values)
        if (value<0){acc = false;}
        if(acc == false)
        throw new IllegalArgumentException("Значение не может быть меньше 0");
        else
        this.output = values;
    }
    @Override
    public int[] getoutput() {
        return this.output;
    }
    @Override
    public double[] getdefect() {
        return this.defect;
    }
    @Override
    public void setdefect(double... values) 
    {
        boolean acc = true;
        for(double value : values)
        if (value<0 || value > 1){acc = false;}
        if(acc == false)
        throw new IllegalArgumentException("Значение не может быть меньше 0 или больше 1");
        else
        defect = Arrays.copyOf(values, values.length);
    }
    @Override
    public void setRate(int value) 
    {
        if(value < 0)
        throw new IllegalArgumentException("Значение не может быть меньше 0");
        else
        this.Rate = value;
    }
    @Override
    public int getRate() {
        return this.Rate;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setName(String newname) 
    {   if(newname.equals("") || newname.equals(" ") || newname.equals(this.name))
        throw new IllegalArgumentException("Новые имя не может быть быть старым или пустым");
        else
        this.name = newname;      
    }

    @Override
    public String toString()
    {
        String out = "";
        String def = "";
        for(int value : this.output) {out += value + " ";}
        for(double value : this.defect) {def += value + " ";}
        return "Текстильный комбинат: "+ this.name + "\nНорма производства в месяц: " + this.Rate +
                "\nПроизведено за каждый месяц: " + out + "\nПроцент брака за каждый месяц: " + def;
    }
    @Override
    public int hashCode()
    {
        int result = 13;
        result = 37 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 37 * result + (int)this.Rate;
        for (int value : this.output)
        {
            result = 37 * result + (int)value;
        }
        for (double value : this.defect)
        {
            long longbits = Double.doubleToLongBits(value);
            result = 37 * result + (int)(longbits - (longbits >>> 32));
        }
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextileFactory that = (TextileFactory) o;
        return Rate == that.Rate && Compare(output, that.output)  && name.equals(that.name) && Compare(defect, that.defect);
    }
    public static boolean Compare(double[] a, double[] a2) {
        if (a==a2)   // checks for same array reference
            return true;
        if (a==null || a2==null)  // checks for null arrays
            return false;
    
        int length = a.length;
        if (a2.length != length)  // arrays should be of equal length
            return false;
    
        for (int i=0; i<length; i++)  // compare array values
            if (a[i] != a2[i])
                return false;
    
        return true;
    }
    public static boolean Compare(int[] a, int[] a2) {
        if (a==a2)   // checks for same array reference
            return true;
        if (a==null || a2==null)  // checks for null arrays
            return false;
    
        int length = a.length;
        if (a2.length != length)  // arrays should be of equal length
            return false;
    
        for (int i=0; i<length; i++)  // compare array values
            if (a[i] != a2[i])
                return false;
    
        return true;
    }

    @Override
    public void outputAsBytes(OutputStream out)
    {
        DataOutputStream dataoutputter;
        try 
        {
            dataoutputter = new DataOutputStream(out);
            
            dataoutputter.writeUTF(getClass().getName());
            dataoutputter.writeUTF(name);
            dataoutputter.writeInt(Rate);
            dataoutputter.writeInt(output.length);

            for(int i=0;i < output.length; i++)
            {
                dataoutputter.writeInt(output[i]);
            }
            
            dataoutputter.writeInt(defect.length);

            for(int i =0;i < defect.length; i++)
            {
                dataoutputter.writeDouble(defect[i]);
            }

            dataoutputter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeAsText(Writer out)
    {
        PrintWriter printer = new PrintWriter(out);

        printer.println(getClass().getName());
        printer.println(name);
        printer.println(Rate);
        printer.println(output.length);

        
        for(int i=0;i < output.length; i++)
        {
            printer.println(output[i]);
        }
        
        printer.println(defect.length);

        for(int i =0;i < defect.length; i++)
        {
            printer.println(defect[i]);
        }
        printer.flush();
    }
   /* @Override
    public String toString() {
        return "TextileFactory{" +
                "output=" + Arrays.toString(output) +
                ", defect=" + Arrays.toString(defect) +
                ", name='" + name + '\'' +
                ", Rate=" + Rate +
                '}';
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, Rate);
        result = 31 * result + Arrays.hashCode(output);
        result = 31 * result + Arrays.hashCode(defect);
        return result;
    }
    */
  
}
