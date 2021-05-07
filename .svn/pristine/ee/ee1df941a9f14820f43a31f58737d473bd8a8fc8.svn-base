package com.xyl.ui.view;

import java.util.Calendar;

import com.xyl.ui.widget.DateTimePicker;
import com.xyl.ui.widget.DateTimePicker.OnDateTimeChangedListener;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;

public class DateTimePickerView extends View{
	private DateTimePicker mDateTimePicker;
	private Calendar mDate = Calendar.getInstance();
	private OnDateTimeSetListener mOnDateTimeSetListener;

	@SuppressWarnings("deprecation")
	public DateTimePickerView(Context context, long date) {
		super(context);
		mDateTimePicker = new DateTimePicker(context);
		mDateTimePicker
				.setOnDateTimeChangedListener(new OnDateTimeChangedListener() {
					@Override
					public void onDateTimeChanged(DateTimePicker view,
							int year, int month, int day, int hour, int minute) {
						mDate.set(Calendar.YEAR, year);
						mDate.set(Calendar.MONTH, month);
						mDate.set(Calendar.DAY_OF_MONTH, day);
						mDate.set(Calendar.HOUR_OF_DAY, hour);
						mDate.set(Calendar.MINUTE, minute);
						mDate.set(Calendar.SECOND, 0);
						updateTitle(mDate.getTimeInMillis());
					}
				});

		mDate.setTimeInMillis(date);
		updateTitle(mDate.getTimeInMillis());
	}

	public View getView() {
		return mDateTimePicker;
	}

	public interface OnDateTimeSetListener {
		void OnDateTimeSet(View dialog, long date);
	}

	private void updateTitle(long date) {
		int flag = DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE
				| DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME;
	}

	public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
		mOnDateTimeSetListener = callBack;
	}

	public void onClick() {
		if (mOnDateTimeSetListener != null) {
			mOnDateTimeSetListener.OnDateTimeSet(this, mDate.getTimeInMillis());
		}
	}
}
