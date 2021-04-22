import Exceptions.NullFactorableObjectException;
import FactoryArea.Factorable;
import FactoryArea.MetalFactory;
import FactoryArea.TextileFactory;
import Threads.ReadingRunnableThread;
import Threads.ReadingThread;
import Threads.WritingRunnableThread;
import Threads.WritingThread;
import Threads.FactorableSynchronizer;

import java.util.ArrayList;
import java.util.Scanner;
import static MenuItems.MenuItems.*;
public class Menu
{
    public static void main(String[] args) 
    {
        Factorable[] fArr = null;
        Factorable f = null;
        Factorable testing;
        Scanner scan = new Scanner(System.in);
        String m;
        do {
            System.out.print(
                    "РАБОТА С БАЗОЙ:\n" +
                    " 1 -- вывести полную информацию базы\n" +
                    " 2 -- создать базу\n" +
                    " 3 -- задание элемента базы\n" +
                    " 4 -- найти в базе объекты,\n" +
                    "      первый функциональный метод которых возвращают одинаковый результат,\n" +
                    "      поместить такие объекты в массив\n" +
                    " 5 -- найти в базе объекты,\n" +
                    "      второй функциональный метод которых возвращают одинаковый результат,\n" +
                    "      поместить такие объекты в массив\n" +
                    " 6 -- разбить базу на два массива,\n" +
                    "      в которых будут храниться однотипные элементы\n" +
                    "РАБОТА С ОБЪЕКТОМ:\n" +
                    " 13 -- показать содержимое объекта\n" +
                    " 14 -- создать и заполнить объект\n" +
                    " 15 -- считать из байтового потока\n" +
                    " 16 -- считать из текстового потока\n" +
                    " 17 -- десериализовать объект\n" +
                    " 18 -- записать объект в байтовый поток\n" +
                    " 19 -- записать объект в символьный поток\n" +
                    " 20 -- сериализовать объект\n" +
                    "Работа с Нитями:\n"+
                    " 21 -- Заполнить нитью +\n"+
                    "считать нитью\n"+
                    " 22 -- write-read-write-read....\n"+
                    "ПАТТЕРНЫ:\n" +
                    " 23 -- Iterable/Iterator\n" +
                    " 0 -- выйти\n" +
                    "выбор ... ");
            m = scan.nextLine();
            switch (m) {
                // region РАБОТА С БАЗОЙ
                case "1":
                    printTask(" 1 -- вывести полную информацию базы");
                    printFactArr(fArr);
                    break;

                case "2":
                    printTask(" 2 -- создать базу");
                    System.out.print("задание размера базы: ");
                    fArr = printGetFactArr();
                    break;

                case "3":
                    printTask(" 3 -- задание элемента базы");
                    printFactArrAsNamesOfEls(fArr);
                    System.out.println();
                    printSetElOfFarr(fArr);
                    break;

                case "4":
                    printTask(" 4 -- найти в базе объекты,\n" +
                            "      первый функциональный метод которых возвращают одинаковый результат,\n" +
                            "      поместить такие объекты в массив");
                    printGetArrWithTwoElsWithSameExcess(fArr);
                    break;

                case "5":
                    printTask(" 5 -- найти в базе объекты,\n" +
                            "      второй функциональный метод которых возвращают одинаковый результат,\n" +
                            "      поместить такие объекты в массив");
                    printGetArrWithTwoElsWithSameUsefulExcess(fArr);
                    break;

                case "6":
                    printTask(" 6 -- разбить базу на два массива,\n" +
                            "      в которых будут храниться однотипные элементы");
                    printSplitArrIntoTwoMetalAndTextileArrs(fArr);
                    break;
                // endregion

                // region РАБОТА С ОБЪЕКТОМ
                case "13":
                    printTask("13 -- показать содержимое объекта");
                    System.out.println(f);
                    break;

                case "14":
                    printTask("14 -- создать и заполнить объект");
                    f = printCreateFac();
                    printSetElsOfFact(f);
                    break;

                case "15":
                    printTask("15 -- считать из байтового потока");
                    try {
                        f = printInputBytesAsFac();
                    } catch (NullFactorableObjectException exc) {
                        System.err.println(exc.getMessage());
                    }
                    break;

                case "16":
                    printTask("16 -- считать из текстового потока");
                    try {
                        f = printReadTextAsFact();
                    } catch (NullFactorableObjectException exc) {
                        System.err.println(exc.getMessage());
                    }
                    break;

                case "17":
                    printTask("17 -- десериализовать объект");
                    try {
                        f = printDeserializeFact();
                    } catch (NullFactorableObjectException exc) {
                        System.err.println(exc.getMessage());
                    }
                    break;

                case "18":
                    printTask("18 -- записать объект в байтовый поток");
                    printOutputFactAsBytes(f);
                    break;

                case "19":
                    printTask("19 -- записать объект в текстовый поток");
                    printWriteFactAsText(f);
                    break;

                case "20":
                    printTask("20 -- сериализовать объект");
                    printSerializeFact(f);
                    break;

                case "21":
                    printTask("21 -- заполнить нитью +\n" +
                            "      считать   нитью");

                    testing = new MetalFactory("Тест", 100, 16);
                    WritingThread wt = new WritingThread(testing);
                    ReadingThread rt = new ReadingThread(testing);

                    wt.setPriority(Thread.MAX_PRIORITY);
                    wt.start();

                    rt.setPriority(Thread.MIN_PRIORITY);
                    rt.start();
                    break;

                case "22":
                    printTask("22 -- write-read-write-read...");


                    testing = new TextileFactory("Тест", 100, 16);
                    FactorableSynchronizer ssyncher = new FactorableSynchronizer(testing);
                    WritingRunnableThread wrt = new WritingRunnableThread(ssyncher);
                    ReadingRunnableThread rrt = new ReadingRunnableThread(ssyncher);

                    new Thread(wrt).start();
                    new Thread(rrt).start();
                // endregion

                case "23":
                    if (f == null) {
                        System.err.println("операция невозможна: завод не задан");
                    } else {
                        for (ArrayList<Number> ar : f) {
                            System.out.print("{"+ar.get(0));
                            System.out.print(';');
                            System.out.print(ar.get(1)+ "}");
                            System.out.print(' ');
                        }
                        System.out.print('\n');
                    }
                    break;
                default:
                    break;
            }
        } while (!m.equals("0"));
    }
 
}