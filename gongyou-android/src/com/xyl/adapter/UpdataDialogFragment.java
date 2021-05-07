package com.xyl.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.xyl.R;
import com.xyl.domain.LoginBean;
import com.xyl.domain.OrderBean;
import com.xyl.ui.view.AddSub;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

/**
 * Created by 47500 on 2018/6/4.
 */

public class UpdataDialogFragment extends DialogFragment {

    private OrderBean.GoodsCaseDetailsVosBean caseDetailsVosBean;
    private TextView tvDiaCategory;
    private TextView tvDiaName;
    private LinearLayout llDiaPrice;
    private TextView tvDiaPrice;
    private EditText etPrice;
    private TextView tvDiaCount;
    private AddSub diaAddSub;
    private int goodsCaseId;
    private int position;
    private OrderBean orderBean;
    private LoginBean loginBean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-06-12 11:46:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */



    public interface UpdataDialogListener {
        void onUpdataPositiveListener(DialogFragment dialog, OrderBean.GoodsCaseDetailsVosBean caseDetailsVosBean, int goodsCaseId, int position);

        void onUpdataNegativeListener(DialogFragment dialog);
    }

    private UpdataDialogListener updataDialogListener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle !=null){
            caseDetailsVosBean = (OrderBean.GoodsCaseDetailsVosBean) bundle.getSerializable("DetailsVosBean");
            orderBean = (OrderBean) bundle.getSerializable("orderBean");
            loginBean = (LoginBean) bundle.getSerializable("LoginBean");
            goodsCaseId = bundle.getInt("GoodsCaseId");
            position = bundle.getInt("position");

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inflate = inflater.inflate(R.layout.updatacontent_dialog, null);
        tvDiaCategory = inflate.findViewById( R.id.tv_dia_category );
        tvDiaName = inflate.findViewById( R.id.tv_dia_name );
        llDiaPrice = inflate.findViewById( R.id.ll_dia_price );
        tvDiaPrice = inflate.findViewById( R.id.tv_dia_price );
        etPrice = inflate.findViewById( R.id.et_price );
        tvDiaCount = inflate.findViewById( R.id.tv_dia_count );
        diaAddSub = inflate.findViewById( R.id.dia__add_sub );
        diaAddSub.setMinValue(0);
        tvDiaCategory.setText("商品类别:"+caseDetailsVosBean.getCategory());
        tvDiaName.setText("商品名称:"+caseDetailsVosBean.getName());
        etPrice.setText(caseDetailsVosBean.getUnitPrice()+"");
        diaAddSub.setValue(caseDetailsVosBean.getAmount());
        builder.setView(inflate)
                // Add action buttons
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (updataDialogListener!=null){
                            String price = etPrice.getText().toString();
                            double value = diaAddSub.getValue();
                            caseDetailsVosBean.setUnitPrice(new BigDecimal(price));
                            caseDetailsVosBean.setAmount(value);

                            OrderBean.GoodsCaseTraceVosBean goodsCaseTraceVosBean = new OrderBean.GoodsCaseTraceVosBean();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                            String time=format.format(new Date());
                            goodsCaseTraceVosBean.setAction("采购单修改");
                            goodsCaseTraceVosBean.setExecuteTime(time);
                            goodsCaseTraceVosBean.setGoodsCaseTraceId(goodsCaseId);
                            goodsCaseTraceVosBean.setStaffId(orderBean.getGoodsCaseTraceVos().size()+1);
                            goodsCaseTraceVosBean.setStaffName(loginBean.name);
                            orderBean.getGoodsCaseTraceVos().add(goodsCaseTraceVosBean);
                            updataDialogListener.onUpdataPositiveListener(UpdataDialogFragment.this,caseDetailsVosBean,goodsCaseId,position);
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (updataDialogListener!=null){
                            updataDialogListener.onUpdataNegativeListener(UpdataDialogFragment.this);
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            updataDialogListener = (UpdataDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
