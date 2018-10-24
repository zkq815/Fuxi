package com.zkq.snail.base.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;


import com.zkq.snail.BuildConfig;
import com.zkq.snail.R;
import com.zkq.snail.base.handler.IHandler;
import com.zkq.snail.base.handler.LeakFreeHandler;
import com.zkq.snail.base.uri.UriUtils;
import com.zkq.snail.base.web.BaseWebFragment;
import com.zkq.snail.common.AppConstantsUrl;
import com.zkq.snail.common.Constants;

import java.util.regex.Pattern;

/**
 * @author zkq
 * @since 2018/10/24
 */
public abstract class BaseWebPluginFragment extends BaseWebFragment implements IHandler {

    //服务端标识H5页面来源的UA
    private static final String PUBLIC_UA_TAG = "SnailApp";
    private static final String APP_VERSION_CODE = "AppVersionCode";
    private static final String SEPARATOR = " ";
    private static final int WEB_FLYME_LOGIN = 6;
    private static final int WEB_SHARE_REQUEST = 7;
    private static final int WEB_SHARE_RESPONSE = 10;
    private LeakFreeHandler mHandler;
    private boolean mIsPayment = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plugin_web, container, false);
        setupView(root);
        return root;
    }

    @Override
    public void onDestroy() {
        if (mHandler != null) {
            mHandler.removeMessages(WEB_FLYME_LOGIN);
        }

        super.onDestroy();
    }

    public boolean webIsPayment() {
        return mIsPayment;
    }

    @Override
    public void goBack() {
        if (null != webView) {
            if (webView.getUrl().contains(AppConstantsUrl.APP_PAY_SUCCESS.getUrl())) {
                getActivity().finish();
            } else {
                super.goBack();
            }
        }
    }

    private void setupView(View v) {
        final FrameLayout webViewContainer = v.findViewById(R.id.web_view_container);
        installWebView(getContext(), webViewContainer);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void setting(@NonNull final WebView webView, @NonNull final WebSettings ws) {
        ws.setAppCacheEnabled(true);
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scale = dm.densityDpi;
        if (scale == 240) {
            webView.setInitialScale(94);
        } else if (scale == 160) {
            webView.setInitialScale(68);
        } else {
            webView.setInitialScale(80);
        }
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);// 设置缓存模式
        // 设置支持Javascript
        ws.setJavaScriptEnabled(true);
        String appVer = BuildConfig.VERSION_NAME;
        StringBuilder tag = new StringBuilder();
        tag.append(PUBLIC_UA_TAG).append("/").append(appVer);
        final String ua = ws.getUserAgentString() + SEPARATOR + tag.toString();
        ws.setUserAgentString(ua);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setSaveFormData(false);
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        ws.setAllowFileAccess(true);
        ws.setDomStorageEnabled(true);
        ws.setDatabaseEnabled(true);
        ws.setDisplayZoomControls(false);
        ws.setTextZoom(100);

        // https://jaq.alibaba.com/community/art/show?articleid=1318
        ws.setAllowFileAccessFromFileURLs(false);
        ws.setAllowUniversalAccessFromFileURLs(false);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setTitle(view.getTitle());
            }

            /**
             * 支持api 19 20
             * */
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (dealUrl(url)) {
                    return true;
                }
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);

            }
        });
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case WEB_FLYME_LOGIN:
                if (getActivity() != null) {

                }
                break;
            case WEB_SHARE_REQUEST://分享请求
                 break;
            case WEB_SHARE_RESPONSE://分享回调

                break;
            default:
                break;
        }
    }

    private boolean dealUrl(final String url) {
        final boolean ret = UriUtils.openUrl(getActivity(), url);
        if (ret) {
            return true;
        }

        final Uri uri = Uri.parse(url);

        //微信支付链接拦截
        if (url.startsWith("weixin://wap/pay?")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            try {
                getActivity().startActivity(intent);
            } catch (Exception e) {
//                MeiZuToast.show("检测到你的手机没有安装微信");
            }
            return true;
        }
        //判断是否是H5购物车页面，若是，跳转到原生购物车
        if (url.contains(AppConstantsUrl.APP_H5_CART.getUrl())) {
//            getActivity().startActivity(new Intent(getActivity(), ShoppingCartActivity.class));
            getActivity().finish();
            return true;
        }

        return false;
    }

    @Override
    public void loadUrl(final String url) {
        if (dealUrl(url)) {
            getActivity().finish();
            return;
        }

        super.loadUrl(url);
    }

    private static Pattern payPattern = null;

    private static boolean checkPayUrl(final String url) {
        if (!TextUtils.isEmpty(url)) {
            final String str1 = url.trim();
            String str2;

            if (str1.startsWith("https://wappaygw.alipay.com/service/rest.htm") || str1.startsWith("http://wappaygw.alipay.com/service/rest.htm")) {
                str2 = str1.replaceFirst("(http|https)://wappaygw.alipay.com/service/rest.htm" + "\\?", "").trim();
                if (!TextUtils.isEmpty(str2)) {
                    return true;
                }
            }

            if (str1.startsWith("https://mclient.alipay.com/service/rest.htm") || str1.startsWith("http://mclient.alipay.com/service/rest.htm")) {
                str2 = str1.replaceFirst("(http|https)://mclient.alipay.com/service/rest.htm" + "\\?", "").trim();
                if (!TextUtils.isEmpty(str2)) {
                    return true;
                }
            }

            if ((str1.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm") || str1.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm"))
                    && str1.contains("alipay.wap.create.direct.pay.by.user")
                    && !TextUtils.isEmpty(str1.replaceFirst("(http|https)://mclient.alipay.com/home/exterfaceAssign.htm" + "\\?", "").trim())) {
                return true;
            }

            if (payPattern == null) {
                payPattern = Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)");
            }

            return payPattern.matcher(url).find();
        }

        return false;
    }






    @Override
    public void onResume() {
        super.onResume();
        mHandler.sendEmptyMessage(10);
    }

    @Override
    protected void onReceiveTitle(String title) {
        setTitle(title);
    }
}
