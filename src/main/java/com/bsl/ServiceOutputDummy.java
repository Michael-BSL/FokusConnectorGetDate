package com.bsl;

import bea.jolt.pool.DataSet;
import bea.jolt.pool.Result;
import com.bsl.exceptions.BSLFokusException;

import java.util.Iterator;
import java.util.Set;

/**
 * Service parameter class used for debugging/logging purposes only.
 * Used for wrapping a {@link Result} object as a ServiceParameter object
 * as a quick hack... Should have been designed into the TuxedoService framework...
 *
 * @author Ninja Team
 * @author rsc
 * @version 7.0
 * @since Jun 11, 2003
 */
public class ServiceOutputDummy extends ServiceOutput {


    /**
     * Holds default values for fields in FML buffer.
     */
    private static String defaultValues[] = null;

    /**
     * Constructor used to wrap a {@link Result} object.
     *
     * @param rs
     * @throws BSLFokusException
     */
    public ServiceOutputDummy(Result rs) throws BSLFokusException {
        createFmlBuffer(rs);
        populateFmlBuffer(rs);
    }

    /**
     * Constructor used for testing only!
     *
     * @param ds
     * @param applicationCode
     * @throws BSLFokusException
     */
    public ServiceOutputDummy(DataSet ds, int applicationCode) throws BSLFokusException {
        createFmlBuffer(ds);
        populateFmlBuffer(ds, applicationCode);
    }


    /**
     * Populates the FML buffer based on the given DataSet.
     * Only the name field of the {@link FmlField}s is populated properly.
     * The rest (type, size, default value, sequence number and max occurrence)
     * are defaulted to dummy values since they have not been retrieved from the
     * database...
     *
     * @param ds output from Tuxedo.
     * @throws BSLFokusException
     */
    private void createFmlBuffer(DataSet ds) throws BSLFokusException {

        Set<?> keysSet = ds.keySet();
        int numKeys = keysSet.size();

        this.fmlBuffer = new FmlField[numKeys];

        String sKey;
        int numValuesForKey;
        Iterator<?> iterKeys = keysSet.iterator();
        for (int i = 0; iterKeys.hasNext(); i++) {
            sKey = (String) iterKeys.next();
            numValuesForKey = ds.getCount(sKey);

            this.fmlBuffer[i] = new FmlField(sKey, FmlField.TYPE_TEST, -1, null, i, numValuesForKey);
        }

    }

    /**
     * Sets dummy default values for testing only!
     *
     * @param sServiceName  Name of the service which the FML buffer relates to.
     * @param nBufferLength Number of fields in the FML buffer.
     * @return Array of string containing default values for the FML fields in the FML buffer.
     *
     */
    protected String[] getDefaultValues(String sServiceName, int nBufferLength)  {

        String defaultValues[] = new String[nBufferLength];
        String sDefaultValue;
        int nFieldSeq;
        for (int i = 0; i < nBufferLength; i++) {
            nFieldSeq = 1;
            sDefaultValue = "123";
            defaultValues[nFieldSeq - 1] = sDefaultValue;
        }
        return defaultValues;
    }

    /**
     * Used for testing only!
     *
     * @throws BSLFokusException
     */
    public Integer get_GENERATION() throws BSLFokusException {
        return ((Integer) this.fmlBuffer[0].getValue(0));
    }

    /**
     * Used for testing only!
     *
     * @throws BSLFokusException
     */
    public void set_GENERATION(Integer value) throws BSLFokusException {
        this.fmlBuffer[0].setValue(0, value);
    }

    /**
     * Used for testing only!
     *
     * @throws BSLFokusException
     */
    public Integer get_DIRECTIVE() throws BSLFokusException {
        return ((Integer) this.fmlBuffer[1].getValue(0));
    }

    /**
     * Used for testing only!
     *
     * @throws BSLFokusException
     */
    public void set_DIRECTIVE(Integer value) throws BSLFokusException {
        this.fmlBuffer[1].setValue(0, value);
    }

    /**
     * Used for testing only!
     *
     * @throws BSLFokusException
     */
    public Integer get_BAN(int index) throws BSLFokusException {
        return ((Integer) this.fmlBuffer[2].getValue(index));
    }

    /**
     * Used for testing only!
     *
     * @throws BSLFokusException
     */
    public void set_BAN(Integer value, int index) throws BSLFokusException {
        this.fmlBuffer[2].setValue(index, value);
    }

    /**
     * Used for testing only!
     *
     * @throws BSLFokusException
     */
    public int get_BAN_size() throws BSLFokusException {
        return this.fmlBuffer[2].getCount();
    }


}

