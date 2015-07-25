package com.example.tabsexample;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FirstFragment extends Fragment {

	private ArrayList<Model> list;
	private MyListViewBaseAdapter adapter;
	private ListView lv;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.layout_first_fragment, container,false);
		lv = (ListView) view.findViewById(R.id.listViewTab1);
		return view;
	}

	/*
	 *	Saving the State of Listview for achieving persistance
	 */
	public void onSaveInstanceState(Bundle outState) {
		
		outState.putParcelableArrayList("object", list);
		super.onSaveInstanceState(outState);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			list = savedInstanceState.getParcelableArrayList("object");
		} 
		if (list == null){
			list = new ArrayList<Model>();
			for (int i = 0; i < 100; i++) {
				list.add(new Model("", false));
			}
		}
		adapter = new MyListViewBaseAdapter(getActivity(), list);
		lv.setAdapter(adapter);
	}
}
