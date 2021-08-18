package com.bsl;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * This class should be extended. It provides <i>some</i> functionality for a
 * registry-like stuff....
 * @author Håkan Gustavsson
 * @version $Id: AbstractRegistry.java,v 1.3 2003/04/16 07:44:47 hakgu Exp $
 */
public abstract class AbstractRegistry
{
    /**
     * The name of the root element.
     * Set when initialised, used when/if dumping to XML.
     */
    private String m_sRootElement;

    /** The file that the settings were read from (if any). */
    private String m_sFilename;

    //--------------------------- Private methods to initialize the member fields

    /**
     * Adds the specified field to the node.
     * @param oNode  The node.
     * @param aField The field to add.
     * @return The passed node-reference.
     * @exception IllegalAccessException If the field could not be read.
     */
//    private final Node add(final Node oNode, final Field aField)
//            throws IllegalAccessException {
//        final RegEntry oEntry     = new RegEntry(this, aField);
//        final String[] sPaths     = oEntry.getNames();
//        Node           oChildNode = oNode;
//        int            nIdx       = 0;
//
//        //
//        // Early escape in case of root vs global member mismatch.
//        //
//        if (!oNode.getNodeName().equals(sPaths[0])) {
//            return oNode;
//        }
//        final NodeList oLsGrandChildren = oChildNode.getChildNodes();
//
//        //
//        // Locate the first child-node that hasn't been created yet.
//        //
//        for (nIdx = 1; nIdx < sPaths.length; ++nIdx) {
//            final Node oGrandchildNode = getChildNode(oChildNode, sPaths[nIdx]);
//            if (oGrandchildNode == null) {
//                break;
//            }
//            oChildNode = oGrandchildNode;
////            System.out.println(
////                    "AbstractRegistry:68; nIdx=[" + nIdx + "], path=[" + sPaths[nIdx] + "], oChildNode=" + ((oChildNode == null)
////                            ? "null"
////                            : ("{name=[" + oChildNode.getNodeName() + "]}")));
//        }
//
//        //
//        // Create all elements prior to the node that carries the actual value (data).
//        //
//        for (int i = nIdx, n = sPaths.length - 1; i < n; ++i, ++nIdx) {
////            System.out.println(
////                    "AbstractRegistry:78; nIdx=[" + nIdx + "], i=[" + i + "], n=[" + n + "], path=[" + sPaths[i] + "], oChildNode=" + ((oChildNode == null)
////                            ? "null"
////                            : ("{name=[" + oChildNode.getNodeName() + "]}")));
//            final Node oNewNode = oNode.getOwnerDocument().createElement(sPaths[i]);
////            System.out.println(
////                    "AbstractRegistry:82; nIdx=[" + nIdx + "], i=[" + i + "], n=[" + n + "], path=[" + sPaths[i] + "], About to create oNewNode=" + ((oNewNode == null)
////                            ? "null"
////                            : ("{name=[" + oNewNode.getNodeName() + "]}")));
//            oChildNode.appendChild(oNewNode);
//            oChildNode = oNewNode;
//        }
//        final Node oNewNode = oNode.getOwnerDocument().createElement(sPaths[nIdx]);
//        oNewNode.appendChild(oNode.getOwnerDocument().createTextNode(oEntry.value));
////        System.out.println(
////                    "AbstractRegistry:96; nIdx=[" + nIdx + "], path=[" + sPaths[nIdx] + "], About to create oNewNode=" + ((oNewNode == null)
////                            ? "null"
////                            : ("{name=[" + oNewNode.getNodeName() + "], textValue=[" + oEntry.value + "]}")));
//        oChildNode.appendChild(oNewNode);
//
//        //
//        // Return the Node passed as argument to this method...
//        //
//        return oNode;
//    }

    /**
     * Prints the content of the class as XML to the specified printstream.
     * @param out The printstream.
     * @return <code>true</code> if the content was printed, otherwise
     * <code>false</code>.
     * @exception IOException If the stream could not be printed to.
     */
//    private final boolean dump(final PrintStream out) throws IOException {
//        //
//        // Initialize the root, if required...
//        //
//        initRootElement();
//
//        try {
//            //
//            // Create the XML document...
//            //
//            final DocumentBuilderFactory dbfac      = DocumentBuilderFactory.newInstance();
//            final DocumentBuilder        docBuilder = dbfac.newDocumentBuilder();
//            final Document               doc        = docBuilder.newDocument();
//
//            //
//            // Create the XML root, and add it to the document.
//            // Add a comment as well, indicating which file based it on.
//            //
//            final Element root = doc.createElement(m_sRootElement);
//            doc.appendChild(root);
//            if (!StringUtil.isNullOrEmpty(m_sFilename)) {
//                final Comment comment = doc.createComment(StringUtil.concat(
//                        " Initialised from file [", m_sFilename, "] "
//                ));
//                //root.appendChild(comment);
//                doc.appendChild(comment);
//            }
//
//            //
//            // Now iterate all members of this class (or more likely it's sub-class),
//            // and add them as XML nodes.
//            //
//            final Field[] fields = getClass().getFields();
//            for (int i = 0; i < fields.length; ++i) {
//                add(root, fields[i]);
//            }
//
//            //
//            // Output it.
//            //
//            final TransformerFactory transfac = TransformerFactory.newInstance();
//            final Transformer        trans    = transfac.newTransformer();
//            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,             "no");
//            trans.setOutputProperty(OutputKeys.INDENT,                           "yes");
//            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//
//            final DOMSource          source = new DOMSource(doc);
//            final Result             result = new StreamResult(out);
//            trans.transform(source, result);
//
//            out.flush();
//        } catch (Throwable t) {
//            final IOException ioe = new IOException("Unable to create and populate XML");
//            ioe.initCause(t);
//            throw ioe;
//        }
//
//        //
//        // Return the boolean we promised by declaring the method to return one... :-)
//        //
//        return true;
//    }

//    private final void initRootElement() {
//        if (m_sRootElement != null) {
//            return;
//        }
//
//        final Field[] fields = getClass().getFields();
//        if (fields.length == 0) {
//            return;
//        }
//        final String s = fields[0].getName();
//        final int    i = s.indexOf('_');
//        if (i != -1) {
//            m_sRootElement =  s.substring(0, i);
//        }
//    }

    /**
     * 'Expands' the specified string using the specified <tt>Template</tt> and
     * the system properties. Passing the string "I am user ${user.name}" while
     * the JVM is run by the user "root", would return the string "I am user root".
     * @param s The string to 'expand'.
     * @return A string.
     */
    private static final String expand(final String s) {
        if (StringUtil.isNullOrEmpty(s)) {
            return s;
        }
        try {
            return Template.expand(s, System.getProperties(), true);
            //return new Template(s).expand(System.getProperties(), true);
        } catch (Throwable t) {
            t.printStackTrace(System.out);
        }

        // Since the preparation failed, return the original String...
        return s;
    }

    /**
     * Reads the specified file, and returns a <code>Properties</code> object.
     * @param filename The file to read.
     * @return A properties object.
     * @throws IOException If the file could not be read or if it was invalid.
     */
    private final Properties readFile(final String filename) throws IOException {
        final int     nIdx    = filename.lastIndexOf('.');
        final String  sSuffix = (nIdx == -1) ? null : filename.substring(nIdx).toLowerCase();
          //
        // Treat it as a regular properties file.
        //
        final Properties p = new Properties();
        p.load(new FileInputStream(filename));
        return p;
    }

    /**
     * Sets the the specified field in the specified object to the specified
     * value using the specified map.
     * @param oObject  The object containing the field.
     * @param aField   The field to set.
     * @param sValue   The value to set.
     * @throws IllegalAccessException If the field could not be set.
     */
    private final void setField(final Object oObject,
                                final Field aField,
                                final String sValue)
            throws IllegalAccessException {
        // Fetch the type of the current field.
        final int nType = ClassNameMap.getClassIdWithName(
                aField.getType().getName()
        );

        // Set the value of the field with the most appropriate method
        switch (nType) {
            case ClassNameMap.JAVA_LANG_STRING:
                aField.set(oObject,
                        sValue);
                break;
            case ClassNameMap.PRIMITIVE_INT:
                aField.setInt(oObject,
                        Integer.parseInt(sValue));
                break;
            case ClassNameMap.PRIMITIVE_SHORT:
                aField.setShort(oObject,
                        Short.parseShort(sValue));
                break;
            case ClassNameMap.PRIMITIVE_LONG:
                aField.setLong(oObject,
                        Long.parseLong(sValue));
                break;
            case ClassNameMap.PRIMITIVE_FLOAT:
                aField.setLong(oObject,
                        Long.parseLong(sValue));
                break;
            case ClassNameMap.PRIMITIVE_DOUBLE:
                aField.setLong(oObject,
                        Long.parseLong(sValue));
                break;
            case ClassNameMap.PRIMITIVE_CHAR:
                aField.setChar(oObject,
                        sValue.charAt(0));
                break;
            case ClassNameMap.PRIMITIVE_BOOLEAN:
                aField.setBoolean(oObject,
                        Boolean.valueOf(sValue).booleanValue());
                break;
            case ClassNameMap.JAVA_LANG_BOOLEAN:
                aField.set(oObject,
                        Boolean.valueOf(sValue));
                break;
            case ClassNameMap.JAVA_LANG_CHARACTER:
                aField.set(oObject,
                        new Character(sValue.charAt(0)));
                break;
            case ClassNameMap.JAVA_LANG_DOUBLE:
                aField.set(oObject,
                        Double.valueOf(sValue));
                break;
            case ClassNameMap.JAVA_LANG_FLOAT:
                aField.set(oObject,
                        Float.valueOf(sValue));
                break;
            case ClassNameMap.JAVA_LANG_INTEGER:
                aField.set(oObject,
                        Integer.valueOf(sValue));
                break;
            case ClassNameMap.JAVA_LANG_LONG:
                aField.set(oObject,
                        Long.valueOf(sValue));
                break;
            case ClassNameMap.JAVA_LANG_SHORT:
                aField.set(oObject,
                        Short.valueOf(sValue));
                break;
            case ClassNameMap.JAVA_LANG_StringBuilder:
                aField.set(oObject,
                        new StringBuilder(sValue));
                break;
            default:
                aField.set(oObject,
                        (Object)sValue);
                break;
        }
    }

    /**
     * Sets the public fields in the specified class, based on the specified
     * properties.
     * @param oObject The object in which to set the fields.
     * @param p       The properties.
     * @return The number of fields that were set.
     * @throws IOException If no fields could be set.
     */
    private final int setFields(final Object oObject, final Properties p) throws IOException {
        int nItems   = 0;

        //
        // Traverse all fields in the properties object
        //
        for (Enumeration oEnum = p.propertyNames(); oEnum.hasMoreElements();) {
            String       sName = (String)oEnum.nextElement();
            final String sVal  = p.getProperty(sName);

            //
            // The value we just recieved is formatted like this:
            // >  "mother.webmail.user.password"
            // since you can't have dots (.) in a field name in Java(TM)
            // we need to replace these with underscores (_).
            //
            sName = sName.replace('.', '_');

            //
            // Set the root element the first time this is run.
            //
            if (nItems == 0) {
                final int nIndex = sName.indexOf('_');
                m_sRootElement = (nIndex == -1) ? sName : sName.substring(0, nIndex);
            }

            try {
                setField(
                        oObject,
                        oObject.getClass().getField(sName),
                        expand(sVal)
                );
                ++nItems;
            } catch (NoSuchFieldException nsfe) {
                if (!StringUtil.isNullOrEmpty(sVal)) {
                    System.out.println(StringUtil.concat(
                            "AbstractRegistry(); not in registry: ", sName, " (value=[", sVal, "])"
                    ));
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(StringUtil.concat(
                        "AbstractRegistry(); Failed to set ", sName, "=[", sVal, "], cause: ",
                        StringUtil.getStackTraceAsString(iae)
                ));
            } catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return nItems;
    }

    //------------------------------------------ Private methods to locate a file

    /**
     * Reads the specified system property, splits the value using the specified
     * separators, and adds each segment to the specified list.
     * @param list        The list to which to add the segments.
     * @param sProperty   The name of the system property to read.
     * @param sSeparators The separators to separate the value by.
     */
    private final void add(final List   list,
                           final String sProperty,
                           final String sSeparators) {
        final String sVal = System.getProperty(sProperty);
        if (sVal == null) {
            return;
        }
        final String[] s = StringUtil.split(sVal, sSeparators);
        for (int i = 0; i < s.length; i++) {
            final File f = getDirectory(s[i]);
            if (f != null) {
                list.add(f.getAbsolutePath());
            }
        }
    }

    /**
     * Checks whether a file exists and is readable.
     * @param f The file to validate.
     * @return <code>true</code> if the file exists and is readable,
     *         otherwise <code>false</code>.
     */
    private final boolean fileExists(final File f) {
        if (!f.exists()) {
            return false;
        }
        if (!f.isFile()) {
            return false;
        }
        if (!f.canRead()) {
            System.err.println(StringUtil.concat(
                    "AbstractRegistry(); File exists but cannot be read: ",
                    f.getAbsolutePath()
            ));
            return false;
        }
        return true;
    }

    /**
     * Checks whether a file with the specified relative or full name exists and
     * is readable.
     * @param sFile The relative or full pathname to validate.
     * @return <code>true</code> if the file exists and is readable,
     *         otherwise <code>false</code>.
     */
    private final boolean fileExists(final String sFile) {
        return fileExists(new File(sFile));
    }

    /**
     * Searches for the files with the specified names using the paths available
     * in the system properties (classpath folders etc.). The first of the located
     * files are returned.
     * @param oLsNames The (relative of full) names of the file to find.
     * @return A <code>File</code> object if the file was found,
     *         otherwise <code>null</code>
     */
    private final File findFile(final List oLsNames) {
        if (oLsNames == null || oLsNames.size() == 0) {
            return null;
        }
        // Create a list of all directories in which the file might be located...
        final List   oLsDirs        = new ArrayList();
        final String sPathSeparator = System.getProperty("path.separator");
        add(oLsDirs, "user.dir",          sPathSeparator);
        add(oLsDirs, "user.home",         sPathSeparator);
        add(oLsDirs, "java.class.path",   sPathSeparator);
        add(oLsDirs, "java.library.path", sPathSeparator);

        for (Iterator it = oLsNames.iterator(); it.hasNext();) {
            final File f = findFile((String)it.next(), oLsDirs);
            if (f != null) {
                return f;
            }
        }
        return null;
    }

    /**
     * Searches for a file with the specified name using the paths available
     * in the system properties (classpath folders etc.).
     * @param sName The (relative of full) name of the file to find
     * @param dirs  A list of the directories where the file might be located.
     * @return A <code>File</code> object if the file was found,
     *         otherwise <code>null</code>
     */
    private final File findFile(final String sName, final List dirs) {
        System.err.println(StringUtil.concat(
                getClass().getName(), ".findFile(); Attempting to find registry configuration file: ", sName
        ));

        final String sFileSeparator = System.getProperty("file.separator");
        for (Iterator it = dirs.iterator(); it.hasNext();) {
            final File f = new File(StringUtil.concat(it.next().toString(), sFileSeparator, sName));
            if (fileExists(f)) {
                return f;
            }
        }
        return null;
    }


    /**
     * Returns the first existing directory in the specified filename. If the
     * passed string is "/home/nosuchdir/file.txt" and the directory
     * "/home/nosuchdir" doesn't exist, "/home" is returned.
     * @param sFile
     * @return A <code>File</code> object of the first existing directory in
     * the specified file, or <code>null</code> if none was found.
     */
    private final File getDirectory(final String sFile) {
        for (File f = new File(sFile); f != null;) {
            if (f.exists() && f.isDirectory()) {
                return f;
            }
            f = f.getParentFile();
        }
        return null;
    }

    /**
     * Attempts to locate the file in any of the directories specified in the
     * classpath, in the home- or current directory.<p>
     * If the specified configuration file is "my1stdir/my2nddir/file.ini",
     * the following files are looked for:
     * <ul>
     *   <li>"my1stdir/my2nddir/file.ini"
     *   <li>"my2nddir/file.ini"
     *   <li>"file.ini"
     * </ul>
     * @param sFile
     * @return A <code>File</code> or <code>null</code> if the file was not found.
     */
    private final File locateConfiguration(final String sFile) {
        // Create the list of names to locate...
        final List list = new ArrayList();
        list.add(sFile);

        final String[] s = StringUtil.split(sFile, "/\\");
        if (s.length != 0) {
            for (int i = 1; i < s.length; i++) {
                list.add(StringUtil.join(s, i, System.getProperty("file.separator")));
            }
        }
        // Check if we can locate the Log4j configuration file.
        return findFile(list);
    }



    /**
     * Reads the file with the specified name. If the file ends with ".xml", it's
     * treated as an XML file, otherwise it's assumed to be a regular properties
     * file.
     * <p/>
     * When the file is read, any public fields in the subclass that matches the
     * x-path like names from the file are set.
     * @param filename The name of the file to read.
     * @return <code>true</code> if the file exists and was read, otherwise
     * <code>false</code>.
     */
    protected final boolean setup( String filename) {
        if (filename == null || filename.length() == 0) {
            return false;
        }
        filename = new DatasourceFormatter(filename).invoke();
        boolean bResult = true;
        try {
            // This will become the file to read from.
            File f = null;

            // Check if the specified file exists.
            if (fileExists(filename)) {
                f = new File(filename);
            } else {
                // Check if we can locate the registry configuration file.
                f = locateConfiguration(filename);
                if (f == null) {

                    System.out.println(StringUtil.concat(
                            getClass().getName(), ".setup(); Unable to locate file '",
                            filename, "'; Unable to use other settings than the default"
                    ));
                    return false;
                }
            }

            // Store the filename for future use...
            m_sFilename = f.getAbsolutePath();

            // Fetch the properties.
            final Properties p = readFile(f.getAbsolutePath());
            // Set all public fields.
            setFields(this, p);
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            bResult = false;
        } finally {
            return bResult;
        }
    }


    public class DatasourceFormatter {
        private String filename;

        public DatasourceFormatter(String filename) {
            this.filename = filename;
        }

        public String invoke() {
            if (filename.endsWith("DataSource.xml")){
                int dsIndex = filename.indexOf("DataSource.xml");
                int dbIndex = filename.indexOf("db");
                String prefixFolder = filename.substring(0,dbIndex+2);
                String dsName = filename.substring(dbIndex+3,dsIndex).toLowerCase();
                if (dsName.startsWith("fokus")){
                    dsName = "fokus"+"_"+dsName.substring(dsName.lastIndexOf("fokus")+5,dsName.length());
                }else if (dsName.startsWith("repdb")){
                
                    dsName = "fokus"+"_"+"rep";
                }

                String newFileName = prefixFolder+filename.charAt(dbIndex+2)+dsName+"_"+"data_source"+".xml";
                filename = newFileName;
            }
            return filename;
        }
    }
}