package com.apreciasoft.mobile.remisclasea.Util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jorge on 3/1/18.
 */

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData(String Title,List<String> subItem) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        expandableListDetail.put(Title, subItem);
        return expandableListDetail;
    }
}