

import java.util.Arrays;

public class MetalFactory implements ISignature
{
    int[] output;
    double[] defect;
    String name;
    int Rate;
    //Конструктор по-умолчанию
    MetalFactory()
    {
        this.Rate = 0;
        this.defect = null;
        this.output = null;
        this.name = null;
    }
    //Конструктор с параметрами и исключениями при неверных параметрах
    MetalFactory(String factoryname,int rate,int[] outvalues, double[] defvalues)
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
    //Заполнить/установить значения для массива *продукции в месяц*
    @Override
    public void setoutput(int... values) {
        boolean acc = true;
        for(int value : values)
        if (value<0){acc = false;}
        if(acc == false)
        throw new IllegalArgumentException("Значение не может быть меньше 0");
        else
        this.output = values;
    }  
    //Заполнить/установить значения для массива *% дефекта в месяц*
    @Override
    public void setdefect(double... values) {
        boolean acc = true;
        for(double value : values)
            if (value<0 || value > 1){acc = false;}
        if(acc == false)
        throw new IllegalArgumentException("Значение не может быть меньше 0 или больше 1");
        else
        defect = Arrays.copyOf(values, values.length);
    } 
    //Установить значение *норма производства в год*
    @Override
    public void setRate(int value) 
    {
        if(value < 0)
        throw new IllegalArgumentException("Значение не может быть меньше 0");
        else
        this.Rate = value;
    } 
    //Установить значение *Название завода*
    @Override
    public void setName(String newname) {
        if(newname.equals("") || newname.equals(" ") || newname.equals(this.name))
        throw new IllegalArgumentException("Новые имя не может быть быть старым или пустым");
        else
        this.name = newname;  
    }
    //#endregion

    //#region get
    //Получить массив *Произведено по месяцам*
    @Override
    public int[] getoutput() {
        return this.output;
    }
    //Получить массив *% брака по месяцам*
    @Override
    public double[] getdefect() {
        return this.defect;
    }
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
        String out = "";
        String def = "";
        for(int value : this.output) {out += value + " ";}
        for(double value : this.defect) {def += value + " ";}
        return "Металлургический комбинат: "+ this.name + "\nНорма производства в месяц: " + this.Rate +
                "\nПроизведено за каждый месяц: " + out + "\nПроцент брака за каждый месяц: " + def;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextileFactory that = (TextileFactory) o;
        return Rate == that.Rate && Compare(output, that.output)  && name.equals(that.name)&& Compare(defect, that.defect);
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
    
    /*
    @Override
    public String toString() {
        return "MetalFactory{" +
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
    //#region
}
