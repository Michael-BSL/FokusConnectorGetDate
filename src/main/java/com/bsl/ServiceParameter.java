/*
 * ServiceInput.java
 *
 * Created on 14. august 2002, 14:42
 */

package com.bsl;


import bea.jolt.pool.DataSet;
import com.bsl.exceptions.BSLFokusException;

import java.util.Vector;


/**
 * Abstract superclass of all service input classes.
 *
 * @author Ninja
 * @version 6.0
 */
public abstract class ServiceParameter {

    public void set_RUN_DATE(String value) throws BSLFokusException {
    }

    public void set_BAN(Integer value) throws BSLFokusException {
    }

    public void set_LINK_TYPE_STR(String value) throws BSLFokusException {
    }

    // Values for null fields in Fokus
    public static final String EMPTY_STRING = "";

    /**
     * String used for indentation in the toString-methods.
     */
    public static final String TOSTRING_PADDING = "  ";


    // FML buffer
    protected FmlField fmlBuffer[] = null;


    /**
     * Returns the FML buffer as a dataset.
     *
     * @return Dataset containing all the values of the FML buffer.
     */
    public DataSet getDataSet() {
        DataSet ds = new DataSet();
        for (int i = 0; i < this.fmlBuffer.length; i++) {
            Vector value = this.fmlBuffer[i].getValues();
            if (value != null && value.size() != 0) {
                if (!(value.get(0) == null && value.size() == 1)) {
                    for (int j = 0; j < value.size(); j++) {
                        ds.setValue(this.fmlBuffer[i].getName(), j, value.elementAt(j));
                    }
                }
            }
        }

        return (ds);
    }

    /**
     * Gets the names of the FML fields.
     * The fields are declared in the correct sequence order as defined in the view file.
     */
    public String[] getFMLFieldNames() {
        // HGU 2007-08-08: A null fmlBuffer should not cause a NullPointerException
        final int numFields = fmlBuffer == null ? 0 : fmlBuffer.length;
        //int numFields = fmlBuffer.length;
        String[] fieldNames = new String[numFields];

        for (int i = 0; i < numFields; i++) {
            fieldNames[i] = fmlBuffer[i].getName();
        }

        return fieldNames;
    }



}