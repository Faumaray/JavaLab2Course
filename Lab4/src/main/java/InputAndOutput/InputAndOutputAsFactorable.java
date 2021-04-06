package InputAndOutput;
import java.io.*;

import Factory.*;

import Exceptions.*;
public class InputAndOutputAsFactorable
{
    public static void outputFactAsBytes(Factorable f, OutputStream out)
    {
        f.outputAsBytes(out);
    }
    public static void writeFactAsText(Factorable f, Writer out)
    {
        f.writeAsText(out);
    }

    public static void serializeFact(Factorable f, OutputStream out)
    {
        ObjectOutputStream serializer;
        try 
        {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(f);
            serializer.flush();    
        } catch (IOException e) 
        {
            System.err.println(e.getMessage());
        }
    }

    public static Factorable inputBytesAsFact(InputStream in) throws NullFactorableObjectException,ClassNotFoundException
    {
        Factorable f;

        DataInputStream dataInputter;

        try 
        {
            dataInputter = new DataInputStream(in);
            
            String className = dataInputter.readUTF();
            String name = dataInputter.readUTF();
            int Rate = dataInputter.readInt();
            int numOfMonths = dataInputter.readInt();

            f = getNewFactByClassName(className,name,Rate,numOfMonths);

            final int len = f.getNumberOfEls();
            int out;
            double def;
            for(int index=0;index<len;index++)
            {
                out = dataInputter.readInt();
                def = dataInputter.readDouble();

                f.setEl(index, out);
                f.setDefectOfEl(index, def);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            f = null;
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }

        if(f == null) {
            throw new NullFactorableObjectException("Не удалось считать Factorable");
        }

        return f;
    }



    private static Factorable getNewFactByClassName(String className, String name, int Rate, int numberOfMonths) throws ClassNotFoundException
    {
        if(className.equals(MetalFactory.class.getName()))
        {
            return new MetalFactory(name,Rate,numberOfMonths);
        }
        else if (className.equals(TextileFactory.class.getName()))
        {
            return new TextileFactory(name,Rate,numberOfMonths);
        }
        else
        {
            throw new ClassNotFoundException("Такого класса не существует");
        }
    }

    public static Factorable readTextAsFact(BufferedReader bf) throws NullFactorableObjectException, ClassNotFoundException
    {
        Factorable f;

        try {
            String className = bf.readLine();
            String name = bf.readLine();
            int Rate = Integer.parseInt(bf.readLine());
            int numberOfMonths = Integer.parseInt(bf.readLine());
            f = getNewFactByClassName(className, name, Rate, numberOfMonths);

            final int len = f.getNumberOfEls();
            int out;
            double def;
            for (int index = 0; index < len; index++ )
            {
                out = Integer.parseInt(bf.readLine());
                def = Double.parseDouble(bf.readLine());

                f.setEl(index, out);
                f.setDefectOfEl(index, def);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
            f = null;
        } catch(ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }  
        if (f == null)
        {
            throw new NullFactorableObjectException("Не удалось считать Factorable");
        }

        return f;
    }

    public static Factorable deserealizeFact(InputStream in) throws NullFactorableObjectException
    {
        Factorable f;

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            f = (Factorable) deserializer.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            f = null;
        }
        if (f == null) {
            throw new NullFactorableObjectException("не удалось считать Factorable");
        }

        return f;
    }
}
