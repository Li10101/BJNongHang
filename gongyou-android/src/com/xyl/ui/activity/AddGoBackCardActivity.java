package com.xyl.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.xyl.domain.personnel.QuFanChengQKBean;
import com.xyl.net.OrderManager;
import com.xyl.ui.view.MyLinearLayout;
import com.xyl.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddGoBackCardActivity extends BaseActivity {


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
    @BindView(R.id.tv_quchengshijian)
    TextView tvQuchengshijian;
    @BindView(R.id.mll_layout5)
    MyLinearLayout mllLayout5;
    @BindView(R.id.et_mudidi)
    EditText etMudidi;
    @BindView(R.id.sfwuhanzhongzhuan)
    TextView sfwuhanzhongzhuan;
    @BindView(R.id.tv_fanchengshijian)
    TextView tvFanchengshijian;
    @BindView(R.id.et_chufadi)
    EditText etChufadi;
    @BindView(R.id.tv_fanchenggongjv)
    TextView tvFanchenggongjv;
    @BindView(R.id.et_banci)
    EditText etBanci;
    @BindView(R.id.tv_isStopWuhan)
    TextView tvIsStopWuhan;
    @BindView(R.id.et_stop_wuhan_where)
    EditText etStopWuhanWhere;
    @BindView(R.id.tv_jingtingshijian)
    TextView tvJingtingshijian;
    @BindView(R.id.tv_commit)
    Button tvCommit;


    List<String> isTrueFalse = new ArrayList<>();
    List<String> backTrackin = new ArrayList<>();
    List<Integer> backTrackinint = new ArrayList<>();
    @BindView(R.id.tv_tianbaoren)
    TextView tvTianbaoren;
    @BindView(R.id.tv_tianbaoshijian)
    TextView tvTianbaoshijian;
    private List<LoginBean> loginBeans;
    private List<String> tianbaos = new ArrayList<>();
    private String staffId;
    private int backInt;
    private QuFanChengQKBean quFanChengQKBean;

    @Override
    protected void initData() {
        quFanChengQKBean = new QuFanChengQKBean();

        int quFanChengQKId = getIntent().getIntExtra("quFanChengQKId", 0);

        if (quFanChengQKId != 0){


        new OrderManager().quFanChengqkFindbyid(quFanChengQKId+"", new BaseNet.BaseCallback<QuFanChengQKBean>() {
            @Override
            public void messageSuccess(QuFanChengQKBean bean) {
                quFanChengQKBean = bean;
                tvTianbaoren.setText(quFanChengQKBean.getShenQingRName());
                tvTianbaoshijian.setText(quFanChengQKBean.getDateTime());
                tvQuchengshijian.setText(quFanChengQKBean.getQuChengSJ());
                etMudidi.setText(quFanChengQKBean.getMuDiD());
                sfwuhanzhongzhuan.setText(quFanChengQKBean.getShiFouJHBZZ());
                tvFanchengshijian.setText(quFanChengQKBean.getFanChengSJ());
                etChufadi.setText(quFanChengQKBean.getChuFaD());
                tvFanchenggongjv.setText(quFanChengQKBean.getFanChengTypeDisplay());
                etBanci.setText(quFanChengQKBean.getFanChengCPH());
                tvIsStopWuhan.setText(quFanChengQKBean.getShiFouJTHB());
                etStopWuhanWhere.setText(quFanChengQKBean.getJingTingHBHD());
                tvJingtingshijian.setText(quFanChengQKBean.getJingTingSJ());

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
        isTrueFalse.add("???");
        isTrueFalse.add("???");


        backTrackin.add("??????");
        backTrackin.add("??????");
        backTrackin.add("??????");
        backTrackin.add("??????");

        backTrackinint.add(1);
        backTrackinint.add(2);
        backTrackinint.add(3);
        backTrackinint.add(4);

    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_health_goback;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_left_btn,R.id.tv_tianbaoren, R.id.tv_tianbaoshijian, R.id.tv_quchengshijian, R.id.mll_layout5, R.id.et_mudidi, R.id.sfwuhanzhongzhuan,
            R.id.tv_fanchengshijian, R.id.et_chufadi, R.id.tv_fanchenggongjv, R.id.et_banci, R.id.tv_isStopWuhan,
            R.id.et_stop_wuhan_where, R.id.tv_jingtingshijian, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.tv_tianbaoren:
                setStaff(tvTianbaoren);
                hideInputMethod(tvTianbaoren);
                break;
            case R.id.tv_tianbaoshijian:
                setinitLunarSHiFMPicker(tvTianbaoshijian);
                hideInputMethod(tvTianbaoshijian);
                pvCustomLunar.show();
                break;
            case R.id.tv_quchengshijian:
                setinitLunarSHiFMPicker(tvQuchengshijian);
                hideInputMethod(tvQuchengshijian);
                pvCustomLunar.show();
                break;
            case R.id.mll_layout5:
                break;
            case R.id.et_mudidi:
                etViewFocusa(etMudidi, 2);
                break;
            case R.id.sfwuhanzhongzhuan:
                hideInputMethod(sfwuhanzhongzhuan);
                setIsFalse(sfwuhanzhongzhuan);
                break;
            case R.id.tv_fanchengshijian:
                setinitLunarSHiFMPicker(tvFanchengshijian);
                hideInputMethod(tvFanchengshijian);
                pvCustomLunar.show();
                break;
            case R.id.et_chufadi:
                etViewFocusa(etChufadi, 2);
                break;
            case R.id.tv_fanchenggongjv:
                hideInputMethod(tvFanchenggongjv);
                setBackTrackin(tvFanchenggongjv);

                break;
            case R.id.et_banci:
                etViewFocusa(etBanci, 2);
                break;
            case R.id.tv_isStopWuhan:
                hideInputMethod(tvIsStopWuhan);
                setIsFalse(tvIsStopWuhan);
                break;
            case R.id.et_stop_wuhan_where:
                etViewFocusa(etStopWuhanWhere, 2);
                break;
            case R.id.tv_jingtingshijian:
                setinitLunarSHiFMPicker(tvJingtingshijian);
                hideInputMethod(tvJingtingshijian);
                pvCustomLunar.show();
                break;
            case R.id.tv_commit:
                quFanChengQKBean.setShenQingRId(quFanChengQKBean.getStaffId());
                quFanChengQKBean.setShenQingRName(tvTianbaoren.getText().toString());
                quFanChengQKBean.setDateTime(tvTianbaoshijian.getText().toString());
                quFanChengQKBean.setQuChengSJ(tvTianbaoshijian.getText().toString());
                quFanChengQKBean.setMuDiD(etMudidi.getText().toString());
                quFanChengQKBean.setShiFouJHBZZ(sfwuhanzhongzhuan.getText().toString());
                quFanChengQKBean.setFanChengSJ(tvFanchengshijian.getText().toString());
                quFanChengQKBean.setChuFaD(etChufadi.getText().toString());
                quFanChengQKBean.setFanChengType( quFanChengQKBean.getFanChengType());
                quFanChengQKBean.setFanChengTypeDisplay(tvFanchenggongjv.getText().toString());
                quFanChengQKBean.setFanChengCPH(etBanci.getText().toString());
                quFanChengQKBean.setShiFouJTHB(tvIsStopWuhan.getText().toString());
                quFanChengQKBean.setJingTingHBHD(etStopWuhanWhere.getText().toString());
                quFanChengQKBean.setJingTingSJ(tvJingtingshijian.getText().toString());

                Gson gson = new Gson();
                String data = gson.toJson(quFanChengQKBean);

                new OrderManager().QuFanChengqksaveorUpdate(data, new BaseNet.EntityCallback() {
                    @Override
                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
                        ToastUtil.showToast(entityrResult.message);
                        setResult(1);
                        finish();
                    }
                });

                break;
        }
    }

    private void setBackTrackin(final TextView textView) {
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                backInt = backTrackinint.get(options1);
                quFanChengQKBean.setFanChengType(backInt);
                String tx = backTrackin.get(options1);
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);
                textView.setText(tx);
                //customerType = options1 + 1;


            }
        })

                .setTitleText("????????????")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //???????????????????????????
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//???????????????
        pvOptions.setPicker(options1Items, options2Items);//???????????????*/


        pvOption.setPicker(backTrackin);//???????????????

        pvOption.show();

    }

    private void setStaff(final TextView textView){
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                staffId = loginBeans.get(options1).staffId;
                quFanChengQKBean.setStaffId(Integer.valueOf(staffId));
                String tx = loginBeans.get(options1).name;
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);p
                textView.setText(tx);
                //customerType = options1 + 1;


            }
        })

                .setTitleText("?????????")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //???????????????????????????
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//???????????????
        pvOptions.setPicker(options1Items, options2Items);//???????????????*/


        pvOption.setPicker(tianbaos);//???????????????

        pvOption.show();
    };


    private void setIsFalse(final TextView textView) {
        OptionsPickerView pvOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                /*private List<String> customeStates;
                private List<String> comeChannels;*/
                String tx = isTrueFalse.get(options1);
                //ToastUtil.showToast(tx);
                //etZukeType.setText(tx);p
                textView.setText(tx);
                //customerType = options1 + 1;


            }
        })

                .setTitleText("??????????????????")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //???????????????????????????
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//???????????????
        pvOptions.setPicker(options1Items, options2Items);//???????????????*/


        pvOption.setPicker(isTrueFalse);//???????????????

        pvOption.show();

    }

    public void setinitLunarSHiFMPicker(final TextView textView) {
        Calendar selectedDate = Calendar.getInstance();//??????????????????
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23, 00, 00);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28, 00, 00);
        //??????????????? ??????????????????
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//??????????????????
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

                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setDividerColor(Color.RED)
                .build();
    }

    private String getTime(Date date) {//???????????????????????????????????????
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }


}
