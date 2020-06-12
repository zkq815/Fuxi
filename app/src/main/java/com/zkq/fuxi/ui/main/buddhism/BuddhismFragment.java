package com.zkq.fuxi.ui.main.buddhism;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.fuxi.R;
import com.zkq.fuxi.basehodler.adapter.AdapterTest;
import com.zkq.fuxi.basehodler.datamodel.EdtionImageDataModel;
import com.zkq.fuxi.basehodler.module.SlideShowEdtionModule;
import com.zkq.fuxi.basehodler.operation.BaseEdtionOperationModel;
import com.zkq.fuxi.basehodler.operation.SlideShowInsideOperationModel;
import com.zkq.weapon.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zkq
 * create:2018/10/24 上午11:42
 * email:zkq815@126.com
 * desc: 佛教页面
 */

public class BuddhismFragment extends BaseFragment implements BuddhismContract.View {
    @BindView(R.id.rv_test)
    RecyclerView rvTest;

    private View rootView;
    private AdapterTest test;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_buddhism,container,false);
        ButterKnife.bind(rootView);
        rvTest.setLayoutManager(new LinearLayoutManager(getContext()));
        test = new AdapterTest(getContext(),getDataList());
        rvTest.setAdapter(test);
        return rootView;
    }

    private List<BaseEdtionOperationModel> getDataList(){
        List<BaseEdtionOperationModel> list = new ArrayList<BaseEdtionOperationModel>(1);
        SlideShowEdtionModule module = new SlideShowEdtionModule();
        ArrayList<EdtionImageDataModel> templist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            EdtionImageDataModel modelTemp = new EdtionImageDataModel();
            modelTemp.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559068148060&di=78736a70f2410d555522f19b138f46bc&imgtype=0&src=http%3A%2F%2Fp2.ssl.cdn.btime.com%2Ft0182ef20bbc3d3aec9.jpg%3Fsize%3D640x849");
            templist.add(modelTemp);
        }
        module.setDataList(templist);
        SlideShowInsideOperationModel model = new SlideShowInsideOperationModel(getContext(), module);
        list.add(model);
        return list;
    }

    @Override
    public void setPresenter(@NonNull BuddhismContract.Presenter presenter) {

    }
}
