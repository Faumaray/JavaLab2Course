package Factories;
import FactoryArea.Factorable;

public interface FactorableFactory {
    Factorable createInstance();

    Factorable createInstance(String factorName, int Rate, int numOfMonths);
}
