package com.zkq.snail.util;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * @author:zkq
 * create:2018/10/24 上午11:49
 * email:zkq815@126.com
 * desc: 
 */
public class TextViewUtil {
    public static void addMidLine(TextView tv){
        tv.getPaint().setAntiAlias(true);//抗锯齿
        tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
    }

}
