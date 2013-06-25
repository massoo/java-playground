package be.demo.bad.examples;

import be.demo.bad.exception.TechnicalException;

/**
 * User: massoo
 */
public final class LoadNativeLibrary {

    // native operation is totally shielded
    private native void nativeOperation(byte[] data, int offset, int len);

    static {
        // load native library in static initializer of class
        System.loadLibrary("OurNativeLibrary");
    }

    // wrapper method performs SecurityManager and input validation checks
    public void doOperation(byte[] data, int offset, int len) {

        // permission needed to invoke native method
        // check security manager HERE !!

        if (data == null) {
            throw new TechnicalException("No bytes given to operation: Nullpointer");
        }

        // give a copy of the real data
        data = data.clone();

        // validate input
        if ((offset < 0) || (len < 0) || (offset > (data.length-len))) {
            throw new IllegalArgumentException();
        }

        nativeOperation(data, offset, len);
    }

}
