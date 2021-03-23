public class Vector
{
    private double[] elements;
    Vector(int len)
    {
        if(len<0)
            throw new IllegalArgumentException("Длина вектора не может быть меньше 0");
        elements = new double[len];
    }

    public double[] getElements() {
        return this.elements;
    }

    public void setElements(double[] elements) {
        this.elements = elements;
    }
    public void SetElement(int index, double value)
    {
        this.elements[index] = value;
    }
    public int getLength() 
    {
        int length = 0;
        for(double value : this.elements)
        {
            length+=1;
        }
        return length;
    }
    public void HighSort()
    {
        boolean sorted = false;
        double temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < getLength()-1; i++) {
                if (elements[i] > elements[i+1]) {
                    temp = elements[i];
                    elements[i] = elements[i+1];
                    elements[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
    public void DownSort()
    {
        boolean sorted = false;
        double temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < getLength()-1; i++) {
                if (elements[i] > elements[i+1]) {
                    temp = elements[i];
                    elements[i] = elements[i+1];
                    elements[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
    public double getElement(int index)
    {
        if(index > getLength()-1 || index < 0)
            throw new IllegalArgumentException("Индекс не может быть больше длинны вектора или меньше 0");
        return elements[index];
    }
    public double GetMinVal(){
        double min = elements[0];
        for (int i = 1; i < getLength(); i++) {
            if (elements[i] < min) {
                min = elements[i];
            }
        }
        return min;
    }
    public double GetMaxVal(){
        double max = elements[0];
        for (int i = 1; i < getLength(); i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        return max;
    }
    public double GetEuclidNorm(){
        double SumOfSquaredElements=0;
        for(double value : elements)
        {
            SumOfSquaredElements+= value*value;
        }
        return Math.sqrt(SumOfSquaredElements);
    }
    public void MultiplyByNuber(double number){
        for(int i =0 ; i < getLength(); i++)
        {
            elements[i] = elements[i]*number;
        }
    }
    @Override
    public String toString()
    {
        String tmp = "";
        for(double value : elements)
        {
            tmp += value + "\t";
        }
        return tmp;
    }
}