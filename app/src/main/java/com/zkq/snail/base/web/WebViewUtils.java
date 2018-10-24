package com.zkq.snail.base.web;

import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.List;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class WebViewUtils {

    public static void destroy(final WebView webView, final List<String> javascriptInterfaces) {
        if (webView != null) {

            try {
                if (webView.getParent() instanceof ViewGroup) {
                    ((ViewGroup) webView.getParent()).removeView(webView);
                }

                webView.getSettings().setJavaScriptEnabled(false);
                webView.setWebChromeClient(null);
                webView.setWebViewClient(null);
                webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                webView.stopLoading();
                webView.clearFormData();
                webView.clearHistory();
//                webView.clearSslPreferences();
                webView.clearView();
                webView.removeAllViews();
                webView.setTag(null);

                if (javascriptInterfaces != null) {
                    for (String js : javascriptInterfaces) {
                        try {
                            webView.removeJavascriptInterface(js);
                        } catch (Exception ignored) {
                        }
                    }
                }

                webView.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
