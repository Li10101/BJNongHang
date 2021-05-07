package com.xyl.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;



import com.xyl.R;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.domain.CasesBean;
import com.xyl.net.OrderStream;

public class CommentActivity extends Activity {

	private ImageView iv_title_left;
	private TextView tv_title;
	private EditText et_comment;
	private RatingBar rb_comment;
	private TextView tv_comment;
	private TextView tv_title_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		
		initView();
		initListener();
		initData();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}

	private void initView() {
		iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
		tv_title = (TextView)this.findViewById(R.id.tv_title);
		tv_title_right = (TextView)this.findViewById(R.id.tv_title_right);
		
		et_comment = (EditText)this.findViewById(R.id.et_comment);
		rb_comment = (RatingBar)this.findViewById(R.id.rb_comment);
		tv_comment = (TextView)this.findViewById(R.id.tv_comment);

	}

	private float rating;
	private float ratings;
	private CasesBean.Records casesBean;
	private void initListener() {
		iv_title_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setResult(10);
				finish();
			}
		});
		
		tv_title_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new OrderStream().evaluate(new EntityCallback() {
					
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						if(entityrResult.entityType == EntityType.messagetrue){
							Intent data = new Intent();
							data.putExtra("rating", ratings);
							data.putExtra("comment", et_comment.getText().toString().trim());
							Toast.makeText(getApplicationContext(), "感谢评价", Toast.LENGTH_SHORT).show();
							setResult(8, data);
							finish();
						}
					}
				}, casesBean.repairCaseCode, String.valueOf(((int)ratings)), et_comment.getText().toString().trim());
			}
		});
		
		rb_comment.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar rb, float rat, boolean arg2) {
				rating = rat;
				rb.setRating(rat);
				if(rating>0&&rating<=1){
					tv_comment.setText("非常不满意");
				}else if(rating>1 && rating<=2){
					tv_comment.setText("不满意");
				}else if(rating>2 && rating<=3){
					tv_comment.setText("一般");
				}else if(rating>3 && rating<=4){
					tv_comment.setText("满意");
				}else if(rating>4 && rating<=5){
					tv_comment.setText("非常满意");
				}
				ratings = rating-1;
				/*if(rating==0){
					tv_comment.setText("非常不满意");
				}else if(rating>0 && rating<=1){
					tv_comment.setText("不满意");
				}else if(rating>1 && rating<=2){
					tv_comment.setText("一般");
				}else if(rating>2 && rating<=3){
					tv_comment.setText("满意");
				}else if(rating>3 && rating<=4){
					tv_comment.setText("非常满意");
				}*/
			}
		});
	}

	private void initData() {
		iv_title_left.setVisibility(View.VISIBLE);
		iv_title_left.setImageResource(R.drawable.rightmenu);
		tv_title.setText("工单评价");
		tv_title_right.setText("完成");
		tv_title_right.setVisibility(View.VISIBLE);
		
		casesBean = (CasesBean.Records)this.getIntent().getSerializableExtra("CasesBean");
	}
}
