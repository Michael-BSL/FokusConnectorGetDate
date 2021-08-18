/*
 * ServiceOutput.java
 *
 * Created on 14. august 2002, 14:42
 */

package com.bsl;

import bea.jolt.pool.DataSet;
import bea.jolt.pool.Result;

import com.bsl.exceptions.BSLFokusException;


import java.util.logging.Logger;


/**
 * Abstract superclass of all service ouput classes.
 *
 * @author Ninja/jel
 * @version 6.0
 */
public abstract class ServiceOutput extends ServiceParameter {


    private static final Logger log = Logger.getLogger(String.valueOf(ServiceOutput.class));

    /**
     * Successful completion of Tuxedo service.
     */
    public static int TUX_APP_SUCCESS = 0;

    // Tux application code received from the Tux service
    private int nTuxAppCode = -1;


    /**
     * Returns the application status code received from the Tuxedo service.
     *
     * @return Value of Tuxedo application status code.
     */
    public int getApplicationCode() {
        return (this.nTuxAppCode);
    }


    /**
     * Sets the value of the Tux application code.
     */
    protected void setApplicationCode(int nValue) {
        log.info("setApplicationCode(" + nValue + ")");

        this.nTuxAppCode = nValue;
    }


    /**
     * Populates the FML buffer with the values specified in the result dataset received from the Tux service.
     *
     * @param ds Result dataset from Tux service.
     * @throws BSLFokusException
     */
    protected void populateFmlBuffer(Result ds) throws BSLFokusException {

        log.info("populateFmlBuffer()");

        String sName = null;
        int nNoOfValues = -1;
        for (int i = 0; i < this.fmlBuffer.length; i++) {
            sName = fmlBuffer[i].getName();
            nNoOfValues = ds.getCount(sName);

            for (int j = 0; j < nNoOfValues; j++) {
                fmlBuffer[i].setValue(j, ds.getValue(sName, j, null));
            }
        }

        // Set the Tux application code
        this.setApplicationCode(ds.getApplicationCode());
    }

    /**
     * Populates the FML buffer with the values specified in the result dataset received from the Tux service.
     * Used for testing only since Result's constructor is protected...
     */
    protected void populateFmlBuffer
            (DataSet
                    ds, int applicationCode) throws BSLFokusException {
        String sName = null;
        int nNoOfValues = -1;
        for (int i = 0; i < this.fmlBuffer.length; i++) {
            sName = fmlBuffer[i].getName();
            nNoOfValues = ds.getCount(sName);

            for (int j = 0; j < nNoOfValues; j++) {
                fmlBuffer[i].setValue(j, ds.getValue(sName, j, null));
            }
        }

        // Set the Tux application code
        this.setApplicationCode(applicationCode);
    }

}