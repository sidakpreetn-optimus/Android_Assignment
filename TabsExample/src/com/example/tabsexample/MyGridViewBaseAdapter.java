package com.example.tabsexample;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * @author optimus158
 *
 *	Adapter class for GridView implementing Device's images
 *
 */
public class MyGridViewBaseAdapter extends BaseAdapter {

	private Context context;
	private String[] galleryImages;
	
	static class ViewHolder {
		public ImageView imageview;
	}
	
	public MyGridViewBaseAdapter(Context context, String[] images) {
		this.context=context;
		galleryImages=images;
	}
	
	public int getCount() {
		return galleryImages.length;
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view = arg1;
		if(view==null) {
			LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.grid_view_row, null);
			ViewHolder holder = new ViewHolder();
			holder.imageview = (ImageView) view.findViewById(R.id.imageViewGridView);
			view.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.imageview.setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(galleryImages[arg0]), 200, 150));
		return view;
	}
}