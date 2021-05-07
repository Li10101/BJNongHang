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

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment implements FragmentBackHandler{

    public Button btFirst;
    boolean backHandled = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_four, container, false);
        btFirst = inflate.findViewById(R.id.bt_first);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getThreeFragmentData(ThreeBean threeBean){
        ToastUtil.showToast("Second"+threeBean);
        btFirst.setText(threeBean.getName());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public Button getButtonView(){
        return btFirst;
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
}
