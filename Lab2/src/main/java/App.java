import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception 
    {
        Scanner in = new Scanner(System.in);
        Vector o = new Vector(5);
        Vector o2 = new Vector(5);
        System.out.printf("Введите %d значений первого вектора\n", o.getLength());
        for( int i=0; i<o.getLength();i++)
        {
            o.SetElement(i,in.nextDouble());
        }
        System.out.printf("Введите %d значений второго вектора\n", o2.getLength());
        for( int i=0; i<o2.getLength();i++)
        {
            o2.SetElement(i,in.nextDouble());
        }
        System.out.println("\nПервый вектор\n" + o.toString());
        System.out.println("\nВторой вектор\n" + o2.toString());
        System.out.println("\nДлина первого вектора " + o.getLength());
        System.out.println("\nДлина второго вектора " + o2.getLength());
        System.out.println("\nТретья позиция первого вектора " + o.getElement(2));
        System.out.println("\nМаксимум второго вектора " + o2.GetMaxVal());
        System.out.println("\nМинимум первого вектора " + o.GetMinVal());
        o2.Sort();
        System.out.println("\nОтсорированный второй вектор\n"+ o2.toString());
        o2.MultiplyByNuber(0.5);
        System.out.println("\nВторой вектор умноженный на 0.5\n"+o2.toString());
        Vector v3 = VectorsOperations.getSumOfTwoVectors(o,o2);
        System.out.println("\nСумма векторов\n"+v3.toString());
        System.out.println("\nСкалярное произведение векторов " + VectorsOperations.getScalarSumOfTwoVectors(o, o2));
        o.SetElement(1, 5);
        System.out.println("\nИзменение 2 элемента 1 вектора\n" + o.toString());
        System.out.println("\nЕвклидова норма 2 вектора "+ o2.GetEuclidNorm());
        in.close();
    }
}
