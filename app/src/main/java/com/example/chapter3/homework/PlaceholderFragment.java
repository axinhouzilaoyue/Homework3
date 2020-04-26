package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;//添加Fragment依赖
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment<MyAdapter> extends Fragment {

    private LottieAnimationView ph_animation_view;
    private ListView ph_listView;
    private ArrayList<String> itemList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ph_animation_view = getView().findViewById(R.id.ph_animation_view);
        ph_listView = getView().findViewById(R.id.ph_listView);
        itemList = new ArrayList<>();
        itemList.add("");
        for (int i=1;i<8;i++)
            itemList.add("item "+i);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ph_listView.getContext(),R.layout.item,itemList);
        ph_listView.setAdapter(adapter);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(ph_animation_view,
                        "alpha",1,0);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(ph_listView,
                        "alpha",0,1);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animator1,animator2);
                animatorSet.start();
            }
        }, 5000);
    }
}
