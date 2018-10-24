package com.zkq.snail.base.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.zkq.snail.base.ui.BaseFragment;

import java.util.Map;

/**
 * @author zkq
 * @since 2018/10/24
 */
public abstract class BaseWebFragment extends BaseFragment {

    protected FineWebView webView;

    private final static int FILE_CHOOSER_RESULT_CODE = 1;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessage2;

    @SuppressWarnings("UnusedReturnValue")
    @SuppressLint("SetJavaScriptEnabled")
    protected FineWebView installWebView(@NonNull final Context context, @NonNull final ViewGroup viewGroup) {
        webView = new FineWebView(context);
        viewGroup.addView(webView);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                BaseWebFragment.this.onReceiveTitle(title);
            }

            // For Android 4.1
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                open(uploadMsg, null, false, TextUtils.isEmpty(acceptType) ? "*/*" : acceptType);
            }

            //Android 5.0+
            @Override
            @SuppressLint("NewApi")
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                String type;
                if (fileChooserParams != null
                        && fileChooserParams.getAcceptTypes() != null
                        && fileChooserParams.getAcceptTypes().length > 0
                        && !TextUtils.isEmpty(fileChooserParams.getAcceptTypes()[0])) {
                    type = fileChooserParams.getAcceptTypes()[0];
                } else {
                    type = "*/*";
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    final boolean allowMultiple = fileChooserParams != null
                            && fileChooserParams.getMode() == FileChooserParams.MODE_OPEN_MULTIPLE;
                    open(null, filePathCallback, allowMultiple, type);
                    return true;
                } else {
                    return false;
                }
            }

            @SuppressLint("NewApi")
            private void open(final ValueCallback<Uri> fileUploadCallbackFirst,
                              final ValueCallback<Uri[]> fileUploadCallbackSecond,
                              final boolean allowMultiple, final String type) {
                if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(null);
                }
                mUploadMessage = fileUploadCallbackFirst;

                if (mUploadMessage2 != null) {
                    mUploadMessage2.onReceiveValue(null);
                }
                mUploadMessage2 = fileUploadCallbackSecond;

                final Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);

                if (allowMultiple) {
                    i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }

                i.setType(type);

                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_CHOOSER_RESULT_CODE);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, final JsResult result) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder
                        .setTitle("是否离开当前页面")
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        })
                        .show();
                return true;
            }

        });

        final String appCacheDir = getContext().getDir("cache", Context.MODE_PRIVATE).getPath();

        final WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);

        // https://jaq.alibaba.com/community/art/show?articleid=1318
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);

        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(appCacheDir);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDatabaseEnabled(true);
        settings.setDefaultZoom(WebSettings.ZoomDensity.valueOf("MEDIUM"));
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        setting(webView, settings);

        return webView;
    }

    public void setWebViewClient(WebViewClient client) {
        if (webView != null) {
            webView.setWebViewClient(client);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (intent != null) {
                    if (mUploadMessage != null) {
                        mUploadMessage.onReceiveValue(intent.getData());
                        mUploadMessage = null;
                    } else if (mUploadMessage2 != null) {
                        Uri[] dataUris = null;

                        try {
                            if (intent.getDataString() != null) {
                                dataUris = new Uri[]{Uri.parse(intent.getDataString())};
                            } else {
                                if (intent.getClipData() != null) {
                                    final int numSelectedFiles = intent.getClipData().getItemCount();

                                    dataUris = new Uri[numSelectedFiles];

                                    for (int i = 0; i < numSelectedFiles; i++) {
                                        dataUris[i] = intent.getClipData().getItemAt(i).getUri();
                                    }
                                }
                            }
                        } catch (Exception ignored) {
                        }

                        mUploadMessage2.onReceiveValue(dataUris);
                        mUploadMessage2 = null;
                    }
                }
            } else {
                if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(null);
                    mUploadMessage = null;
                } else if (mUploadMessage2 != null) {
                    mUploadMessage2.onReceiveValue(null);
                    mUploadMessage2 = null;
                }
            }
        }
    }

    public boolean canGoBack() {
        return webView != null && webView.canGoBack();
    }

    public void goBack() {
        if (webView != null) {
            webView.goBack();
        }
    }

    public void loadUrl(final String url) {
        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (webView != null) {
            webView.loadUrl(url, additionalHttpHeaders);
        }
    }

    public void postUrl(final String url, final byte[] postData) {
        if (webView != null) {
            webView.postUrl(url, postData);
        }
    }

    public void reload() {
        if (webView != null) {
            webView.reload();
        }
    }

    protected void onReceiveTitle(String title) {

    }

    protected void setting(@NonNull final WebView webView, @NonNull final WebSettings settings) {

    }

}
