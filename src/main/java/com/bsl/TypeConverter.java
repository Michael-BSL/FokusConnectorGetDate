/*
 * TypeConv.java
 *
 * Created on 28. august 2002, 15:01
 */

package com.bsl;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


/**
 * Class used for conversion between different types.
 *
 * @author Ninja Team
 * @author jel
 * @author rsc
 * @version 7.0
 * @since 7.0
 */
public class TypeConverter {

    /**
     * Logger utility.
     */
    private static final Logger log = Logger.getLogger(String.valueOf(TypeConverter.class));


    /*
     * NOTE! Any future dates and formats should be added to the Map<String,SimpleDateFormat>.
     *       Multiple if/else statements comparing the formats to find the SimpleDateFormat
     *       is simply ludicrous!
     */
    public  static final String DATE_FORMAT_SIMPLE               = "yyyyMMdd";
    public  static final String DATE_FORMAT_PORT_REGULAR         = "yyyyMMddHH"; // HGU: This is only used to compare the porting date (that we're processing the correct item)
    public  static final String DATE_FORMAT_TO_HOUR              = "yyyyMMddHH"; // NHA: This can be used as a compromise for cached rule data, etc.
    public  static final String DATE_FORMAT_TO_HOUR_SQL          = "YYYYMMDDHH24";
    public  static final String DATE_FORMAT_COMPLEX              = "yyyyMMddHHmmss";
    public  static final String DATE_FORMAT_COMPLEX_SQL          = "YYYYMMDDHH24MISS";
    public  static final String DATE_FORMAT_NORWEGIAN            = "ddMMyyyy";
    public  static final String DATE_FORMAT_NORWEGIAN_SHORT      = "ddMMyy";
    public  static final String DATE_FORMAT_NORWEGIAN_SEPARATED  = "dd.MM.yyyy";
    public  static final String DATE_FORMAT_MONTH_AND_YEAR       = "MMyyyy";
    public  static final String DATE_FORMAT_SIMPLE_REVERSED_HYPHENATED = "yyyy-MM-dd";
    public static final String DATE_FORMAT_REPORTS = "dd.MM.yyyy HH:mm:ss";
    public static final String DATE_FORMAT_DASH_SEPARATED = "dd-MM-yyyy";
    public static final String DATE_FORMAT_DASH_SEPARATED_COMPLEX = "yyyy-MM-dd HH:mm:dd";
    public static final String DATE_FORMAT_DASH_SEPARATED_MON_COMPLEX = "yyyy-MMM-dd HH:mm:dd";
    public static final String DATE_FORMAT_FULL_DISPLAY_COMPLEX = "EEEE, d MMM yyyy HH:mm:ss"; // Example: Wednesday, 4 Jul 2001 12:08:56
    public static final String DEFAULT_LANGUAGE = "DK";

    /** A <tt>Double</tt> with the value zero. */
    private static final Double DOUBLE_ZERO                      = Double.valueOf(0d);

    public static final SimpleDateFormat norwegianDateSeparated       = new SimpleDateFormat(DATE_FORMAT_NORWEGIAN_SEPARATED);
    public static final SimpleDateFormat norwegianDateShort           = new SimpleDateFormat(DATE_FORMAT_NORWEGIAN_SHORT);
    public static final SimpleDateFormat norwegianDate                = new SimpleDateFormat(DATE_FORMAT_NORWEGIAN);
    public static final SimpleDateFormat complexDate                  = new SimpleDateFormat(DATE_FORMAT_COMPLEX);
    public static final SimpleDateFormat simpleDate                   = new SimpleDateFormat(DATE_FORMAT_SIMPLE);
    public static final SimpleDateFormat regularDate                  = new SimpleDateFormat(DATE_FORMAT_PORT_REGULAR);
    public static final SimpleDateFormat toHourDate                   = new SimpleDateFormat(DATE_FORMAT_TO_HOUR);
    public static final SimpleDateFormat monthAndYearDate             = new SimpleDateFormat(DATE_FORMAT_MONTH_AND_YEAR);
    public static final SimpleDateFormat simpleReversedHyphenatedDate = new SimpleDateFormat(DATE_FORMAT_SIMPLE_REVERSED_HYPHENATED);
    public static final SimpleDateFormat simpleDateFormatReports      = new SimpleDateFormat(DATE_FORMAT_REPORTS);
    public static final SimpleDateFormat simpleDateFormatHourTosql    = new SimpleDateFormat(DATE_FORMAT_TO_HOUR_SQL);
    public static final SimpleDateFormat dashDateSeparated            = new SimpleDateFormat(DATE_FORMAT_DASH_SEPARATED);
    public static final SimpleDateFormat dashDateSeparatedComplex     = new SimpleDateFormat(DATE_FORMAT_DASH_SEPARATED_COMPLEX);
    public static final SimpleDateFormat dashDateSeparatedMonComplex  = new SimpleDateFormat(DATE_FORMAT_DASH_SEPARATED_MON_COMPLEX);
    public static final SimpleDateFormat complexFullDisplay  = new SimpleDateFormat(DATE_FORMAT_FULL_DISPLAY_COMPLEX);

    public static final NumberFormat     s_oNumberFormat        = NumberFormat.getInstance();

    private static final Map<String,SimpleDateFormat> mapSimplaeDateFormats = new HashMap<String,SimpleDateFormat>();

    static {
        mapSimplaeDateFormats.put(DATE_FORMAT_NORWEGIAN_SEPARATED,        norwegianDateSeparated);
        mapSimplaeDateFormats.put(DATE_FORMAT_NORWEGIAN_SHORT,            norwegianDateShort);
        mapSimplaeDateFormats.put(DATE_FORMAT_NORWEGIAN,                  norwegianDate);
        mapSimplaeDateFormats.put(DATE_FORMAT_COMPLEX,                    complexDate);
        mapSimplaeDateFormats.put(DATE_FORMAT_SIMPLE,                     simpleDate);
        mapSimplaeDateFormats.put(DATE_FORMAT_PORT_REGULAR,               regularDate);
        mapSimplaeDateFormats.put(DATE_FORMAT_TO_HOUR,                    toHourDate);
        mapSimplaeDateFormats.put(DATE_FORMAT_MONTH_AND_YEAR,             monthAndYearDate);
        mapSimplaeDateFormats.put(DATE_FORMAT_SIMPLE_REVERSED_HYPHENATED, simpleReversedHyphenatedDate);
        mapSimplaeDateFormats.put(DATE_FORMAT_REPORTS,                    simpleDateFormatReports);
        mapSimplaeDateFormats.put(DATE_FORMAT_TO_HOUR_SQL,                simpleDateFormatHourTosql);
        mapSimplaeDateFormats.put(DATE_FORMAT_DASH_SEPARATED,             dashDateSeparated);
        mapSimplaeDateFormats.put(DATE_FORMAT_DASH_SEPARATED_COMPLEX,     dashDateSeparatedComplex);
        mapSimplaeDateFormats.put(DATE_FORMAT_DASH_SEPARATED_MON_COMPLEX, dashDateSeparatedMonComplex);
        mapSimplaeDateFormats.put(DATE_FORMAT_FULL_DISPLAY_COMPLEX,       complexFullDisplay);

        s_oNumberFormat.setMaximumFractionDigits(3);
        s_oNumberFormat.setGroupingUsed(false);
    }

    /**
     * ===============================
     * /**
     * Date used as the largest date in the system (31.dec.4700 00:00:00).
     * Useful as an alternative to setting dates to null, when one needs to compare Date objects.
     */
    //public static Date FUTURE_DATE = new Date(32535126000225L);
    /*

    /**
     * Date used as the largest date in the system (31.dec.4700 00:00:00). (Syncronized with Fokus max date)
     * Useful as an alternative to setting dates to null, when one needs to compare Date objects.
     */
    public static Date FUTURE_DATE = new Date(86181922800225L);

    /**
     * Date used as the smallest date in the system (01.jan.1960 00:00:00).
     * Useful as an alternative to setting dates to null, when one needs to compare Date objects.
     */
    public static Date HISTORICAL_DATE = new Date(-315622799962L);

    /**
     * Date used as the 00.00.0000. Added as part of DKP-6198 to copy Fokus functionality.
     * Useful as an alternative to setting dates to null, when one needs to compare Date objects.
     */
    public static final String NULL_FUTURE_DATE = "00.00.0000";

    public static final String DURATION_UNIT_MONTH = "M";
    public static final String DURATION_UNIT_DAY   = "D";

    public static final String BOOLEAN_TRUE        = "Y";
    public static final String BOOLEAN_FALSE       = "N";



    /**
     * Returns a string representing an SQL date.
     * Returned format is: <code>to_date('date-as-string', 'format')</code>.
     * This method assumes that the format used for Java and SQL is the same.
     *
     * @param date      the date to convert.
     * @param strFormat the format of the date (both Java and SQL).
     * @return the string representation of the date.
     */
    public static String dateToSQL(Date date, String strFormat) {
        return dateToSQL(date, strFormat, null);
    }


    /**
     * Returns a string representing an SQL date.
     * Returned format is: <code>to_date('date-as-string', 'sql-format')</code>.
     * Use this method if the format used in Java and the format used in SQL is different.
     *
     * @param date       the date to convert.
     * @param javaFormat the Java format of the date. For example for a complex date: <code>yyyyMMddHHmmss</code>
     * @param sqlFormat  the SQL format of the date. For example for a complex date: <code>YYYYMMDDHH24MISS</code>
     * @return the string representation of the date.
     */
    public static String dateToSQL(Date date, String javaFormat, String sqlFormat) {
        if (sqlFormat == null) {
            sqlFormat = javaFormat;
        }
        return "to_date('" + TypeConverter.dateToString(date, javaFormat) + "', '" + sqlFormat + "')";
    }


    /**
     * Converts a Date object into a string.
     * If null is specified in Date, then empty String is returned.
     *
     * @param date Date object to be converted.
     * @param dateFormat Format of the stringified Date to be returned.
     * @return String representation of a oDate object, or empty String if given Date is null.
     */
    public static String dateToString(final Date date, final String dateFormat) {

        if ( date == null || dateFormat == null ) {
            return ""; // This will cause the calling dateToSQL(...) method to break... :-/
        }

        SimpleDateFormat simpleDateFormat = mapSimplaeDateFormats.get(dateFormat);
        if ( simpleDateFormat == null ) {
            throw new IllegalArgumentException("Illegal dateFormat : <" + dateFormat + ">");
        }

        // Convert the input date to string
        synchronized (simpleDateFormat) {
            return ( simpleDateFormat.format(date) );
        }

    }


    /**
     * Converts a string into a byte object. If null is specified in sStr,
     * then null is returned. If the length of sStr exceeds 1, then an exception
     * is thrown.
     *
     * @param sStr String containing one character.
     * @return Byte object initialized with value specified by sStr.
     */
    public static Byte stringToByte(String sStr) {
        if (sStr == null) {
            // Null object
            return null;
        } else if (sStr.length() == 0) {
            // Empty string
            return (TypeConverter.stringToByte(" "));
        } else if (sStr.length() == 1) {
            // Normal conversion
            return (new Byte((byte) sStr.charAt(0)));
        } else {
            // String of more than one character. Cannot convert, so we return null
            return (null);
        }
    }


    /**
     * Converts a Byte object into a string. If the byte object is bull, then null
     * return returned.
     *
     * @param bt Byte object to be converted.
     * @return String representation of Byte object.
     */
    public static String byteToString(Byte bt) {
        if (bt == null) {
            return (null);
        } else {
            byte btArr[] = {bt.byteValue()};
            return (new String(btArr));
        }
    }


    public static boolean identicalObjects(Object obj1, Object obj2) {
        // Are both null, i.e. equal
        if (obj1 == null && obj2 == null) {
            return true;
        }
        // Is #1 null both not #2, i.e. not equal
        if (obj1 == null && obj2 != null) {
            return false;
        }
        // Is #2 null but not #1, i.e. not equal
        if (obj1 != null && obj2 == null) {
            return false;
        }
        // In case both objects are strings, trim before comparison
        if (obj1 instanceof String && obj2 instanceof String) {
            return ((String) obj1).trim().equals(((String) obj2).trim());
        }
        // Last resort, falling back to java.lang.Object's equals method.
        return obj1.equals(obj2);
    }

    /**
     * Adds (or subtracts) the specified number of intMonths to (or from) the
     * specified datDate. To subtract, specify a negative number for strUnit.
     * TODO: Why is this operattion not in Logical date or FokusDates ? (TypeConverter is a growing collection of functions...)
     *
     * @param datDate          The datDate to add or subtract units to/from.
     * @param strUnit          Units as defined in Fokus (public static fields in this class).
     * @param intNumberOfUnits The number of units to add or subtract.
     * @return New datDate or null if errors occur.
     * @see this#DURATION_UNIT_DAY
     * @see this#DURATION_UNIT_MONTH
     */
    public static Date add(Date datDate, String strUnit, int intNumberOfUnits) {

        Date datNewDate;
        GregorianCalendar gcaCalendar = new GregorianCalendar();

        if (strUnit.equals(DURATION_UNIT_MONTH)) {
            datNewDate = TypeConverter.addMonths(datDate, intNumberOfUnits);
        } else if (strUnit.equals(DURATION_UNIT_DAY)) {
            gcaCalendar.setTime(datDate);
            gcaCalendar.add(Calendar.DATE, intNumberOfUnits);
            datNewDate = gcaCalendar.getTime();
        } else {
            datNewDate = null;
        }

        return datNewDate;
    }



    /**
     * Adds (or subtracts) the specified number of intMonths to (or from) the
     * specified datDate. To subtract, specify a negative number for intMonths. If
     * the specified datDate is the last day of a month, then the returned datDate will
     * also be a datDate on the last day of a month. E.g. if the specified datDate is
     * 28-02-2001 and you add one month, the datDate 31-03-2001 is returned.<br>
     * N.B. The above statement is no longer true from 1st October 2008 - do not adjust to month end!!!
     * TODO: Why is this operation not in Logical date or FokusDates ? (TypeConverter is a growing collection of functions...)
     */
    static public Date addMonths(Date datDate, int intMonths) {
        GregorianCalendar gcaCalendar = new GregorianCalendar();

        Date datReturn = null;

        try {
            gcaCalendar.setTime(datDate);
            /* As confirmed in email from OLSEN, Kim Laage Saltoft & Henrik Hjarno - do not adjust calendar for month end!
            if (gcaCalendar.get(Calendar.DAY_OF_MONTH) == gcaCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                gcaCalendar.set(Calendar.DAY_OF_MONTH, 1);
                gcaCalendar.add(Calendar.MONTH, intMonths);
                gcaCalendar.set(Calendar.DAY_OF_MONTH, gcaCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                gcaCalendar.add(Calendar.MONTH, intMonths);
            }.. Remove end of month calculation - RISM00 */

            gcaCalendar.add(Calendar.MONTH, intMonths);
            datReturn = gcaCalendar.getTime();

        } catch (Exception e) {
            log.warning("addMonths " + e);
        }
        // Default return value
        return datReturn;
    }



    /**
     * Returns the number of months between two dates. Please note that a
     * fraction can also be returned. If endDate is later than startDate, then a
     * positive number is returned. If the reverse is the case, then a negative
     * number is returned. Any day in the period not constituting a full month
     * is divided by 30 to calculate a fraction.
     */
    public static double monthsBetween(Date startDate, Date endDate) {

        double            result     = 0d;
        GregorianCalendar calStart   = new GregorianCalendar();
        GregorianCalendar calEnd     = new GregorianCalendar();
        int               months;
        int               days;
        int               multiplier = 1;

        try {

            // Sets the two dates in the two gregorian calendar objects
            calStart.setTime(startDate);
            calEnd.setTime(endDate);

            // Order the dates so that startDate is always the earliest date. If we switch
            // the dates, then we must multiply the final result by -1
            if (calStart.after(calEnd)) {

                GregorianCalendar calClone = (GregorianCalendar) calStart.clone();
                calStart   = calEnd;
                calEnd     = calClone;
                multiplier = -1;
            }

            // Gets absolute number of months in the two dates, i.e. the number of
            // months since "The Lord" was born.
            final int startMonths = calStart.get(Calendar.YEAR) * 12 + calStart.get(Calendar.MONTH);
            final int endMonths   = calEnd.get(Calendar.YEAR)   * 12 + calEnd.get(Calendar.MONTH);

            // Gets the day component of the two dates
            final int startDay = calStart.get(Calendar.DAY_OF_MONTH);
            final int endDay   = calEnd.get(Calendar.DAY_OF_MONTH);

            // Gets the number remaining days of the first month in the period
            final int remainingDaysFirstMonth = calStart.getActualMaximum(Calendar.DAY_OF_MONTH) - calStart.get(Calendar.DAY_OF_MONTH);

            // Gets the number of days in the last month in the period
            final int usedDaysSecondMonth     = calEnd.get(Calendar.DAY_OF_MONTH) - calEnd.getActualMinimum(Calendar.DAY_OF_MONTH);

            // Calculates the number of 'full' months bwtween the two dates
            months = endMonths - startMonths - 1;

            // And now for the tricky bits!!!
            // Before you even start to analyse the code below, it is recommended
            // that you draw up an example on a piece of paper.
            if (endDay == calEnd.getActualMaximum(Calendar.DAY_OF_MONTH)) {

                // If the latest date is the last day of a month, then we add 1 to the
                // number of months. The fraction is then the remaining days of the
                // first month.
                ++months;
                days = remainingDaysFirstMonth;
            } else {

                // If the latest date is NOT on the last day of a month, then we have two
                // separate cases.
                if (startDay <= endDay) {

                    // Case 1:
                    // If the day component of the latest date is higher than (or equal to)
                    // the day component of the earliest date, then we must add 1 to the
                    // number of months. The fraction is then the difference between the
                    // two day components.
                    ++months;
                    days = endDay - startDay;
                } else {

                    // Case 2:
                    // If the day component of the ealiest date is bigger than the day
                    // component of the latest date, then the fraction simply consists of
                    // the remaining days of the first month and the number of days in
                    // the last month.
                    days = remainingDaysFirstMonth + usedDaysSecondMonth + 1;
                }
            }

            // Now that we have the number of full months and days in the period
            // between the two specified dates, we can now calculate the period in
            // number of months.
            result = ((months + (double) days / 30) * (double) multiplier);

        } catch (Exception e) {
            log.warning("monthsBetween "+ e);
        }

        return result;
    }



    public static Integer shortToInteger(Short sh) {
        return sh == null ? null : new Integer(sh.intValue());
    }


    public static Short integerToShort(Integer i) {
        Short sh = null;
        if (i != null) {
            sh = new Short(i.shortValue());
        }

        return sh;
    }



    /**
     * Gets a String with the given integer prefixed by padding
     * up tp the length of the String representation of the max integer.
     *
     * @param i       the value to get as padded String.
     * @param max     maximum value for the integer.
     * @param padding padding character used for prefix.
     */
    public static String getPaddedInt(int i, int max, char padding) {
        String sPadding = "";
        String sMaxIndex = Integer.toString(max);
        String sIndex = Integer.toString(i);

        int bufSize = sMaxIndex.length() - sIndex.length();
        char[] caPrefix = new char[bufSize];
        Arrays.fill(caPrefix, padding);
        sPadding = new String(caPrefix);

        return sPadding + sIndex;
    }


    /**
     * Gets the size of the longest <code>java.lang.String</code> object in the given collection.
     *
     * @param values
     */
    public static int getMaxStringSize(Collection values) {
        int max = 0;

        Object oVal;
        String sVal;
        int len;
        Iterator iterValues = values.iterator();
        while (iterValues.hasNext()) {
            oVal = iterValues.next();
            if (oVal instanceof String) {
                sVal = (String) oVal;
                //noinspection ConstantConditions
                if (sVal != null) {
                    len = sVal.length();
                    max = (len > max) ? len : max;
                }
            }
        }

        return max;
    }


    /**
     * Gets the name of the given object without the package prefix.
     *
     * @return name of class or null if given object is null;
     */
    public static String getSimpleClassName(final Object obj) {
        if (obj == null) {
            return null;
        }
        final String sClassPathName = obj.getClass().getName();
        final int    nLastDot       = sClassPathName.lastIndexOf('.');
        return sClassPathName.substring(nLastDot + 1);
    }


    public static int stringToInt(String string) {
        return Integer.parseInt(string);
    }


    /**
     * Replaces all occurrences of the replacee in the string s with the replacement.<br>
     * <strong>NOTE:</strong> substitute for String.replaceAll method in Java SDK 1.4.
     *
     * @param s           the target string.
     * @param replacee    the string being replaced.
     * @param replacement
     * @return string with replecements.
     */
    public static String replaceAll(String s, String replacee, String replacement) {
        String sRes = s;

        if ((s != null) && (replacee != null) && (replacee.length() > 0) && (replacement != null)) {
            int replaceeSize = replacee.length();

            StringBuilder strBuf = new StringBuilder();

            String sBefore;
            String sAfter;
            int idxFrom = 0;
            int idx;

            for (; (idx = s.indexOf(replacee, idxFrom)) != -1;) {
                sBefore = s.substring(idxFrom, idx);
                strBuf.append(sBefore);
                strBuf.append(replacement);

                idxFrom = idx + replaceeSize;
            }
            if (idxFrom < s.length()) {
                sAfter = s.substring(idxFrom);
                strBuf.append(sAfter);
            }
            sRes = strBuf.toString();
        }

        return sRes;
    }

   private static final void addTime2Buf(final int nTime, final StringBuilder buf) {
        addTime2Buf(nTime, buf, true);
    }
    private static final void addTime2Buf(final int nTime, final StringBuilder buf, final boolean bAddTrailingColon) {
        if (nTime == 0) {
            buf.append('0').append('0');
        } else if (nTime > 9) {
            buf.append(nTime);
        } else {
            buf.append('0').append(nTime);
        }
        if (bAddTrailingColon) {
            buf.append(':');
        }
    }

    /**
     * According to what the method appears to do in Fokus/CSM's PowerBuilder code,
     * it should return a value with at most three (3) decimals.
     * @param nBytes The number of bytes to convert into megabytes (as a string).
     * @return A string with the converted amount as megabytes.
     */
    public static final String convertBytes2Mb(final double nBytes) {
        if (nBytes == 0D) {
            return "0";
        }
        return convertKb2Mb(nBytes / 1024D);
    }

    /**
     * According to what the method appears to do in Fokus/CSM's PowerBuilder code,
     * it should return a value with at most three (3) decimals.
     * @param nDeciMegaBytes The number of DeciMb to convert into megabytes (as a string).
     * @return A string with the converted amount as megabytes.
     */
    public static final String convertDeciMb2Mb(final double nDeciMegaBytes) {
        if (nDeciMegaBytes == 0D) {
            return "0";
        }
        //return double2string(nDeciMegaBytes / 10D /*, 3*/);
        return double2string(divideWithTen(nDeciMegaBytes).doubleValue());
    }

    /**
     * This method converts a <tt>Double</tt> value from DeciMegaBytes to regular MegaBytes.
     *
     * @param nDeciMegaBytes The number of DeciMb to convert into megabytes (as a <tt>Double</tt>).
     * @return A <tt>Double</tt> with the converted amount as megabytes.
     */
    public static final Double convertDeciMb2MbDouble(final double nDeciMegaBytes) {
        if (nDeciMegaBytes == 0D) {
            return DOUBLE_ZERO;
        }
        //return Double.valueOf(nDeciMegaBytes / 10d);
        return divideWithTen(nDeciMegaBytes);
    }

    /**
     * According to what the method appears to do in Fokus/CSM's PowerBuilder code,
     * it should return a value with at most three (3) decimals.
     * @param nKiloBytes The number of kilobytes to convert into megabytes (as a string).
     * @return A string with the converted amount as megabytes.
     */
    public static final String convertKb2Mb(final double nKiloBytes) {
        if (nKiloBytes == 0D) {
            return "0";
        }
        return double2string(nKiloBytes / 1024D /*, 3*/);
    }

    public static final String double2string(final double nDouble /*, final int nDecimals */) {
        //final BigDecimal oBigDec = new BigDecimal(nDouble).setScale(nDecimals, BigDecimal.ROUND_HALF_UP);
        //return oBigDec.toString();
        return s_oNumberFormat.format(nDouble); // The internals of this NumberFormat is syncronised, so that we don't need to...
    }

    private static final DateFormat cloneDateFormat(final DateFormat oDateFormat) {
        return (DateFormat) oDateFormat.clone();
    }

    private static final Double divideWithTen(final double nDouble) {
        if (nDouble == 0d) {
            return DOUBLE_ZERO;
        }
        final BigDecimal bd = BigDecimal.valueOf(nDouble);
        return Double.valueOf(bd.divide(BigDecimal.TEN).doubleValue());
    }

    /**
     * trimLeft() - remove leading spaces.
     * @param s
     * @return
     */
    public static String trimLeft(String s) {
        return s.replaceAll("^\\s+", "");
    }
}