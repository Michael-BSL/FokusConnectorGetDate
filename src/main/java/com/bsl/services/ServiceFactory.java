package com.bsl.services;

import com.bsl.TuxedoService;
import com.bsl.exceptions.BSLFokusException;

public class ServiceFactory {

    private static  ServiceFactory instance = null ;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        if(instance == null){
            instance = new ServiceFactory();
        }
        return  instance;
    }

    public TuxedoService createService(String serviceName,Integer operatorId) throws BSLFokusException, BSLFokusException {

        if(serviceName.equals("ArGtCstBanService")){
            return new ArGtCstBanService(operatorId);
        }
        if(serviceName.equals("ArGtBanService")){
            return new ArGtBanService(operatorId);
        }
        return null;
    }


}
