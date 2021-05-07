package com.xyl.fragment.approvalFragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.FirstAdapter;
import com.xyl.adapter.StorageRoomAdapter;
import com.xyl.domain.FirstBean;
import com.xyl.domain.SecondBean;
import com.xyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FristFragment extends Fragment {

    public RecyclerView rv_first_data;
    private TextView tv_show;
    boolean backHandled = true;
    private SecondFragment secondFragment;
    List<FirstBean> stringList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_frist, container, false);
        rv_first_data = inflate.findViewById(R.id.rv_first_data);
        tv_show = inflate.findViewById(R.id.tv_show);
        return inflate;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        stringList.add(new FirstBean("杨洋",12));
        stringList.add(new FirstBean("刘德华",12));
        stringList.add(new FirstBean("赵丽颖",12));
        stringList.add(new FirstBean("霍元甲",12));
        rv_first_data.setLayoutManager(new LinearLayoutManager(getActivity()));
        final FirstAdapter firstAdapter = new FirstAdapter(getActivity(),stringList);
        rv_first_data.setAdapter(firstAdapter);
        firstAdapter.setOnItemClick(new FirstAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                FirstBean firstBean = stringList.get(position);
                EventBus.getDefault().post("fragment2");//告诉MainActivity,fragment1中的按钮点击了
                EventBus.getDefault().post(firstBean);//告诉MainActivity,fragment1中的按钮点击了
//                FirstBean firstBean = stringList.get(position);
//                EventBus.getDefault().post(firstBean);
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                if (secondFragment == null){
//                    secondFragment = new SecondFragment();
//                    fragmentTransaction.add(R.id.fl_approval_list,secondFragment).hide(FristFragment.this).commit();
//                }else{
//                    fragmentTransaction.hide(FristFragment.this).show(secondFragment).commit();
//                }

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSecondFragmentData(SecondBean firstBean){
        tv_show.setText(firstBean.getName());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }




}
