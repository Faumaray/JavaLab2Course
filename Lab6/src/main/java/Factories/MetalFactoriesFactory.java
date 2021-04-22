package Factories;

import FactoryArea.Factorable;
import FactoryArea.MetalFactory;

public class MetalFactoriesFactory implements FactorableFactory 
{

    @Override
    public Factorable createInstance() {
        return new MetalFactory();
    }

    @Override
    public Factorable createInstance(String factorName, int Rate, int numOfMonths) {
        return new MetalFactory(factorName, Rate, numOfMonths);
    }
    
}
