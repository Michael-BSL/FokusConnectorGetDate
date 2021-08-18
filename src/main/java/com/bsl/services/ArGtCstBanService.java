/**
 * ArGtCstBanService.java
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
 *          ArGtCstBanService Class.
 */
public class ArGtCstBanService extends TuxedoService {
    // Name of Tuxedo service
    private static String NAME = "arGtCstBan00";

    /**
     * Creates a new instance of ArGtCstBanService.
     *
     * @throws BSLFokusException
     * @throws BSLFokusException
     */
    public ArGtCstBanService(Integer operatorId) throws BSLFokusException, BSLFokusException {
        super(operatorId, NAME);
        this.input = new ArGtCstBanParameter();
        ((ArGtCstBanParameter) this.input).set_OPERATOR_ID(operatorId);
    }

    public ArGtCstBanService() {
    }

    /**
     * Calls Tuxedo service 'ArGtCstBan'
     *
     * @return Output value object: ArGtCstBanOutput
     * @throws BSLFokusException
     * @throws BSLFokusException
     */
    public ArGtCstBanOutput exec() throws BSLFokusException, BSLFokusException {
        this.output = new ArGtCstBanOutput(super.execute());
        return ((ArGtCstBanOutput) this.output);
    }

    /**
     * Returns a input value object for service.
     *
     * @return Input value object for service.
     */
    public ArGtCstBanParameter getInput() {
        return ((ArGtCstBanParameter) this.input);
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