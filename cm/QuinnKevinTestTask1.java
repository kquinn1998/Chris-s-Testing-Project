/**
 * Created by Kevin Quinn on 17/02/2020.
 */

package cm;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class QuinnKevinTestTask1{
    //cm.Rate Constructor Tests
    Period periodOne = new Period(7, 10);
    Period periodTwo = new Period(20, 23);
    Period periodThree = new Period(8, 12);
    ArrayList<Period> normalPeriods = new ArrayList<>();
    ArrayList<Period> reducedPeriods = new ArrayList<>();
    BigDecimal normalRate = new BigDecimal("5");
    BigDecimal reducedRate = new BigDecimal("2");

    @Test(expected = IllegalArgumentException.class)
    public void reducePeriodsOverlap(){
        reducedPeriods.clear();
        normalPeriods.clear();
        reducedPeriods.add(periodOne);
        reducedPeriods.add(periodThree);
        normalPeriods.add(periodTwo);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalPeriodsOverlap(){
        reducedPeriods.clear();
        normalPeriods.clear();
        normalPeriods.add(periodOne);
        normalPeriods.add(periodThree);
        reducedPeriods.add(periodTwo);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

}
