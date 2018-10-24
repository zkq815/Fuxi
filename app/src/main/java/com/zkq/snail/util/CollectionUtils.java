package com.zkq.snail.util;

import java.util.Collection;

/**
 * @author:zkq
 * create:2018/10/24 上午11:47
 * email:zkq815@126.com
 * desc:
 */
public class CollectionUtils {

    public static boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(final Collection<?> collection) {
        return !isEmpty(collection);
    }

}
