package com.zkq.fuxi.basehodler.module;

import android.content.Context;
import androidx.annotation.NonNull;
import android.widget.FrameLayout;

import com.zkq.fuxi.R;

/**
 * @author zkq
 * create:2019/5/29 12:05 AM
 * email:zkq815@126.com
 * desc: 模块视图基类
 */
public class BaseEdtionModuleView<T extends BaseNativeEdtionModule> extends FrameLayout {

    private T mEdtionModule;

    public BaseEdtionModuleView(@NonNull Context context) {
        super(context);
    }

    public BaseEdtionModuleView(@NonNull Context context, @NonNull T edtionModule) {
        super(context);
        this.mEdtionModule = edtionModule;
        this.setBackgroundColor(getContext().getResources().getColor(R.color.bg_home_module));
    }

    public T getEdtionModule(){
        return mEdtionModule;
    }
}
