package InputAndOutput;
import java.io.*;

import Factory.*;

import Exceptions.*;
public class InputAndOutputAsISignature 
{
    public static void outputISigAsBytes(ISignature s, OutputStream out)
    {
        s.outputAsBytes(out);
    }
    public static void writeISigAsText(ISignature s, Writer out)
    {
        s.writeAsText(out);
    }

    public static void serializeISig(ISignature s, OutputStream out)
    {
        ObjectOutputStream serializer;
        try 
        {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(s);
            serializer.flush();    
        } catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public static ISignature inputBytesAsISig(InputStream in) throws NullISignatureObjectException,ClassNotFoundException
    {
        ISignature s;

        DataInputStream dataInputter;

        try 
        {
            dataInputter = new DataInputStream(in);
            
            String className = dataInputter.readUTF();
            String name = dataInputter.readUTF();
            int Rate = dataInputter.readInt();
            int lengthOfOutput = dataInputter.readInt();
            int[] output = new int[lengthOfOutput];
            for(int i =0; i<lengthOfOutput; i++)
            {
                output[i] = dataInputter.readInt();
            }
            int lengthOfDefect = dataInputter.readInt();
            double[] defect = new double[lengthOfDefect];
            for(int i=0; i < lengthOfDefect; i++)
            {
                defect[i] = dataInputter.readDouble();
            }
            s = getNewISigByClassName(className,name,Rate,output,defect);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            s = null;
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }

        if(s == null) {
            throw new NullISignatureObjectException("Не удалось считать ISignature");
        }

        return s;
    }



    private static ISignature getNewISigByClassName(String className, String name, int Rate, int[] output, double[] defect) throws ClassNotFoundException
    {
        if(className.equals(MetalFactory.class.getName()))
        {
            return new MetalFactory(name,Rate,output,defect);
        }
        else if (className.equals(TextileFactory.class.getName()))
        {
            return new TextileFactory(name,Rate,output,defect);
        }
        else
        {
            throw new ClassNotFoundException("Такого класса не существует");
        }
    }

    public static ISignature readTextAsISig(BufferedReader bf) throws NullISignatureObjectException, ClassNotFoundException
    {
        ISignature s;

        try {
            String className = bf.readLine();
            String name = bf.readLine();
            int Rate = Integer.parseInt(bf.readLine());
            int lengthOfOutput = Integer.parseInt(bf.readLine());
            int[] output = new int[lengthOfOutput];
            for(int i=0;i<lengthOfOutput;i++)
            {
                output[i] = Integer.parseInt(bf.readLine());
            }
            int lengthOfDefect = Integer.parseInt(bf.readLine());
            double[] defect = new double[lengthOfDefect];
            for(int i =0; i< lengthOfDefect;i++)
            {
                defect[i]=Double.parseDouble(bf.readLine());
            }
            s = getNewISigByClassName(className, name, Rate, output, defect);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
            s = null;
        } catch(ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }  
        if (s == null)
        {
            throw new NullISignatureObjectException("Не удалось считать ISignature");
        }

        return s;
    }

    public static ISignature deserealiseISig (InputStream in) throws NullISignatureObjectException
    {
        ISignature s;

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            s = (ISignature) deserializer.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            s = null;
        }
        if (s == null) {
            throw new NullISignatureObjectException("не удалось считать ISignature");
        }

        return s;
    }
}
