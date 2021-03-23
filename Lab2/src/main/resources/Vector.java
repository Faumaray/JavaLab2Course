import javax.lang.model.type.NullType;

public class Vector 
{
    private int length;
    double[] array;
    Vector(int n)
    {
        if(n<0)
            throw new IllegalArgumentException("Значение длинны не может быть меньше 0");
        this.array = new double[n];
        this.length = n;
    }
    public void Add(int index,double value)
    {
         if(index < 0 || index>this.length)
              throw new ArrayIndexOutOfBoundsException("Индекс за пределами размера вектора");
         array[index] = value;

    }
    public double Get(int position)
    {
        if(position < 0 || position>this.length)
            throw new ArrayIndexOutOfBoundsException("Индекс за пределами размера вектора");
        return this.array[position];
        
    }
    public void Change(int position, double value)
    {
        System.out.printf("Замена %f на позиции %d на значение %f", this.array[position], position, value);
        this.array[position] = value;
    }
    public int getLength()
    {
        return this.length;
    }
    public double GetMin()
    {
        double min = this.array[0];
        for (double value : this.array) 
        {
            if(min< value)
            {
                min = value;
            }
        }
        return min;
    }
    public double GetMax()
    {
        double max = this.array[0];
        for (double value : this.array) 
        {
            if(max< value)
            {
                max = value;
            }
        }
        return max;
    }
    public void Sort()
    {
        boolean sorted = false;
        double temp;
        while(!sorted)
        {
            sorted = true;
            for (int i = 0; i < length-1; i++) 
            {
                if (this.array[i] > this.array[i + 1]) 
                {
                    temp = this.array[i];
                    this.array[i] = this.array[i + 1];
                    this.array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }
    public double Normal()
    {
        double normal = 0;
        for (double value : this.array) 
        {
            normal += value*value;
        }
        return Math.sqrt(normal);
    }
    public Vector multiply(double value)
    {
        Vector tmp = new Vector(this.length);
        for(int i=0; i< this.length; i++)
        {
            tmp.Add(i, this.array[i] * value);
        }
        return tmp;
    }
    public Vector SummVectors(Vector vector)
    {
        if(!(this.length == vector.length))
            throw new IllegalArgumentException("Длинны векторов должны быть равны");
        Vector tmp = new Vector(vector.length);
        for(int i=0; i< length;i++)
        {
            tmp.Add(i, vector.array[i] + this.array[i]);
        }
        return tmp;
    }
    public double MultiplyVectors(Vector vector)
    {
        if(!(this.length == vector.length))
            throw new IllegalArgumentException("Длинны векторов должны быть равны");
        double multi = 0;
        for(int i=0;i<length;i++)
        {
            multi += vector.array[i]*this.array[i];
        }
        return multi;
    }
    public double[] getArray()
    {
        return this.array;
    }
    private String GetArray()
    {
        String tmp = "";
        for(double value : this.array)
        {
            tmp += value + " ";
        }
        return tmp;
    }
    @Override
    public String toString() {
        return "{"  +
            "Значения вектора='" + GetArray() + "'" +
            "}";
    }
    
}

