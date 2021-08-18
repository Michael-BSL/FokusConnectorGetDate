package com.bsl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Contains static utility methods which might be very handy when it comes
 * to handling strings...
 * <p/>
 * All methods have been optimized ovcer time.
 *
 * @author H�kan Gustavsson
 * @version $Id: 1.0 $
 */
public class StringUtil {
    //--------------------------------------------------------- Private Members
    private static final String s_sNull = "null";

    //--------------------------------------------------------------- append(...)

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2) {
        int i =
                strlen(s1) +
                        strlen(s2);

        return ensureCapacity(buf, i)
                .append(s1).append(s2);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3);

        return ensureCapacity(buf, i).append(s1).append(s2).append(s3);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4);

        return ensureCapacity(buf, i).append(s1).append(s2).append(s3).append(s4);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5);

        return ensureCapacity(buf, i).append(s1).append(s2).append(s3).append(s4).append(s5);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9);
    }


    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10);
    }


    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11);
    }


    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11, String s12) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11) +
                        strlen(s12);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12);
    }


    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11, String s12,
                                            String s13) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11) +
                        strlen(s12) +
                        strlen(s13);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13);
    }


    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11, String s12,
                                            String s13, String s14) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11) +
                        strlen(s12) +
                        strlen(s13) +
                        strlen(s14);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14);
    }


    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11, String s12,
                                            String s13, String s14, String s15) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11) +
                        strlen(s12) +
                        strlen(s13) +
                        strlen(s14) +
                        strlen(s15);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11, String s12,
                                            String s13, String s14, String s15,
                                            String s16) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11) +
                        strlen(s12) +
                        strlen(s13) +
                        strlen(s14) +
                        strlen(s15) +
                        strlen(s16);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11, String s12,
                                            String s13, String s14, String s15,
                                            String s16, String s17) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11) +
                        strlen(s12) +
                        strlen(s13) +
                        strlen(s14) +
                        strlen(s15) +
                        strlen(s16) +
                        strlen(s17);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17);
    }

    /**
     * Appends the specified strings to the specified StringBuilder.
     */
    public static final StringBuilder append(StringBuilder buf,
                                            String s1, String s2, String s3,
                                            String s4, String s5, String s6,
                                            String s7, String s8, String s9,
                                            String s10, String s11, String s12,
                                            String s13, String s14, String s15,
                                            String s16, String s17, String s18) {
        int i =
                strlen(s1) +
                        strlen(s2) +
                        strlen(s3) +
                        strlen(s4) +
                        strlen(s5) +
                        strlen(s6) +
                        strlen(s7) +
                        strlen(s8) +
                        strlen(s9) +
                        strlen(s10) +
                        strlen(s11) +
                        strlen(s12) +
                        strlen(s13) +
                        strlen(s14) +
                        strlen(s15) +
                        strlen(s16) +
                        strlen(s17) +
                        strlen(s18);

        return ensureCapacity(buf, i)
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18);
    }

    //-------------------------------------------------------------------------

    /**
     * Returns <tt>true</tt> <i>iff</i> the specified <tt>Object</tt> is an,
     * array, otherwise <tt>false</tt>.
     * @param aArray The object to check whether it's an array or not.
     * @return <tt>true</tt> <i>iff</i> the specified <tt>Object</tt> is an,
     * array, otherwise <tt>false</tt>.
     */
    public static final boolean checkObjectIsArray(final Object aArray) {
        return aArray.getClass().isArray();
    }

    //--------------------------------------------------------------- concat(...)

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2) {
        return new StringBuilder(strlen(s1) +
                strlen(s2))
                .append(s1).append(s2).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3))
                .append(s1).append(s2).append(s3).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4))
                .append(s1).append(s2).append(s3).append(s4).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5))
                .append(s1).append(s2).append(s3).append(s4).append(s5).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9).toString();
    }


    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).toString();
    }


    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).toString();
    }


    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12).toString();
    }


    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).toString();
    }


    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).toString();
    }


    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).toString();
    }


    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18,
                                      String s19) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18) +
                strlen(s19))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18)
                .append(s19).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18,
                                      String s19, String s20) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18) +
                strlen(s19) +
                strlen(s20))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18)
                .append(s19).append(s20).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18,
                                      String s19, String s20, String s21) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18) +
                strlen(s19) +
                strlen(s20) +
                strlen(s21))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18)
                .append(s19).append(s20).append(s21).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18,
                                      String s19, String s20, String s21,
                                      String s22) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18) +
                strlen(s19) +
                strlen(s20) +
                strlen(s21) +
                strlen(s22))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18)
                .append(s19).append(s20).append(s21)
                .append(s22).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18,
                                      String s19, String s20, String s21,
                                      String s22, String s23) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18) +
                strlen(s19) +
                strlen(s20) +
                strlen(s21) +
                strlen(s22) +
                strlen(s23))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18)
                .append(s19).append(s20).append(s21)
                .append(s22).append(s23).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18,
                                      String s19, String s20, String s21,
                                      String s22, String s23, String s24) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18) +
                strlen(s19) +
                strlen(s20) +
                strlen(s21) +
                strlen(s22) +
                strlen(s23) +
                strlen(s24))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18)
                .append(s19).append(s20).append(s21)
                .append(s22).append(s23).append(s24).toString();
    }

    /**
     * Concatenates the specified strings into one.
     */
    public static final String concat(String s1, String s2, String s3,
                                      String s4, String s5, String s6,
                                      String s7, String s8, String s9,
                                      String s10, String s11, String s12,
                                      String s13, String s14, String s15,
                                      String s16, String s17, String s18,
                                      String s19, String s20, String s21,
                                      String s22, String s23, String s24,
                                      String s25) {
        return new StringBuilder(strlen(s1) +
                strlen(s2) +
                strlen(s3) +
                strlen(s4) +
                strlen(s5) +
                strlen(s6) +
                strlen(s7) +
                strlen(s8) +
                strlen(s9) +
                strlen(s10) +
                strlen(s11) +
                strlen(s12) +
                strlen(s13) +
                strlen(s14) +
                strlen(s15) +
                strlen(s16) +
                strlen(s17) +
                strlen(s18) +
                strlen(s19) +
                strlen(s20) +
                strlen(s21) +
                strlen(s22) +
                strlen(s23) +
                strlen(s24) +
                strlen(s25))
                .append(s1).append(s2).append(s3)
                .append(s4).append(s5).append(s6)
                .append(s7).append(s8).append(s9)
                .append(s10).append(s11).append(s12)
                .append(s13).append(s14).append(s15)
                .append(s16).append(s17).append(s18)
                .append(s19).append(s20).append(s21)
                .append(s22).append(s23).append(s24)
                .append(s25).toString();
    }

    /**
     * Returns the length of the specified string. If the specified string
     * is <tt>null</tt>, 4 is returned, as a string representation of
     * <tt>null</tt> is 4 characters long.
     * @param s The string to get the length of.
     * @return The length of the string.
     */
    private static final int strlen(final String s) {
        return s == null ? 4 : s.length();
    }

    //---------------------------------------------------------------------------

    /**
     * Used to format the memory size.
     */
    private static final NumberFormat s_oB2KBFormat = new DecimalFormat("###,###,###,###,##0.0");

    /**
     * Returns the specified number of bytes formatted as kilobytes. Passing the
     * primitive long <tt>50000L</tt> would return the string <tt>48,8</tt> as
     * 1024 bytes is one kilobyte. Each thousand kilobyte will also be space-
     * separated, i.e; passing <tt>50000000L</tt> returns <tt>48�828,1</tt>.
     *
     * @param b The bytes to format into kilobytes.
     * @return The specified number of bytes formatted as kilobytes.
     */
    public static final String byte2kilobyte(long b) {
        return s_oB2KBFormat.format((float) b / 1024F);
    }

    /**
     * Ensures the capacity of the specified StringBuilder to handle the additional
     * specified number of characters as well. If the passed StringBuilder is
     * <code>null</code>; a new StringBuilder is created.
     *
     * @param buf The StringBuilder to ensure the capacity of.
     * @param len The length to ensure the capacity for.
     * @return A reference to the StringBuilder with the ensured capacity.
     */
    public static final StringBuilder ensureCapacity(StringBuilder buf, int len) {
        if (buf == null) {
            buf = new StringBuilder(len);
        } else {
            buf.ensureCapacity(len + buf.length());
        }
        return buf;
    }

    /**
     * Returns the stacktrace of the specified <tt>Throwable</tt> as a string.
     * <p/>
     * If the specified <tt>Throwable</tt> is <tt>null</tt>, a string with the
     * content null is returned, not the <tt>null</tt> object reference.
     *
     * @param t The <tt>Throwable</tt> to get the stacktrace of.
     * @return The stacktrace of the specified <tt>Throwable</tt> as a string.
     * @deprecated Use getStackTraceAsString() instead
     */
    public static final String exception2string(final Throwable t) {
        if (t == null) {
            return "null";
        }
        final StringWriter sw = new StringWriter(1024);
        final PrintWriter  pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    public static String getStackTraceAsString(Throwable throwable) {
    	//This to supercede the method it calls as its name describes what it does much better
    	return exception2string(throwable);
    }
    
    /**
     * Gets the exception's stack trace as a String
     * 
     * @return
     */
    /*
     //Requires Java 1.5 on...
    public static String getStackTraceAsString(Throwable throwable) {
    	
    	final StringBuilder stackTraceString = new StringBuilder("");
        //stackTraceString.append(throwable.toString());
        final String NEW_LINE = System.getProperty("line.separator");
        stackTraceString.append(NEW_LINE);

        //add each element of the stack trace to the result
        //1.5 on:
        //for (StackTraceElement element : throwable.getStackTrace() ){
        //1.4:
        StackTraceElement[] stackElements = throwable.getStackTrace();
        for (int i = 0; i < stackElements.length; i++) {
        	StackTraceElement element = stackElements[i];
          //end 1.4
          stackTraceString.append(element);
          stackTraceString.append(NEW_LINE);
        }
        
        return stackTraceString.toString();
    } // end getStackTraceAsString()
    */

    public static final String getShortClassname(final Class aClass) {
        final String[] s = StringUtil.split(aClass.getName(), ".");
        return s[s.length - 1];
    }
    
    /**
     * Gets the current thread's stack trace, as a String, without an exception being raised
     * 
     * @return
     */
    public static String getStackTraceAsString() {
    	//This exception is not thrown. I'm only doing it to get the 
    	//stack trace. If anyone knows a better way then do that instead.
    	//**** THIS DOESN'T SEEM TO RETURN ANYTHING VERY USEFUL ****
    	Exception e = new Exception();
    	e.fillInStackTrace();
        return getStackTraceAsString(e);
    }

    /**
     * Returns <tt>true</tt> iff the specified string <tt>s</tt> constist only of
     * alphanumeric characters (letter and digits).
     * <p/>
     * If the specified string is <tt>null</tt>, <tt>false</tt> is returned.
     *
     * @param s The string to validate.
     * @return <tt>true</tt> iff the specified string <tt>s</tt> constist only of
     *         alphanumeric characters (letter and digits).
     */
    public static final boolean isAlpha(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0, n = s.length(); i < n; ++i) {
            final char ch = s.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns <tt>true</tt> iff the specified string <tt>s</tt> consist only of
     * digits.
     * <p/>
     * If the specified string is <tt>null</tt>, <tt>false</tt> is returned.
     *
     * @param s The string to validate.
     * @return <tt>true</tt> iff the specified string <tt>s</tt> consist only of
     *         digits.
     */
    public static final boolean isDigits(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0, n = s.length(); i < n; ++i) {
            final char ch = s.charAt(i);
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns <tt>true</tt> iff the specified string <tt>s</tt> consist only of
     * letters.
     * <p/>
     * If the specified string is <tt>null</tt>, <tt>false</tt> is returned.
     *
     * @param s The string to validate.
     * @return <tt>true</tt> iff the specified string <tt>s</tt> consist only of
     *         letters.
     */
    public static final boolean isLetters(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0, n = s.length(); i < n; ++i) {
            final char ch = s.charAt(i);
            if (!Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first index in first parameter of any of the characters in the
     * second parameter. For example, calling
     * <code>StringUtil.lastIndexOf("abcdefghijklmnopqrstuvwxyz", "aeiouy")</code>
     * will return 0, since it's the index of the character 'a'.
     *
     * @param s     The string in which to find the first index of a list of
     *              characters.
     * @param chars The characters to look for in the first parameter.
     * @return The first index of the first encountered character, or -1 if none
     *         was encountered.
     */
    public static final int firstIndexOf(String s, String chars) {
        char[] val = s.toCharArray();
        char[] ch = chars.toCharArray();

        for (int i = 0, len = val.length; i < len; i++) {
            for (int j = 0, n = ch.length; j < n; j++) {
                if (val[i] == ch[j]) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns <tt>true</tt> <i>iff</i> the specified <tt>Object</tt> is not <tt>null</tt>
     * and is an array; otherwise <tt>false</tt>.
     * @param aItem The <tt>Object</tt> to validate.
     * @return <tt>true</tt> <i>iff</i> the specified <tt>Object</tt> is not <tt>null</tt>
     * and is an array; otherwise <tt>false</tt>.
     */
    public static boolean isNonNullArray(final Object aItem){
        return aItem != null && checkObjectIsArray(aItem);
    }

    /**
     * Returns <code>true</code> if and only if the specified string is
     * <code>null</code> or if its' length is zero.
     *
     * @param s The string to check.
     * @return <code>true</code> if and only if the specified string is
     *         <code>null</code> or if its' length is zero, otherwise <code>false</code>.
     */
    public static final boolean isNullOrEmpty(final String s) {
        return s == null ? true : s.length() == 0;
    }

    public static final boolean isNotNullOrEmpty(final String s) {
        return !isNullOrEmpty(s);
    }

    /**
     * Joins the passed string array into a flat string.
     *
     * @param data The array to join
     * @return A string.
     */
    public static final String join(final String[] data) {
        if (data == null || data.length == 0) {
            return "";
        }
        return join(data, 0);
    }

    /**
     * Joins the passed string array into a flat string, starting with the
     * specified index.
     *
     * @param data  The array to join.
     * @param index The start index.
     * @return A string.
     */
    public static final String join(final String[] data, final int index) {
        if (data == null || data.length == 0) {
            return "";
        }
        return join(data, index, null);
    }

    /**
     * Joins the passed string array into a flat string. The separator is
     * inserted between the former array elements.
     *
     * @param data      The array to join.
     * @param separator The separator to insert bewteen the array elements.
     * @return A string.
     */
    public static final String join(final String[] data, final String separator) {
        if (data == null || data.length == 0) {
            return "";
        }
        if (data.length == 1) {
            return data[0];
        }
        return join(data, 0, separator);
    }

    /**
     * Joins the passed string array (starting with the specified index) into a
     * flat string. The separator is inserted between the former array elements.
     *
     * @param data      The array to join.
     * @param index     The start index.
     * @param separator The separator to insert bewteen the array elements.
     * @return A string.
     */
    public static final String join(final String[] data,
                                    final int index,
                                    final String separator) {
        return join(data, index, -1, separator);
        /*
        if (data == null || data.length == 0) {
          return "";
        }
        if (index < 0 || index > data.length - 1) {
          return "";
        }
        if (data.length == 1) {
          return data[0];
        }
        StringBuilder sb = new StringBuilder(80);
        if (separator == null || separator.length() == 0) {
          for (int i = index, n = data.length; i < n; i++) {
            sb.append(data[i]);
          }
        } else {
          for (int i = index, n = data.length; i < n; i++) {
            if (i != index) {
              sb.append(separator);
            }
            sb.append(data[i]);
          }
        }
        return sb.toString();
        */
    }

    /**
     * Joins the passed string array (starting with the specified index and
     * ending with the specified index) into a flat string.
     * The separator is inserted between the former array elements.
     * <p/>
     * The following code
     * <pre><code>
     * String[] s = {"a", "b", "c", "d", "e", "f"};
     * String   p = "-";
     * System.out.println(StringUtil.join(s, p));
     * System.out.println(StringUtil.join(s, 1, p));
     * System.out.println(StringUtil.join(s, 1, 4, p));
     * System.out.println(StringUtil.join(s, 0, 3, p));
     * </code></pre>
     * generates the following output:
     * <pre>
     * a-b-c-d-e-f
     * b-c-d-e-f
     * b-c-d-e
     * a-b-c
     * </pre>
     * Please note that the specified end-index is included in the joint string.
     *
     * @param data       The array to join.
     * @param startIndex The start index.
     * @param len        The number of array elements to join.
     * @param separator  The separator to insert bewteen the joint array elements.
     * @return A string.
     */
    public static final String join(final String[] data,
                                    final int startIndex,
                                    final int len,
                                    final String separator) {
        /*
        if (data == null || data.length == 0) {
          return "";
        }
        if (startIndex < 0 || startIndex > data.length - 1) {
          return "";
        }
        if (len == -1) {
          len = data.length - startIndex;
        }
        if (data.length == 1) {
          return data[0];
        }
        StringBuilder sb = new StringBuilder(80);
        if (separator == null || separator.length() == 0) {
          for (int i = startIndex, n = 0; i < data.length && n < len; n++) {
            sb.append(data[i++]);
          }
        } else {
          for (int i = startIndex, n = 0; i < data.length && n < len; n++) {
            if (i != startIndex) {
              sb.append(separator);
            }
            sb.append(data[i++]);
          }
        }
        return sb.toString();
        */
        return join(new StringBuilder(1024), data, startIndex, len, separator).toString();
    }

    /**
     * Joins the passed string array (starting with the specified index and
     * ending with the specified index) into a flat string.
     * The separator is inserted between the former array elements.
     * <p/>
     * The following code
     * <pre><code>
     * String[] s = {"a", "b", "c", "d", "e", "f"};
     * String   p = "-";
     * System.out.println(StringUtil.join(s, p));
     * System.out.println(StringUtil.join(s, 1, p));
     * System.out.println(StringUtil.join(s, 1, 4, p));
     * System.out.println(StringUtil.join(s, 0, 3, p));
     * </code></pre>
     * generates the following output:
     * <pre>
     * a-b-c-d-e-f
     * b-c-d-e-f
     * b-c-d-e
     * a-b-c
     * </pre>
     * Please note that the specified end-index is included in the joint string.
     *
     * @param res        The StringBuilder into which the result is appended.
     * @param data       The array to join.
     * @param startIndex The start index.
     * @param len        The number of array elements to join.
     * @param separator  The separator to insert bewteen the joint array elements.
     * @return The passed StringBuilder reference..
     */
    public static final StringBuilder join(final StringBuilder res,
                                          final String[]     data,
                                          final int startIndex,
                                          int len,
                                          final String separator) {
        if (data == null || data.length == 0) {
            return res;
        }
        if (startIndex < 0 || startIndex > data.length - 1) {
            return res;
        }
        if (len == -1) {
            len = data.length - startIndex;
        }
        if (data.length == 1) {
            return res.append(data[0]);
        }
        if (separator == null || separator.length() == 0) {
            for (int i = startIndex, n = 0; i < data.length && n < len; n++) {
                res.append(data[i++]);
            }
        } else {
            for (int i = startIndex, n = 0; i < data.length && n < len; n++) {
                if (i != startIndex) {
                    res.append(separator);
                }
                res.append(data[i++]);
            }
        }
        return res;
    }

    /**
     * Returns the last index in first parameter of any of the characters in the
     * second parameter. For example, calling
     * <code>StringUtil.lastIndexOf("abcdefghijklmnopqrstuvwxyz", "aeiouy")</code>
     * will return 24, since it's the index of the character 'y'.
     * <p/>
     * The passed string in the example above is indeed 26 characters long, and
     * the 'y' is in the second last position, which is index 24 as the first
     * character, 'a', has index 0.
     *
     * @param s     The string in which to find the last index of a list of
     *              characters.
     * @param chars The characters to look for in the first parameter.
     * @return The last index of the first encountered character, or -1 if none
     *         was encountered.
     */
    public static final int lastIndexOf(final String s, final String chars) {
        /*
        char[] val = s.toCharArray();
        int    len = val.length;
        char[] ch  = chars.toCharArray();

        for (int i = len - 1; i > -1; i--) {
          for (int j = 0, n = ch.length; j < n; j++) {
            if (val[i] == ch[j]) {
              return i;
            }
          }
        }
        return -1;
        */
        return lastIndexOf(s, chars, s.length() - 1);
    }

    /**
     * Returns the last index in first parameter of any of the characters in the
     * second parameter. For example, calling
     * <code>StringUtil.lastIndexOf("abcdefghijklmnopqrstuvwxyz", "aeiouy")</code>
     * will return 24, since it's the index of the character 'y'.
     * <p/>
     * The passed string in the example above is indeed 26 characters long, and
     * the 'y' is in the second last position, which is index 24 as the first
     * character, 'a', has index 0.
     *
     * @param s         The string in which to find the last index of a list of
     *                  characters.
     * @param chars     The characters to look for in the first parameter.
     * @param fromIndex The starting index. If greater than the length of the
     *                  passed string, the whole string is searched. If negative, <code>-1</code>
     *                  is returned;
     * @return The last index of the first encountered character, or -1 if none
     *         was encountered.
     */
    public static final int lastIndexOf(final String s,
                                        final String chars,
                                        final int fromIndex) {
        if (fromIndex < 0) {
            return -1;
        }
        char[] val = s.toCharArray();
        int len = fromIndex > val.length ? val.length - 1 : fromIndex;
        char[] ch = chars.toCharArray();

        for (int i = len; i > -1; i--) {
            for (int j = 0, n = ch.length; j < n; j++) {
                if (val[i] == ch[j]) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Appends the specified character to the specified string until it has the
     * specified length.
     *
     * @param str The string to suffix.
     * @param ch  The character to suffix with.
     * @param len The desired length of the string.
     * @return A String
     */
    public static final String makeLength(final String str,
                                          final char ch,
                                          final int len) {
        return makeLength(str, ch, len, false);
    }

    /**
     * Pre- or suffixes the specified string with the specified character
     * until it has the specified length
     *
     * @param str     The string to prefix.
     * @param ch      The character to prefix with.
     * @param len     The desired length of the string.
     * @param bPrefix If <code>true</code> the passed string will be prefixed;
     *                if <code>false</code> it will be suffixed.
     * @return A String
     */
    public static final String makeLength(final String str,
                                          final char ch,
                                          final int len,
                                          final boolean bPrefix) {
        return makeLength(new StringBuilder(len), str, ch, len, bPrefix).toString();
    }

    /**
     * Appends the specified character to the specified string until it has the
     * specified length.
     *
     * @param sb  The StringBuilder where the result is stored.
     * @param str The string to suffix.
     * @param ch  The character to suffix with.
     * @param len The desired length of the string.
     * @return The passed StringBuilder reference.
     */
    public static final StringBuilder makeLength(final StringBuilder sb,
                                                final String str,
                                                final char ch,
                                                final int len) {
        return makeLength(sb, str, ch, len, false);
    }

    /**
     * Pre- or suffixes the specified string with the specified character
     * until it has the specified length.
     *
     * @param sb      The StringBuilder where the result is stored.
     * @param str     The string to prefix.
     * @param ch      The character to prefix with.
     * @param len     The desired length of the string.
     * @param bPrefix If <code>true</code> the passed string will be prefixed;
     *                if <code>false</code> it will be suffixed.
     * @return The passed StringBuilder reference.
     */
    public static final StringBuilder makeLength(final StringBuilder sb,
                                                final String str,
                                                final char ch,
                                                final int len,
                                                final boolean bPrefix) {
        // If we're not prefixing, append the string to the StringBuilder here.
        if (!bPrefix) {
            sb.append(str);
        }

        // Add the specified char until the specifed length is reached.
        for (int i = 0, n = len - str.length(); i < n; i++) {
            sb.append(ch);
        }

        // Since we already added the specified char,
        // all we need to do is append the specified string.
        if (bPrefix) {
            sb.append(str);
        }
        return sb;
    }

    /**
     * Returns the specified number of milliseconds as minutes and seconds.
     * Passing the long primitive <tt>500000L</tt> would return the string
     * <tt>8.20</tt> (8 minutes and 20 seconds).
     *
     * @param ms The milliseconds to format as minutes and seconds.
     * @return The minutes and seconds as a string.
     */
    public static final String ms2min(final long ms) {
        int sec = (int) (ms / 1000L);
        int min = sec / 60;

        // 'sec' must be trimmed to under 60.
        if (sec > 59) {
            sec = sec % 60;
        }

        // Quick solution if the seconds isn't over 60
        if (sec < 10) {
            return StringUtil.concat(String.valueOf(min), ".0", String.valueOf(sec));
        } else {
            return StringUtil.concat(String.valueOf(min), ".", String.valueOf(sec));
        }
    }

    /**
     * Replaces all occurrences of the specified char <code>out</code> in the
     * string <code>s</code> with the string <code>in</code>.
     * Example: calling <code>StringUtil.replace("A&B&C", '&', " and ")</code>
     * would return the string "<code>A and B and C</code>".
     *
     * @param s   The string within which to perform the replace.
     * @param out The char to replace.
     * @param in  The string to replace the char with.
     * @return A string.
     */
    public static final String replace(final String s,
                                       final char out,
                                       final String in) {
        // I guess that we won't double the length of the string during the replace.
        StringBuilder result = new StringBuilder(2 * s.length());
        return replace(s, out, in, result).toString();
    }

    /**
     * Replaces all occurrences of the specified char <code>out</code> in the
     * string <code>s</code> with the string <code>in</code>.
     * Example: calling <code>StringUtil.replace("A&B&C", '&', " and ")</code>
     * would return the string "<code>A and B and C</code>".
     *
     * @param s      The string within which to perform the replace.
     * @param out    The char to replace.
     * @param in     The string to replace the char with.
     * @param result The StringBuilder to which to append the result.
     * @return The passed StringBuilder reference.
     */
    public static final StringBuilder replace(final String s,
                                             final char out,
                                             final String in,
                                             final StringBuilder result) {
        char ch;
        for (int i = 0, n = s.length(); i < n; i++) {
            ch = s.charAt(i);
            if (ch != out) {
                result.append(ch);
            } else {
                result.append(in);
            }
        }
        return result;
    }

    /**
     * Replaces all occurrences of the specified char <code>out</code> in the
     * string <code>s</code> with the string <code>in</code>.
     * Example: calling <code>StringUtil.replace("A&B&C", '&', " and ")</code>
     * would return the string "<code>A and B and C</code>".
     *
     * @param s      The string within which to perform the replace.
     * @param out    The char to replace.
     * @param in     The string to replace the char with.
     * @param result The StringBuffer to which to append the result.
     * @return The passed StringBuffer reference.
     */
    public static final StringBuffer replace(final String s,
                                             final char out,
                                             final String in,
                                             final StringBuffer result) {
        char ch;
        for (int i = 0, n = s.length(); i < n; i++) {
            ch = s.charAt(i);
            if (ch != out) {
                result.append(ch);
            } else {
                result.append(in);
            }
        }
        return result;
    }

    /**
     * Replaces one substring with another substring within a string.
     *
     * @param src The string within which to perform the replace.
     * @param out The substring to be replaced.
     * @param in  The substring to replace with.
     * @return A string where an old substring has been replaced
     *         with a new one. If the old substring was not found within
     *         the string, the original string will be replaced.
     */
    public static final String replace(final String src,
                                       final String out,
                                       final String in) {
        // Estimate the optimal length of the StringBuilder...
        final int nOut = out.length();
        final int nIn  = in.length();
        int       nLen = src.length();
        if (nOut < nIn) {
            nLen *= 2;
        }
        // Replace...
        return replace(src,
                out,
                in,
                new StringBuilder(nLen)).toString();
    }

    /**
     * Replaces one substring with another substring within a string.
     *
     * @param src The string within which to perform the replace.
     * @param out The substring to be replaced.
     * @param in  The substring to replace with.
     * @param buf The StringBuilder where the resulting string is appended.
     * @return A string where an old substring has been replaced
     *         with a new one. If the old substring was not found within
     *         the string, the original string will be replaced.
     */
    public static final StringBuilder replace(final String src,
                                             final String out,
                                             final String in,
                                             final StringBuilder buf) {
        int nIndex = src.indexOf(out, 0);

        // If there's nothing to replace; don't!
        if (nIndex == -1) {
            return buf.append(src);
        }

        // Replace...
        int nLast = 0;
        for (int nLenNew = in.length(), nLenOld = out.length(); nIndex != -1;) {
            buf.append(src.substring(nLast, nIndex)).append(in);
            nLast = nIndex + nLenOld;
            nIndex = src.indexOf(out, nLast);
        }

        // Return the result...
        return buf.append(src.substring(nLast));
    }

    /**
     * Replaces one substring with another substring within a string. The
     * replacing algorithm ignores case, for example the following call:
     * <pre>
     * StringUtil.replaceIgnoreCase("Is this a dagger I see before me", "i", "X");
     * </pre>
     * would return the string <code>Xs thXs a dagger X see before me</code>.
     *
     * @param src The string within which to perform the replace.
     * @param out The substring to be replaced.
     * @param in  The substring to replace with.
     * @return A string.
     */
    public static final String replaceIgnoreCase(final String src,
                                                 final String out,
                                                 final String in) {
        // Estimate the optimal length of the StringBuilder...
        final int nOut = out.length();
        final int nIn  = in.length();
        int       nLen = src.length();
        if (nOut < nIn) {
            nLen *= 2;
        }
        // Replace...
        return replaceIgnoreCase(
                src,
                out,
                in,
                new StringBuilder(nLen)
        ).toString();
    }

    /**
     * Replaces one substring with another substring within a string. The
     * replacing algorithm ignores case, for example the following call:
     * <pre>
     * StringUtil.replaceIgnoreCase("Is this a dagger I see before me", "i", "X");
     * </pre>
     * would return the string <code>Xs thXs a dagger X see before me</code>.
     * The result is appended to the specified StringBuilder.
     *
     * @param src The string within which to perform the replace.
     * @param out The substring to be replaced.
     * @param in  The substring to replace with.
     * @param buf The StringBuilder where the resulting string is appended.
     * @return The specified StringBuilder reference.
     */
    public static final StringBuilder replaceIgnoreCase(final String src,
                                                       String out,
                                                       final String in,
                                                       final StringBuilder buf) {
        final String sLower = src.toLowerCase();
        out = out.toLowerCase();

        // If nothing to replace is found, bail out.
        int i = sLower.indexOf(out, 0);
        if (i == -1) {
            return buf.append(src);
        }

        // Search (and replace) until no more hits are found
        int nLast = 0; // The last index where we found an occurance
        for (int nIn = in.length(), nOut = out.length(); i != -1; i = sLower.indexOf(out, nLast)) {
            buf.append(src.substring(nLast, i)).append(in);
            nLast = i + nOut;
        }

        // Append the missing piece and return the result...
        return buf.append(src.substring(nLast));
    }

    /**
     * Splits a string into an array based on the separating characters.
     * <br>
     * If parameter one is "<code>howdy, ho ranger Joe!</code>", and the second parameter
     * is "<code>, </code>", the returning array will equal:
     * <blockquote><pre>
     * [0]: howdy
     * [1]: ho
     * [2]: ranger
     * [3]: Joe!
     * </pre></blockquote>
     *
     * @param text       The text to split into an array.
     * @param separators The characters separating the array elements.
     * @return An array with the array elements.
     */
    public static final String[] split(final String text, final String separators) {
        /*
        StringTokenizer st     = new StringTokenizer(text, separators, false);
        String[]        result = new String[st.countTokens()];

        for (int i = 0; st.hasMoreTokens(); i++) {
          result[i] = st.nextToken() ;
        }
        return result;
        */
        return split(text, separators, false);
    }

    /**
     * Splits a string into an array based on the separating characters.
     * <br>
     * If parameter one is "<code>howdy, ho ranger Joe!</code>", the second
     * parameter is "<code>, </code>" and the third parameter is <tt>true</tt>,
     * the returning array will equal:
     * <blockquote><pre>
     * [0]: "howdy"
     * [1]: ","
     * [2]: " "
     * [3]: "ho"
     * [4]: " "
     * [5]: "ranger"
     * [6]: " "
     * [7]: "Joe!"
     * </pre></blockquote>
     *
     * @param text       The text to split into an array.
     * @param separators The characters separating the array elements.
     * @return An array with the array elements.
     */
    public static final String[] split(final String text,
                                       final String separators,
                                       final boolean returnSeparators) {
        final StringTokenizer st = new StringTokenizer(text, separators, returnSeparators);
        String[]              result = new String[st.countTokens()];

        for (int i = 0; st.hasMoreTokens(); i++) {
            result[i] = st.nextToken();
        }
        return result;
    }

    /**
     * Splits the specified <tt>text</tt> into an array at the specified index.
     * The character at the specified index is the first index in the second array.
     * Calling <tt>StringUtil.split("abcdef", 3);</tt> will return an array where
     * the first index is <tt>abc</tt>, and the second is <tt>def</tt>.
     *
     * @param text  The text to split.
     * @param index The index to split at.
     * @return A string array with the length two.
     */
    public static final String[] split(final String text, final int index) {
        if (text == null) {
            return new String[0];
        }
        if (index < 1) {
            return new String[]{text};
        }
        return new String[]{
                text.substring(0, index),
                text.substring(index)
        };
    }

    /**
     * Splits the specified <tt>text</tt> into an array, where the array elements
     * are the substrings from one index (inclusive) to the next (exclusive).
     * <br>
     * Calling <tt>StringUtil.split("abcdefghi", new int[] { 3, 7 });</tt> would
     * return the following array: <tt>abc</tt>, <tt>defg</tt> and <tt>hi</tt>.
     * <br>
     * Passing indexes in a non-sorted order, for example
     * <tt>StringUtil.split("abcdef", new int[] { 4, 3 });</tt>, will throw an
     * exception.
     *
     * @param text    The text to split.
     * @param indexes The indexes to split at.
     * @return A string array.
     */
    public static final String[] split(final String text, final int[] indexes) {
        // Validate...
        if (text == null) {
            return new String[0];
        }
        final int nLen = text.length();
        if (nLen == 0) {
            return new String[0];
        }
        if (indexes.length == 0) {
            return new String[]{text};
        }

        // Process...
        final List list = new ArrayList(indexes.length + 1);
        for (int i = 0, n = indexes.length - 1; i < indexes.length; ++i) {
            if (i == 0) {
                if (indexes[0] != 0) {
                    list.add(text.substring(0, indexes[i]));
                }
                list.add(text.substring(indexes[0], indexes[1]));
            } else if (i == n) {
                list.add(text.substring(indexes[i]));
            } else {
                list.add(text.substring(indexes[i], indexes[i + 1]));
            }
        }

        // Convert the result.
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * Splits the specified string into a <tt>Map</tt> using the specified
     * separator. The result is a <tt>Map</tt> where the keys are mapped to a
     * value equal to the key.
     *
     * @param text       The text to split into a <tt>Map</tt>.
     * @param separators The characters separating the mapped elements.
     * @return A <tt>Map</tt>.
     */
    public static final Map split2map(final String text, final String separators) {
        final String[] sArr = split(text, separators);
        final Map map = new HashMap(sArr.length);

        for (int i = 0; i < sArr.length; ++i) {
            map.put(sArr[i], sArr[i]);
        }
        return map;
    }

    /**
     * Trims the specified string to be at most as long as the specified length.
     * If the string is <tt>null</tt>, an empty string is returned. If the string
     * is shorter than the specified length, the original string is returned. If
     * the string is longer than the specified length, the string is cropped and
     * appended with "...", with the specified length of course.
     *
     * @param s         The string to trim the size of.
     * @param maxLength The maximum length of the string.
     * @return A string matching the specified length.
     */
    public static final String trim2size(final String s, final int maxLength) {
        return trim2size(s, maxLength, true);
    }

    /**
     * Trims the specified string to be at most as long as the specified length.
     * If the string is <tt>null</tt>, an empty string is returned. If the string
     * is shorter than the specified length, the original string is returned. If
     * the string is longer than the specified length, the string is cropped. If
     * the <tt>addDots</tt> is <tt>true</tt>, the string is appended with "...".
     * The returned string is however never longer than the specified length.
     *
     * @param s         The string to trim the size of.
     * @param maxLength The maximum length of the string.
     * @param dots      If <tt>true</tt>, "..." will be appended to the string if
     *                  it needs to be trimmed.
     * @return A string matching the specified length.
     */
    public static final String trim2size(final String s,
                                         final int maxLength,
                                         final boolean dots) {
        return trim2size(s, maxLength, dots ? "..." : null);
    }

    /**
     * Trims the specified string to be at most as long as the specified length.
     * If the string is <tt>null</tt>, an empty string is returned. If the string
     * is shorter than the specified length, the original string is returned. If
     * the string is longer than the specified length, the string is cropped. If
     * a non <tt>null</tt> or empty suffix has been specified, the string is
     * appended with it. The returned string is however never longer than the
     * specified length.
     * <p/>
     * If the specified length is <tt>-1</tt>, no trimming of the length is
     * performed.
     * <p/>
     * If the specified suffix is longer than the requested length, the suffix is
     * ignored.
     *
     * @param s         The string to trim the size of.
     * @param maxLength The maximum length of the string.
     * @param suffix    The suffix to append  to the string if it needs to be
     *                  trimmed. If the suffix is <tt>null</tt> or empty it's
     *                  ignored.
     * @return A string matching the specified length.
     */
    public static final String trim2size(final String s,
                                         final int maxLength,
                                         final String suffix) {
        // Check if the specified string needs to be trimmed...
        if (isNullOrEmpty(s) || maxLength == 0) {
            return "";
        }
        // If no trimming (of the size) is specified, bail out.
        if (maxLength == -1) {
            return s;
        }
        // Check if the length needs to be reduced. If not, bail out.
        final int i = s.length();
        if (i < maxLength) {
            return s;
        }
        // Trim the length, check if the suffix should be added.
        final int len = suffix == null ? 0 : suffix.length();
        if (len == 0 || maxLength < len + 1) {
            return s.substring(0, maxLength);
        }
        // Return the trimmed string with the specified suffix.
        return StringUtil.concat(s.substring(0, maxLength - len), suffix);
    }

    /**
     * Returns a string representation of the specified object, assuming it being an array.
     * <p/>
     * The format of the returned <tt>String</tt> is the same as <tt>AbstractCollection.toString</tt>:
     * <ul>
     *   <li>non-empty array: [blah, blah]
     *   <li>empty array: []
     *   <li>null array: null
     *   <li>not an array: blah
     * </ul>
     * @param oArray
     * @return A <tt>String</tt> representation of the specified object or array.
     */
    public static final String valueOf(final Object oArray) {
        if (oArray == null) {
            return s_sNull;
        }
        if (!checkObjectIsArray(oArray)) {
            return String.valueOf(oArray);
        }
        final int          nLength = Array.getLength(oArray);
        final StringBuilder buf     = new StringBuilder(nLength * 16);
        for (int i = 0; i < nLength ; ++i) {
            if (i == 0) {
                buf.append('[');
            } else {
                buf.append(',').append(' ');
            }
            final Object item = Array.get(oArray, i);
            if (isNonNullArray(item) ) {
                // Recursive call!
                buf.append(valueOf(item));
            } else {
                buf.append(item);
            }
        }
        if (nLength == 0) {
            buf.append('[').append(']');
        } else {
            buf.append(']');
        }
        return buf.toString();
    }

    public static void main3(String[] args) {
        String[] s = {
                "Thissssssssssssssssssss",
                "issssssssssssssssssss",
                "jusssssssssssssssssssst",
                "a",
                "ssssssssssssssssssssimple",
                "tesssssssssssssssssssst",
                "sssssssssssssssssssstring",
                "!!!"
        };
        int n = 100000;

        // To "warm up" the StringBuilder...
        /*
        for (int i = 0; i < n; i++) {
          StringUtil.concat(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
        }
        */

        StringBuilder buf = new StringBuilder();
        long l1 = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            StringUtil.append(buf, s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
            buf.setLength(0);
        }
        long l2 = System.currentTimeMillis();

        System.out.println("Test 1: " + (l2 - l1) + "ms");

        l1 = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            StringUtil.concat(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
        }
        l2 = System.currentTimeMillis();

        System.out.println("Test 2: " + (l2 - l1) + "ms");
    }

    public static void main2(String[] args) {
        String[] s = {
                "This is THE source of The Greathest stuff to replace the stuff...",
                "the", "tHE"};

        System.out.print("Replacing '");
        System.out.print(s[1]);
        System.out.print("' with '");
        System.out.print(s[2]);
        System.out.print("' in \n'");
        System.out.print(s[0]);
        System.out.println('\'');
        System.out.print('\'');
        System.out.print(StringUtil.replaceIgnoreCase(s[0], s[1], s[2]));
        System.out.println('\'');
    }

    public static void main(String[] args) {
        /*
        final int      l = 2;
        final String[] s = {
          "abcdefghijklmnopqrstuvwxyz",
          "0123456789"
        };
        System.out.println("With the dots...");
        for (int i = 0; i < s.length; ++i) {
          System.out.print(" + ");
          System.out.println(s[i]);
          System.out.print(" > ");
          System.out.println(trim2size(s[i], l));

          System.out.print(" > ");
          for (int j = 1; j < l + 1; ++j) {
            if (j < 10) {
              System.out.print(j);
            } else {
              System.out.print(j % 10);
            }
          }
          System.out.println();
        }

        System.out.println("Without the dots...");
        for (int i = 0; i < s.length; ++i) {
          System.out.print(" + ");
          System.out.println(s[i]);
          System.out.print(" > ");
          System.out.println(trim2size(s[i], l, false));

          System.out.print(" > ");
          for (int j = 1; j < l + 1; ++j) {
            if (j < 10) {
              System.out.print(j);
            } else {
              System.out.print(j % 10);
            }
          }
          System.out.println();
        }

        final String sSuffix = "<crop>";
        System.out.println("With a suffix (" + sSuffix + ")");
        for (int i = 0; i < s.length; ++i) {
          System.out.print(" + ");
          System.out.println(s[i]);
          System.out.print(" > ");
          System.out.println(trim2size(s[i], l, sSuffix));

          System.out.print(" > ");
          for (int j = 1; j < l + 1; ++j) {
            if (j < 10) {
              System.out.print(j);
            } else {
              System.out.print(j % 10);
            }
          }
          System.out.println();
        }
        */
        //System.out.println(byte2kilobyte(50000000L));
        //System.out.println(ms2min(590000L));

        /*
        final String str = "abcdefghijklmnopqrstuvwxyz";
        final int[]    idx = new int[]{0, 7, 11, 16, 20, 25};

        System.out.println(str);
        for (int i = 0, n = str.length(); i < n; ++i) {
            int j = i < 10 ? i : i % 10;
            System.out.print(j);
        }
        System.out.println();

        String[] res = StringUtil.split(str, idx);
        for (int i = 0; i < res.length; ++i) {
            System.out.println(i + ": " + res[i]);
        }

        System.out.println(str);
        for (int i = 0, n = str.length(); i < n; ++i) {
            int j = i < 10 ? i : i % 10;
            System.out.print(j);
        }
        System.out.println();
        res = StringUtil.split(str, str.length() / 2);
        for (int i = 0; i < res.length; ++i) {
            System.out.println(i + ": " + res[i]);
        }
        */

        final boolean[]        booleans = {true,                 false,              false};
        final char[]           chars    = {'B',                  'P',                'H'};
        final byte[]           bytes    = {  3};
        final short[]          shorts   = {  5,                    6};
        final int[]            ints     = {  7,                    8,                  9,  10};
        final long[]           longs    = {100,                  101,                102};
        final float[]          floats   = {99.9f,                63.2f};
        final double[]         doubles  = {212.2,                16.236,             42.2};
        final String[]         strings  = {"blah",               "blah",             "blah"};
        final java.util.Date[] dates    = {new java.util.Date(), new java.util.Date()};
        System.out.println("::StringUtil.valueOf(); booleans.......: " + valueOf(booleans));
        System.out.println("::StringUtil.valueOf(); chars..........: " + valueOf(chars));
        System.out.println("::StringUtil.valueOf(); bytes..........: " + valueOf(bytes));
        System.out.println("::StringUtil.valueOf(); shorts.........: " + valueOf(shorts));
        System.out.println("::StringUtil.valueOf(); ints...........: " + valueOf(ints));
        System.out.println("::StringUtil.valueOf(); longs..........: " + valueOf(longs));
        System.out.println("::StringUtil.valueOf(); floats.........: " + valueOf(floats));
        System.out.println("::StringUtil.valueOf(); double.........: " + valueOf(doubles));
        System.out.println("::StringUtil.valueOf(); string.........: " + valueOf(strings[0]));
        System.out.println("::StringUtil.valueOf(); strings........: " + valueOf(strings));
        System.out.println("::StringUtil.valueOf(); dates..........: " + valueOf(dates));

        final int[]    nullInts     = null;
        final int[]    emptyInts    = {};
        final String[] emptyStrings = {"",   ""};
        final String[] nullStrings  = {null, null};
        System.out.println("::StringUtil.valueOf(); null ints......: " + valueOf(nullInts));
        System.out.println("::StringUtil.valueOf(); empty ints.....: " + valueOf(emptyInts));
        System.out.println("::StringUtil.valueOf(); empty Strings..: " + valueOf(emptyStrings));
        System.out.println("::StringUtil.valueOf(); null Strings...: " + valueOf(nullStrings));

        final String[]   arrayA        = {"A",    "a"};
        final String[]   arrayB        = {"B",    "b"};
        final String[][] arrayOfArrays = {arrayA, arrayB};
        System.out.println("::StringUtil.valueOf(); array Of Arrays: " + valueOf(arrayOfArrays));
    }

    public static void printBreakLine() {
        System.out.println("====================================================================================================================================");
    }

    public static void printBreakLineHeader() {
        System.out.println();
        printBreakLine();
    }

    public static void printBreakLineFooter() {
        printBreakLine();
        System.out.println();
    }


    public static boolean isYes(String s) {

        if ( isNullOrEmpty(s) ){return false;}

        if ( s.trim().equalsIgnoreCase("Y") ){ return true;}

        return false;
    }

    public static boolean isSubStatusCanceled(String s) {

        if ( isNullOrEmpty(s) ){return false;}

        if ( s.trim().equalsIgnoreCase("C") ){ return true;}

        return false;
    }


    public static boolean isSubStatusNotCanceled(String s) {

        return !isSubStatusCanceled(s);
    }

    public static boolean isBanStatusOpen(String s) {

        if ( isNullOrEmpty(s) ){return false;}

        if ( s.trim().equalsIgnoreCase("O") ){ return true;}

        return false;
    }


    /**
     * makeProper() - converts words to Title string with first letter in each word upper case
     * @param theString
     * @return
     * @throws java.io.IOException
     */
    public static final String makeProper(String theString) throws java.io.IOException{

        java.io.StringReader in = new java.io.StringReader(theString.toLowerCase());
        boolean precededBySpace = true;
        StringBuffer properCase = new StringBuffer();
        while(true) {
            int i = in.read();
            if (i == -1)  break;
            char c = (char)i;
            if (c == ' ' || c == '"' || c == '(' || c == '.' || c == '/' || c == '\\' || c == ',') {
                properCase.append(c);
                precededBySpace = true;
            } else {
                if (precededBySpace) {
                    properCase.append(Character.toUpperCase(c));
                } else {
                    properCase.append(c);
                }
                precededBySpace = false;
            }
        }

        return properCase.toString();

    }


    public static final String strAfterStr(String strInput, String strSearch) {
        String strOutput = "";
        String strNotFound = null;

        if ( isNullOrEmpty(strInput) ){
            return strNotFound;
        }
        if ( isNullOrEmpty(strSearch) ){
            return strNotFound;
        }

        int i = strInput.indexOf(strSearch);
        if ( i == -1 ) {
            return strNotFound;
        }

        i = i + strSearch.length();

        strOutput = strInput.substring(i);

        return strOutput;
    }

    public static final String strBeforeStr(String strInput, String strSearch) {
        String strOutput = "";
        String strNotFound = null;

        if ( isNullOrEmpty(strInput) ){
            return strNotFound;
        }
        if ( isNullOrEmpty(strSearch) ){
            return strNotFound;
        }

        int i = strInput.indexOf(strSearch);
        if ( i == -1 ) {
            return strNotFound;
        }

        strOutput = strInput.substring(0,i);

        return strOutput;
    }


    public static final String strBetweenStr(String strInput, String strSearch1, String strSearch2) {
        String strOutput;

        strOutput = strAfterStr(strInput,strSearch1);

        strOutput = strBeforeStr(strOutput,strSearch2);

        return strOutput;
    }





}
