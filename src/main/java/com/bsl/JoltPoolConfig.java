package com.bsl;


/**
 * Created by IntelliJ IDEA.
 * User: hakgu
 * Date: 5/10/11
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public final class JoltPoolConfig extends AbstractRegistry
{
    //--| These are initialised by the ancestor class |---------------------------------------------------------------


    //--| Private and static members |--------------------------------------------------------------------------------
    private static final String         s_sWhiteSpaceChars = " ,;\t\r\n\f";
    private static final JoltPoolConfig s_oInstance        = new JoltPoolConfig();

    //--| Constructor |-----------------------------------------------------------------------------------------------

    private JoltPoolConfig() {
        System.out.println("constructor");
       // setup(CONFIG_JOLT_FILE);
    }

    public static final JoltPoolConfig getInstance() {

        return s_oInstance;
    }

    public final Settings getSettings() {
        return new Settings() {
            public String getPoolName() {
                return Constants.JOLT_POOLNAME;
            }
            public String[] getAddressPrimary() {
                return StringUtil.split( Constants.JOLT_ADDRESS_PRIMARY, s_sWhiteSpaceChars);
            }
            public String[] getAddressFailover() {
                return StringUtil.split(Constants.JOLT_ADDRESS_FAILOVER, s_sWhiteSpaceChars);
            }
            public int getSizeMax() {
                return Constants.JOLT_SIZE_MAX;
            }
            public int getSizeMin() {
                return Constants.JOLT_SIZE_MIN;
            }
        };
    }

    /**
     * An interface through which a complete set of Jolt Connection Settings
     * can be passed to a Jolt connection.
     * @author H�kan Gustavsson
     */
    public interface Settings {
        /**
         * The name of the Jolt pool.
         * @return The Jolt pool name as a String.
         */
        public String   getPoolName();

        /**
         * The primary Jolt address(es)
         * @return The primary Jolt address(es) as an array of Strings.
         */
        public String[] getAddressPrimary();

        /**
         * The failover Jolt address(es)
         * @return The failover Jolt address(es) as an array of Strings.
         */
        public String[] getAddressFailover();

        /**
         * The maximum size of the Jolt pool.
         * @return The maximum size of the Jolt pool.
         */
        public int      getSizeMax();

        /**
         * The minimum size of the Jolt pool.
         * @return The minimum size of the Jolt pool.
         */
        public int      getSizeMin();
    }
}
