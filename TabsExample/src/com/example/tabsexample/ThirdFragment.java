package com.example.tabsexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class ThirdFragment extends Fragment {

	private MyExpandableListViewBaseAdapter adapter;
	private ExpandableListView tweets;
	private List<String> list;
	private HashMap<String, List<String>> map;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.layout_third_fragment, container, false);
		tweets = (ExpandableListView) view.findViewById(R.id.expandableListViewTab3);
		list = new ArrayList<String>();
		map = new HashMap<String, List<String>>();
		return view;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		list.add("#optimusinfo");
		list.add("#optimusmobility");
		list.add("#optimusqa");
		list.add("#optimusbi");
		map.put("#optimusinfo", MainActivity.getTweets("optimusinfo"));
		map.put("#optimusmobility", MainActivity.getTweets("optimusmobility"));
		map.put("#optimusqa", MainActivity.getTweets("optimusqa"));
		map.put("#optimusbi", MainActivity.getTweets("optimusbi"));		
        adapter = new MyExpandableListViewBaseAdapter(getActivity(), list, map);
        tweets.setAdapter(adapter);
	}
	

}
