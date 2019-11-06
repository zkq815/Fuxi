package com.zkq.fuxi.basehodler.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.zkq.fuxi.R;
import com.zkq.fuxi.basehodler.holder.BaseNativeEdtionHolder;
import com.zkq.fuxi.basehodler.holder.DefaultEdtionHolder;
import com.zkq.fuxi.basehodler.operation.BaseEdtionOperationModel;
import com.zkq.weapon.market.util.ZLog;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author zkq
 * time: 2019/5/29 12:27 AM
 * email: zkq815@126.com
 * desc:
 */
public class AdapterTest extends AdapterRecyclerBase<BaseNativeEdtionHolder
        , BaseEdtionOperationModel> {
    public AdapterTest(Context context, List<BaseEdtionOperationModel> list){
        super(context,list);
    }

    @NonNull
    @Override
    public BaseNativeEdtionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for (BaseEdtionOperationModel model : getList()) {
            if (viewType == model.getEdtionModule().getViewType()) {
                return model.createEdtionHolder();
            }
        }

        return new DefaultEdtionHolder(getContext()
                , getLayoutInflater().inflate(R.layout.layout_edtion_defalut
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseNativeEdtionHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.showViews(getList().get(position).getEdtionModule());
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return 1;
    }
}
