/**
 * ArGtBanService.java
 *
 * Created on 18-08-2006 14:31:11
 */
package com.bsl.services;


import com.bsl.TuxedoService;
import com.bsl.exceptions.BSLFokusException;

/**
 * @author Ninja
 * @version 9.5
 *          <p/>
 *          ArGtBanService Class.
 */
public class ArGtBanService extends TuxedoService {
    // Name of Tuxedo service
    private static String NAME = "arGtBan00";

    /**
     * Creates a new instance of ArGtBanService.
     *
     * @throws BSLFokusException
     * @throws BSLFokusException
     */
    public ArGtBanService(Integer operatorId) throws BSLFokusException, BSLFokusException {
        super(operatorId, NAME);
        this.input = new ArGtBanParameter();
        ((ArGtBanParameter) this.input).set_OPERATOR_ID(operatorId);
    }

    public ArGtBanService() {
    }

    /**
     * Calls Tuxedo service 'ArGtBan'
     *
     * @return Output value object: ArGtBanOutput
     * @throws BSLFokusException
     * @throws BSLFokusException
     */
    public ArGtBanOutput exec() throws BSLFokusException, BSLFokusException {
        this.output = new ArGtBanOutput(super.execute());
        return ((ArGtBanOutput) this.output);
    }

    /**
     * Returns a input value object for service.
     *
     * @return Input value object for service.
     */
    public ArGtBanParameter getInput() {
        return ((ArGtBanParameter) this.input);
    }

    /**
     *
     * Return the tuxedo service name
     * @return
     */
    public static String getServiceName() {
        return NAME;
    }

}