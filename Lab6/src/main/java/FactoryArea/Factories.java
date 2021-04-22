package FactoryArea;
import Exceptions.DatabaseNotSetException;
import Exceptions.NullFactorableObjectException;
import Factories.FactorableFactory;
import Factories.MetalFactoriesFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Factories 
{

    private static FactorableFactory factory;

    public static void setFactorableFactory(FactorableFactory ff) {
        factory = ff;
    }

    public static Factorable createInstance() {
        return factory.createInstance();
    }

    public static Factorable createInstance(String factoryName, int Rate, int numOfMonths) {
        return factory.createInstance(factoryName, Rate, numOfMonths);
    }

    public static Factorable getSynchronizedFactorable(Factorable f) {
        return new SynchronizedFactorable(f);
    }

    public static Factorable getUnmodifiableFactorable(Factorable f) {
        return new UnmodifiableFactorable(f);
    }

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
    public static void outputFactorable(Factorable f, OutputStream out)
    {f.outputAsBytes(out);}
    public static void writeFactorable(Factorable f, Writer out)
    {f.writeAsText(out);}
    public static void serializeFactorable(Factorable f, OutputStream out)
    {
     ObjectOutputStream serializer;
     try {
         serializer =new ObjectOutputStream(out);
         serializer.writeObject(f);
         serializer.flush();
     } catch (Exception e) {
         System.err.println(e.getMessage());
         e.printStackTrace();
     }   
    }
    public static Factorable inputFactorable(InputStream in) throws NullFactorableObjectException, ClassNotFoundException
    {
        Factorable f;

        DataInputStream dataInputter;
        try {
            dataInputter = new DataInputStream(in);

            String className = dataInputter.readUTF();
            String factoryName = dataInputter.readUTF();
            int rate = dataInputter.readInt();
            int numOfMonths = dataInputter.readInt();

            f= getNewFacByClassName(className, factoryName, rate, numOfMonths);

            final int len = f.getNumberOfEls();
            int output;
            double defect;
            for (int index =0; index < len; index++)
            {
                output = dataInputter.readInt();
                defect = dataInputter.readDouble();
                f.setEl(index, output);
                f.setDefectOfEl(index, defect);
            }
        } catch (IOException exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
            f = null;
        }   catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }
        if (f == null) {
            throw new NullFactorableObjectException("не удалось считать Factorable");
        }
        
        return f;
    }
    public static Factorable readFactorable(Reader in) throws NullFactorableObjectException, ClassNotFoundException {
        Factorable f;

        StreamTokenizer st;
        try {
            st = new StreamTokenizer(in);

            st.nextToken();
            String classname = st.sval;
            st.nextToken();
            String factoryName = st.sval;
            st.nextToken();
            int rate = (int)st.nval;
            st.nextToken();
            int numberOfMonths = (int) st.nval;

            f= getNewFacByClassName(classname, factoryName, rate, numberOfMonths);

            final int len = f.getNumberOfEls();
            int output;
            double defect;
            for (int index=0;index<len;index++)
            {
                st.nextToken();
                output = (int) st.nval;
                st.nextToken();
                defect = st.nval;
                f.setEl(index, output);
                f.setDefectOfEl(index, defect);
            }
        }
        catch (IOException | NumberFormatException exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
            f = null;
        } catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }

        if (f == null) {
            throw new NullFactorableObjectException("не удалось считать Factorable");
        }

        return f;
    }

    private static Factorable getNewFacByClassName(String classname, String factoryName, int rate, int numOfEls) throws ClassNotFoundException {
        if(classname.equals(MetalFactory.class.getName()))
        {
            return new MetalFactory(factoryName,rate,numOfEls);
        }  
        else if(classname.equals(TextileFactory.class.getName()))
        {
            return new TextileFactory(factoryName, rate, numOfEls);
        }
        else {
            throw new ClassNotFoundException("ошибка: такого класса не существует");
        }
    }
    public static Factorable deserializeFactorable(InputStream in) throws NullFactorableObjectException
    {
        Factorable f;

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            f = (Factorable) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
            f = null;
        }

        if (f == null) {
            throw new NullFactorableObjectException("не удалось считать Factorable");
        }

        return f;
    }
}
