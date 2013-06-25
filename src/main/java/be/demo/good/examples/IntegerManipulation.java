package be.demo.good.examples;

import java.math.BigInteger;

/**
 * User: massoo
 */
public class IntegerManipulation {

    private static final BigInteger bigMaxInt = BigInteger.valueOf(Integer.MAX_VALUE);
    private static final BigInteger bigMinInt = BigInteger.valueOf(Integer.MIN_VALUE);
    private static final String INTEGER_OVERFLOW = "Integer overflow";

    // detecting integer overflow with bigIntegers
    public static BigInteger detectIntegerOverflow(BigInteger value) throws ArithmeticException {

        if (value.compareTo(bigMaxInt) == 1 || value.compareTo(bigMinInt) == -1) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return value;
    }


    // safe integer conversion --> can be used for long as wel
    public static int convertToInteger(long value) throws ArithmeticException {

        if ((value < Integer.MIN_VALUE) || (value > Integer.MAX_VALUE)) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return (int) value;
    }

    public static int safeAdd(int left, int right) throws ArithmeticException {

        if (right > 0 ? left > Integer.MAX_VALUE - right
                : left < Integer.MIN_VALUE - right) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return left + right;
    }

    public static int safeSubtract(int left, int right) throws ArithmeticException {

        if (right > 0 ? left < Integer.MIN_VALUE + right
                : left > Integer.MAX_VALUE + right) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return left - right;

    }

    public static int safeMultiply(int left, int right) throws ArithmeticException {

        if (right > 0 ? left > Integer.MAX_VALUE / right
                || left < Integer.MIN_VALUE / right
                : (right < -1 ? left > Integer.MIN_VALUE / right
                || left < Integer.MAX_VALUE / right
                : right == -1
                && left == Integer.MIN_VALUE)) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return left * right;

    }

    public static int safeDivide(int left, int right) throws ArithmeticException {

        if ((left == Integer.MIN_VALUE) && (right == -1)) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return left / right;

    }

    public static int safeNegate(int a) throws ArithmeticException {

        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return -a;
    }

    public static int safeAbs(int a) throws ArithmeticException {

        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException(INTEGER_OVERFLOW);
        }

        return Math.abs(a);
    }
}
