import Exceptions.NullFactorableObjectException;
import Factory.Factorable;

import java.util.Scanner;

import javax.xml.parsers.FactoryConfigurationError;

import static MenusMetod.MenuPrints.*;

public class Menu
{
    public static void main(String[] args) 
    {
        Factorable[] fArr = null;
        Factorable f = null;

        Scanner scan = new Scanner(System.in);
        String menuItem;
        do {
            System.out.print(LINE +
                    "РАБОТА С БАЗОЙ:\n" +
                    LINE +
                    " 1 -- вывести полную информацию базы\n" +
                    LINE +
                    " 2 -- создать базу\n" +
                    " 3 -- задание элемента базы\n" +
                    LINE +
                    " 4 -- найти в базе объекты,\n" +
                    "      первый функциональный метод которых возвращают одинаковый результат,\n" +
                    "      поместить такие объекты в массив\n" +
                    " 5 -- найти в базе объекты,\n" +
                    "      второй функциональный метод которых возвращают одинаковый результат,\n" +
                    "      поместить такие объекты в массив\n" +
                    " 6 -- разбить базу на два массива,\n" +
                    "      в которых будут храниться однотипные элементы\n" +
                    LINE +
                    " 7 -- считать базу из байтового потока\n" +
                    " 8 -- считать базу из текстового потока\n" +
                    " 9 -- десериализовать базу\n" +
                    LINE +
                    " 10 -- записать базу в байтовый поток\n" +
                    "11 -- записать базу в символьный поток\n" +
                    "12 -- сериализовать базу\n" +
                    LINE +
                    LINE +
                    "РАБОТА С ОБЪЕКТОМ:\n" +
                    LINE +
                    "13 -- показать содержимое объекта\n" +
                    LINE +
                    "14 -- создать и заполнить объект Seriesable\n" +
                    "15 -- считать из байтового потока\n" +
                    "16 -- считать из текстового потока\n" +
                    "17 -- десериализовать объект\n" +
                    LINE +
                    "18 -- записать объект в байтовый поток\n" +
                    "19 -- записать объект в символьный поток\n" +
                    "20 -- сериализовать объект\n" +
                    LINE +
                    LINE +
                    "0 -- выйти\n" +
                    LINE +
                    "выбор ... ");
            menuItem = scan.nextLine();
            switch (menuItem) {
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
                    printSetElOfArr(fArr);
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

                case "7":
                    printTask(" 7 -- считать базу из байтового потока");
                    try {
                        fArr = printInputBytesAsFactArr();
                    } catch (NullFactorableObjectException exc) {
                        System.err.println(exc.getMessage());
                    }
                    break;

                case "8":
                    printTask(" 8 -- считать базу из текстового потока");
                    try {
                        fArr = printReadTextAsFactArr();
                    } catch (NullFactorableObjectException exc) {
                        System.err.println(exc.getMessage());
                    }
                    break;

                case "9":
                    printTask(" 9 -- десериализовать базу");
                    try {
                        fArr = printDeserializeFactArr();
                    } catch (NullFactorableObjectException exc) {
                        System.err.println(exc.getMessage());
                    }
                    break;

                case "10":
                    printTask(" 10 -- записать базу в байтовый поток");
                    printOutputFactArrAsBytes(fArr);
                    break;

                case "11":
                    printTask("11 -- записать базу в символьный поток");
                    printWriteFactArrAsText(fArr);
                    break;

                case "12":
                    printTask("12 -- сериализовать базу");
                    printSerializeFactArr(fArr);
                    break;
                // endregion

                // region РАБОТА С ОБЪЕКТОМ
                case "13":
                    printTask("13 -- показать содержимое объекта");
                    System.out.println(f);
                    break;

                case "14":
                    printTask("14 -- создать и заполнить объект Seriesable");
                    f = printGetAndSetFact();
                    break;

                case "15":
                    printTask("15 -- считать из байтового потока");
                    try {
                        f = printInputBytesAsFact();
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
                // endregion
                default:
                    break;
            }
            printExit();
            System.out.println();
        } while (!menuItem.equals("0"));
        scan.close();
    }
 
}