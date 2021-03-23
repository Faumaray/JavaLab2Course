

import java.util.Arrays;

/*
Предметная область:
Заводы (1)Текстиль (2)Метиллические детали
Поля:
1.1)Массив {Количество произведённой продукции по месяцам}
1.2)Массив {Процент брака по месяцам} Размер = размеру (1.1)
2)Строкове поле Название завода
3)Поле целого типа содержащее норму производства в год
4.1)Функциональный метод производящий подсчёт общего количеста избыточной продукции sum(1.1) - (3)*length(1.1)
4.2)Функциональный метод производящий подсчёт общего количества положительной продукции в год (1.1[i]-(1.1[i]*1.2[i]))-(3)*length(1.1)
*/
public class App
{
    public static void main(String[] args) 
    {
        
        ISignature[] array = new ISignature[]
        {
            new MetalFactory("Oppo",400,new int[]{650,400,300},new double[]{0.2,0.1}),
            new TextileFactory("Oppo2",400,new int[]{650,1400,300},new double[]{0.2,0.5}),
            new MetalFactory("Oppo3",400,new int[]{650,450,300},new double[]{0.6,0.1}),
            new TextileFactory("Oppo4",400,new int[]{650,400,200},new double[]{0.3,0.1}),
            new MetalFactory("Oppo5",400,new int[]{650,470,300},new double[]{0.2,0.2}),
            new TextileFactory("Oppo6",400,new int[]{650,400,300},new double[]{0.2,0.1}),
            new TextileFactory(),
            new MetalFactory()
        };
        ISignature[] array2 = new ISignature[]{};
        ISignature[] metal = new ISignature[]{};
        ISignature[] textile = new ISignature[]{};
        for(ISignature a : array)
        {
            try {
            System.out.println(a.toString());
            }
            catch (NullPointerException e )
            {
                //System.out.println(e.getMessage());
            }
            if(a instanceof MetalFactory)
            {
                metal = Arrays.copyOf(metal, metal.length + 1);
                metal[metal.length-1] = a;
            }
            if(a instanceof TextileFactory)
            {
                textile = Arrays.copyOf(textile, textile.length + 1);
                textile[textile.length-1] = a;
            }
        }
        try
        {
        for(int i=0; i<array.length; i++) 
        {
            for (int j=i+1; j<array.length; j++) 
            {
                
               if(array[i].excess() == array[j].excess() || array[i].usefulexcess() == array[j].usefulexcess()) 
               {
                   array2 = Arrays.copyOf(array2, array2.length + 1);
                   array2[array2.length-1] = array[i];
                   array2 = Arrays.copyOf(array2, array2.length + 1);
                   array2[array2.length-1] = array[j];
               }
            }
        }
    }
    catch(NullPointerException e){};
        System.out.println("Только Металл");
        for(ISignature a : metal){System.out.println(a.getName());}
        System.out.println("Только Текстиль");
        for(ISignature a : textile){System.out.println(a.getName());}
        System.out.println("С одинаковым выходом метода");
        for(ISignature a : array2)
        {
            System.out.println(a.getName());
            System.out.println(a.excess());
            System.out.println(a.usefulexcess());
        }



    }
 
}