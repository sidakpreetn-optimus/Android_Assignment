package com.example.tabsexample;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

/**
 * @author optimus158
 *
 *	Adapter class for ListView 
 */
public class MyListViewBaseAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Model> list;
	
	static class ViewHolder {
		public EditText edittext;
		public CheckBox checkbox;
	}
	
	public MyListViewBaseAdapter(Context context, ArrayList<Model> list) {
		this.context = context;
		
		this.list = list;
	}
	
	public int getCount() {
		return list.size();
	}

	public Model getItem(int arg0) {
		return list.get(arg0);
	}
	
	public int getItemViewType(int position) {
		return position;
	}

	public int getViewTypeCount() {
		return getCount();
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(final int position, View arg1, ViewGroup arg2) {
		View view = arg1;
		ViewHolder holder = null;

		if(view == null) {
			LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.list_view_row, null);
			holder = new ViewHolder();
			holder.edittext = (EditText) view.findViewById(R.id.editTextListView);
			holder.checkbox = (CheckBox) view.findViewById(R.id.checkBoxListView);
			view.setTag(holder);	
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.edittext.setText(getItem(position).getText());
		holder.checkbox.setChecked(getItem(position).isCheck());
		
		holder.edittext.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				getItem(position).setText(arg0.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});
		
		holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				getItem(position).setCheck(arg1);
			}
		});
		
		return view;
	}

}
