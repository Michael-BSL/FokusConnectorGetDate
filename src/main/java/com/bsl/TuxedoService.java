package com.bsl;

import bea.jolt.pool.*;

import com.bsl.exceptions.BSLFokusException;
import weblogic.wtc.jatmi.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


/**
 * Abstract ancestor class for all all Tuxedo service classes.<br>
 * For debugging the FML buffers (<code>bea.jolt.pool.DataSet</code>) refer to
 * the {@link DebugManager} and {@link ServiceInvocation} classes.
 * For management of the debugging from the WebLogic Administration Console via JMX refer to
 *
 * @author Ninja Team
 * @author jel
 * @author rsc
 * @version 7.0
 * @since 7.0
 */
public abstract class TuxedoService
{
    private static       int          s_nSessionPoolCount    = 0;


    /** Object used to debug the FML buffers (<code>bea.jolt.pool.DataSet</code>) of Tuxedo services. */

    public  static       int          TUX_SESSION_POOL_ERROR = 1;
    public  static       int          TUX_APPLICATION_ERROR  = 2;
    public  static       int          TUX_SERVICE_ERROR      = 3;



    /**
     * Logger utility.
     */
    private static final Logger log = Logger.getLogger(String.valueOf(TuxedoService.class));

    /**
     * Object used to debug the FML buffers (<code>bea.jolt.pool.DataSet</code>) of Tuxedo services.
     */
    private static DebugManager DEBUG_MANAGER = null;
    /*
     * Some TUXEDO services do not fail, but set an error string in the output.
     */
    public static final String NO_ERROR = "NO-ERROR";

    /**
     * MMPARAM_FML_SZ - constant representing the String length of Memo Parameters.
     */
    public static final int MMPARAM_FML_SZ = 51;

    /**
     * DATE_FML_SZ  - constant representing the String length of simple date fields - YYYYMMDD.
     */
    public static final int DATE_FML_SZ = 9;
    /**
     * BLFRTXT_FML_SZ - constant representing the String length of BillText.
     */
    public static final int BLFRTXT_FML_SZ = 40;
    /**
     * CKTID_FML_SZ - constant representing the String length of InternalCircuitId.
     */
    public static final int CKTID_FML_SZ = 21;

    public static final int CSM_FTR_BUFFSIZE = 400;


    // Name of the WebLogic Jolt pool. Making this static will ensure that we only perform the lookup once
    //    private static String joltPoolName = null;

    /**
     * Indicates whether we are using Jolt or Tuxedo Connector.
     */
    private static boolean usingJolt = true;

    /**
     * Indicates whether we are using Jolt or Tuxedo Connector.
     */
    //private static String sTuxConnType = SystemDefaultsReferenceTable.getTuxedoConnectionType();
    private static String sTuxConnType = "JOLT";

    /**
     * Constant for JOLT
     */
    private static String JOLT = "JOLT";
    /**
     * Constant for Weblogic Tuxedo Connector
     */
    private static String WTC = "WTC";

    /**
     * ID of user who instanciated the service.
     */
    private Integer operatorId = null;
    /**
     * Name of Tuxedo service.
     */
    protected String name = null;
    /**
     * Input value object for service.
     */
    protected ServiceParameter input = null;
    /**
     * Output value object for service.
     */
    protected ServiceOutput output = null;



    public TuxedoService() {
    }

    /**
     * Instanciates a TuxedoService object.
     *
     * @param operatorId   ID of user who instanciates the object.
     * @param sServiceName Name of the Tuxedo service to instanciate.
     */
    protected TuxedoService(Integer operatorId, String sServiceName) {
        this.operatorId = operatorId;
        this.name = sServiceName;

    }

    /**
     * Executes the Tuxedo service.
     * TODO: this method relies on Jolt specific datatypes! Should be redesigned (rsc) to be implementatin neutral.
     * TODO: the handling of output (in subclasses) should be redesigned (rsc).
     *
     * @throws BSLFokusException
     */
    protected Result execute() throws BSLFokusException, BSLFokusException {
            log.info("execute()");
        

        Result res   = null;
        Reply  reply = null;
        log.info("Tuxedo connection type is " + sTuxConnType + ". Will use JOLT if null ...");


            //-----------------------------------------------------------------------------------

            res = executeJolt();

                try {
                    // We need to create the corresponding output object...
                        final String sClassName = getClass().getName();

                    // Load the class using reflection...
                    //inl 05.11.07 - there is no such tuxedo service cvFolder.
                    // This is a dummy name therefore doesn't have any real FML output that can be printed.
                    // csApiFldr is the real name of the service and will be printed.out

                        final ServiceOutput oOutput = getServiceOutput(sClassName, res);

                        // If we received null, the error has been logged elsewhere,
                        // so we should only bother about logging.
                        if (oOutput != null) {
                            // We've got the class, let's log! :)
                            String output =  JoltServiceLogFormatUD.doFormat(
                                    this.name,
                                    res,
                                    ServiceParameter.TOSTRING_PADDING,
                                    oOutput.getFMLFieldNames()
                            );

                            String ban = res.get("BAN").toString().replace("[","").replace("]","");

                            log.info(output);

                            //System.out.println(output);
                            write2File(ban,output);

                        }

                } catch (Throwable t) {
                    log.warning("execute(); Failed to log output for Service=[" + this.name + "]" +  t);
                }



        return res;
    }




    /**
     * Executes the Tuxedo service with the values which are set in the input buffer.
     *
     * @return A raw result dataset from the Jolt call.
     * @throws BSLFokusException
     */
    protected Result executeJolt() throws BSLFokusException{
        Transaction transaction = null;
        DataSet     dsInput     = null;
        String      sThreadName = Thread.currentThread().getName();

        Object oBanNo        = null;
        Object oCustomerId   = null;
        Object oSubscriberNo = null;
        Object oOperatorId   = null;
        Object oSubNo        = null;
        Object oCtn          = null;

        //..record Start Time
        long        lJoltConnStartTime = System.currentTimeMillis();
        SessionPool joltSession        = getSessionPool();

        if (joltSession == null) {
            throw new BSLFokusException("No Jolt connections available.", TUX_SESSION_POOL_ERROR);
        }
        //..accumulate Jolt Connection Times
//        ExecutionTimes.addToTime(sThreadName, ExecutionTimes.JOLT_CONN, System.currentTimeMillis() - lJoltConnStartTime);
        boolean           stopTuxCall  = false;
     //   DebugManager      debugManager = TuxedoService.getDebugManager();
        ServiceInvocation invoc        = null; //Needed both before and after call (if debugging).
        Result            result       = null;
        try {


            dsInput = this.input.getDataSet();


            oBanNo        = dsInput.getValue("BAN",           null);
            oCustomerId   = dsInput.getValue("CUSTOMER_ID",   null);
            oOperatorId   = dsInput.getValue("OPERATOR_ID",   null);
            oSubscriberNo = dsInput.getValue("SUBSCRIBER_NO", null);
            oSubNo        = dsInput.getValue("SUBNO",         null);
            oCtn          = dsInput.getValue("CTN",           null);

            log.info(JoltServiceLogFormatUD.doFormat(this.name, dsInput, ServiceParameter.TOSTRING_PADDING, input.getFMLFieldNames()));


            if (!stopTuxCall) {
                boolean    bError             = true; // HGU: Assume error until proven otherwise.
                long       lJoltCallStopTime  = 0L;

                final long lJoltCallStartTime = System.currentTimeMillis();
                try {

                    //*** This is where the actual call to Tuxedo is executed ***:
                    result = joltSession.call(this.name, dsInput, transaction);
                    //bError = false; // HGU: OK, it wasn't an error.

                    // HGU: This call is considered an error if it returned null
                    //      or the application code wasn't 0.
                    bError = result == null ? true : result.getApplicationCode() != 0;

                } finally {
                    // HGU: Make sure we ALWAYS get the most correct stop-time, even in case of errors.
                    lJoltCallStopTime  = System.currentTimeMillis();

                    // HGU: Log in case of error or if infomercials are enabled.
                    if (bError ) {
                        final String s = new StringBuilder(128)
                                .append("TuxCall | ").append(this.name)
                                .append(" | Response Time: ").append(lJoltCallStopTime - lJoltCallStartTime)
                                .append("ms | Fokus User: ").append(oOperatorId)
                                .append(" | BAN: ").append(oBanNo)
                                .append("/").append(oCustomerId)
                                .append(" | Subscriber: ").append(oSubscriberNo)
                                .append("/").append(oSubNo)
                                .append("/").append(oCtn)
                                .toString();

                        // HGU: Log using Error-priority if an error has occured.
                        if (bError) {
                            log.warning(s);
                        } else {
                            log.info(s);
                        }
                    }
                }
//                 ExecutionTimes.addToTime(sThreadName, ExecutionTimes.TUX_CALL, lJoltCallStopTime - lJoltCallStartTime);

                    try {

                        ServiceOutputDummy serviceOutput = new ServiceOutputDummy(result);
                     //   System.out.println(this.name +  result.toString() +  serviceOutput.getFMLFieldNames());
                        log.info(this.name +  result.toString() +  serviceOutput.getFMLFieldNames());

                    } catch (Exception e) {
                        //Let's suppress this - we don't want the transaction to fail because of a logging format problem.
                        //We will log it (in a simple form that won't fail) though:
                        log.info("Problem logging output for Tuxedo service " + this.name + ". Exception: " + e);
                    }

            }

            // BEGIN Ninja Test
            if (invoc != null) {
                if (invoc.isEnabled()) {
                    invoc.setOutput(result);
                    invoc.saveDataSets();
                }
            }
            // END Ninja Test
        } catch (SessionPoolException spe) {
            // No connection available -> throw exception
            // This exception is thrown when there is an error in the session pool.
            log.warning("Tux Call Error | " + this.name + "\n No Jolt connections available" + spe);
            throw(new BSLFokusException("No Jolt connections available.", TUX_SESSION_POOL_ERROR));
        } catch (ApplicationException ae) {
            // Application error occurred -> throw exception
            // This exception is thrown when an application error occurs during a BEA Tuxedo service invocation.
            log.warning("Tux Call Error | " + this.name + "\n Output - " + printUDformat(result) + "\n Input Buffer - " + printUDformat(dsInput) + ae);
            throw(new BSLFokusException("Application exception occurred during the execution of the Tuxedo service: " + this.name + ".", TUX_APPLICATION_ERROR));
        } catch (ServiceException se) {
            // System error occurred -> throw exception
            // This exception is thrown when an error occurs in the execution of a BEA Tuxedo service.
            log.warning("ServiceException Details: " + se.getMessage() + " Errno: " + se.getErrno() + " ErrorDetail: " + se.getErrorDetail() + " - " + se.getStringErrorDetail());
            log.warning("Tux Call Error | " + this.name + "\n Output - " + printUDformat(result) + "\n Input Buffer - " + printUDformat(dsInput) + "\nTPERRNO = " + se.getErrno()+ se);
            if(se.getErrno() == ServiceException.TPENOENT){
                throw new BSLFokusException("Tuxedo server, containing " + this.name + " service is down.", TUX_SESSION_POOL_ERROR);
            } else if(se.getErrno() == ServiceException.TPEJOLT || se.getErrno() == ServiceException.TPETIME){
            	log.warning("This exception indicates a timeout because remote is too slow responding or is stuck: " + se.getErrno());
            	// Throw appropriate error code for higher level services to decode.
                throw new BSLFokusException("Tuxedo server, containing " + this.name + " has timed out.", 0);
            } else {
               throw(new BSLFokusException("Tuxedo system exception occurred during the execution of the Tuxedo service: " + this.name + ".", TUX_SERVICE_ERROR));
            }
        } catch (TransactionException te) {
            // Transaction error occurred -> throw exception
            // This exception is thrown when a transaction cannot be started, committed, or aborted
            log.warning("Tux Call Error | " + this.name + "\n Output - " + printUDformat(result) + "\n Input Buffer - " + printUDformat(dsInput) + te);
            throw(new BSLFokusException("Unexpected exception occurred during the execution of the Tuxedo service: " + this.name + ".", TUX_SERVICE_ERROR));
        }

        // Check the application status code
        if (result.getApplicationCode() != 0) {
            // The Tuxedo service did not terminate successfully -> throw exception
            log.warning("Tux Call Error | " + this.name + "\n Output - " + printUDformat(result) + "\n Input Buffer - " + printUDformat(dsInput));
         //   throw (new BSLFokusException(result.toString()));


        }

        //..Even If we get down here, we may have an Error in the Service....  So check the Error Buffer
        int nErrs = result.getCount("ERR_TEXT");
        if (nErrs != 0) {
            log.warning("Tux Call Error2 | " + this.name + "\n Output - " + printUDformat(result) + "\n Input Buffer - " + printUDformat(dsInput));
            throw (new BSLFokusException(result.toString()));

        }
        return result;
    }


    private final SessionPool getSessionPool() throws BSLFokusException {

            ++s_nSessionPoolCount;
            try {
                return JoltPoolManager.getSessionPoolForTuxedoService(name);
            } catch (Exception ne) {
                final BSLFokusException spue = new BSLFokusException(
                        "Unable to initialise and or retrieve Session Pool.", 0
                );
                spue.initCause(ne);
                throw spue;
            }

    }




    /**
     * Returns all attributes in this class.
     *
     * @return All attributes in the TuxedoService class.
     */
    public String toString() {
        String str =
                "{\n" +
                        //        "  joltPoolName=" + this.joltPoolName + "\n" +
                        "  sTuxConnType=" + sTuxConnType + "\n" +
                        "  name=" + this.name + "\n";

        if (this.input != null) {
            str = str + "  input=" + this.input.hashCode() + "\n";
        } else {
            str = str + "  input=null\n";
        }

        if (this.output != null) {
            str = str + "  output=" + this.output.hashCode() + "\n";
        } else {
            str = str + "  output=null\n";
        }

        str = str + "}";

        return (str);
    }


    /**
     * Prints the contects of the result dataset.
     */
    private String printUDformat(DataSet dset) {
        StringBuilder sDataSet = new StringBuilder();
        if (dset != null) {
            for (Enumeration keys = dset.keys(); keys.hasMoreElements();) {
                String key = (String) keys.nextElement();
                int i = dset.getCount(key);
                for (int j = 0; j < i; j++) {
                    Object tmpObj = dset.getValue(key, j, "null");
                    if (tmpObj instanceof Byte) {
                        tmpObj = TypeConverter.byteToString((Byte) tmpObj);
                    }
                    sDataSet.append("\n" + key + "\t|" + j + "\t|" + tmpObj);
                }
            }
        }
        return sDataSet.toString();
    }



    /**
     * Class managing instrumentation of Tuxedo service calls for debugging and
     * details about FML buffers (<code>bea.jolt.pool.DataSet</code>) created
     * after execution of the instrumented service invocations.
     */
    public static class DebugManager {

        private HashMap<String, Integer> hmInvocation = null;
        private HashMap<String, ServiceInvocation> hmServiceDS = null;
        private boolean enabled = false;

        /**
         * Creates a new disabled DebugManager object.
         */
        public DebugManager() {
            this(false);
        }

        /**
         * Creates a new DebugManager object.
         *
         * @param enabled whether or not the DebugManager opens in an enabled state (ready to start debugging).
         */
        public DebugManager(boolean enabled) {
            hmInvocation = new HashMap<String, Integer>();
            hmServiceDS = new HashMap<String, ServiceInvocation>();
        }

        /**
         * Gets a service invocation based on the given key.
         *
         * @see ServiceInvocation#getKey
         */
        public ServiceInvocation getServiceInvocation(String key) {
            return (ServiceInvocation) hmServiceDS.get(key);
        }


        /**
         * Gets the HashMap of all ServiceInvocation objects.
         * The keys have the format given by the {@link ServiceInvocation#getKey} method.
         */
        public HashMap<String, ServiceInvocation> getServiceInvocations() {
            return hmServiceDS;
        }

        /**
         * Increments the invocation counter for the specified service.
         *
         * @param serviceName name of service for which to increment the invocation counter.
         * @return the incremented counter.
         */
        public int incrementInvocation(String serviceName) {
            int nInvocation = 0;
            Integer invocationNr = (Integer) hmInvocation.get(serviceName);
            if (invocationNr != null) {
                nInvocation = invocationNr.intValue();
            }
            nInvocation++;
            invocationNr = new Integer(nInvocation);

            hmInvocation.put(serviceName, invocationNr);

            return invocationNr.intValue();
        }

        /**
         * Resets all invocation counters for all instrumented services managed by this class.
         */
        public void resetInvocationCounters() {
            hmInvocation.clear();
        }

    }

    /**
     * Class holding instrumentation and debug details about a specific Tuxedo service call.
     */
    public static class ServiceInvocation {

        private String serviceName = null;
        private int invocation = -1;
        private boolean stopExcecution = false;

        private boolean enabled = true;

        private DataSet input = null;
        private DataSet output = null;

        private TypedFML32 inputTypedFML = null;
        private TypedFML32 outputTypedFML = null;

        private String path = null;

        public static final String KEY_DELIMITER = "_";

        /**
         * Gets the key for the given invocation parameters.
         * Format: servicename_counter (for example csLsNpTrx00_1).
         *
         * @param serviceName the name of the instrumented Tuxedo service call.
         * @param invocation  invocation number (starting with 1).
         */
        public static String getKey(String serviceName, int invocation) {
            String key = serviceName + KEY_DELIMITER + invocation;
            return key;
        }


        public String toString() {
            String s = "serviceName='" + getServiceName() + "', invocation='" + getInvocation() + "', enabled='" + enabled + "', stopExcecution='" + stopExcecution + "', KEY='" + getKey() + "'";
            return s;
        }

        /**
         * Gets the key for this invocation.
         * Format: servicename_counter (for example csLsNpTrx00_1).
         * This method also exists in a static version.
         */
        public String getKey() {
            return getServiceName() + KEY_DELIMITER + getInvocation();
        }

        /**
         * Validates whether this service invocation should be stopped before execution.
         */
        public boolean getStopExecution() {
            return stopExcecution;
        }

        /**
         * Enables or disables this service instrumentation.
         */
        public void setEnabled(boolean b) {
            enabled = b;
        }

        /**
         * Validates whether this service invocation should be intercepted and debugged.
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the input FML buffer of this service invocation.
         */
        public void setInput(DataSet ds) {
            input = ds;
        }

        /**
         * Sets the output FML buffer of this service invocation.
         */
        public void setOutput(DataSet ds) {
            output = ds;
        }

        /**
         * Gets the input FML buffer of this service invocation.
         */
        public DataSet getInput() {
            return input;
        }

        /**
         * Gets the output FML buffer of this service invocation.
         */
        public DataSet getOutput() {
            return output;
        }


        /**
         * Gets the name of this service invocation.
         */
        public String getServiceName() {
            return serviceName;
        }

        /**
         * Sets the name of this service invocation.
         */
        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        /**
         * Gets the instrumented invocation number for this service invocation.
         * First invocation is 1.
         */
        public int getInvocation() {
            return invocation;
        }

        /**
         * Sets the instrumented invocation number for this service invocation.
         * First invocation is 1.
         */
        public void setInvocation(int invocation) {
            this.invocation = invocation;
        }

        /**
         * Sets the instrumented invocation number for this service invocation.
         * First invocation is 1.
         */
        public void setSavePath(String path) {
            this.path = path;
        }

        /**
         * Stores the FML buffers to file if path is set (@see setSavePath()) and also
         * returns a stringified representation of them.
         */
        public String saveDataSets() {
            if (this.path != null) {
                try {
                    return saveDataSets(this.path, "\t", "\n");
                } catch (IOException e) {
                    log.warning(e.getMessage());
                    return null;
                }
            } else {
                return null;
            }
        }

        /**
         * Stores the FML buffers to file and also returns a stringified
         * representation of them.
         *
         * @param path
         * @throws IOException
         */
        public String saveDataSets(String path) throws IOException {
            return saveDataSets(path, "\t", "\n");
        }

        /**
         * Stores the FML buffers to file and also returns a stringified
         * representation of them.
         *
         * @param path
         * @param TAB  string used to represent the tabulator character.
         * @param NL   string used to represent the newline character.
         * @return
         * @throws IOException
         */
        public String saveDataSets(String path, String TAB, String NL) throws IOException {

            String sFileSep = System.getProperty("file.separator");
            Date date = new Date();
            if (path == null) {
                path = "";
                sFileSep = "";
            } else if (path.equals("")) {
                path = "";
                sFileSep = "";
            }
            String fileNameBase = path + sFileSep + getKey() + KEY_DELIMITER;
            String fileNameIn = null;
            String fileNameOut = null;
            String fileInfo = "";
            File file;
            if (input != null) {
                fileNameIn = fileNameBase + "INPUT" + KEY_DELIMITER + date.getTime() + ".ser";
                storeSerializedObject(fileNameIn, input);

                file = new File(fileNameIn);
                fileInfo += "Input saved in: '" + file.getAbsolutePath() + "'" + NL;
            }
            if (output != null) {
                fileNameOut = fileNameBase + "OUTPUT" + KEY_DELIMITER + date.getTime() + ".ser";
                storeSerializedObject(fileNameOut, output);

                file = new File(fileNameOut);
                fileInfo += "Output saved in: '" + file.getAbsolutePath() + "'" + NL;
            }

            boolean returnHTML = NL.equalsIgnoreCase("<BR>");
            String header = returnHTML ? "<html><head></head><body>\n" : "";
            String sInput = (input != null) ? input.toString() : "null";
            String sOutput = (output != null) ? output.toString() : "null";
            String trailer = returnHTML ? "</body></html>" : "";
            String s = header + fileInfo + NL + sInput + NL + NL + sOutput + NL + trailer;
            return s;
        }

        /**
         * Stores the given object in the given file.
         *
         * @param fileName
         * @param obj
         * @throws IOException
         */
        private void storeSerializedObject(String fileName, Object obj) throws IOException {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            log.info("Stored DataSet in file: '" + fileName + "'");

        }

    }

    private final ServiceOutput loadServiceOutput(final String sClassName, final Object[] oConstructorArgs) {
        try {
            Class       cls   = Class.forName(sClassName);
            Class[]     oArgs = new Class[] { Result.class };
            Constructor ctr   = cls.getConstructor(oArgs);
            Object      obj   = ctr.newInstance(oConstructorArgs);

            if (cls.isInstance(obj)) {
                return (ServiceOutput)obj;
            }
            log.warning(
                    "loadServiceOutput(); Class=[" + sClassName
                            + "] is not an instance of ServiceOutput"
            );
            return null;
        } catch (Throwable t) {
            log.warning(
                    "loadServiceOutput(); Failed to load ServiceOutput class with name=["
                    + sClassName + "]" +  t.getMessage()
            );
            return null;
        }
    }

    //Only used (once) by the Tuxedo logger only!!
    private final ServiceOutput getServiceOutput(final String sClassName, final Result res) {
        // The name of the class should only differ by the end,
        // therefore if they end with *Service, we could replace it with *Output
        if (!sClassName.endsWith("Service")) {
            log.warning("getServiceOutput(); Classname=[" + sClassName + "] does not end with *Service");
            return null;
        }
        final int    nDotIdx      = sClassName.lastIndexOf('.');
        final int    nSrvIdx      = sClassName.lastIndexOf("Service");
        String packageNamePrefix = null;
        //inl 05.11.07 - cvFolder is not a real service name therefor doesn't have FML buffer that can be printed out. The correct service to print out is csApiBan.

        packageNamePrefix = "com.bsl.services.";


        final String sOutputClass =
                packageNamePrefix+
                sClassName.substring(nDotIdx + 1, nSrvIdx)+ "Output";



        // Create the arguments to pass the constructor...
        final Object[] oConstructorArgs = new Object[] { res };

        // ...load the class using reflection...
        final ServiceOutput oOutput = loadServiceOutput(sOutputClass, oConstructorArgs);

        // ...and return it.
        return oOutput;
    }

    private void  write2File(String ban,String serviceOutput){
        try {
            String timeStamp =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName =  Constants.OUTPUT_FILE_PATH+ File.separator + this.name + "_" + ban + "_" + timeStamp + ".ud";
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(serviceOutput);
            myWriter.close();
            log.info("Output of " + serviceOutput + " successfully wrote to the file " + fileName);
        } catch (IOException e) {
            log.warning("Can't write to file: " + e.getMessage());
        }

    }
}
