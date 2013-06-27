package be.demo.good.bean;

import be.demo.utility.IntegerOperationUtility;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * User: massoo
 */
public class IntegerOperationsTestCase {

    public static final int standardValue = 1000;

    @Test
    public void testDetectIntegerOverflow() {
        BigInteger intValue = BigInteger.valueOf(standardValue);
        BigInteger intOverflow = BigInteger.valueOf(Integer.MAX_VALUE + standardValue);

        try {
            IntegerOperationUtility.detectIntegerOverflow(intOverflow);
            Assert.fail("ArithmeticException expected due to an Integer overflow");
        } catch (ArithmeticException ex) {
            // everything is going as planned
        }

        IntegerOperationUtility.detectIntegerOverflow(intValue);
    }

    @Test
    public void testConvertToInteger() {
        try {
            IntegerOperationUtility.convertToInteger(new Long(Integer.MAX_VALUE) + standardValue);
            Assert.fail("ArithmeticException expected due to an Integer overflow");
        } catch (ArithmeticException ex) {
            // everything is going as planned
        }

        int convertedInteger = IntegerOperationUtility.convertToInteger(standardValue);

        Assert.assertThat(standardValue, equalTo(convertedInteger));
    }

    @Test
    public void testSafeAdd() {
        try {
            IntegerOperationUtility.safeAdd(Integer.MAX_VALUE, standardValue);
            Assert.fail("ArithmeticException expected due to an Integer overflow");
        } catch (ArithmeticException ex) {
            // everything is going as planned
        }

        int calculatedInteger = IntegerOperationUtility.safeAdd(standardValue, standardValue);
        Assert.assertThat(standardValue + standardValue, equalTo(calculatedInteger));
    }

    @Test
    public void testSafeSubtract() {
        try {
            IntegerOperationUtility.safeSubtract(Integer.MIN_VALUE, standardValue);
            Assert.fail("ArithmeticException expected due to an Integer overflow");
        } catch (ArithmeticException ex) {
            // everything is going as planned
        }

        int calculatedInteger = IntegerOperationUtility.safeSubtract(standardValue, standardValue);
        Assert.assertThat(standardValue - standardValue, equalTo(calculatedInteger));
    }

    @Test
    public void testSafeMultiply() {
        try {
            IntegerOperationUtility.safeMultiply(Integer.MAX_VALUE, standardValue);
            Assert.fail("ArithmeticException expected due to an Integer overflow");
        } catch (ArithmeticException ex) {
            // everything is going as planned
        }

        int calculatedInteger = IntegerOperationUtility.safeMultiply(standardValue, standardValue);
        Assert.assertThat(standardValue * standardValue, equalTo(calculatedInteger));
    }

    @Test
    public void testSafeDivide() {
        try {
            IntegerOperationUtility.safeDivide(Integer.MIN_VALUE, -1);
            Assert.fail("ArithmeticException expected due to an Integer overflow");
        } catch (ArithmeticException ex) {
            // everything is going as planned
        }

        int calculatedInteger = IntegerOperationUtility.safeDivide(standardValue, standardValue);
        Assert.assertThat(standardValue / standardValue, equalTo(calculatedInteger));
    }

    @Test
    public void testSafeNegate() {
         try {
             IntegerOperationUtility.safeNegate(Integer.MIN_VALUE);
             Assert.fail("ArithmeticException expected due to an Integer overflow");
         } catch (ArithmeticException ex) {
             // everything is going as planned
         }

        int calculatedInteger = IntegerOperationUtility.safeNegate(-standardValue);
        Assert.assertThat(standardValue,equalTo(calculatedInteger));
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testUnsafeAddToIntegerArray() {
        Integer[] intArray = new Integer[3];
        intArray[0] = 1;
        intArray[1] = 2;
        intArray[2] = 3;

        // causes errors
        intArray[3] = 4;
    }

    @Test
    public void testSafeAddToIntegerArray() {
        Integer[] intArray = new Integer[0];

        intArray = safeAddToIntegerArray(intArray, 1);
        intArray = safeAddToIntegerArray(intArray, 2);
        intArray = safeAddToIntegerArray(intArray, 3);
        intArray = safeAddToIntegerArray(intArray, 4);

        MatcherAssert.assertThat(4, equalTo(intArray.length));
    }

    @Test
    public void testSafeBestPracticeIntegerArray() {
        List<Integer> intArray = new ArrayList<Integer>();
        intArray.add(1);
        intArray.add(2);
        intArray.add(3);
        intArray.add(4);

        MatcherAssert.assertThat(4, equalTo(intArray.size()));
    }

    private static Integer[] safeAddToIntegerArray(Integer[] array, Integer element) {

        Integer[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = element;

        return newArray;

    }

}
