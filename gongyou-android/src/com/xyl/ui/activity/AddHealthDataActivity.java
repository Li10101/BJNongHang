package com.xyl.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.JianKangQKBean;
import com.xyl.domain.personnel.QuFanChengQKBean;
import com.xyl.net.OrderManager;
import com.xyl.ui.view.MyLinearLayout;
import com.xyl.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddHealthDataActivity extends BaseActivity {

    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_left_tv)
    TextView toolbarLeftTv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.toolbar_right_btn)
    Button toolbarRightBtn;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.tv_tianbaoren)
    TextView tvTianBaoRen;
    @BindView(R.id.tv_riqi)
    TextView tvRiqi;
    @BindView(R.id.mll_layout2)
    MyLinearLayout mllLayout2;
    @BindView(R.id.tv_xingshi)
    TextView tvXingshi;
    @BindView(R.id.tv_isdayu)
    TextView tvIsdayu;
    @BindView(R.id.tv_shangxia)
    TextView tvShangXia;
    @BindView(R.id.et_jvtiwendu)
    EditText etJvTiWenDu;
    @BindView(R.id.tv_mQzhuangtai)
    TextView tvMQzhuangtai;
    @BindView(R.id.mll_layout4)
    MyLinearLayout mllLayout4;
    @BindView(R.id.tv_isfkg)
    TextView tvIsfkg;
    @BindView(R.id.tv_jiarenshifou)
    TextView tvJiarenshifou;
    @BindView(R.id.et_beizhu)
    EditText etBeizhu;
    @BindView(R.id.shifoujiechu)
    TextView shifoujiechu;
    @BindView(R.id.tv_shifoumiqiejcz)
    TextView tvShifoumiqiejcz;
    @BindView(R.id.tv_shifouyisibl)
    TextView tvShifouyisibl;
    @BindView(R.id.tv_shifouqvezhenbl)
    TextView tvShifouqvezhenbl;
    @BindView(R.id.tv_commit)
    Button tvCommit;


    List<String> xingshi = new ArrayList<>();
    List<Integer> xingshiIdLst = new ArrayList<>();
    List<String> isTrueFalse = new ArrayList<>();
    List<String> currentStatus = new ArrayList<>();
    List<Integer> currentStatusIdList = new ArrayList<>();
    List<String> shangxia = new ArrayList<>();
    private List<LoginBean> loginBeans;
    private List<String> tianbaos = new ArrayList<>();;
    private String staffId;
    private int shangxiawu;
    private int xingshiId;
    private int currentStautsId;
    private JianKangQKBean jianKangQKBean;

    @Override
    protected void initData() {
        jianKangQKBean = new JianKangQKBean();
        int jianKangQKId = getIntent().getIntExtra("jianKangQKId", 0);

        if (jianKangQKId != 0) {
            new OrderManager().jianKangqkFindbyid(jianKangQKId + "", new BaseNet.BaseCallback<JianKangQKBean>() {
                @Override
                public void messageSuccess(JianKangQKBean bean) {
                    jianKangQKBean = bean;
                    tvTianBaoRen.setText(jianKangQKBean.getShenQingRName());
                    tvRiqi.setText(jianKangQKBean.getDateTime());
                    tvShangXia.setText(jianKangQKBean.getShangXiaWDisplay());
                    tvXingshi.setText(jianKangQKBean.getXingShiDisplay());
                    tvIsdayu.setText(jianKangQKBean.getTiWenSFZC());
                    etJvTiWenDu.setText(jianKangQKBean.getJuTiWD());
                    tvMQzhuangtai.setText(jianKangQKBean.getMuQianZTDisplay());
                    tvIsfkg.setText(jianKangQKBean.getJiaRenSFYZZ());
                    tvJiarenshifou.setText(jianKangQKBean.getJiaRenSFYZZ());
                    etBeizhu.setText(jianKangQKBean.getBeiZhu());
                    shifoujiechu.setText(jianKangQKBean.getShiFouJCYQRY());
                    tvShifoumiqiejcz.setText(jianKangQKBean.getShiFouMQJCZ());
                    tvShifouyisibl.setText(jianKangQKBean.getShiFouYSBL());
                    tvShifouqvezhenbl.setText(jianKangQKBean.getShiFouQZBL());

                }

                @Override
                public void messageFailure(MessageBean backError) {

                }

                @Override
                public void connectFailure(MessageBean messageBean) {

                }
            });
        }

        getAllStaff();
    }

    private void getAllStaff() {
        new OrderManager().getFindAllStaff(new BaseNet.BaseCallback<List<LoginBean>>() {
            @Override
            public void messageSuccess(List<LoginBean> bean) {
                loginBeans = bean;
                for (int i = 0; i < loginBeans.size(); i++) {
                    tianbaos.add(loginBeans.get(i).name);
                }


            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);


        xingshi.add("现场检测");
        xingshi.add("电话沟通");
        xingshi.add("其它");
        xingshiIdLst.add(0);
        xingshiIdLst.add(1);
        xingshiIdLst.add(-1);

        isTrueFalse.add("是");
        isTrueFalse.add("否");

        currentStatus.add("应急值守");
        currentStatus.add("居家观察");
        currentStatus.add("医院就诊");
        currentStatus.add("其他");

        currentStatusIdList.add(1);
        currentStatusIdList.add(2);
        currentStatusIdList.add(3);
        currentStatusIdList.add(4);

        shangxia.add("上午");
        shangxia.add("下午");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_health_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_left_btn, R.id.tv_riqi, R.id.tv_xingshi, R.id.tv_isdayu, R.id.tv_shangxia, R.id.et_jvtiwendu,
            R.id.tv_mQzhuangtai, R.id.tv_isfkg, R.id.tv_jiarenshifou, R.id.et_beizhu, R.id.tv_tianbaoren,
            R.id.shifoujiechu, R.id.tv_shifoumiqiejcz, R.id.tv_shifouyisibl, R.id.tv_shifouqvezhenbl, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                break;
            case R.id.tv_tianbaoren:
                setStaff(tvTianBaoRen);
                hideInputMethod(tvTianBaoRen);
                break;
            case R.id.tv_riqi:
                setinitLunarSHiFMPicker(tvRiqi);
                hideInputMethod(tvRiqi);
                pvCustomLunar.show();
                break;
            case R.id.tv_shangxia:
                hideInputMethod(tvShangXia);
                setShangxiaWu(tvShangXia);
                break;
            case R.id.tv_xingshi:

                hideInputMethod(tvXingshi);
                setXingshi(tvXingshi);

                break;
            case R.id.tv_isdayu:
                hideInputMethod(tvIsdayu);
                setIsFalse(tvIsdayu);
                break;

            case R.id.et_jvtiwendu:
                etViewFocusa(etJvTiWenDu, 2);
                break;
            case R.id.tv_mQzhuangtai:
                hideInputMethod(tvMQzhuangtai);
                setCurrentStatus(tvMQzhuangtai);
                break;
            case R.id.tv_isfkg:
                hideInputMethod(tvIsfkg);
                setIsFalse(tvIsfkg);
                break;
            case R.id.tv_jiarenshifou:
                hideInputMethod(tvJiarenshifou);
                setIsFalse(tvJiarenshifou);
                break;
            case R.id.et_beizhu:
                etViewFocusa(etBeizhu, 2);
                break;
            case R.id.shifoujiechu:
                hideInputMethod(shifoujiechu);
                setIsFalse(shifoujiechu);
                break;
            case R.id.tv_shifoumiqiejcz:
                hideInputMethod(tvShifoumiqiejcz);
                setIsFalse(tvShifoumiqiejcz);
                break;
            case R.id.tv_shifouyisibl:
                hideInputMethod(tvShifouyisibl);
                setIsFalse(tvShifouyisibl);
                break;
            case R.id.tv_shifouqvezhenbl:
                hideInputMethod(tvShifouqvezhenbl);
                setIsFalse(tvShifouqvezhenbl);
                break;
            case R.id.tv_commit:
                jianKangQKBean.setShenQingRId(jianKangQKBean.getStaffId());
                jianKangQKBean.setDateTime(tvRiqi.getText().toString());
                jianKangQKBean.setShangXiaW(jianKangQKBean.getShangXiaW());
                jianKangQKBean.setXingShi(jianKangQKBean.getXingShi());
                jianKangQKBean.setTiWenSFZC(tvIsdayu.getText().toString());
                jianKangQKBean.setJuTiWD(etJvTiWenDu.getText().toString());
                jianKangQKBean.setMuQianZT(jianKangQKBean.getMuQianZT());

                jianKangQKBean.setShiFouYZZ(tvIsfkg.getText().toString());
                jianKangQKBean.setJiaRenSFYZZ(tvJiarenshifou.getText().toString());
                jianKangQKBean.setBeiZhu(etBeizhu.getText().toString());
                jianKangQKBean.setShiFouJCYQRY(shifoujiechu.getText().toString());
                jianKangQKBean.setShiFouMQJCZ(tvShifoumiqiejcz.getText().toString());
                jianKangQKBean.setShiFouYSBL(tvShifouyisibl.getText().toString());
                jianKangQKBean.setShiFouQZBL(tvShifouqvezhenbl.getText().toString());
                Gson gson = new Gson();
                String data = gson.toJson(jianKangQKBean);

                new OrderManager().jianKangqkSaveorUpdate(data, new BaseNet.EntityCallback() {
                    @Override
                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
                        ToastUtil.showToast(entityrResult.message);
                        setResult(2);
                        finish();
                    }
                });
                break;
        }
    }

    private void setStaff(final TextView textView){
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                staffId = loginBeans.get(options1).staffId;
                jianKangQKBean.setStaffId(Integer.valueOf(staffId));
                String tx = tianbaos.get(options1);
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);p
                textView.setText(tx);
                //customerType = options1 + 1;


            }
        })

                .setTitleText("填报人")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/


        pvOption.setPicker(tianbaos);//三级选择器

        pvOption.show();
    };

    public void setinitLunarSHiFMPicker(final TextView textView) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23, 00, 00);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28, 00, 00);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                ToastUtil.showToast(getTime(date));
                textView.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, false})

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    private void setXingshi(final TextView textView) {
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                xingshiId = xingshiIdLst.get(options1);
                jianKangQKBean.setXingShi(xingshiId);
                String tx = xingshi.get(options1);
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);
                textView.setText(tx);
                //customerType = options1 + 1;


            }
        })

                .setTitleText("形式")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/


        pvOption.setPicker(xingshi);//三级选择器

        pvOption.show();

    }

    private void setIsFalse(final TextView textView) {
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                String tx = isTrueFalse.get(options1);
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);
                textView.setText(tx);
                //customerType = options1 + 1;


            }
        })

                .setTitleText("是/否")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/


        pvOption.setPicker(isTrueFalse);//三级选择器

        pvOption.show();

    }

    private void setShangxiaWu(final TextView textView) {
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                shangxiawu = options1;
                jianKangQKBean.setShangXiaW(shangxiawu);
                String tx = shangxia.get(options1);
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);
                textView.setText(tx);
                //customerType = options1 + 1;


            }
        })

                .setTitleText("上午/下午")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/


        pvOption.setPicker(shangxia);//三级选择器

        pvOption.show();

    }

    private void setCurrentStatus(final TextView textView) {
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                currentStautsId = currentStatusIdList.get(options1);
                jianKangQKBean.setMuQianZT(currentStautsId);
                String tx = currentStatus.get(options1);
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);
                textView.setText(tx);
                //customerType = options1 + 1;

            }
        })

                .setTitleText("当前状态")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/


        pvOption.setPicker(currentStatus);//三级选择器

        pvOption.show();

    }

}
