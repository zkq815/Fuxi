package com.zkq.snail.ui.main.toolutil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @author zkq on 2016/12/15.
 */
public class CheckData {
    public static boolean hasData(Collection<?> data) {
        return data != null && data.size() > 0;
    }

    public static boolean noData(Collection<?> data) {
        return data == null || data.size() == 0;
    }

    public static boolean noData(HashMap<String, List<Integer>> data) {
        return data == null || data.size() == 0;
    }
}
