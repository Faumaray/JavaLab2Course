package MenusMetod;

import Exceptions.IllegalIndexException;
import Exceptions.NullFactorableObjectException;
import Factory.MetalFactory;
import Factory.TextileFactory;
import InputAndOutput.InputAndOutputAsFactorable;
import Factory.Factorable;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static InputAndOutput.InputAndOutputFactorableArray.*;
import static InputAndOutput.InputAndOutputAsFactorable.*;
import static Factory.Factories.*;


public class MenuPrints 
{
    public static final String LINE = "-------------------------------------------------------------------------------\n";
    private static final String BYTES_FILE_WITH_FACT = "FactAsBytes.bin";
    private static final String TEXT_FILE_WITH_FACT = "FactAsText.txt";
    private static final String SERIALIZED_FILE_WITH_FACT = "FactSerialized.bin";

    private static final String BYTES_FILE_WITH_FACT_ARR = "FactArrAsBytes.bin";
    private static final String TEXT_FILE_WITH_FACT_ARR = "FactArrAsText.txt";
    private static final String SERIALIZED_FILE_WITH_FACT_ARR = "FactArrSerialized.bin";
    public static void printTask(String task) {
        System.out.print('\n' + task + '\n' + LINE);
    }
    public static void printExit() {
        System.out.print('\n' + "нажмите Enter, чтобы выйти в меню ... ");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
    public static void printFactArrAsNamesOfEls(Factorable[] fArr) {
        System.out.print("база данных: ");
        if (fArr == null) {
            System.err.println("не задана");
        } else {
            System.out.print('\n' + LINE);

            for (int i = 0; i < fArr.length; i++) {
                System.out.print("[" + i + "] ");
                if (fArr[i] == null) {
                    System.err.println("элемент не задан");
                } else {
                    System.out.println('«' + fArr[i].getName() + '»');
                }
            }
        }
    }
    public static void printFactArr(Factorable[] fArr) {
        System.out.print("база данных: ");
        if (fArr == null) {
            System.err.println("не задана");
        } else {
            System.out.print('\n' + LINE);

            for (int i = 0; i < fArr.length; i++) { // по элементам БД
                System.out.print("[" + i + "] ");
                printFact(fArr[i]);
                System.out.print(LINE + LINE);
            }
        }
    }
    private static void printFact(Factorable f) {
        if (f == null) {
            System.err.println("Завод не задан");
        } else {
            System.out.println('«' + f.getName() + '»');
            System.out.print(LINE);
            System.out.println(f);
        }
    }
    private static void printElsOfFact(Factorable f) {
        if (f == null) {
            System.err.println("завод не задан");
        } else 
        {
            for (int i=0;i<f.getNumberOfEls(); i++)
            {
                System.out.print("Месяц №" +i + ") ");
                System.out.println("Произведено в месяц: " +f.getEl(i) + " Процент дефекта: "+ f.getDefect(i) + ")");
            }
        }
    }
    public static Factorable[] printGetFactArr()
    {
        int len;

        do {
            len = printGetInt();

            if (len < Factorable.MIN_LEN_OF_AR) {
                System.err.println("массив должен вмещать хотя бы" + Factorable.MIN_LEN_OF_AR + " элемент/-ов");
            }
             else {
                Factorable[] factorableArr = new Factorable[len];
                System.out.println("массив размером в " + len + " элементов успешно создан");
                return factorableArr;
            }
        } while (true);

    }
    public static int printGetInt() 
    {
        int num;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите число ... ");
            str = scan.nextLine();

            try {
                num = Integer.parseInt(str);
                break;
            } catch (NumberFormatException exc) {
                System.err.println("ошибка: введённая строка не является числом");
            }
        } while (true);
        
        return num;
    }
    public static double printGetDouble() 
    {
        double num;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите число ... ");
            str = scan.nextLine();

            try {
                num = Double.parseDouble(str);
                break;
            } catch (NumberFormatException exc) {
                System.err.println("ошибка: введённая строка не является числом");
            }
        } while (true);
        
        return num;
    }
    public static int printGetIndex(int maxIndex) 
    {
        int index;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите индекс ... ");
            str = scan.nextLine();
            System.out.println();

            try {
                index = Integer.parseInt(str);
                if (index < 0 || index > maxIndex) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalIndexException exc) {
                System.err.println("ошибка: неверный индекс");
            } catch (Exception exc) {
                System.err.println("ошибка: введённая строка не является числом");
            }
        } while (true);
        
        return index;
    }

    public static void printSetElOfArr(Factorable[] db) {
        if (db == null) {
            System.err.println("операция невозможна: база данных не задана");
        } else {
            System.out.println("задайте индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля):");
            int index = printGetIndex(db.length - 1);

            Scanner scan = new Scanner(System.in);
            String str;

            System.out.print("задание элемента под индексом " + index + '\n' + LINE);
            do {
                System.out.print("выберите тип элемента\n" +
                        LINE +
                        "1 -- MetalFactory\n" +
                        "2 -- TextileFactory\n" +
                        LINE +
                        "выбор ... ");
                str = scan.nextLine();
                System.out.println();

                if (str.equals("1")) {
                    db[index] = printGetAndSetMetalFactory();
                    break;
                } else if (str.equals("2")) {
                    db[index] = printGetAndSetTextileFactory();
                    break;
                } else {
                    System.err.println("ошибка: неверный пункт меню");
                }
            } while (true);
        }
    }
    private static MetalFactory printGetAndSetMetalFactory() {
        System.out.print("введите имя MetalFactory ................................. ");
        Scanner scan = new Scanner(System.in);
        String factoryName = scan.nextLine();
        int numOfMonths = printGetNumOfMonths();
        int rate = printGetRate();
        MetalFactory mf = new MetalFactory(factoryName, rate, numOfMonths);
        System.out.println("завод успешно создан");
        System.out.println();

        System.out.print("заполните завод кол-вом произведенной продукции в месяц-ах и их процентом дефекта\n" + LINE);
        printSetElsOfFact(mf);
        return mf;
    }
    private static TextileFactory printGetAndSetTextileFactory() {
        System.out.print("введите имя TextileFactory ................................. ");
        Scanner scan = new Scanner(System.in);
        String factoryName = scan.nextLine();
        int numOfMonths = printGetNumOfMonths();
        int rate = printGetRate();
        TextileFactory tf = new TextileFactory(factoryName, rate, numOfMonths);
        System.out.println("завод успешно создан");
        System.out.println();

        System.out.print("заполните завод кол-вом произведенной продукции в месяц-ах и их процентом дефекта\n" + LINE);
        printSetElsOfFact(tf);
        return tf;
    }
    private static int printGetNumOfMonths() 
    {
        int num;

        do {
            System.out.print("задание количества месяцев: ... ");
            num = printGetInt();

            if (num < Factorable.MIN_LEN_OF_AR) 
            {
                System.err.println("Кол-во должно быть хотя бы " + Factorable.MIN_LEN_OF_AR + " элемент/-та/-ов");
            }
            else 
            {
                return num;
            }
        } while (true);
    }
    private static int printGetRate() {
        int num;

        do {
            System.out.print("задание нормы производства в год: ");
            num = printGetInt();

            if (num < Factorable.MIN_NUM_OF_RATE) {
                System.err.println("Норма производства в год должны быть хотя бы " + Factorable.MIN_NUM_OF_RATE);
            } 
            else {
                return num;
            }
        } while (true);
    }
    private static void printSetElsOfFact(Factorable f) 
    {
        if (f == null) {
            System.err.println("Завод не задан");
        } else {
            for (int i = 0; i < f.getNumberOfEls(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n' + LINE);
                try {
                    if (!printSetElOfFactory(f, i)) {
                        i--;
                    }
                } catch (Exception exc) {
                    System.err.println(exc.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }
    private static boolean printSetElOfFactory(Factorable f, int index) throws Exception 
    {
        if (f == null) {
            throw new UnsupportedOperationException("Завод не задан");
        } else {
            try {
                System.out.print("Произведено в месяц ............................... ");
                Scanner scan = new Scanner(System.in);
                int output = printGetOutput();
                f.setEl(index, output);

                System.out.print("Процент дефекта в месяц ........................... ");
                double defect = printGetDefect();
                f.setDefectOfEl(index, defect);

                return true;
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
                return false;
            } catch (Exception exc) {
                throw new Exception(exc.getMessage());
            }
        }
    }
    private static int printGetOutput() 
    {
        int num;

        do {
            num = printGetInt();
            if (num < Factorable.MIN_VALUE_OF_OUTPUT) {
                System.err.println("ошибка: должна/-но быть хотя бы " + Factorable.MIN_VALUE_OF_OUTPUT);
            } 
            else {
                return num;
            }
        } while (true);
    }
    private static double printGetDefect() 
    {
        double num;

        do {
            num = printGetDouble();
            if (num < Factorable.MIN_VALUE_OF_DEFECT) {
                System.err.println("ошибка: должна/-но быть хотя бы " + Factorable.MIN_VALUE_OF_DEFECT);
            } else if (num > Factorable.MAX_VALUE_OF_DEFECT) {
                System.err.println("ошибка: должна/-но быть не больше" + Factorable.MAX_VALUE_OF_DEFECT);
            } else {
                return num;
            }
        } while (true);
    }
    public static Factorable printGetAndSetFact() {
        Factorable s;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("выберите тип элемента\n" +
                    LINE +
                    "1 -- " + MetalFactory.class.getName() + "\n" +
                    "2 -- " + TextileFactory.class.getName() + "\n" +
                    LINE +
                    "выбор ... ");
            str = scan.nextLine();
            System.out.println();

            if (str.equals("1")) {
                s = printGetAndSetMetalFactory();
                break;
            } else if (str.equals("2")) {
                s = printGetAndSetTextileFactory();
                break;
            } else {
                System.err.println("ошибка: неверный пункт меню");
            }
        } while (true);

        return s;
    }
    public static void printGetArrWithTwoElsWithSameExcess(Factorable[] f) 
    {
        List<Factorable[]> arr;
        try {
            arr = getArrWithTwoElsWithSameExcess(f);
            System.out.println("база данных успешно разделена");
            System.out.println();
            int count = 0;
            for (Factorable[] factorables : arr) {
                System.out.println("Масств №"+ count);
                for (Factorable factory : factorables) 
                {
                    System.out.println('«' + factory.getName() + '»');
                }   
                count++; 
            }
            System.out.println("Какой массив вывести?");
            int choice = printGetIndex(arr.size()-1);
            printFactArr(arr.get(choice));
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
    public static void printGetArrWithTwoElsWithSameUsefulExcess(Factorable[] f) 
    {
        List<Factorable[]> arr;

        try {
            arr = getArrWithTwoElsWithSameUsefulExcess(f);
            System.out.println("база данных успешно разделена");
            System.out.println();
            int count = 0;
            for (Factorable[] factorables : arr) {
                System.out.println("Масств №"+ count);
                for (Factorable factory : factorables) 
                {
                    System.out.println('«' + factory.getName() + '»');
                }   
                count++; 
            }
            System.out.println("Какой массив вывести?");
            int choice = printGetIndex(arr.size()-1);
            printFactArr(arr.get(choice));
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
    public static void printSplitArrIntoTwoMetalAndTextileArrs(Factorable[] fArr) {
        if (fArr == null) {
            System.err.println("операция невозможна: база данных не задана");
        } else {
            try {
                MetalFactory[] mf = getMetalFactorysArrFromFactorableArr(fArr);
                TextileFactory[] tf = getTextileFactorysArrFromFactorableArr(fArr);
                System.out.println("база данных разбита на два массива, в которых хранятся однотипные элементы");
                System.out.println();

                printFactArr(mf);
                printFactArr(tf);
            } catch (Exception exc) {
                System.err.println(exc.getMessage());
            }
        }
    }
    public static void printOutputFactAsBytes(Factorable f) {
        if (f == null) {
            System.err.println("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(BYTES_FILE_WITH_FACT);
                InputAndOutputAsFactorable.outputFactAsBytes(f, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                System.out.println("объект успешно записан в байтовый поток");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }
    public static void printWriteFactAsText(Factorable f) {
        if (f == null) {
            System.err.println("операция невозможна: объект не задан");
        } else {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(TEXT_FILE_WITH_FACT);
                InputAndOutputAsFactorable.writeFactAsText(f, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                System.out.println("объект успешно записан в текстовый поток");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }

    public static void printSerializeFact(Factorable f) {
        if (f == null) {
            System.err.println("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(SERIALIZED_FILE_WITH_FACT);
                InputAndOutputAsFactorable.serializeFact(f, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                System.out.println("объект успешно сериализован");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }

    public static void printOutputFactArrAsBytes(Factorable[] fArr) {
        if (fArr == null) {
            System.err.println("операция невозможна: массив не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(BYTES_FILE_WITH_FACT_ARR);
                outputFactArrAsBytes(fArr, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                System.out.println("объект успешно записан в байтовый поток");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }

    public static void printWriteFactArrAsText(Factorable[] fArr) {
        if (fArr == null) {
            System.err.println("операция невозможна: массив не задан");
        } else {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(TEXT_FILE_WITH_FACT_ARR);
                writeFactArrAsText(fArr, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                System.out.println("массив успешно записан в текстовый поток");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }

    public static void printSerializeFactArr(Factorable[] fArr) {
        if (fArr == null) {
            System.err.println("операция невозможна: массив не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(SERIALIZED_FILE_WITH_FACT_ARR);
                serializeFactArr(fArr, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                System.out.println("массив успешно сериализован");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }

    public static Factorable printInputBytesAsFact() throws NullFactorableObjectException {
        Factorable f = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(BYTES_FILE_WITH_FACT);
            f = inputBytesAsFact(fileInputter);
            fileInputter.close();

            System.out.println("объект успешно считан из байтового потока (файла)");
        } catch (IOException | NullFactorableObjectException | ClassNotFoundException exc) {
            System.err.println(exc.getMessage());
        }

        if (f == null) {
            throw new NullFactorableObjectException("не удалось считать Factorable");
        }

        return f;
    }

        public static Factorable printReadTextAsFact() throws NullFactorableObjectException {
            Factorable f = null;

            FileReader fileReader;
            BufferedReader bufferedReader;
            try {
                fileReader = new FileReader(TEXT_FILE_WITH_FACT);
                bufferedReader = new BufferedReader(fileReader);

                f = readTextAsFact(bufferedReader);

                bufferedReader.close();
                fileReader.close();

                System.out.println("объект успешно считан из тектового потока (файла)");
            } catch (IOException | NullFactorableObjectException | ClassNotFoundException exc) {
                System.err.println(exc.getMessage());
            }

            if (f == null) {
                throw new NullFactorableObjectException("не удалось считать Factorable");
            }

            return f;
        }

    public static Factorable printDeserializeFact() throws NullFactorableObjectException {
        Factorable f = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(SERIALIZED_FILE_WITH_FACT);
            f = deserealizeFact(fileInputter);
            fileInputter.close();

            System.out.println("объект успешно десериализован (из файла)");
        } catch (IOException | NullFactorableObjectException exc) {
            System.err.println(exc.getMessage());
        }

        if (f == null) {
            throw new NullFactorableObjectException("не удалось считать Factorable");
        }

        return f;
    }

    public static Factorable[] printInputBytesAsFactArr() throws NullFactorableObjectException {
        Factorable[] fArr = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(BYTES_FILE_WITH_FACT_ARR);
            fArr = inputBytesAsFactArr(fileInputter);
            fileInputter.close();

            System.out.println("массив успешно считан из байтового потока (файла)");
        } catch (IOException | NullFactorableObjectException | ClassNotFoundException exc) {
            System.err.println(exc.getMessage());
        }

        if (fArr == null) {
            throw new NullFactorableObjectException("не удалось считать массив Factorable[]");
        }

        return fArr;
    }
    public static Factorable[] printReadTextAsFactArr() throws NullFactorableObjectException {
        Factorable[] fArr = null;

        FileReader fileReader;
        try {
            fileReader = new FileReader(TEXT_FILE_WITH_FACT_ARR);
            fArr = readTextAsFactArr(fileReader);
            fileReader.close();

            System.out.println("массив успешно считан из тектового потока (файла)");
        } catch (IOException | NullFactorableObjectException | ClassNotFoundException exc) {
            System.err.println(exc.getMessage());
        }

        if (fArr == null) {
            throw new NullFactorableObjectException("не удалось считать массив Factorable[]");
        }

        return fArr;
    }

    public static Factorable[] printDeserializeFactArr() throws NullFactorableObjectException {
        Factorable[] fArr = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(SERIALIZED_FILE_WITH_FACT_ARR);
            fArr = deserializeFactArr(fileInputter);
            fileInputter.close();

            System.out.println("массив успешно десериализована (из файла)");
        } catch (IOException | NullFactorableObjectException exc) {
            System.err.println(exc.getMessage());
        }

        if (fArr == null) {
            throw new NullFactorableObjectException("не удалось десериализовать массив Factorable[]");
        }

        return fArr;
    }
}

