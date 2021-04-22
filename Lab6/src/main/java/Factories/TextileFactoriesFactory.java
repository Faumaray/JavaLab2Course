package Factories;

import FactoryArea.Factorable;
import FactoryArea.TextileFactory;

public class TextileFactoriesFactory implements FactorableFactory
{
    @Override
    public Factorable createInstance() {
        return new TextileFactory();
    }

    @Override
    public Factorable createInstance(String factorName, int Rate, int numOfMonths) {
        return new TextileFactory(factorName, Rate, numOfMonths);
    }
}
