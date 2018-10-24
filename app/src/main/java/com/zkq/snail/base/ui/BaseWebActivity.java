package com.zkq.snail.base.ui;


/**
 * @author zkq
 * @since 2018/10/24
 */
public abstract class BaseWebActivity extends BaseActionBarActivity {

    public static final String FROM_MAIN = "from_main";

//    /**
//     * 是否由主进程启动
//     */
//    private boolean fromMain = false;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        android.os.Debug.waitForDebugger();
//        super.onCreate(savedInstanceState);
//
//        fromMain = getIntent().getBooleanExtra(FROM_MAIN, false);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.superOnBackPressed();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        if (ActivityStack.aliveSize() == 0) {
//            // 主进程启动的页面退出不启动首页，push启动的页面退出启动首页
//            if (!fromMain) {
//                MzService.launchHome(this);
//            }
//            System.exit(0);
//        }
//    }
}
