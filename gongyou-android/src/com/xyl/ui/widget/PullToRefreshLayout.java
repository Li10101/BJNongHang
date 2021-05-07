package com.xyl.ui.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xyl.R;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;



public class PullToRefreshLayout extends RelativeLayout implements
		OnTouchListener {
	public static final String TAG = "PullToRefreshLayout";
	public static final int PULL_TO_REFRESH = 0;
	public static final int RELEASE_TO_REFRESH = 1;
	public static final int REFRESHING = 2;
	public static final int DONE = 3;
	private int state = PULL_TO_REFRESH;
	private OnRefreshListener mListener;
	public static final int REFRESH_SUCCEED = 0;
	public static final int REFRESH_FAIL = 1;
	private View headView;
	private View contentView;
	private float downY, lastY;
	public float moveDeltaY = 0;
	private float refreshDist = 200;
	private Timer timer;
	private MyTimerTask mTask;
	public float MOVE_SPEED = 8;
	private boolean isLayout = false;
	private boolean canPull = true;
	private boolean isTouchInRefreshing = false;
	private float radio = 2;
	private RotateAnimation rotateAnimation;
	private RotateAnimation refreshingAnimation;
	private View pullView;
	private View refreshingView;
	private View stateImageView;
	private TextView stateTextView;

	Handler updateHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			MOVE_SPEED = (float) (8 + 5 * Math.tan(Math.PI / 2
					/ getMeasuredHeight() * moveDeltaY));
			if (state == REFRESHING && moveDeltaY <= refreshDist
					&& !isTouchInRefreshing) {
				moveDeltaY = refreshDist;
				mTask.cancel();
			}
			if (canPull)
				moveDeltaY -= MOVE_SPEED;
			if (moveDeltaY <= 0) {
				moveDeltaY = 0;
				pullView.clearAnimation();
				if (state != REFRESHING)
					changeState(PULL_TO_REFRESH);
				mTask.cancel();
			}
			requestLayout();
		}

	};

	public void setOnRefreshListener(OnRefreshListener listener) {
		mListener = listener;
	}

	public PullToRefreshLayout(Context context) {
		super(context);
		initView(context);
	}

	public PullToRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	private void initView(Context context) {
		timer = new Timer();
		mTask = new MyTimerTask(updateHandler);
		rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
				context, R.anim.reverse_anim);
		refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
				context, R.anim.rotating);
		LinearInterpolator lir = new LinearInterpolator();
		rotateAnimation.setInterpolator(lir);
		refreshingAnimation.setInterpolator(lir);
	}

	private void hideHead() {
		if (mTask != null) {
			mTask.cancel();
			mTask = null;
		}
		mTask = new MyTimerTask(updateHandler);
		timer.schedule(mTask, 0, 5);
	}

	public void refreshFinish(int refreshResult) {
		refreshingView.clearAnimation();
		refreshingView.setVisibility(View.GONE);
		switch (refreshResult) {
		case REFRESH_SUCCEED:
			stateImageView.setVisibility(View.VISIBLE);
			stateTextView.setText(R.string.refresh_succeed);
			stateImageView.setBackgroundResource(R.drawable.refresh_succeed);
			break;
		case REFRESH_FAIL:
			stateImageView.setVisibility(View.VISIBLE);
			stateTextView.setText(R.string.refresh_fail);
			stateImageView.setBackgroundResource(R.drawable.refresh_failed);
			break;
		default:
			break;
		}
		new Handler() {
			@Override
			public void handleMessage(Message msg) {
				state = PULL_TO_REFRESH;
				hideHead();
			}
		}.sendEmptyMessageDelayed(0, 1000);
	}

	private void changeState(int to) {
		state = to;
		switch (state) {
		case PULL_TO_REFRESH:
			stateImageView.setVisibility(View.GONE);
			stateTextView.setText(R.string.pull_to_refresh);
			pullView.clearAnimation();
			pullView.setVisibility(View.VISIBLE);
			break;
		case RELEASE_TO_REFRESH:
			stateTextView.setText(R.string.release_to_refresh);
			pullView.startAnimation(rotateAnimation);
			break;
		case REFRESHING:
			pullView.clearAnimation();
			refreshingView.setVisibility(View.VISIBLE);
			pullView.setVisibility(View.INVISIBLE);
			refreshingView.startAnimation(refreshingAnimation);
			stateTextView.setText(R.string.refreshing);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			downY = ev.getY();
			lastY = downY;
			if (mTask != null) {
				mTask.cancel();
			}
			if (ev.getY() < moveDeltaY)
				return true;
			break;
		case MotionEvent.ACTION_MOVE:
			if (canPull) {
				moveDeltaY = moveDeltaY + (ev.getY() - lastY) / radio;
				if (moveDeltaY < 0)
					moveDeltaY = 0;
				if (moveDeltaY > getMeasuredHeight())
					moveDeltaY = getMeasuredHeight();
				if (state == REFRESHING) {
					isTouchInRefreshing = true;
				}
			}
			lastY = ev.getY();
			radio = (float) (2 + 2 * Math.tan(Math.PI / 2 / getMeasuredHeight()
					* moveDeltaY));
			requestLayout();
			if (moveDeltaY <= refreshDist && state == RELEASE_TO_REFRESH) {
				changeState(PULL_TO_REFRESH);
			}
			if (moveDeltaY >= refreshDist && state == PULL_TO_REFRESH) {
				changeState(RELEASE_TO_REFRESH);
			}
			if (moveDeltaY > 8) {
				clearContentViewEvents();
			}
			if (moveDeltaY > 0) {
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (moveDeltaY > refreshDist)
				isTouchInRefreshing = false;
			if (state == RELEASE_TO_REFRESH) {
				changeState(REFRESHING);
				if (mListener != null)
					mListener.onRefresh();
			} else {

			}
			hideHead();
		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	private void clearContentViewEvents() {
		try {
			Field[] fields = AbsListView.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++)
				if (fields[i].getName().equals("mPendingCheckForLongPress")) {
					// mPendingCheckForLongPress��AbsListView�е��ֶΣ�ͨ�������ȡ������Ϣ�б�ɾ����ȥ�������¼�
					fields[i].setAccessible(true);
					contentView.getHandler().removeCallbacks(
							(Runnable) fields[i].get(contentView));
				} else if (fields[i].getName().equals("mTouchMode")) {
					fields[i].setAccessible(true);
					fields[i].set(contentView, -1);
				}
			((AbsListView) contentView).getSelector().setState(new int[] { 0 });
		} catch (Exception e) {
			Log.d(TAG, "error : " + e.toString());
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (moveDeltaY == 0)
			return;
		RectF rectF = new RectF(0, 0, getMeasuredWidth(), moveDeltaY);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		LinearGradient linearGradient = new LinearGradient(0, moveDeltaY, 0,
				moveDeltaY - 26, 0x66000000, 0x00000000, TileMode.CLAMP);
		paint.setShader(linearGradient);
		paint.setStyle(Style.FILL);
		canvas.drawRect(rectF, paint);
	}

	private void initView() {
		pullView = headView.findViewById(R.id.pull_icon);
		stateTextView = (TextView) headView.findViewById(R.id.state_tv);
		refreshingView = headView.findViewById(R.id.refreshing_icon);
		stateImageView = headView.findViewById(R.id.state_iv);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (!isLayout) {
			headView = getChildAt(0);
			contentView = getChildAt(1);
			contentView.setOnTouchListener(this);
			isLayout = true;
			initView();
			refreshDist = ((ViewGroup) headView).getChildAt(0)
					.getMeasuredHeight();
		}
		if (canPull) {
			headView.layout(0, (int) moveDeltaY - headView.getMeasuredHeight(),
					headView.getMeasuredWidth(), (int) moveDeltaY);
			contentView.layout(0, (int) moveDeltaY,
					contentView.getMeasuredWidth(), (int) moveDeltaY
							+ contentView.getMeasuredHeight());
		} else
			super.onLayout(changed, l, t, r, b);
	}

	class MyTimerTask extends TimerTask {
		Handler handler;

		public MyTimerTask(Handler handler) {
			this.handler = handler;
		}

		@Override
		public void run() {
			handler.sendMessage(handler.obtainMessage());
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		AbsListView alv = null;
		try {
			alv = (AbsListView) v;
		} catch (Exception e) {
			return false;
		}
		if (alv.getCount() == 0) {
			canPull = true;
		} else if (alv.getFirstVisiblePosition() == 0
				&& alv.getChildAt(0).getTop() >= 0) {
			canPull = true;
		} else
			canPull = false;
		return false;
	}

	public interface OnRefreshListener {
		void onRefresh();
	}
}
