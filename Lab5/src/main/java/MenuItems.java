
import Exceptions.*;
import Factory.Factorable;
import Factory.Factories;
import Factory.MetalFactory;
import Factory.TextileFactory;

import java.io.*;
import java.util.Scanner;

import static Factory.Factories.*;


class MenuItems {
    public static final String LINE = "-------------------------------------------------------------------------------\n";
    private static final String BYTES_FILE = "bytes.bin";
    private static final String TEXT_FILE = "text.txt";
    private static final String OBJECT_FILE = "object.bin";
    
    public static void printTask(String task) {
        System.out.print('\n' + task + '\n' + LINE);
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
                    db[index] = Factories.getSynchronizedFactorable(printGetAndSetMetalFactory());
                    break;
                } else if (str.equals("2")) {
                    db[index] = Factories.getSynchronizedFactorable(printGetAndSetTextileFactory());
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
    public static void printGetArrWithTwoElsWithSameExcess(Factorable[] fArr) {
        Factorable[] arrWithTwoElsWithSameExcess;

        try {
            arrWithTwoElsWithSameExcess = getArrWithTwoElsWithSameExcess(fArr);
            System.out.println("база данных успешно разделена");
            System.out.println();

            printFactArr(arrWithTwoElsWithSameExcess);
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
    public static void printGetArrWithTwoElsWithSameUsefulExcess(Factorable[] fArr) {
        Factorable[] arrWithTwoElsWithSameUsefulExcess;

        try {
            arrWithTwoElsWithSameUsefulExcess = getArrWithTwoElsWithSameUsefulExcess(fArr);
            System.out.println("база данных успешно разделена");
            System.out.println();

            printFactArr(arrWithTwoElsWithSameUsefulExcess);
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
                fileOutputter = new FileOutputStream(BYTES_FILE);
                Factories.outputFactorable(f, fileOutputter);
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
                fileWriter = new FileWriter(TEXT_FILE);
                Factories.writeFactorable(f, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                System.out.println("объект успешно записан в текстовый поток");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }
    public static void printSerializeFact(Factorable f) 
    {
        if (f == null) {
            System.err.println("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(OBJECT_FILE);
                Factories.serializeFactorable(f, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                System.out.println("объект успешно сериализован");
            } catch (IOException exc) {
                System.err.println(exc.getMessage());
            }
        }
    }
   static Factorable printInputBytesAsFac() throws NullFactorableObjectException
   {
       Factorable f;

       FileInputStream fileInputter;
       try 
       {
           fileInputter = new FileInputStream(BYTES_FILE);
           f = inputFactorable(fileInputter);
           fileInputter.close();

           System.out.println("объект успешно считан из байтового потока (файла)");
       }
        catch (IOException | NullFactorableObjectException | ClassNotFoundException exc) 
        {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
        }

        if (f == null) 
        {
            throw new NullFactorableObjectException("не удалось считать Seriesable");
        }

        return f;
    
   }
   
   public static Factorable printReadTextAsFact() throws NullFactorableObjectException {
    Factorable f = null;

    FileReader fileReader;
    BufferedReader bufferedReader;
    try {
        fileReader = new FileReader(TEXT_FILE);
        bufferedReader = new BufferedReader(fileReader);

        f = readFactorable(bufferedReader);

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
            fileInputter = new FileInputStream(OBJECT_FILE);
            f = deserializeFactorable(fileInputter);
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

}


