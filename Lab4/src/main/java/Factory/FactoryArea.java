package Factory;

import Exceptions.DatabaseNotSetException;

import java.util.LinkedList;
import java.util.NoSuchElementException;
public class FactoryArea 
{
    public static MetalFactory[] getMetalFactoriesArrFromISigArr(ISignature[] iArr) throws DatabaseNotSetException
    {
        if(iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else
        { 
            LinkedList<Integer> indexesOfMetalFactorys = getIndexesOfMetalFactory(iArr);
            MetalFactory[] mf = new MetalFactory[indexesOfMetalFactorys.size()];

            for(int i = 0; i < mf.length; i++)
            {
                mf[i] = (MetalFactory) iArr[indexesOfMetalFactorys.get(i)];
            }

            return mf;
        }
    }

    private static LinkedList<Integer> getIndexesOfMetalFactory(ISignature[] iArr) throws DatabaseNotSetException
    {
        if(iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else
        {
            LinkedList<Integer> indexesOfMetalFactorys = new LinkedList<>();

            for(int i=0; i < iArr.length ; i++)
            {
                if(iArr[i] instanceof MetalFactory)
                {
                    indexesOfMetalFactorys.add(i);
                }
            }
            
            return indexesOfMetalFactorys;
        }
    }

    public static TextileFactory[] getTextileFactoriesArrFromISigArr(ISignature[] iArr) throws DatabaseNotSetException
    {
        if(iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else
        { 
            LinkedList<Integer> indexesOfTextileFactorys = getIndexesOfTextileFactory(iArr);
            TextileFactory[] tf = new TextileFactory[indexesOfTextileFactorys.size()];

            for(int i = 0; i < tf.length; i++)
            {
                tf[i] = (TextileFactory) iArr[indexesOfTextileFactorys.get(i)];
            }

            return tf;
        }
    }

    private static LinkedList<Integer> getIndexesOfTextileFactory(ISignature[] iArr) throws DatabaseNotSetException
    {
        if(iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else
        {
            LinkedList<Integer> indexesOfTextileFactorys = new LinkedList<>();

            for(int i=0; i < iArr.length ; i++)
            {
                if(iArr[i] instanceof MetalFactory)
                {
                    indexesOfTextileFactorys.add(i);
                }
            }
            
            return indexesOfTextileFactorys;
        }
    }

    public static ISignature[] getISigArrWithTwoElsWithSameUsefulExces(ISignature[] iArr) throws DatabaseNotSetException
    {
        if (iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else 
        {
            int[] usefulexcess = getUsefulExcess(iArr);

            int currIndexOfUseful;
            int indexToCompareWith;
            int len = usefulexcess.length;
            for(currIndexOfUseful = 0; currIndexOfUseful < len; currIndexOfUseful++)
            {
                for(indexToCompareWith = currIndexOfUseful + 1; indexToCompareWith < len; indexToCompareWith++)
                {
                    if(usefulexcess[currIndexOfUseful] == usefulexcess[indexToCompareWith])
                    {
                        ISignature[] twoSig = new ISignature[2];
                        twoSig[0] = iArr[currIndexOfUseful];
                        twoSig[1] = iArr[indexToCompareWith];

                        return twoSig;
                    }
                }
            }

            throw new NoSuchElementException("Нет таких элементов");

        }
    }

    private static int[] getUsefulExcess(ISignature[] iArr) throws DatabaseNotSetException
    {
        if (iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else 
        {
            int[] usefulexcess = new int[iArr.length];

            for(int i = 0; i < usefulexcess.length; i++)
            {
                usefulexcess[i] = iArr[i].usefulexcess();
            }

            return usefulexcess;
        }
    }

    public static ISignature[] getISigArrWithTwoElsWithSameExces(ISignature[] iArr) throws DatabaseNotSetException
    {
        if (iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else 
        {
            int[] excess = getExcess(iArr);

            int currIndexOfUseful;
            int indexToCompareWith;
            int len = excess.length;
            for(currIndexOfUseful = 0; currIndexOfUseful < len; currIndexOfUseful++)
            {
                for(indexToCompareWith = currIndexOfUseful + 1; indexToCompareWith < len; indexToCompareWith++)
                {
                    if(excess[currIndexOfUseful] == excess[indexToCompareWith])
                    {
                        ISignature[] twoSig = new ISignature[2];
                        twoSig[0] = iArr[currIndexOfUseful];
                        twoSig[1] = iArr[indexToCompareWith];

                        return twoSig;
                    }
                }
            }

            throw new NoSuchElementException("Нет таких элементов");

        }
    }

    private static int[] getExcess(ISignature[] iArr) throws DatabaseNotSetException
    {
        if (iArr == null)
        { throw new DatabaseNotSetException("База данных не задана");}
        else 
        {
            int[] excess = new int[iArr.length];

            for(int i = 0; i < excess.length; i++)
            {
                excess[i] = iArr[i].excess();
            }

            return excess;
        }
    }
}
