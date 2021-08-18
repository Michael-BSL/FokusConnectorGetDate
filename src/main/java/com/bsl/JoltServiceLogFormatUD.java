package com.bsl;

import bea.jolt.pool.DataSet;
import bea.jolt.pool.Result;

/**
 * Handles the UD formatting of <code>bea.jolt.pool.DataSet</code>s
 * (or its subclass used for output: <code>bea.jolt.pool.Result</code>).
 * The UD format is the format used by the FML debugger in the CSM (PowerBuilder application).
 *
 * @author Ninja Team
 * @author rsc
 * @version 7.0
 * @since Jun 16, 2003
 */
public class JoltServiceLogFormatUD extends JoltServiceLogFormat {
    public static final String ident = "$Id: JoltServiceLogFormatUD.java,v 1.3 2012/09/17 11:07:38 xrism00 Exp $";
    /**
     * Logger utility.
     */
 //   private static final Logger log = Logger.getLogger(JoltServiceLogFormatUD.class);

    /**
     * Convenience method that
     * gets the UD formatted log entry for the given input or output Jolt service parameter.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     Input or output (subclass Result) parameter for Tuxedo service.
     */
    public static String doFormat(String serviceName, DataSet dataset) {
        return doFormat(serviceName, dataset, "");
    }

    /**
     * Convenience method that
     * gets the UD formatted log entry for the given input or output Jolt service parameter.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     input or output (subclass Result) parameter for Tuxedo service.
     * @param padding     string used as padding for each new line.
     */
    public static String doFormat(String serviceName, DataSet dataset, String padding) {
        return doFormat(serviceName, dataset, padding, null);
    }


    /**
     * Convenience method that
     * gets the UD formatted log entry for the given input or output Jolt service parameter.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     input or output (subclass Result) parameter for Tuxedo service.
     * @param padding     string used as padding for each new line.
     * @param fmlOrder    array that declares the order of the field names in the view file.
     *                    Use null to ignore the order.
     */
    public static String doFormat(String serviceName, DataSet dataset, String padding, String[] fmlOrder) {
        return doFormat(serviceName, dataset, padding, fmlOrder, "\t", "\n");
    }

    /**
     * Convenience method that
     * gets the UD formatted log entry for the given input or output Jolt service parameter.
     *
     * @param serviceName   name of the Tuxedo service.
     * @param dataset       input or output (subclass Result) parameter for Tuxedo service.
     * @param padding       string used as padding for each new line.
     * @param fmlOrder      array that declares the order of the field names in the view file.
     *                      Use null to ignore the order.
     * @param delimiterChar character used to separate key and value pairs.
     * @param newLineChar   character used to represent a new line.
     */
    public static String doFormat(String serviceName, DataSet dataset, String padding, String[] fmlOrder, String delimiterChar, String newLineChar) {
        JoltServiceLogFormatUD udFormat = new JoltServiceLogFormatUD();
        return udFormat.format(serviceName, dataset, padding, fmlOrder, delimiterChar, newLineChar);
    }


    /**
     * Gets the UD representation of a given DataSet.
     * The UD format is the format used by the FML debugger the CSM (PowerBuilder application).
     *
     * @param serviceName   the name of the service to which the DataSet belongs.
     * @param ds            the DataSet to get in UD format.
     * @param prefix        optional prefix used for each new line.
     * @param fmlOrder      array of FML field names in the order as declared in the view file.
     *                      use null to ignore.
     * @param delimiterChar character used to separate key and value pairs.
     * @param newLineChar   character used to represent a new line.
     * @return String with the UD representaion.
     */
    public static String getDataSetInUDFormat(String serviceName, DataSet ds, String prefix, String[] fmlOrder, String delimiterChar, String newLineChar) {
        String ud = prefix + "SRVCNM" + delimiterChar + serviceName;

        Object[] saKeys = ds.keySet().toArray();
        if (fmlOrder != null) {
            saKeys = sortKeys(saKeys, fmlOrder);
        }

        int i;
        String key;
        Object value;
        for (int keyIdx = 0; keyIdx < saKeys.length; keyIdx++) {
            key = (String) saKeys[keyIdx];
            i   = key == null ? 0 : ds.getCount(key);
            for (int j = 0; j < i; j++) {
                value = ds.getValue(key, j, "");
                if (value instanceof Byte) {
                    value = TypeConverter.byteToString((Byte) value);
                }
                ud += newLineChar + prefix + key + delimiterChar + value;
            }
        }
        ud += (newLineChar + newLineChar);

        return ud;
    }

    /**
     * Gets the formatted log entry for a given input or output Jolt <code>bea.jolt.pool.DataSet</code>.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     parameter values.
     */
    public String format(String serviceName, DataSet dataset) {
        return format(serviceName, dataset, "");
    }

    /**
     * Gets the formatted log entry for a given input or output Jolt <code>bea.jolt.pool.DataSet</code>.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     parameter values.
     * @param padding     string used as padding prefix for each new line of the log entry.
     */
    public String format(String serviceName, DataSet dataset, String padding) {
        return format(serviceName, dataset, padding, null);
    }

    /**
     * Gets the formatted log entry for a given input or output Jolt <code>bea.jolt.pool.DataSet</code>.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     parameter values.
     * @param padding     string used as padding prefix for each new line of the log entry.
     * @param fmlOrder    array that declares the order of the field names in the view file.
     *                    Use null to ignore the order.
     */
    public String format(String serviceName, DataSet dataset, String padding, String[] fmlOrder) {
        return format(serviceName, dataset, padding, fmlOrder, "\t", "\n");
    }

    /**
     * Gets the formatted log entry for a given input or output Jolt <code>bea.jolt.pool.DataSet</code>.
     *
     * @param serviceName   name of the Tuxedo service.
     * @param dataset       parameter values.
     * @param padding       string used as padding prefix for each new line of the log entry.
     * @param fmlOrder      array that declares the order of the field names in the view file.
     *                      Use null to ignore the order.
     * @param delimiterChar character used to separate key and value pairs.
     * @param newLineChar   character used to represent a new line.
     */
    public String format(String serviceName, DataSet dataset, String padding, String[] fmlOrder, String delimiterChar, String newLineChar) {
        StringBuilder buf = new StringBuilder();

        boolean isOutput = dataset instanceof Result;
        appendPrefix(buf, serviceName, isOutput, newLineChar);
        if (dataset != null) {
            if (dataset instanceof Result) {
                Result res = (Result) dataset;
                int appCode = getApplicationCode(res);
                appendApplicationCode(buf, appCode, padding, newLineChar);
            }
            buf.append(getDataSetInUDFormat(serviceName, dataset, padding, fmlOrder, delimiterChar, newLineChar));
        }
        appendSuffix(buf);

        return buf.toString();
    }


    private void appendPrefix(StringBuilder buf, String serviceName, boolean isOutput, String newLineChar) {
        String direction = (isOutput) ? "output" : "input";
        buf.append(serviceName + " " + direction + ":" + newLineChar + "{" + newLineChar);
    }

    private void appendSuffix(StringBuilder buf) {
        buf.append("}");
    }

    private void appendApplicationCode(StringBuilder buf, int appCode, String padding, String newLineChar) {
        buf.append(padding + "ApplicationCode=" + appCode + newLineChar);
    }
}
