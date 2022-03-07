package com.zkq.fuxi.ui.main.chaos;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkq.fuxi.R;
import com.zkq.weapon.base.BaseFragment;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:zkq
 * create:2018/10/24 上午11:42
 * email:zkq815@126.com
 * desc: 混沌页面
 */

public class ChaosFragment extends BaseFragment implements ChaosContract.View{
    private View rootView;
    private TextView tvBeginTime;
    private TextView tvNowTime;
    private TextView tvTimeShow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chaos,container,false);
        ButterKnife.bind(rootView);
        tvBeginTime = rootView.findViewById(R.id.tv_begin_time);
        tvNowTime = rootView.findViewById(R.id.tv_now_time);
        tvTimeShow = rootView.findViewById(R.id.tv_time_show);
        time();
        return rootView;
    }

    @Override
    public void setPresenter(@NonNull ChaosContract.Presenter presenter) {

    }

    private void time(){
        tvBeginTime.setText("起始时间：2021.07.07");
        tvNowTime.setText("当前时间：" + stampToDate(String.valueOf(System.currentTimeMillis())));
        tvTimeShow.setText(getTime(dateToStamp("2021-07-07 00:00:00"), System.currentTimeMillis()));
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s){
        long ts = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(s);
            ts = date.getTime();
//        res = String.valueOf(ts);
        } catch (Exception e){

        }

        return ts;
    }

    private static String getTime(long start, long end) {
        return "相差周数为:" + ((end - start) / (1000 * 3600 * 24)) / 7 + "周  " + ((end - start) / (1000 * 3600 * 24)) % 7 +  "天"
                +"\n" + "相差天数为:" + (end - start) / (1000 * 3600 * 24) + "天"
                +"\n" + "相差小时数为:" + (end - start) / (1000 * 3600 * 24) + "天 " + ((end - start) / (1000 * 3600)) % 24 + "小时";

    }

}
