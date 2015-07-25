package com.example.tabsexample;

import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SecondFragment extends Fragment implements OnItemClickListener {

	private GridView gv;
	private String[] allImages;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.layout_second_fragment, container, false);
		gv = (GridView) view.findViewById(R.id.gridViewTab2);
		allImages = getImagesPath();
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		MyGridViewBaseAdapter adapter = new MyGridViewBaseAdapter(getActivity(), allImages);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(this);
	}
    
	public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
		
		Intent intent = new Intent(getActivity(),ImageActivity.class);
		intent.putExtra("ImagePath", allImages[paramInt]);
		startActivity(intent);
	}

    /**
     * Method for getting images from device using ContentResolver
     * 
     * @return String Array
     */
    public String[] getImagesPath() {
    	
    	Uri uri;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        Cursor cursor;
        int column_index_data;
        String PathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
        cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        column_index_data = cursor.getColumnIndexOrThrow(MediaColumns.DATA);        
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(PathOfImage);
        }
        return listOfAllImages.toArray(new String[listOfAllImages.size()]);
    }
}
