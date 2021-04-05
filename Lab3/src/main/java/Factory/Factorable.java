package Factory;
//Interface для описания сигнатур методов
public interface Factorable 
{
    int MIN_LEN_OF_AR = 1;// Правильное значение = 12. Данное значение является тестовым
    int MIN_NUM_OF_ELS_OF_INDUSTRY = 1;
    int MIN_NUM_OF_RATE = 100;
    int MIN_VALUE_OF_OUTPUT = 0;
    double MIN_VALUE_OF_DEFECT = 0;
    double MAX_VALUE_OF_DEFECT = 1;
    public void setEl(int index, int value);
    public void setDefectOfEl(int index, double value);
    public void setRate(int value);
    public void setName(String newname);

    public int getNumberOfEls();
    public int getEl(int index);
    public double getDefect(int index);
    public int getRate();
    public String getName();

    public int excess();
    public int usefulexcess();
}
