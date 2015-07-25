package com.example.tabsexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;

/**
 * @author optimus158
 *
 *	Listener for switching tabs in Action Bar
 *
 */
public class MyTabListener implements TabListener {

	private Fragment fragment;
	
	public MyTabListener(Fragment fragment) {
		this.fragment = fragment;
	}

	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
	}

	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		arg1.replace(R.id.container, fragment);
	}

	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		arg1.remove(fragment);
	}

}
