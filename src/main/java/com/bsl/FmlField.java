/*
 * FmlField.java
 *
 * Created on 26. juli 2002, 14:13
 */
package com.bsl;


import com.bsl.exceptions.*;

import java.util.Arrays;
import java.util.Vector;


/**
 * This class represents a single field in a FML buffer, used when communicating with the
 * Fokus Tuxedo services.
 *
 * @author Ninja
 * @version 6.0
 */
public class FmlField {
    /**
     * Type used for testing only...
     */
    public static final int TYPE_TEST = 42;
    /**
     * Type Unknown.
     */
    public static final int TYPE_NULL = -1;
    /**
     * Type String - corresponds the STRING type in the FML buffer.
     */
    public static final int TYPE_STRING = 0;
    /**
     * Type Byte - corresponds to the CHAR type in the FML buffer.
     */
    public static final int TYPE_BYTE = 1;
    /**
     * Type Integer - corresponds to the LONG type in the FML buffer.
     */
    public static final int TYPE_INTEGER = 2;
    /**
     * Type short - corresponds to the SHORT type in the FML buffer.
     */
    public static final int TYPE_SHORT = 3;
    /**
     * Type double - corresponds to the DOUBLE type in the FML buffer.
     */
    public static final int TYPE_DOUBLE = 4;
    /**
     * Type 'array of bytes' - corresponds to the CARRAY in the FML buffer.
     */
    public static final int TYPE_BYTE_ARR = 5;

    private static final char CHAR_SPACE = ' ';

    // Name of FML field
    private String name = null;
    // Type of FML field
    private int type = -1;
    // If type is STRING, then this attribute contains its maximum length, else the value is -1
    private int size = -1;
    // The sequence of the field in the FML buffer
    private int sequence = -1;
    // The maximun number of values this field we can have
    private int maxOccurrence = -1;
    // The values of the field
    private Vector<Object> value = null;

    protected FmlField() {
    }

    /**
     * Creates a new instance of FmlField.
     *
     * @param sName          Name of FML field (Mandatory).
     * @param nType          Type of FML field (Mandatory).
     * @param nSize          Maximum length of FML field. (Mandatory when specifying a STRING type FML field. Will be
     *                       ignored when specifying any other type of FML fields.).
     * @param sDefaultValue  Default value of FML field (Optional).
     * @param nSequence      Sequence of FML field in FML buffer.
     * @param nMaxOccurrence Maximum number of values for FML field (Mandatory).
     * @throws BSLFokusException
     */
    public FmlField(String sName, int nType, int nSize, String sDefaultValue, int nSequence, int nMaxOccurrence)
            throws BSLFokusException {
        if (sName == null || sName.equals("")) {
            // Field name not specified -> throw exception
            throw(new BSLFokusException("Field name cannot be null or empty."));
        }

        if (nType != FmlField.TYPE_STRING && nType != FmlField.TYPE_BYTE && nType != FmlField.TYPE_INTEGER &&
                nType != FmlField.TYPE_SHORT && nType != FmlField.TYPE_DOUBLE && nType != FmlField.TYPE_BYTE_ARR && nType != FmlField.TYPE_TEST)
        {
            // Illegal type -> throw exception
            throw(new BSLFokusException("Illegal field type specified. Type: " + nType + "."));
        }

        if (nType == FmlField.TYPE_STRING && nSize < 1) {
            // String types must have a size greater the zero -> throw exception
            throw(new BSLFokusException("A field of type STRING cannot have a maximum length which is less than 1."));
        }

        if (nSequence < 0) {
            // Field sequence cannot be negative -> throw exception
            throw(new BSLFokusException("Field sequence cannot be less than 0."));
        }

        if (nMaxOccurrence < 1) {
            // Max. occurrences must be greater than zero -> throw exception
            throw(new BSLFokusException("Maximum occurrence of a field cannot be less than 1."));
        }

        this.name = sName;
        this.type = nType;
        this.sequence = nSequence;
        this.maxOccurrence = nMaxOccurrence;

        if (this.type == FmlField.TYPE_STRING) {
            this.size = nSize;
        }

        if (sDefaultValue != null) {
            this.value = new Vector<Object>();

            try {
                if (this.type == FmlField.TYPE_STRING) {
                    if (sDefaultValue.length() > this.size) {
                        // Default value is too long -> throw exception
                        throw(new BSLFokusException("Length of the default value is greater than the defined maximum length of the field."));
                    }
                    setValue(0, sDefaultValue);
                } else if (this.type == FmlField.TYPE_BYTE) {
                    setValue(0, new Byte((byte) sDefaultValue.charAt(0)));
                } else if (this.type == FmlField.TYPE_INTEGER) {
                    setValue(0, new Integer(sDefaultValue));
                } else if (this.type == FmlField.TYPE_SHORT) {
                    setValue(0, new Short(sDefaultValue));
                } else if (this.type == FmlField.TYPE_DOUBLE) {
                    setValue(0, new Double(sDefaultValue));
                } else if (this.type == FmlField.TYPE_TEST) {
                    setValue(0, sDefaultValue);
                }


            } catch (Exception e) {
                // Specified default value could not be converted into the specified type -> throw exception
                throw(new BSLFokusException("The specified default value cannot be converted into the proper type."));
            }
        }
    }


    /**
     * Returns the name of the FML field.
     *
     * @return Name of FML field.
     */
    protected String getName() {
        return (this.name);
    }


    /**
     * Returns the type of the FML field.
     *
     * @return Type of FML field.
     */
    protected int getType() {
        return (this.type);
    }


    /**
     * Return the number of values defined for the FML field.
     *
     * @return number of values defined for FML field.
     */
    public int getCount() {
        if (this.value == null) {
            return (0);
        } else {
            return (this.value.size());
        }
    }


    /**
     * Returns the maximum length of the FML field. The length only have any meaning,
     * if the type of the field is STRING. For any other type -1 is returned.
     *
     * @return Maximum length of FML field (only applicaple to STRINGs).
     */
    protected int getSize() {
        return (this.size);
    }


    /**
     * Returns the sequence of the FML field.
     *
     * @return Sequrnce of FML field in FML buffer.
     */
    protected int getSequence() {
        return (this.sequence);
    }



    /**
     * Returns a single value of a FML field.
     *
     * @param nIndex - Specifies which value must eb returned.
     * @return Single value of FML field.
     * @throws BSLFokusException
     */
    public Object getValue(int nIndex) throws BSLFokusException {
        if (nIndex < 0) {
            // Specified index is negative -> throw exception
            throw(new BSLFokusException("Specified value index cannot be less than 0."));
        }

        if (nIndex > this.maxOccurrence) {
            // The specified index is larger than the maximum number of allowed
            // values for the field -> throw exception
            throw(new BSLFokusException("Specified value index cannot be greater than the defined maximum occurrence for the field."));
        }

        if (this.value == null || nIndex >= this.value.size()) {
            return null;
        } else {
            return (this.value.elementAt(nIndex));
        }
    }


    /**
     * Returns all values defined for a field.
     *
     * @return Vector containing all values of FML field.
     */
    public Vector<Object> getValues() {
        return (this.value);
    }


    /**
     * Sets the value of the FML field.
     *
     * @param nIndex - Specified which value must be set.
     * @param oValue - The new value of the FML field.
     * @throws BSLFokusException
     */
    public void setValue(int nIndex, Object oValue) throws BSLFokusException {
        if (nIndex < 0) {
            // Specified index is negative -> throw exception
            throw(new BSLFokusException("In field name: " + getName() + ", specified value index cannot be less than 0."));
        }

        if (nIndex > this.maxOccurrence) {
            // Trying to go past the max allowed values for the field -> throw exception
            //Shahar change for future change rating null pointer exception avoidance
            throw(new BSLFokusException("In field name: " + getName() + " with value "+String.valueOf(oValue)+" , specified value index (" + nIndex + ") cannot be greater than the defined maximum occurrence for the field (" + this.maxOccurrence + ")."));
        }

        // Validate that the type of the specified value corresponds to the type of the FML field
        if (this.type == FmlField.TYPE_STRING && oValue != null && !(oValue instanceof String)) {
            throw(new BSLFokusException("In field name: " + getName() + ", type of specified object (" + oValue.getClass().getName() + ") does not match the type of the field (String)."));
        }

        if (this.type == FmlField.TYPE_BYTE && oValue != null && !(oValue instanceof Byte)) {
            throw(new BSLFokusException("In field name: " + getName() + ", type of specified object (" + oValue.getClass().getName() + ") does not match the type of the field (Byte)."));
        }

        if (this.type == FmlField.TYPE_INTEGER && oValue != null && !(oValue instanceof Integer)) {
            throw(new BSLFokusException("In field name: " + getName() + ", type of specified object (" + oValue.getClass().getName() + ") does not match the type of the field (Integer)."));
        }

        if (this.type == FmlField.TYPE_SHORT && oValue != null && !(oValue instanceof Short)) {
            throw(new BSLFokusException("In field name: " + getName() + ", type of specified object (" + oValue.getClass().getName() + ") does not match the type of the field (Short)."));
        }

        if (this.type == FmlField.TYPE_DOUBLE && oValue != null && !(oValue instanceof Double)) {
            throw(new BSLFokusException("In field name: " + getName() + ", type of specified object (" + oValue.getClass().getName() + ") does not match the type of the field (Double)."));
        }

        if (this.type == FmlField.TYPE_BYTE_ARR && oValue != null && !(oValue instanceof Byte[])) {
            throw(new BSLFokusException("In field name: " + getName() + ", type of specified object (" + oValue.getClass().getName() + ") does not match the type of the field (Byte[])."));
        }

        if (this.type == FmlField.TYPE_STRING && oValue != null && ((String) oValue).length() > this.size) {
            // String is too long -> throw exception
        	//KJN
            throw(new BSLFokusException( "In field name: " + getName() + ", length of specified string (" + oValue + ") exceeds the maximum string length (" + this.size + ")."));
        }

        if (this.value == null) {
            this.value = new Vector<Object>();
        }

        if (nIndex < this.value.size()) {
            // Replace existing value
            this.value.setElementAt(oValue, nIndex);
        } else if (nIndex == this.value.size()) {
            // Add to the end of the vector
            this.value.add(nIndex, oValue);
        } else {
            // We need to 'fill up' the to the specified index place..
            int size = this.value.size();
            for (int i = 0; i < nIndex - size; i++) {
                this.value.add(null);
            }

            // .. and the add the value
            this.value.add(oValue);
        }
    }


    /**
     * Sets the specified vector of values.
     *
     * @param vValues Vector of values to be set.
     */
    protected void setValues(Vector<Object> vValues) {
        this.value = vValues;
    }


    /**
     * Returns all attributes for the FML field.
     */
    public String toString() {
        String str =
                "{Name=" + this.name + ", " +
                        "Type=" + this.type + ", " +
                        "Size=" + this.size + ", " +
                        "Sequence=" + this.sequence + ", " +
                        "MaxOccurrence=" + this.maxOccurrence + ", " +
                        "Value=[";

        if (this.value == null || this.value.size() == 0) {
            str = str + "null";
        } else {
            for (int i = 0; i < this.value.size(); i++) {
                if (i == 0) {
                    if (this.value.elementAt(i) != null) {
                        str = str + this.value.elementAt(i).toString();
                    } else {
                        str = str + "null";
                    }
                } else {
                    if (this.value.elementAt(i) != null) {
                        str = str + ", " + this.value.elementAt(i).toString();
                    } else {
                        str = str + ", null";
                    }
                }
            }
        }

        str = str + "]}";

        return (str);
    }




    /**
     * Gets a String with length equal to name's length
     * filled with the replacement character.
     *
     * @param replacement replacement character.
     * @return name replacement String or null if name is null;
     */
    private String getNameReplacement(char replacement) {
        String nameReplacement = null;

        if (name != null) {
            char[] caNameReplacement = new char[name.length()];
            Arrays.fill(caNameReplacement, replacement);
            nameReplacement = new String(caNameReplacement);
        }

        return nameReplacement;
    }


}