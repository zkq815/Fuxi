package com.zkq.snail.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class Constants {

    public static final List<String> MEIZU_STORE_COOKIE_URLS = new ArrayList<>(Arrays.asList(
            AppConstantsUrl.APP_MALL_URL.getUrl(),
            AppConstantsUrl.APP_ALAD_URL.getUrl(),
            AppConstantsUrl.APP_INSURANCE_URL.getUrl(),
            AppConstantsUrl.APP_PYRAMID_URL.getUrl()));
    public static final String MEIZU_STORE_COOKIE_DOMAIN = ".meizu.com";
    public static final String MEIZU_STORE_COOKIE_PATH = "/";

    public static final String INTENT_DATA_URL = "url";
    public static final String INTENT_DATA_FROM_PAGE = "from_page";
    public static final String INTENT_DATA_TYPE = "type";
    public static final String INTENT_DATA_TITLE = "title";

    public static final String INTENT_PUSH_DATA = "param";
    public static final String SEARCH_RESULT_NULL = "empty";
    public static final String INTENT_PARAMS_ID = "id";
    public static final String INTENT_PARAMS_SKUID = "skuid";
    public static final String INTENT_POST = "post";

    public static final String INTENT_PARAMS = "params";
    public static final String INTENT_ITEM_TYPE = "item_type";

    public static final String INTENT_PUSH_JSON = "json";

    /**
     * 字符编码统一 UTF-8
     */
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String HTTP = "http:";
    public static final String HTTPS = "https:";
    public static final String OVER_LIMIT = "over limit";

    public static final int FAST_ARRIVAL_FRPM_DETAIL_PAGE = 1;
    public static final int FAST_ARRIVAL_FRPM_ORDER_PAGE = 2;

    public static final String XIAONENG_SITEID = "zu_1000";
    public static final String XIAONENG_SDKKEY = "F0C0DD49-8304-4B7D-8649-88FEB31FC4FF";
    public static final String XIAONENG_GROUP = "zu_1000_1499150603350";//最初的默认接待组id   "zu_1000_9999";
    public static final String SERVICE_PHONE = "400-788-3333";
    public static final String XIAONENG_ERP_PARAMS_SUPPLIER_ID = "supplier_id";
    public static final String HTML_END = ".html";

    //积分兑换ID productId
    public static final String INTENT_POINTS_PRODUCT_ID = "productId";
    public static final String INTENT_POINTS_TYPE = "type";

    //flyme数据sdk key
    public static final String FLYME_DATA_SDK_KEY = "7X1O75164FCW5SEDCP24FLDB";

    //购物车
    public static final String CART_EDIT = "编辑";
    public static final String CART_NORMAL = "完成";
}
