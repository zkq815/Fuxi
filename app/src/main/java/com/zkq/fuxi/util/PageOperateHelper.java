//package com.zkq.fuxi.util;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import android.text.TextUtils;
//
//
//import com.zkq.fuxi.common.AppConstantsUrl;
//import com.zkq.fuxi.common.Constants;
//import com.zkq.weapon.base.WebViewPluginActivity;
//import com.zkq.weapon.market.util.ZLog;
//
//import java.util.HashMap;
//
///**
// * Created by meizu on 3/7/18.
// * 页面操作跳转的工具类
// */
//
//public class PageOperateHelper {
//
//    public static void jumpActivityByOperateType(final Activity fromActivity, int operateType,
//                                                 final Intent intent, HashMap<String, String> paramsMap) {
//        OperateType operateTypeObject = OperateType.from(operateType);
//        switch (operateTypeObject) {
//            case DETAIL_ITEM:
//                intent.setClass(fromActivity, DetailActivity.class);
//
//                if (paramsMap != null) {
//                    final Bundle bundle = new Bundle();
//                    bundle.putSerializable(Constants.INTENT_PARAMS, paramsMap);
//
//                    intent.putExtras(bundle);
//                    intent.putExtra(Constants.INTENT_ITEM_TYPE, ProductType.ITEM.type());
//                } else {
//                    ZLog.e(fromActivity, "数据异常，无法跳转");
//                    return;
//                }
//                break;
//
//            case DETAIL_SKU:
//                intent.setClass(fromActivity, DetailActivity.class);
//
//                if (paramsMap != null) {
//                    final Bundle bundle = new Bundle();
//                    bundle.putSerializable(Constants.INTENT_PARAMS, paramsMap);
//
//                    intent.putExtras(bundle);
//                    intent.putExtra(Constants.INTENT_ITEM_TYPE, ProductType.SKU.type());
//                } else {
//                    ZLog.e(fromActivity, "数据异常，无法跳转");
//                    return;
//                }
//                break;
//
//            case WEB:
//                boolean flag = true;
//                if (paramsMap != null) {
//                    final String url = paramsMap.get("url");
//                    if (TextUtils.isEmpty(url)) {
//                        flag = false;
//                    } else {
//                        if (url.contains(AppConstantsUrl.APP_H5_DETAIL_HTML_KEY.getUrl())
//                                || url.contains(AppConstantsUrl.H5_DETAIL_2.getUrl())) {
//                            intent.putExtra(Constants.INTENT_DATA_URL, AppRequestUrl.APP_GET_DETAIL_DATA_URL.getUrl() + url);
//                            intent.setClass(fromActivity, DetailActivity.class);
//                        } else {
//                            intent.setClass(fromActivity, WebViewPluginActivity.class);
//
//                            if (url.contains("://care.meizu.com/repair/home/profile.html")) {
//                                if (LoginHelper.isCookieExpired() || LoginHelper.hasToken()) {
//                                    LoginHelper.login(fromActivity, true, new LoginHelper.IToken() {
//
//                                        @Override
//                                        public void token(boolean success, @NonNull LoginHelper.Reason reason, String token) {
//                                            if (success && !TextUtils.isEmpty(token)) {
//                                                intent.putExtra(Constants.INTENT_DATA_URL, "http://mwx-api.meizu.com/login/token?useruri=http://service.meizu.com/repair/home.html&token=" + token);
//                                                fromActivity.startActivity(intent);
//                                            }
//                                        }
//                                    });
//                                    return;
//                                } else {
//                                    intent.putExtra(Constants.INTENT_DATA_URL, "http://mwx-api.meizu.com/login/token?useruri=http://service.meizu.com/repair/home.html&token=" + LoginHelper.getToken());
//                                }
//                            } else {
//                                intent.putExtra(Constants.INTENT_DATA_URL, url);
//                            }
//                        }
//                    }
//                } else {
//                    flag = false;
//                }
//
//                if (!flag) {
//                    ZLog.e(fromActivity, "数据异常，无法跳转");
//                    return;
//                }
//                break;
//
//            case LIST:
//                intent.putExtra(CategoryBizConstant.CATEGORY_ITEM_FRAGMENT_MAP_FLAG, paramsMap);
//                intent.setClass(fromActivity, CateProductListActivity.class);
//                break;
//
//            case POINT:
//                intent.setClass(fromActivity, PointsActivity.class);
//                if (LoginHelper.isCookieExpired()) {
//                    LoginHelper.startLogin(fromActivity, true, new LoginHelper.ILoginListener() {
//                        @Override
//                        public void result(@NonNull LoginHelper.Code code, String msg) {
//                            if (LoginHelper.Code.SUCCESS == code) {
//                                fromActivity.startActivity(intent);
//                            }
//                        }
//                    });
//                } else {
//                    fromActivity.startActivity(intent);
//                }
//                return;
//            case RECEIVE_COUPON:
//                intent.setClass(fromActivity, GetCouponActivity.class);
//                break;
//            case SECENE:
//                intent.setClass(fromActivity, SceneActivity.class);
//                if (paramsMap != null) {
//                    final Bundle bundle = new Bundle();
//                    bundle.putSerializable(Constants.INTENT_PARAMS, paramsMap);
//                    intent.putExtras(bundle);
//                }
//                break;
//            case PRODUCT_LIST:
//                intent.setClass(fromActivity, CateProductListActivity.class);
//                if (paramsMap != null) {
//                    final Bundle bundle = new Bundle();
//                    bundle.putSerializable(CategoryBizConstant.CATEGORY_ITEM_FRAGMENT_MAP_FLAG, paramsMap);
//                    intent.putExtras(bundle);
//                }
//                break;
//            case CUT_PRICE:
//                intent.setClass(fromActivity, CutPriceListActivity.class);
//                break;
//            case TIME_LIMIT:
//                intent.setClass(fromActivity, TimeLimitPurchaseActivity.class);
//                break;
//            default:
//                // no op
//                return;
//        }
//
//        fromActivity.startActivity(intent);
//    }
//}
