package com.bsl;

import bea.jolt.pool.DataSet;
import bea.jolt.pool.Result;

import java.util.Arrays;
import java.util.List;

/**
 * Ancestor of all formats that operates on <code>bea.jolt.pool.DataSet</code>s
 * (or its subclass used for output: <code>bea.jolt.pool.Result</code>).
 *
 * @author Ninja Team
 * @author rsc
 * @version 7.0
 * @since Jun 16, 2003
 */
public abstract class JoltServiceLogFormat  {
    public static final String ident = "$Id: JoltServiceLogFormat.java,v 1.4 2012/09/17 11:07:38 xrism00 Exp $";
    /**
     * Gets the formatted log entry for a given input or output Jolt service parameter.
     *
     * @param serviceName name of the Tuxedo service.
     * @param param       parameter values (must be of type <code>bea.jolt.pool.DataSet</code> or <code>bea.jolt.pool.Result</code>).
     */
    public String format(String serviceName, Object param) {
        return format(serviceName, param, "");
    }

    /**
     * Gets the formatted log entry for a given input or output Jolt service parameter.
     *
     * @param serviceName name of the Tuxedo service.
     * @param param       parameter values (must be of type <code>bea.jolt.pool.DataSet</code> or <code>bea.jolt.pool.Result</code>).
     * @param padding     string used as padding prefix for each new line of the log entry.
     * @return formatted text or null if illegal parameter type.
     */
    public String format(String serviceName, Object param, String padding) {
        String sFormatted = null;

        if (serviceName != null && param != null) {
            if (param instanceof DataSet) {
                sFormatted = format(serviceName, (DataSet) param, padding);
            }
        }

        return sFormatted;
    }

    /**
     * Gets the application code from the given result set.
     * @param result
     */
    protected static int getApplicationCode(Result result) {
        int appCode = result.getApplicationCode();
        return appCode;
    }

    /**
     * Sorts the given array of keys according to the given order of the keys in the view file.
     * @param keys
     * @param keyOrderViewFile
     * @return new sorted array.
     */
    public static Object[] sortKeys(Object[] keys, String[] keyOrderViewFile) {
        Object key;
        int numActualKeys = keys.length;
        Object[] keysSorted = new String[numActualKeys];
        List<Object> actualKeys = Arrays.asList(keys);

        int numOrderedKeys = keyOrderViewFile.length;
        for (int i = 0, j = 0; i < numOrderedKeys; i++) {
            key = keyOrderViewFile[i];

            if (actualKeys.contains(key)
            		&& j < numActualKeys){
                keysSorted[j++] = key;
            }
        }

        return keysSorted;
    }


    /**
     * Gets the formatted log entry for a given input or output Jolt <code>bea.jolt.pool.DataSet</code>.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     parameter values.
     */
    public abstract String format(String serviceName, DataSet dataset);

    /**
     * Gets the formatted log entry for a given input or output Jolt <code>bea.jolt.pool.DataSet</code>.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     parameter values.
     * @param padding     string used as padding prefix for each new line of the log entry.
     */
    public abstract String format(String serviceName, DataSet dataset, String padding);

    /**
     * Gets the formatted log entry for a given input or output Jolt <code>bea.jolt.pool.DataSet</code>.
     *
     * @param serviceName name of the Tuxedo service.
     * @param dataset     parameter values.
     * @param padding     string used as padding prefix for each new line of the log entry.
     * @param fmlOrder    array that declares the order of the field names in the view file.
     *                    Use null to ignore the order.
     */
    public abstract String format(String serviceName, DataSet dataset, String padding, String[] fmlOrder);

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
    public abstract String format(String serviceName, DataSet dataset, String padding, String[] fmlOrder, String delimiterChar, String newLineChar);
}
