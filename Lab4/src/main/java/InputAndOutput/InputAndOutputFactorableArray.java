package InputAndOutput;

import Exceptions.NullFactorableObjectException;

import java.io.*;

import Factory.*;
import static InputAndOutput.InputAndOutputAsFactorable.inputBytesAsFact;
import static InputAndOutput.InputAndOutputAsFactorable.readTextAsFact;

public class InputAndOutputFactorableArray {
    public static void outputFactArrAsBytes(Factorable[] fArr, OutputStream out) {
        BufferedOutputStream bos = new BufferedOutputStream(out);
        outputLenOfFactArrAsBytes(fArr, bos);
        for(Factorable f : fArr)
        {
            f.outputAsBytes(out);
        }
    }

    private static void outputLenOfFactArrAsBytes(Factorable[] fArr, BufferedOutputStream bos) {
        DataOutputStream dataoutputter;
        try {
            dataoutputter = new DataOutputStream(bos);
            dataoutputter.writeInt(fArr.length);
            dataoutputter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeFactArrAsText(Factorable[] fArr, Writer out) {
        BufferedWriter bw = new BufferedWriter(out);
        writeLenOfFactArrAsText(fArr, bw);
        for(Factorable f : fArr)
        {
            f.writeAsText(out);
        }
    }

    private static void writeLenOfFactArrAsText(Factorable[] fArr, BufferedWriter bw) {
        PrintWriter printer = new PrintWriter(bw);
        printer.println(fArr.length);
        printer.flush();
    }

    public static void serializeFactArr(Factorable[] fArr, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(fArr);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static Factorable[] inputBytesAsFactArr(InputStream in) throws NullFactorableObjectException, ClassNotFoundException
    {
        final int len = getLenOfFactArrFromBytes(in);
        Factorable[] fArr = new Factorable[len];

        for(int i=0; i < len; i++)
        {
            fArr[i] = inputBytesAsFact(in);
        }

        return fArr;
    }

    private static int getLenOfFactArrFromBytes(InputStream in)
    {
        int len = -1;

        DataInputStream dataInputter;
        try {
            dataInputter = new DataInputStream(in);
            len = dataInputter.readInt();
            if (len == -1) {
                throw new IOException("Не удалось считать длину массива из байтвого потока");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return len;
    }

    public static Factorable[] readTextAsFactArr(Reader in) throws NullFactorableObjectException, ClassNotFoundException
    {
        BufferedReader bf = new BufferedReader(in);
        final int len = getLenOfFactArrFromText(bf);
        Factorable[] fArr = new Factorable[len];

        for(int i=0; i < len; ++i)
        {
            fArr[i] = readTextAsFact(bf);
        }
        return fArr;
    } 

    private static int getLenOfFactArrFromText(BufferedReader bf)
    {
        int len = -1;

        try {
            len = Integer.parseInt(bf.readLine());
            if (len == -1) {
                throw new IOException(" Не удалось считать длину массива из байтвого потока");
            }
        } catch (IOException | NumberFormatException exc) {
            System.err.println(exc.getMessage());
        }

        return len;
    }

    public static Factorable[] deserializeFactArr (InputStream in) throws NullFactorableObjectException
    {
        Factorable[] fArr;

        ObjectInputStream deserealizer;
        try {
            deserealizer = new ObjectInputStream(in);
            fArr = (Factorable[]) deserealizer.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            fArr = null;
        }
        if (fArr == null) {
            throw new NullFactorableObjectException("не удалось считать массив Factorable[]");
        }

        return fArr;
    }
}
