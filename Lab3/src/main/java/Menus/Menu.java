package Menus;
import Exceptions.IllegalIndexException;
import Factory.MetalFactory;
import Factory.TextileFactory;
import Factory.Factorable;

import static Factory.Factories.*;

import java.util.Scanner;

public class Menu 
{
    public static final String line = "-------------------------------------------------------------------------------\n";

    public static void printExit() 
    {
        System.out.print('\n' + "нажмите Enter, чтобы выйти в меню ... ");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
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
    public static void printTask(String task) 
    {
        System.out.print('\n' + task + '\n' + line);
    }
    public static Factorable[] printGetFactorableleArr() 
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
    public static void printDbAsNamesOfEls(Factorable[] db) 
    {
        System.out.print("база данных: ");
        if (db == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + line);

            for (int i = 0; i < db.length; i++) {
                System.out.print("[" + i + "] ");
                if (db[i] == null) {
                    System.out.println("элемент не задан");
                } else {
                    System.out.println('«' + db[i].getName() + '»');
                }
            }
        }
    }
    public static void printSetElOfDb(Factorable[] db) 
    {
        if (db == null) {
            System.err.println("операция невозможна: база данных не задана");
        } else {
            System.out.println("задайте индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля):");
            int index = printGetIndex(db.length - 1);

            Scanner scan = new Scanner(System.in);
            String str;

            System.out.print("задание элемента под индексом " + index + '\n' + line);
            do {
                System.out.print("выберите тип элемента\n" +
                        line +
                        "1 -- MetalFactory\n" +
                        "2 -- TextileFactory\n" +
                        line +
                        "выбор ... ");
                str = scan.nextLine();
                System.out.println();

                if (str.equals("1")) {
                    db[index] = printGetMetalFactory();
                    break;
                } else if (str.equals("2")) {
                    db[index] = printGetTextileFactory();
                    break;
                } else {
                    System.err.println("ошибка: неверный пункт меню");
                }
            } while (true);
        }
    }
    private static MetalFactory printGetMetalFactory() {
        System.out.print("введите название завода ................................. ");
        Scanner scan = new Scanner(System.in);
        String name= scan.nextLine();

        int numOfMonths = printGetNumOfMonths();
        int rate = printGetRate();
        MetalFactory mf = new MetalFactory(name, rate, numOfMonths);
        System.out.println("завод успешно создан");
        System.out.println();

        System.out.print("заполните завод кол-вом произведенной продукции в месяц-ах и их процентом дефекта\n" + line);
        printSetElsOfFactory(mf);

        return mf;
    }

    private static TextileFactory printGetTextileFactory() {
        System.out.print("введите название завода ........................... ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        int numOfMonths = printGetNumOfMonths();
        int rate = printGetRate();
        TextileFactory tf = new TextileFactory(name, rate, numOfMonths);
        System.out.println("завод успешно создан");
        System.out.println();

        System.out.print("заполните завод кол-вом произведенной продукции в месяц-ах и их процентом дефекта\n" + line);
        printSetElsOfFactory(tf);

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
    private static void printSetElsOfFactory(Factorable f) 
    {
        if (f == null) {
            System.err.println("Завод не задан");
        } else {
            for (int i = 0; i < f.getNumberOfEls(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n' + line);
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
    public static void printDb(Factorable[] db) 
    {
        System.out.print("база данных: ");
        if (db == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + line);

            for (int i = 0; i < db.length; i++) { // по элементам БД
                System.out.print("[" + i + "] ");
                printFactory(db[i]);
                System.out.print(line + line);
            }
        }
    }
    private static void printFactory(Factorable f) 
    {
        if (f == null) {
            System.err.println("Завод не задан");
        } else 
        {
            System.out.println('«' + f.getName() + '»');
            System.out.print(line);
            System.out.println(f);
        }
    }
    private static void printElsOfFactory(Factorable f) 
    {
        if (f == null) {
            System.err.println("Завод не задан");
        } else {
            for (int i = 0; i < f.getNumberOfEls(); i++) 
            {
                System.out.println("Произведено в " + i + " месяц: " + f.getEl(i) + 
                " (Процент дефекта: " + f.getDefect(i)+")");
            }
        }
    }
    public static void printGetArrWithTwoElsWithSameExcess(Factorable[] f) 
    {
        Factorable[] arr;

        try {
            arr = getArrWithTwoElsWithSameExcess(f);
            System.out.println("база данных успешно разделена");
            System.out.println();

            printDb(arr);
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
    public static void printGetArrWithTwoElsWithSameUsefulExcess(Factorable[] f) 
    {
        Factorable[] arr;

        try {
            arr = getArrWithTwoElsWithSameUsefulExcess(f);
            System.out.println("база данных успешно разделена");
            System.out.println();

            printDb(arr);
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
    public static void printSplitDbIntoTwoMetalAndTextileArrs(Factorable[] f) 
    {
        if (f == null) {
            System.err.println("операция невозможна: база данных не задана");
        } else {
            try {
                MetalFactory[] mf = getMetalFactorysArrFromFactorableArr(f);
                TextileFactory[] tf = getTextileFactorysArrFromFactorableArr(f);
                System.out.println("база данных разбита на два массива, в которых хранятся однотипные элементы");
                System.out.println();

                printDb(mf);
                printDb(tf);
            } catch (Exception exc) {
                System.err.println(exc.getMessage());
            }
        }
    }
}
