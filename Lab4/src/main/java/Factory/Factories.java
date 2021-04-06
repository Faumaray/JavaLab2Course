package Factory;
import Exceptions.DatabaseNotSetException;

import java.util.LinkedList;
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
    public static Factorable[] getArrWithTwoElsWithSameExcess(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("База данных не задана");
        }
        else
        {
            int[] excesses = getExcesses(f);

            int curIndex;
            int indexToCompareWith;
            int len = excesses.length;

            for(curIndex = 0; curIndex < len; curIndex++)
            {
                for(indexToCompareWith = curIndex+1; indexToCompareWith < len; indexToCompareWith++)
                    {
                        if(excesses[curIndex]==excesses[indexToCompareWith])
                        {
                            Factorable[] twoFactorys = new Factorable[2];
                            twoFactorys[0] = f[curIndex];
                            twoFactorys[1] = f[indexToCompareWith];

                            return twoFactorys;
                        }
                    }
            }
            throw new NoSuchElementException("Нет таких элементов");
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
    public static Factorable[] getArrWithTwoElsWithSameUsefulExcess(Factorable[] f) throws DatabaseNotSetException
    {
        if(f == null)
        {
            throw new DatabaseNotSetException("База данных не задана");
        }
        else
        {
            int[] usefulExcesses = getUsefulExcesses(f);

            int curIndex;
            int indexToCompareWith;
            int len = usefulExcesses.length;

            for(curIndex = 0; curIndex < len; curIndex++)
            {
                for(indexToCompareWith = curIndex+1; indexToCompareWith < len; indexToCompareWith++)
                    {
                        if(usefulExcesses[curIndex]==usefulExcesses[indexToCompareWith])
                        {
                            Factorable[] twoFactorys = new Factorable[2];
                            twoFactorys[0] = f[curIndex];
                            twoFactorys[1] = f[indexToCompareWith];

                            return twoFactorys;
                        }
                    }
            }
            throw new NoSuchElementException("Нет таких элементов");
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
