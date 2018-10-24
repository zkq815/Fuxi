package com.zkq.snail.ui.main.toolutil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author:zkq
 * create:2018/10/24 上午11:45
 * email:zkq815@126.com
 * desc:
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
