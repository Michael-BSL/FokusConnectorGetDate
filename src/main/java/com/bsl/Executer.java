package com.bsl;

import com.bsl.exceptions.BSLFokusException;
import com.bsl.services.ServiceFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class Executer {

    private static final Logger log = Logger.getLogger(String.valueOf(Executer.class));



    public static void main(String[] args) {

        Map inputParams = new HashMap();

        Executer executer = new Executer();
       // String serviceName = "ArGtBanService";//  "ArGtCstBanService";

        String serviceName = args[0];
        Integer ban = Integer.valueOf(args[1]);
        String link_type = args[2];


        try {
          //  506921311
            inputParams.put("ban",ban);
            inputParams.put("link_type",link_type);

            executer.executeService(serviceName,inputParams);
        } catch (BSLFokusException e) {
            String stackTrace = "";
            for(StackTraceElement stackTraceElement : e.getStackTrace()) {
                stackTrace = stackTrace + System.lineSeparator() + stackTraceElement.toString();
            }

            log.warning("tuxedo failed: " + stackTrace);
            e.printStackTrace();

            try {
                Thread.sleep(1000);
                executer.executeService(serviceName,inputParams);
            } catch (Exception ex) {
                log.warning(ex.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void executeService(String serviceName,Map inputParams) throws BSLFokusException {

       Integer ban = (Integer)inputParams.get("ban");
       String link_type = (String)inputParams.get("link_type");
       TuxedoService tuxedoService = ServiceFactory.getInstance().createService(serviceName,Constants.BSL_OPERATOR);
        ServiceParameter serviceInput = tuxedoService.input;

        serviceInput.set_RUN_DATE(TypeConverter.dateToString( new Date(), TypeConverter.DATE_FORMAT_SIMPLE));
        serviceInput.set_BAN(ban);
        serviceInput.set_LINK_TYPE_STR(link_type); // Only retrieve legal and contact information
        tuxedoService.execute();


    }





}
