/**
 * Created by Kevin Quinn on 25/02/2020.
 */

package cm;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class QuinnKevinTestTask2{
    //cm.Rate Constructor Tests
    Period periodOne = new Period(7, 10);
    Period periodTwo = new Period(2, 4);
    Period periodThree = new Period(8, 12);
    ArrayList<Period> normalPeriods = new ArrayList<>();
    ArrayList<Period> reducedPeriods = new ArrayList<>();
    BigDecimal normalRate = new BigDecimal("5");
    BigDecimal reducedRate = new BigDecimal("2");

    //RATE CONSTRUCTOR TESTS

    @Test
    public void successScenario(){
        reducedPeriods.clear();
        normalPeriods.clear();

        reducedPeriods.add(periodTwo);
        normalPeriods.add(periodOne);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

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
        normalPeriods.add(new Period(3,7));
        normalPeriods.add(new Period(4,8));
        normalPeriods.add(new Period(5,9));
        reducedPeriods.add(new Period(1,2));

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalReducedPeriodsOverlap(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(periodOne);
        reducedPeriods.add(periodThree);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalAndReducedPeriodsOverlap(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(3,7));
        reducedPeriods.add(new Period(2,4));
        reducedPeriods.add(new Period(5,8));

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalPeriodsNull(){
        reducedPeriods.clear();
        normalPeriods.clear();

        reducedPeriods.add(periodOne);
        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void reducedPeriodsNull(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(periodOne);
        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, null, normalPeriods );

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalRateNull(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(periodOne);
        normalPeriods.add(periodThree);
        reducedPeriods.add(periodTwo);

        Rate myRate = new Rate(CarParkKind.STAFF, null, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void reducedRateNull(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(periodOne);
        normalPeriods.add(periodThree);
        reducedPeriods.add(periodTwo);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, null, reducedPeriods, normalPeriods);

    }

    @Test
    public void normalAndReducedRateCanBeEqual(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(3,5));
        reducedPeriods.add(new Period(5,10));
        normalRate = BigDecimal.valueOf(1);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalRateNegative(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(periodOne);
        normalPeriods.add(periodThree);
        reducedPeriods.add(periodTwo);
        normalRate = BigDecimal.valueOf(-1);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void reducedRateNegative(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(periodOne);
        normalPeriods.add(periodThree);
        reducedPeriods.add(periodTwo);
        normalRate = BigDecimal.valueOf(1);
        reducedRate = BigDecimal.valueOf(-1);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    @Test(expected = IllegalArgumentException.class)
    public void normalRateLessThanReducedRate(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(periodOne);
        normalPeriods.add(periodThree);
        reducedPeriods.add(periodTwo);
        normalRate = BigDecimal.valueOf(2);
        reducedRate = BigDecimal.valueOf(5);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

    }

    //CALCULATE METHOD TESTS
    @Test
    public void calculateMethodTestOne(){
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(3,5));
        reducedPeriods.add(new Period(5,10));
        normalRate = BigDecimal.valueOf(5);
        reducedRate = BigDecimal.valueOf(2);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,6);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(new BigDecimal(12),result);
    }

    @Test
    public void calculateMethodVisitorFreeTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(3,5));
        reducedPeriods.add(new Period(5,10));
        normalRate = BigDecimal.valueOf(5);
        reducedRate = BigDecimal.valueOf(2);

        Rate myRate = new Rate(CarParkKind.VISITOR, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,3);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(new BigDecimal(0),result);
    }

    @Test
    public void calculateMethodVisitorReductionTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(1,6));
        reducedPeriods.add(new Period(6,10));
        normalRate = BigDecimal.valueOf(10);
        reducedRate = BigDecimal.valueOf(2);

        Rate myRate = new Rate(CarParkKind.VISITOR, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,3);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(new BigDecimal(1),result);
    }

    @Test
    public void calculateMethodManagementMinPayTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(1,6));
        reducedPeriods.add(new Period(6,10));
        normalRate = BigDecimal.valueOf(2);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.MANAGEMENT, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,3);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(BigDecimal.valueOf(3.0),result);
    }

    @Test
    public void calculateMethodManagementNormalPayTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(1,6));
        reducedPeriods.add(new Period(6,10));
        normalRate = BigDecimal.valueOf(2);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.MANAGEMENT, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,4);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(BigDecimal.valueOf(4),result);
    }

    @Test
    public void calculateMethodStudentLessThanTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(1,6));
        reducedPeriods.add(new Period(6,10));
        normalRate = BigDecimal.valueOf(2);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.STUDENT, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,4);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(BigDecimal.valueOf(4),result);
    }

    @Test
    public void calculateMethodStudentMoreThanTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(1,6));
        reducedPeriods.add(new Period(6,10));
        normalRate = BigDecimal.valueOf(5.50);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.STUDENT, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,4);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(BigDecimal.valueOf(9.625),result);
    }

    @Test
    public void calculateMethodStaffMoreThanTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(1,6));
        reducedPeriods.add(new Period(6,10));
        normalRate = BigDecimal.valueOf(10);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,4);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(BigDecimal.valueOf(16.0),result);
    }

    @Test
    public void calculateMethodStaffLessThanTest() {
        reducedPeriods.clear();
        normalPeriods.clear();

        normalPeriods.add(new Period(1,6));
        reducedPeriods.add(new Period(6,10));
        normalRate = BigDecimal.valueOf(2);
        reducedRate = BigDecimal.valueOf(1);

        Rate myRate = new Rate(CarParkKind.STAFF, normalRate, reducedRate, reducedPeriods, normalPeriods);

        Period stay = new Period(2,4);
        BigDecimal result = myRate.calculate(stay);

        assertEquals(BigDecimal.valueOf(4),result);
    }
}
