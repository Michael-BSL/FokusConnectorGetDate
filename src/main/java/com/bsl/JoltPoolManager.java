package com.bsl;

import bea.jolt.pool.SessionPool;
import bea.jolt.pool.SessionPoolManager;
import bea.jolt.pool.UserInfo;
import bea.jolt.pool.servlet.ServletSessionPoolManager;
import bea.jolt.pool.servlet.weblogic.SecurityContextImpl;
import com.bsl.exceptions.BSLFokusException;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: hakgu
 * Date: 5/6/11
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class JoltPoolManager
{
    //--+----------------+---------------------------------------------------------------------------------------------
    //--| Global members |---------------------------------------------------------------------------------------------
    //--+----------------+---------------------------------------------------------------------------------------------

    private static final String                   s_sWhiteSpaceChars  = " ,;\t\r\n\f";
    private static final Map<Integer,SessionPool> s_mpJoltPools       = new HashMap<Integer, SessionPool>();
    private static final Map<Integer,Integer>     s_mpJoltCalls       = new HashMap<Integer, Integer>();
    private static       int                      s_nJoltPoolCount    = 0; // The number of pools we've created.
    private static       int                      s_nSessionPoolCount = 0;
    private              boolean                  m_bInitDone         = false;
    //private              Logger                   m_oLogger           = null;
    private static final Logger log = Logger.getLogger(String.valueOf(JoltPoolManager.class));

    public static final Integer DEFAULT_TUXEDO_TIMEOUT_SECONDS = Integer.valueOf(120);



    //--+-------------+------------------------------------------------------------------------------------------------
    //--| Constructor |------------------------------------------------------------------------------------------------
    //--+-------------+------------------------------------------------------------------------------------------------

    public JoltPoolManager() {
        //
    }

    //--+----------------+---------------------------------------------------------------------------------------------
    //--| Public methods |---------------------------------------------------------------------------------------------
    //--+----------------+---------------------------------------------------------------------------------------------

    public static final SessionPool getSessionPoolForTuxedoService(final String sTuxedoService) throws Exception {
        final Integer     oTuxTimeout  = DEFAULT_TUXEDO_TIMEOUT_SECONDS;
        final SessionPool oSessionPool = s_mpJoltPools.get(oTuxTimeout);
        boolean           bSuccess     = true;
        //
        // Check if we need to initialise a new pool, and if so - do it!
        //
        try {
            if (oSessionPool == null) {
                return new JoltPoolManager().start(oTuxTimeout);
            } else {
                return oSessionPool;
            }
        } catch (Exception ne) {
            bSuccess = false;
            throw ne;
        } finally {
            if (bSuccess) {
                increaseJoltCounter(oTuxTimeout);
            }
        }
    }

    public static final Map<Integer,Integer> getSessionPoolCounters() {
        return Collections.unmodifiableMap(s_mpJoltCalls);
    }

    //--+-------------------+------------------------------------------------------------------------------------------
    //--| Protected methods |------------------------------------------------------------------------------------------
    //--+-------------------+------------------------------------------------------------------------------------------

 

    protected final void start() throws Exception {
        start(DEFAULT_TUXEDO_TIMEOUT_SECONDS);
    }

    protected final void startPreFF3160() throws Exception {
            
        log.info("JoltPoolManager::start");
       
        if (m_bInitDone) {
            log.info("JoltPoolManager::start::Already initialized...");
            
            return;
        } else {
                log.info("Jolt is used for the first time. From where?");
            
        }
        //
        // Make sure that the internal Weblogic pool manager is instantiated...
        //
        
            log.info("JoltPoolManager::start::Setting up SessionPoolManager...");

        Object obj = SessionPoolManager.poolmgr;
        if (obj == null) {
            SessionPoolManager.poolmgr = ((SessionPoolManager) (obj = new ServletSessionPoolManager()));
        }

        
            log.info("JoltPoolManager::start::Setting up SessionPool...");

        final SessionPoolManager      oPoolMgr         = (SessionPoolManager) obj;
        final SecurityContextImpl     oSecCtx          = null;
        final JoltPoolConfig.Settings oSettings        = JoltPoolConfig.getInstance().getSettings();
        final int                     nConnectionCount = oPoolMgr.createSessionPool(
                oSettings.getAddressPrimary(),
                oSettings.getAddressFailover(),
                oSettings.getSizeMin(),
                oSettings.getSizeMax(),
                getUserInfo(),
                oSettings.getPoolName(),
                false, // // no security context (i.e. username, password, etc.)
                oSecCtx
        );
        if (nConnectionCount < oSettings.getSizeMin()) {
            oPoolMgr.removeSessionPool(oSettings.getPoolName());
            throw new BSLFokusException(StringUtil.concat(
                    "Cannot create ", String.valueOf(oSettings.getSizeMin()),
                    " connection",(oSettings.getSizeMin() == 1 ? "." : "s.")
            ), 0
            );
        }

        //
        // Store the connections...
        //
        //System.out.println("JoltPoolManager::start::Storing Jolt connections...");
        //final SessionPool oSessionPool = (SessionPool)oPoolMgr.get(oSettings.getPoolName()); // Not needed, we're not using it...
        ++s_nJoltPoolCount;
        m_bInitDone = true;
            log.info("JoltPoolManager::start::Done, Jolt Session Pool [" + oSettings.getPoolName() + "] initialized.");

    }

    protected final SessionPool start(final Integer oTuxTimeout) throws Exception {
            log.info("JoltPoolManager::start::timeout=" + String.valueOf(oTuxTimeout) + "sec");

        SessionPool oSessionPool = s_mpJoltPools.get(oTuxTimeout);
        if (oSessionPool != null) {

            log.info("JoltPoolManager::start::timeout=" + String.valueOf(oTuxTimeout) + "sec::Already initialized...");

            return oSessionPool;
        }
        
            log.info("JoltPoolManager::start::timeout=" +  String.valueOf(oTuxTimeout) + "sec::Used for the first time");



        //
        // Make sure that the internal Weblogic pool manager is instantiated...
        //

        log.info("JoltPoolManager::start::timeout="+ String.valueOf(oTuxTimeout)+ "sec::Setting up SessionPoolManager...");

        Object obj = SessionPoolManager.poolmgr;
        if (obj == null) {
            SessionPoolManager.poolmgr = ((SessionPoolManager) (obj = new ServletSessionPoolManager()));
        }

        
            log.info("JoltPoolManager::start::timeout=" +String.valueOf(oTuxTimeout) + "sec::Setting up SessionPool...");

        final SessionPoolManager      oPoolMgr         = (SessionPoolManager) obj;
        final SecurityContextImpl     oSecCtx          = null;
        final JoltPoolConfig.Settings oSettings        = JoltPoolConfig.getInstance().getSettings();
        final String                  sPoolName        = StringUtil.concat(oSettings.getPoolName(), "_", String.valueOf(oTuxTimeout)); // Append the timeout to the cached pool's name.
        final int                     nConnectionCount = oPoolMgr.createSessionPool(
                oSettings.getAddressPrimary(),
                oSettings.getAddressFailover(),
                oSettings.getSizeMin(),
                oSettings.getSizeMax(),
                getUserInfo(oTuxTimeout),
                sPoolName,
                false, // No security context (i.e. username, password, etc.)
                oSecCtx
        );
        if (nConnectionCount < oSettings.getSizeMin()) {
            oPoolMgr.removeSessionPool(oSettings.getPoolName());
            throw new BSLFokusException(StringUtil.concat(
                    "Cannot create ", String.valueOf(oSettings.getSizeMin()), " connection",
                    (oSettings.getSizeMin() == 1 ? "" : "s"), ", instead got ", String.valueOf(nConnectionCount), "."
            ), 0
            );
        }

        //
        // Store the connections...
        //
        oSessionPool = (SessionPool)oPoolMgr.get(sPoolName);
        ++s_nJoltPoolCount;
        s_mpJoltPools.put(oTuxTimeout, oSessionPool);
            log.info(StringUtil.concat(
                    "JoltPoolManager::start::timeout=", String.valueOf(oTuxTimeout), "sec::Done, Jolt Session Pool [", sPoolName, "] initialized."
            ));

        return oSessionPool;
    }

    protected final void stop() {
            log.info("JoltPoolManager::stop");

        final SessionPoolManager oPoolMgr = SessionPoolManager.poolmgr;

        if (oPoolMgr == null) {
         //   if (log.isInfoEnabled()) {
                log.info("JoltPoolManager::stop::Done (SessionPoolManager was already null).");
          //  }
            return;
        }
       // if (log.isInfoEnabled()) {
            log.info("JoltPoolManager::stop::About to shut down...");
        //}
        handleJoltShutDown(oPoolMgr);
        oPoolMgr.done();
            final Map<Integer,Integer> map  = s_mpJoltCalls;
            final StringBuilder         buf = new StringBuilder(128).append("JoltPoolManager::stop::Done (SessionPoolManager was shot down in flames). Counters{");
            int i = 0;
            for (final Integer oTimeout : map.keySet()) {
                if (++i != 1) {
                    buf.append(',').append(' ');
                }
                buf.append(i).append("{timeout=").append(oTimeout).append(", calls=").append(map.get(oTimeout)).append('}');
            }
            log.info(buf.append('}').toString());

        //
        // FF-1881/FF3160 Make sure we clear the locally cached sessions...
        //
        if (s_mpJoltPools != null) {
            s_mpJoltPools.clear();
        }
        if (s_mpJoltCalls != null) {
            s_mpJoltCalls.clear();
        }
    }

    private void handleJoltShutDown(SessionPoolManager oPoolMgr) {
        if (false){
            final JoltPoolConfig.Settings oSettings = JoltPoolConfig.getInstance().getSettings();
            SessionPool sessionPool = oPoolMgr.getSessionPool(oSettings.getPoolName());
            if(sessionPool != null && !sessionPool.isSuspended()) {
                    log.info("JoltPoolManager::suspendSessionPool");

                oPoolMgr.suspendSessionPool(oSettings.getPoolName(), true);
                // Wait 30 seconds for current calls to complete
                startWaiting();
            }
                log.info("JoltPoolManager::removeSessionPool: " + oSettings.getPoolName());

            oPoolMgr.removeSessionPool(oSettings.getPoolName());

        }
    }

    /**
     * Wait for 30 seconds for extant calls to complete
     */
    private synchronized void startWaiting() {
        try {
            // Wait waits for milliseconds, so multiply out
            wait((30) * 1000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }

    //--+-----------------+--------------------------------------------------------------------------------------------
    //--| Private methods |--------------------------------------------------------------------------------------------
    //--+-----------------+--------------------------------------------------------------------------------------------

    private static final UserInfo getUserInfo() {
        return getUserInfo(null);
    }

    private static final UserInfo getUserInfo(final Integer oRecvTimeout) {
        final UserInfo oInfo = new UserInfo();
        oInfo.setUserName("");      // UserName
        oInfo.setUserPassword("");  // UserPassword
        oInfo.setUserRole("");      // UserRole
        oInfo.setAppPassword("");   // ApplicationPassword
        oInfo.setRecvTimeout(       // RecvTimeout (Seconds)
                oRecvTimeout == null
                        ? DEFAULT_TUXEDO_TIMEOUT_SECONDS
                        : oRecvTimeout.intValue()
        );
        return oInfo;
    }

    private static final void increaseJoltCounter(final Integer oTuxTimeout) {
        final Integer oCount = s_mpJoltCalls.get(oTuxTimeout);
        if (oCount == null) {
            s_mpJoltCalls.put(oTuxTimeout, 1);
        } else {
            s_mpJoltCalls.put(oTuxTimeout, oCount.intValue() + 1);
        }
    }
}
