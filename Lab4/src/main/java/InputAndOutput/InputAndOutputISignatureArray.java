package InputAndOutput;

import Exceptions.NullISignatureObjectException;

import java.io.*;

import Factory.*;
import static InputAndOutput.InputAndOutputAsISignature.inputBytesAsISig;
import static InputAndOutput.InputAndOutputAsISignature.readTextAsISig;

public class InputAndOutputISignatureArray {
    public static void outputISigArrAsBytes(ISignature[] iArr, OutputStream out) {
        BufferedOutputStream bos = new BufferedOutputStream(out);
        outputLenOfISigArrAsBytes(iArr, bos);
        for(ISignature s : iArr)
        {
            s.outputAsBytes(out);
        }
    }

    private static void outputLenOfISigArrAsBytes(ISignature[] iArr, BufferedOutputStream bos) {
        DataOutputStream dataoutputter;
        try {
            dataoutputter = new DataOutputStream(bos);
            dataoutputter.writeInt(iArr.length);
            dataoutputter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeISigArrAsText(ISignature[] iArr, Writer out) {
        BufferedWriter bw = new BufferedWriter(out);
        writeLenOfISigArrAsText(iArr, bw);
        for(ISignature s : iArr)
        {
            s.writeAsText(out);
        }
    }

    private static void writeLenOfISigArrAsText(ISignature[] iArr, BufferedWriter bw) {
        PrintWriter printer = new PrintWriter(bw);
        printer.println(iArr.length);
        printer.flush();
    }


    public static ISignature[] inputBytesAsISigArr (InputStream in) throws NullISignatureObjectException, ClassNotFoundException
    {
        final int len = getLenOfISigArrFromBytes(in);
        ISignature[] iArr = new ISignature[len];

        for(int i=0; i < len; i++)
        {
            iArr[i] = inputBytesAsISig(in);
        }

        return iArr;
    }

    private static int getLenOfISigArrFromBytes(InputStream in)
    {
        int len = -1;

        DataInputStream dataInputter;
        try {
            dataInputter = new DataInputStream(in);
            len = dataInputter.readInt();
            if (len == -1) {
                throw new IOException("Нее удалось считать длину массива из байтвого потока");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return len;
    }

    public static ISignature[] readTextAsISigArr(Reader in) throws NullISignatureObjectException, ClassNotFoundException
    {
        BufferedReader bf = new BufferedReader(in);
        final int len = getLenOfISigArrFromText(bf);
        ISignature[] iArr = new ISignature[len];

        for(int i=0; i < len; ++i)
        {
            iArr[i] = readTextAsISig(bf);
        }
        return iArr;
    } 

    private static int getLenOfISigArrFromText(BufferedReader bf)
    {
        int len = -1;

        try {
            len = Integer.parseInt(bf.readLine());
            if (len == -1) {
                throw new IOException(" Не удалось считать длину массива из байтвого потока");
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println(exc.getMessage());
        }

        return len;
    }

    public static ISignature[] deserializeISigArr (InputStream in) throws NullISignatureObjectException
    {
        ISignature[] iArr;

        ObjectInputStream deserealizer;
        try {
            deserealizer = new ObjectInputStream(in);
            iArr = (ISignature[]) deserealizer.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            iArr = null;
        }
        if (iArr == null) {
            throw new NullISignatureObjectException("не удалось считать массив ISignature[]");
        }

        return iArr;
    }
}
