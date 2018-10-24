package com.zkq.snail.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;


import com.zkq.snail.common.Constants;
import com.zkq.snail.util.MobileNetworkUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class WebViewPluginFragment extends BaseWebPluginFragment {

    private boolean mIsLoaded = false;
    private Bundle mBundle;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mBundle != null) {
            init(mBundle);
        } else {
            init(getArguments());
        }
    }

    public void setData(final Bundle bundle) {
        this.mBundle = bundle;
        if (isAdded() && isInLayout()) {
            init(bundle);
        }
    }

    private void init(final Bundle bundle) {
        if (bundle != null) {
            final String title = bundle.getString(Constants.INTENT_DATA_TITLE);
            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }

            final boolean connected = MobileNetworkUtils.isNetAvailable(getContext());
            if (connected) {
                load(bundle);
            } else {

            }
        }
    }

    void load(@NonNull final Bundle bundle) {
        final boolean post = bundle.getBoolean(Constants.INTENT_POST, false);

        String url = bundle.getString(Constants.INTENT_DATA_URL, "");
        if (post) {
            final String params = bundle.getString(Constants.INTENT_PARAMS, "");
            try {
                postUrl(url, params.getBytes(Constants.ENCODING_UTF8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            loadUrl(url);
        }
    }

    @Override
    public void postUrl(final String url, final byte[] postData) {
        super.postUrl(url, postData);
        mIsLoaded = true;
    }

    @Override
    public void loadUrl(String url) {
        super.loadUrl(url);
        mIsLoaded = true;
    }

    @Override
    protected boolean netSensitive() {
        return true;
    }

}
