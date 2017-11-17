package com.chamodshehanka.thogaKadeServer.reservation;

import com.chamodshehanka.thogaKadeCommon.controller.ItemController;

import java.util.HashMap;

/**
 * @author chamodshehanka on 11/17/2017
 * @project ThogaKadeFX
 **/
public class ItemReservation {
    private HashMap<String,ItemController> reserveData = new HashMap<>();

    public boolean reserveItem(String code,ItemController itemController){
        if (reserveData.containsKey(code)){
            return false;
        }else {
            reserveData.put(code,itemController);
            return true;
        }
    }

    public boolean releaseItem(String code,ItemController itemController){
        if (reserveData.get(code) == itemController){
            reserveData.remove(code);
            return true;
        }else {
            return false;
        }
    }
}
