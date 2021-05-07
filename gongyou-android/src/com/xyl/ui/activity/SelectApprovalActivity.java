package com.xyl.ui.activity;

import android.os.Bundle;


import android.util.SparseArray;
import android.widget.Button;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.adapter.ShowSelectApprovalAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.domain.FirstBean;
import com.xyl.domain.SecondBean;
import com.xyl.domain.ThreeBean;
import com.xyl.fragment.approvalFragment.FourFragment;
import com.xyl.fragment.approvalFragment.FristFragment;
import com.xyl.fragment.approvalFragment.SecondFragment;
import com.xyl.fragment.approvalFragment.ThreeFragment;
import com.xyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;


public class SelectApprovalActivity extends BaseActivity {

    private FragmentTransaction fragmentTransaction;
    private FristFragment fristFragment;
    private SecondFragment secondFragment;
    private FragmentManager fragmentManager;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;


    private Fragment mCurrentFragment;
    private SparseArray<Fragment> mainFragments;
    private Button secondButton;


    @Override
    protected void initData() {
        ShowSelectApprovalAdapter approvalAdapter = new ShowSelectApprovalAdapter(SelectApprovalActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectApprovalActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fristFragment = new FristFragment();
        secondFragment = new SecondFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();

        fragmentManager = this.getSupportFragmentManager();
        initFragmentSelected(3);
        initFragmentSelected(2);
        initFragmentSelected(1);
        initFragmentSelected(0);


    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_approval;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getFirstFragmentData(FirstBean firstBean) {
        ToastUtil.showToast("first" + firstBean);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSecondFragmentData(SecondBean secondBean) {
        ToastUtil.showToast("Second" + secondBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getThreeFragmentData(ThreeBean threeBean){
        ToastUtil.showToast("Three"+threeBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void FragmentButtonClicked(String str) {
        switch (str) {
            case "fragment1":
                // Toast.makeText(getApplicationContext(), "fragment1点击", Toast.LENGTH_LONG).show();
                initFragmentSelected(0);//展示fragment2界面
                break;
            case "fragment2":
                //Toast.makeText(getApplicationContext(), "fragment2点击", Toast.LENGTH_LONG).show();
                initFragmentSelected(1);//展示fragment1界面
                break;
            case "fragment3":
                //Toast.makeText(getApplicationContext(), "fragment2点击", Toast.LENGTH_LONG).show();
                initFragmentSelected(2);//展示fragment1界面
                break;
            case "fragment4":
                //Toast.makeText(getApplicationContext(), "fragment2点击", Toast.LENGTH_LONG).show();
                initFragmentSelected(3);//展示fragment1界面
                break;
            default:
                break;
        }

    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getFragmentData(String fragments){
//        switch (fragments){
//            case "fragment1":
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                if (secondFragment == null){
//                    secondFragment = new SecondFragment();
//                    fragmentTransaction.add(R.id.fl_approval_list,secondFragment).hide(fristFragment).commit();
//                }else{
//                    fragmentTransaction.hide(fristFragment).show(secondFragment).commit();
//                }
//
//                    break;
//
//            case "fragment2":
//                break;
//            case "fragment3":
//                break;
//        }
//
//
//    }

    private void initFragmentSelected(int i) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hindFragments(fragmentTransaction);
        switch (i) {
            case 0:
                if (!fristFragment.isAdded()) {
                    fragmentTransaction.add(R.id.fl_approval_list, fristFragment, "navitionFragment1");
                }
                fragmentTransaction.show(fristFragment);
                break;
            case 1:
                if (!secondFragment.isAdded()) {
                    fragmentTransaction.add(R.id.fl_approval_list, secondFragment, "navitionFragment1");
                }
                fragmentTransaction.show(secondFragment);
                break;
            case 2:
                if (!threeFragment.isAdded()) {
                    fragmentTransaction.add(R.id.fl_approval_list, threeFragment, "navitionFragment1");
                }
                fragmentTransaction.show(threeFragment);
                break;
            case 3:
                if (!fourFragment.isAdded()) {
                    fragmentTransaction.add(R.id.fl_approval_list, fourFragment, "navitionFragment1");
                }
                fragmentTransaction.show(fourFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 隐藏fragment
     *
     * @param fragmentTransaction
     */
    private void hindFragments(FragmentTransaction fragmentTransaction) {
        if (fristFragment != null) {
            fragmentTransaction.hide(fristFragment);
        }
        if (secondFragment != null) {
            fragmentTransaction.hide(secondFragment);
        }
        if (threeFragment != null) {
            fragmentTransaction.hide(threeFragment);
        }
        if (fourFragment != null) {
            fragmentTransaction.hide(fourFragment);
        }
    }


}
