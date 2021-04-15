package Factory;
import Exceptions.DatabaseNotSetException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Factories 
{
    public static MetalFactory[] getMetalFactorysArrFromFactorableArr(Factorable[] f) throws DatabaseNotSetException
    {
       if(f == null)
       {
           throw new DatabaseNotSetException("База данных не задана");
       } 
       else
       {
           LinkedList<Integer> indexesOfMetalFactorys = getIndexesOfMetalFactorys(f);
           MetalFactory[] mf = new MetalFactory[indexesOfMetalFactorys.size()];

           for(int i=0; i < mf.length; i++)
           {
               mf[i] = (MetalFactory) f[indexesOfMetalFactorys.get(i)];
           }

           return mf;
       }
    }
    private static LinkedList<Integer> getIndexesOfMetalFactorys(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("Metal Factory не задана");
        }
        else
        {
            LinkedList<Integer> indexesOfMetalFactorys = new LinkedList<>();

            for(int i=0; i < f.length; i++)
            {
                if(f[i] instanceof MetalFactory)
                {
                    indexesOfMetalFactorys.add(i);
                }
            }
            return indexesOfMetalFactorys;
        }
    }
    public static TextileFactory[] getTextileFactorysArrFromFactorableArr(Factorable[] f) throws DatabaseNotSetException
    {
       if(f == null)
       {
           throw new DatabaseNotSetException("База данных не задана");
       } 
       else
       {
           LinkedList<Integer> indexesOfTextileFactorys = getIndexesOfTextileFactorys(f);
           TextileFactory[] tf = new TextileFactory[indexesOfTextileFactorys.size()];

           for(int i=0; i < tf.length; i++)
           {
               tf[i] = (TextileFactory) f[indexesOfTextileFactorys.get(i)];
           }

           return tf;
       }
    }
    private static LinkedList<Integer> getIndexesOfTextileFactorys(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("Textile Factory не задана");
        }
        else
        {
            LinkedList<Integer> indexesOfTextileFactorys = new LinkedList<>();

            for(int i=0; i < f.length; i++)
            {
                if(f[i] instanceof TextileFactory)
                {
                    indexesOfTextileFactorys.add(i);
                }
            }
            return indexesOfTextileFactorys;
        }
    }
    public static List<Factorable[]> getArrWithTwoElsWithSameExcess(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("База данных не задана");
        }
        else
        {
            List<Factorable[]> list = new ArrayList<Factorable[]>();
            int[] excesses = getExcesses(f);
            boolean[] result = new boolean[f.length];
            Arrays.fill(result, Boolean.TRUE);
            int curIndex;
            int indexToCompareWith;
            int len = excesses.length;
            List<Factorable> tmp = new ArrayList<Factorable>();
            for(curIndex = 0; curIndex < len; curIndex++)
            {
                tmp.add(f[curIndex]);
                for(indexToCompareWith = curIndex+1; indexToCompareWith < len; indexToCompareWith++)
                    {

                        if(excesses[curIndex]==excesses[indexToCompareWith])
                        {
                            if(result[indexToCompareWith] == true)
                            {                        
                            tmp.add(f[indexToCompareWith]);
                            result[indexToCompareWith] = false;
                            }
                        }
                    }
                if(tmp.size() != 1)
                {   
                    Factorable[] fact = new Factorable[tmp.size()];
                    for(int index = 0; index<tmp.size();index++)
                    {
                        fact[index] = tmp.get(index);
                    }
                    list.add(fact);
                }
                tmp.clear();
            }
            if(list.isEmpty())
            throw new NoSuchElementException("Нет таких элементов");
            return list;
            
        }
    }
    private static int[] getExcesses(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("База данных не задана");
        }
        else
        {
            int[] excesses = new int[f.length];

            for(int i = 0; i < excesses.length; i++)
            {
                excesses[i] = f[i].excess();
            }

            return excesses;
        }
    }
    public static List<Factorable[]> getArrWithTwoElsWithSameUsefulExcess(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("База данных не задана");
        }
        else
        {
            List<Factorable[]> list = new ArrayList<Factorable[]>();
            int[] usefulExcesses = getUsefulExcesses(f);
            boolean[] result = new boolean[f.length];
            Arrays.fill(result, Boolean.TRUE);
            int curIndex;
            int indexToCompareWith;
            int len = usefulExcesses.length;
            List<Factorable> tmp = new ArrayList<Factorable>();
            for(curIndex = 0; curIndex < len; curIndex++)
            {
                tmp.add(f[curIndex]);
                for(indexToCompareWith = curIndex+1; indexToCompareWith < len; indexToCompareWith++)
                    {
                        if(usefulExcesses[curIndex]==usefulExcesses[indexToCompareWith])
                        {
                            if(result[indexToCompareWith] == true)
                            {                        
                            tmp.add(f[indexToCompareWith]);
                            result[indexToCompareWith] = false;
                            }
                        }
                    }
                    if(tmp.size() != 1)
                    {   
                        Factorable[] fact = new Factorable[tmp.size()];
                        for(int index = 0; index<tmp.size();index++)
                        {
                            fact[index] = tmp.get(index);
                        }
                        list.add(fact);
                    }
                    tmp.clear();
            }
            if(list.isEmpty())
            throw new NoSuchElementException("Нет таких элементов");
            return list;
        }
    }
    private static int[] getUsefulExcesses(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("База данных не задана");
        }
        else
        {
            int[] usefulExcesses = new int[f.length];

            for(int i = 0; i < usefulExcesses.length; i++)
            {
                usefulExcesses[i] = f[i].usefulexcess();
            }

            return usefulExcesses;
        }
    }
}
