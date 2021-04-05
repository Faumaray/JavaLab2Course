import Exceptions.IllegalIndexException;
import Exceptions.NullISignatureObjectException;
import Factory.MetalFactory;
import Factory.TextileFactory;
import InputAndOutput.InputAndOutputAsISignature;
import Factory.ISignature;

import java.io.*;
import java.util.Scanner;

import static InputAndOutput.InputAndOutputISignatureArray.*;
import static InputAndOutput.InputAndOutputAsISignature.*;
import static Factory.FactoryArea.*;


public class MenuPrints 
{
    static final String LINE = "-------------------------------------------------------------------------------\n";
    private static final String BYTES_FILE_WITH_SER = "ISigAsBytes.bin";
    private static final String TEXT_FILE_WITH_SER = "ISigAsText.txt";
    private static final String SERIALIZED_FILE_WITH_SER = "ISigSerialized.bin";

    private static final String BYTES_FILE_WITH_SER_ARR = "ISigArrAsBytes.bin";
    private static final String TEXT_FILE_WITH_SER_ARR = "ISigArrAsText.txt";
    private static final String SERIALIZED_FILE_WITH_SER_ARR = "ISigArrSerialized.bin";
    static void printTask(String task) {
        System.out.print('\n' + task + '\n' + LINE);
    }
    static void printExit() {
        System.out.print('\n' + "нажмите Enter, чтобы выйти в меню ... ");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        scan.close();
    }
    static void printISigArrAsISigOfEls(ISignature[] iArr) {
        System.out.print("база данных: ");
        if (iArr == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + LINE);

            for (int i = 0; i < iArr.length; i++) {
                System.out.print("[" + i + "] ");
                if (iArr[i] == null) {
                    System.out.println("элемент не задан");
                } else {
                    System.out.println('«' + iArr[i].getName() + '»');
                }
            }
        }
    }
    private static void printISig(ISignature s) {
        if (s == null) {
            System.out.println("Завод не задан");
        } else {
            System.out.println('«' + s.getName() + '»');
            System.out.print(LINE);
            System.out.println(s);
        }
    }
    private static void printElsOfISig(ISignature s) {
        if (s == null) {
            System.out.println("завод не задан");
        } else {
            for (int i = 0; i < s.getNumOfOutput(); i++) {
                System.out.print(i + ") ");

                if (s.getEllOfOutput(i) == 0) {
                    System.out.println("элемент на задан");
                } else {
                    System.out.println(" (Произведенно-- " + s.getEllOfOutput(i) + ')');
                }
            }
            for (int i = 0; i < s.getNumOfDefect(); i++) {
                System.out.print(i + ") ");

                if (s.getEllOfDefect(i) == 0) {
                    System.out.println("элемент на задан");
                } else {
                    System.out.println(" (Процент дефекта-- " + s.getEllOfDefect(i) + ')');
                }
            }
        }
    }
    static ISignature[] printGetISigArr()
    {
        int len;
        do{
            len = printGetInt();

            if(len < 1)
            {
                System.out.print("Массив должен вмещать хотя бы 1 элемент");
            }
            else if (len > 5)
            {
                System.out.println("Слишком большая база");
            }
            else 
            {
                ISignature[] iArr = new ISignature[len];
                System.out.println("Массив размером в " + len + " элементов успешно создан");
                return iArr;
            }
        } while(true);

    }
    private static int printGetInt()
    {
        int num;
        Scanner scan = new Scanner(System.in);
        String str;
        do {
            System.out.println("Введите число ...");
            str = scan.nextLine();
            try {
                num = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) 
            {
                System.out.println("Ошибка: введённая строка не является числом");
            }

        } while(true);
        scan.close();
        return num;
    }
    private static int printGetIndex(int maxIndex) {
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
                System.out.println("ошибка: неверный индекс");
            } catch (Exception exc) {
                System.out.println("ошибка: введённая строка не является числом");
            }
        } while (true);
        scan.close();
        return index;
    }

    static void printSetElOfArr(ISignature[] db) {
        if (db == null) {
            System.out.println("операция невозможна: база данных не задана");
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
                        "1 -- " + MetalFactory.class.getName() + "\n" +
                        "2 -- " + TextileFactory.class.getName() + "\n" +
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
                    System.out.println("ошибка: неверный пункт меню");
                }
            } while (true);
            scan.close();
        }
    }
    private static MetalFactory printGetAndSetMetalFactory() {
        System.out.print("введите имя MetalFactory ................................. ");
        Scanner scan = new Scanner(System.in);
        String factoryName = scan.nextLine();
        System.out.print("заполните Metal Factory норму производства в год, количество месяцев, кол-во произведённой продукции в месяц-ах и процент-ы дефекта в месяц-ах \n" + LINE);
        int Rate = Integer.parseInt(scan.nextLine());
        int months = Integer.parseInt(scan.nextLine());
        int[] numOutput = printGetOutput(months);
        double[] numDefect = printGetDefect(months);
        MetalFactory mf = new MetalFactory(factoryName, Rate, numOutput, numDefect);
        System.out.println("Metal Factory успешно создано");
        System.out.println();
        scan.close();
        return mf;
    }
    private static TextileFactory printGetAndSetTextileFactory() {
        System.out.print("введите имя TextileFactory ................................. ");
        Scanner scan = new Scanner(System.in);
        String factoryName = scan.nextLine();
        System.out.print("заполните Textile Factory норму производства в год, количество месяцев, кол-во произведённой продукции в месяц-ах и процент-ы дефекта в месяц-ах \n" + LINE);
        int Rate = Integer.parseInt(scan.nextLine());
        int months = Integer.parseInt(scan.nextLine());
        int[] numOutput = printGetOutput(months);
        double[] numDefect = printGetDefect(months);
        TextileFactory tf = new TextileFactory(factoryName, Rate, numOutput, numDefect);
        System.out.println("Textile Factory успешно создано");
        System.out.println();
        scan.close();
        return tf;
    }
    private static int[] printGetOutput(int months)
    {
        int[] out = new int[months];
        Scanner scan = new Scanner(System.in);
        int i=0;
        do
        {
            if(i>months)
            {
                break;
            }
            int tmp = Integer.parseInt(scan.nextLine());
            if(tmp < 0)
            {
                System.out.println("Значение не может быть меньше 0");
            }
            else
            {
                out[i]= tmp;
                i++;
            }
        } while (true);
        scan.close();
        return out;
    }
    private static double[] printGetDefect(int months)
    {
        double[] out = new double[months];
        Scanner scan = new Scanner(System.in);
        int i=0;
        do
        {
            if(i>months)
            {
                break;
            }
            double tmp = Double.parseDouble(scan.nextLine());
            if(tmp < 0 || tmp > 1)
            {
                System.out.println("Значение не может быть меньше 0 или больше 1");
            }
            else
            {
                out[i]= tmp;
                i++;
            }
        } while (true);
        scan.close();
        return out;
    }
    private static void printSetElsOfIsig(ISignature sig)
    {
        if (sig == null) {
            System.out.println("операция невозможна: серия не задана");
        } else {
            for (int i = 0; i < sig.getNumOfEls(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n' + LINE);
                try {
                    if (!printSetElOfSer(sig, i)) {
                        i--;
                    }
                } catch (Exception exc) {
                    System.out.println(exc.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }
    
}

