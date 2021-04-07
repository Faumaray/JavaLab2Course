package Ray;

import java.util.Random;

public class Guesser 
{
    private int minNumb;
    private int maxNumb;
    public Guesser()
    {}
    public Guesser(int minNumb, int maxNumb)
    {
        if (maxNumb < minNumb) {
            throw new IllegalArgumentException("максимальное число больше минимального");
        }

        this.minNumb = minNumb;
        this.maxNumb = maxNumb;
    }
    public int getMinNumb() 
    {
        return minNumb;
    }
    public int getMaxNumb() 
    {
        return maxNumb;
    }
    public void setMinNumb(int newMin) 
    {
        minNumb = newMin;
    }
    public void setMaxNumb(int newMax) 
    {
        maxNumb = newMax;
    }
    public int guess()
    {
        Random rand = new Random();
        return minNumb + rand.nextInt((((maxNumb+minNumb)/2)-minNumb)+1);
    }
}
