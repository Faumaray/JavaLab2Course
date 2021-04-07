package Factory;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;

import Exceptions.IllegalIndexException;
public class MetalFactory implements Factorable, Serializable
{
    private int[] output;
    private double[] defect;
    private String name;
    private int Rate;
    //Конструктор по-умолчанию
    MetalFactory()
    {
        Rate = 100;
        output = new int[1];
        defect = new double[output.length];
        name = "Без Названия";
    }
    //Конструктор с параметрами и исключениями при неверных параметрах
    public MetalFactory(String factoryname,int rate,int numberOfMonths)
    {
        this.name = factoryname;
        this.Rate = rate;
        output = new int[numberOfMonths];
        defect = new double[output.length];
    }
    //Метод получения общего кол-ва избыточной продукции (Функциональный метод №1)
    @Override
    public int excess()
    {
        int sum =0;
        for (int value : output) 
        {
            sum += value;
        }
        return (sum-Rate);
    }
    //Метод получения общего кол-ва полезной избыточной продукции (Функциональный метод №2)
    @Override
    public int usefulexcess()
    {
        int useful=0;
        for(int i=0; i<output.length;i++)
        {
            useful += output[i]- (int)(output[i]*defect[i]);
        }
        return (useful - (Rate*output.length));
    }
    //#region set
    @Override
    public void setName(String newname)
    {
        this.name = newname;
    }
    @Override
    public void setRate(int value) 
    {
        if(value < Factorable.MIN_NUM_OF_RATE)
        {
            throw new IllegalArgumentException("Неверное значение годовой нормы");
        }    
        this.Rate = value;
    }
    @Override
    public void setEl(int index, int value) 
    {
        if(index < 0 || index >= output.length)
        {
            throw new IllegalIndexException("Неверный индекс");
        }    
        if(value < Factorable.MIN_VALUE_OF_OUTPUT)
        {
            throw new IllegalArgumentException("Неверное значений");
        }
        output[index]=value;
    }
    @Override
    public void setDefectOfEl(int index, double value) 
    {
        if(index < 0 || index >= output.length)
        {
            throw new IllegalIndexException("Неверный индекс");
        }    
        if(value < Factorable.MIN_VALUE_OF_DEFECT || value > Factorable.MAX_VALUE_OF_DEFECT)
        {
            throw new IllegalArgumentException("Неверное процентное значения дефекта");
        }   
        defect[index]=value;
    }
    //Заполнить/установить значения для массива *продукции в месяц*
    //Получить значение *Норма производства в год*
    @Override
    public int getRate() {
        return this.Rate;
    }
    //Получить значение *Имя завода*
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int getNumberOfEls() 
    {
        return output.length;    
    }
    @Override
    public int getEl(int index) 
    {
        if(index < 0 || index >= output.length)
        {
            throw new IllegalIndexException("Неверный индекс");
        } 
        return output[index];   
    }
    @Override
    public double getDefect(int index) 
    {
        if(index < 0 || index >= defect.length)
        {
            throw new IllegalIndexException("Неверный индекс");
        }     
        return defect[index];
    }
    //#endregion

    //#region Переопределений
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
    public String toString()
    {
        String output = "";
        output += "Название завода................ " + name + "\n";
        output += "Норма производства в год....... " + Rate + "\n";
        output += "Кол-во избыточной продукции.... " + excess() + "\n";
        output += "Кол-во полезного избытка....... " + usefulexcess() + "\n";
        output += "Кол-во месяцев................. " + getNumberOfEls() + "\n";
        output += "Тип объекта.................... " + getClass() + "\n";
        output += "-------------------------------\n";
        output += monthsInfo();
        return output;  
    }
    private String monthsInfo()
    {
        int lastIndex = output.length - 1;
        String out = "";
        for(int i = 0; i < lastIndex; i++)
        {
            out += "Произведено в " + i + " месяц: " + output[i] + 
            " (Процент дефекта: " + defect[i]+")\n";
        }
        out += "Произведено в " + lastIndex + " месяц: " + output[lastIndex] + 
            " (Процент дефекта: " + defect[lastIndex]+")";
        return out;
    }
    @Override
    public boolean equals(Object o) 
    {
        boolean isFactorable = o instanceof Factorable;

        if(isFactorable)
        {
            Factorable anotherFactory = (Factorable) o;

            if(this.name.equals(anotherFactory.getName()))
                return areElsEqual(anotherFactory);
        }
        return false;
    }
    private boolean areElsEqual(Factorable anotherFactory)
    {
        if(!areRateAndElsEqual(anotherFactory))
        {
            return false;
        }
        for(int i = 0; i < output.length; i++)
        {
            if(!isElEqual(i, anotherFactory))
                return false; 
        }

        return true;
    }
    private boolean areRateAndElsEqual(Factorable anotherFactory)
    {
        return this.getRate() == anotherFactory.getRate() && 
        this.output.length == anotherFactory.getNumberOfEls();
    }
    private boolean isElEqual(int index,Factorable anotherFactory)
    {
        return output[index] == anotherFactory.getEl(index) &&
        defect[index] == anotherFactory.getDefect(index);
    }

    @Override
    public void outputAsBytes(OutputStream out) 
    {
        DataOutputStream dataOuputter = new DataOutputStream(out);
        try 
        {
            dataOuputter.writeUTF(getClass().getName());
            dataOuputter.writeUTF(name);
            dataOuputter.writeInt(Rate);
            dataOuputter.writeInt(output.length);
            for (int index = 0; index < output.length; index++)
            {
                dataOuputter.writeInt(output[index]);
                dataOuputter.writeDouble(defect[index]);
            }    
            dataOuputter.flush();
        } catch (IOException e) 
        {
            System.err.println(e.getMessage());
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
        for (int index = 0;index < output.length; index++)
        {
            printer.println(output[index]);
            printer.println(defect[index]);
        }
        printer.flush();
    }
}
