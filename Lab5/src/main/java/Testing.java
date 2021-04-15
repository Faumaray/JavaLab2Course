import Factory.MetalFactory;
import Factory.TextileFactory;
import Factory.Factorable;

import java.util.Random;



public class Testing {


    static Factorable[] getFarrThenSetWithFiveElsAutomatically() {
        Factorable] farr = getFarrWithFiveEls();
        setElsInFarrWithFiveEls(farr);

        System.out.println("массив успешно создан и заполнен");

        return farr;
    }
}
