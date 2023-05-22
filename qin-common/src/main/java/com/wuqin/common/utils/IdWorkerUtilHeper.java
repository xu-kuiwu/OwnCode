package com.wuqin.common.utils;

import java.net.InetAddress;
import java.util.Random;

public class IdWorkerUtilHeper {
    private static IdWorkerUtil idWorkerUtil = null;
    private static IdTimeUtil idTimeUtil = null;

    private static synchronized IdWorkerUtil getIdWorkerInstance(){
        if(idWorkerUtil == null){
            long workerId = 0;
            try {
                String ip = InetAddress.getLocalHost().toString();
                String[] arrLastIp = ip.split("\\.");
                String lastIp = arrLastIp[arrLastIp.length-1];
                workerId = Long.parseLong(lastIp);

            }catch (Exception e){
                e.printStackTrace();
                workerId = new Random().nextInt(100);
            }
            idWorkerUtil = new IdWorkerUtil(workerId, 0);
        }
        return idWorkerUtil;
    }

    public static String getNextId(){
        return getIdWorkerInstance().nextId() + "";
    }

}
