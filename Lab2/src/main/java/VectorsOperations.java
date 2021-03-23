
public class VectorsOperations
{
    public static Vector getSumOfTwoVectors(Vector v1, Vector v2)
    {   
        if(!(v1.getLength() == v2.getLength()))
            throw new IllegalArgumentException("Длинны векторов должны быть равны");
        Vector v3 = new Vector(v1.getLength());
        for(int i =0 ; i <v3.getLength(); i++)
        {
            v3.SetElement(i, v1.getElement(i)+v2.getElement(i));
        }
        return v3;
    }
    public static double getScalarSumOfTwoVectors(Vector v1, Vector v2)
    {
        if(!(v1.getLength() == v2.getLength()))
            throw new IllegalArgumentException("Длинны векторов должны быть равны");
        double scalarum = 0;
        for(int i=0;i<v1.getLength();i++)
        {
            scalarum+=v1.getElement(i)*v2.getElement(i);
        }
        return scalarum;
    }
}