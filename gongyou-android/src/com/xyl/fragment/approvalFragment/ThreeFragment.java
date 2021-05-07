package com.xyl.fragment.approvalFragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xyl.R;
import com.xyl.domain.SecondBean;
import com.xyl.domain.ThreeBean;
import com.xyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment implements FragmentBackHandler, View.OnClickListener {

    public Button btFirst;
    boolean backHandled = true;
    private ThreeBean threeBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_three, container, false);
        btFirst = inflate.findViewById(R.id.bt_first);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        threeBean = new ThreeBean("成龙",3);
        btFirst.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSecondFragmentData(SecondBean secondBean){
        ToastUtil.showToast("Second"+secondBean);
        btFirst.setText(secondBean.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onBackPressed() {
        if (backHandled) {
            ToastUtil.showToast("First");
            return true;
        } else {
            return BackHandlerHelper.handleBackPress(this);
        }
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post("fragment4");
        EventBus.getDefault().post(threeBean);
    }
}
