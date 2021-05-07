package com.xyl.adapter;

import java.util.List;


import com.xyl.R;
import com.xyl.domain.TeamListBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	List<TeamListBean> child;
	private Context context;
	List<TeamListBean> father;

	public ExpandableListAdapter(Context context,
			List<TeamListBean> fatherList, List<TeamListBean> childList) {
		this.context = context;
		// 子菜单的选项的数据
		// 父菜单的选项的数据
		this.father = fatherList;
		this.child = childList;
	}
		/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupCount()
	 * 
	 * 获取第一级菜单的选的总数目
	 *
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return father.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
	 * 
	 * 获取一级菜单下面二级菜单的选项的总数目
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return child.get(groupPosition).staffs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroup(int)
	 * 
	 * 获取一级菜单的具体的选项的内容
	 */
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return father.get(groupPosition).name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChild(int, int)
	 * 
	 * 获取一级菜单下第二级菜单的具体的选项的内容
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return child.get(groupPosition).name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupId(int)
	 * 
	 * 获取第一级菜单的选项的id
	 */
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildId(int, int)
	 * 
	 * 获取一级菜单下二级菜单选项的id
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#hasStableIds()
	 * 
	 * 指定位置相应的组视图(指定视图相应的id)
	 */
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean,
	 * android.view.View, android.view.ViewGroup)
	 * 
	 * 对一级菜单的标签的内容进行设置
	 */

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		convertView = (LinearLayout) convertView.inflate(context,
				R.layout.item_expandablelist_layout, null);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.img_icon); 
		TextView textView = (TextView) convertView
				.findViewById(R.id.expandablelist_item_txt);
		TextView textName = (TextView) convertView
				.findViewById(R.id.name_item_txt);
		// 是否可以点击扩展开来,设置字体显示的位置
		if (isExpanded) {
			textName.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_down, 0);
		} else {
			textName.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_up, 0);
		}

		// 设置图片和字体的内容
		imageView.setImageResource(R.drawable.worker);
		textView.setText(father.get(groupPosition).name);
		textName.setText(father.get(groupPosition).teamLeaderName);

		return convertView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean,
	 * android.view.View, android.view.ViewGroup)
	 * 
	 * 
	 * 设置一级菜单下二级菜单的内容
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = (RelativeLayout) convertView.inflate(context,
				R.layout.item_expandablelist_child_layout, null);

		TextView textView = (TextView) convertView
				.findViewById(R.id.expandablelist_child_item);
		if(childPosition==0){
			textView.setText(child.get(groupPosition).teamLeaderName);
		}
		textView.setText(child.get(groupPosition).staffs.get(childPosition).name);
		return convertView;
	}

	/**
	 * 当选择子节点的时候，调用该方法
	 * 
	 * */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
