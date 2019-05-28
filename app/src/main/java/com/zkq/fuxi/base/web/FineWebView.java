package com.zkq.fuxi.base.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class FineWebView extends WebView {

    private boolean isDestroying = false;

    public FineWebView(Context context) {
        super(context.getApplicationContext());
    }

    public FineWebView(Context context, AttributeSet attrs) {
        super(context.getApplicationContext(), attrs);
    }

    public FineWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context.getApplicationContext(), attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FineWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context.getApplicationContext(), attrs, defStyleAttr, defStyleRes);
    }

    @Deprecated
    public FineWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context.getApplicationContext(), attrs, defStyleAttr, privateBrowsing);
    }

    private final List<String> jsInterfaceNames = new ArrayList<>();

    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavascriptInterface(Object object, String name) {
        super.addJavascriptInterface(object, name);

        jsInterfaceNames.add(name);
    }

    @Override
    public void removeJavascriptInterface(@NonNull String name) {
        super.removeJavascriptInterface(name);

        jsInterfaceNames.remove(name);
    }

    @Override
    public void destroy() {
        if (!isDestroying) {
            isDestroying = true;
            final List<String> jsIfNames = new ArrayList<>(jsInterfaceNames);
            WebViewUtils.destroy(this, jsIfNames);
            super.destroy();
            isDestroying = false;
        }
    }

}
