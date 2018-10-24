package com.zkq.snail.base.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zkq.snail.R;
import com.zkq.snail.common.Constants;
import com.zkq.snail.util.IntentUtils;
import com.zkq.snail.util.StringUtil;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class WebViewPluginActivity extends BaseWebActivity {

    private WebViewPluginFragment mWebFragment;
    private boolean mShowRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebFragment = new WebViewPluginFragment();

        mShowRefresh = getIntent().getBooleanExtra(IntentUtils.SHOW_REFRESH, false);

        final Bundle data = new Bundle();

        String targetUrl = getIntent().getStringExtra(Constants.INTENT_DATA_URL);
        String pushMsgUrl = getIntent().getStringExtra(Constants.INTENT_PUSH_DATA);
        if (!StringUtil.isEmpty(pushMsgUrl)) {
            targetUrl = pushMsgUrl;
        }

        Uri uri = getIntent().getData();
        if (uri != null) {
//            String adfrom = uri.getQueryParameter(H5CallParam.H5_PARAM_AD_FROM);
//            String from = uri.getQueryParameter(H5CallParam.H5_PARAM_FROM);
//            targetUrl = uri.getQueryParameter(H5CallParam.H5_PARAM_WEB_URL);
//            mShowRefresh = uri.getBooleanQueryParameter(H5CallParam.H5_PARAM_WEB_REFRESH, false);
//
//            final boolean internal = uri.getBooleanQueryParameter(UriUtils.INTERNAL, false);
//            if (!internal) {
//                Tracker.trackDeepLink(PageIndex.appweb.toString(), adfrom, from);
//            }
        }

        data.putString(Constants.INTENT_DATA_URL, targetUrl);
        data.putString(Constants.INTENT_DATA_TITLE, getIntent().getStringExtra(Constants.INTENT_DATA_TITLE));
        mWebFragment.setArguments(data);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, mWebFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        final MenuItem menuItem = menu.findItem(R.id.refresh);
        menuItem.setVisible(mShowRefresh);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            case R.id.refresh:
                if (mWebFragment != null && mWebFragment.isAdded()) {
                    mWebFragment.reload();
                }
                return true;
            default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mWebFragment.canGoBack() && !mWebFragment.webIsPayment()) {
            mWebFragment.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected boolean showBack() {
        return true;
    }
}
