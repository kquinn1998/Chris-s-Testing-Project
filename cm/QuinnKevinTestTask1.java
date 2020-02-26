package cm;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class QuinnKevinTestTask1{
    //cm.Rate Constructor Tests
    Period periodOne = new Period(7,10);
    Period periodTwo = new Period(20,24);
    Period periodThree = new Period(8, 12);
    ArrayList<Period> normalPeriods = new ArrayList<>();
    ArrayList<Period> reducedPeriods = new ArrayList<>();

    @Test(expected = IllegalArgumentException.class)
    public void reducePeriodsOverlap(){
        reducedPeriods.add(periodOne);
        reducedPeriods.add(periodThree);
        normalPeriods.add(periodTwo);

        Rate myRate = new Rate(CarParkKind.STAFF, 5, 2, reducedPeriods, normalPeriods)

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalPeriodsOverlap(){
        normalPeriods.add(periodOne);
        normalPeriods.add(periodThree);
        reducedPeriods.add(periodTwo);

        Rate myRate = new Rate(CarParkKind.STAFF, 5, 2, reducedPeriods, normalPeriods)

    }

}
