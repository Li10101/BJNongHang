package com.xyl.fragment.approvalFragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.FirstBean;
import com.xyl.domain.SecondBean;
import com.xyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment implements  View.OnClickListener {
    Button btSecond;
    private boolean backHandled = true;
    ThreeFragment threeFragment;
    FristFragment fristFragment;

    List<SecondBean> stringList = new ArrayList<>();

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        btSecond = view.findViewById(R.id.bt_second);
        btSecond.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        stringList.add(new SecondBean("杨洋",12));
        stringList.add(new SecondBean("刘德华",12));
        stringList.add(new SecondBean("赵丽颖",12));
        stringList.add(new SecondBean("霍元甲",12));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getFirstFragmentData(FirstBean firstBean){
        ToastUtil.showToast("First"+firstBean.getName());
        btSecond.setText(firstBean.getName());
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post("fragment3");
        EventBus.getDefault().post(stringList.get(0));
//        EventBus.getDefault().post(new FirstBean("Li",13));
//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        if (fristFragment == null){
//            fristFragment = new FristFragment();
//            fragmentTransaction.add(R.id.fl_approval_list,fristFragment).hide(SecondFragment.this).commit();
//        }else{
//            fragmentTransaction.hide(SecondFragment.this).show(fristFragment).commit();
//        }
    }
}
