package com.example.tabsexample;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends Activity {

	private ImageView iv;

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_image);
		iv = (ImageView) findViewById(R.id.imageViewImage);
		Intent intent = getIntent();
		String imagePath = intent.getStringExtra("ImagePath");
		setImage(imagePath);
	}
	
	/**
	 * Takes the path of the image and sets the image to the View
	 * 
	 * @param path
	 */
	private void setImage(String path) {
		
		Uri uri = Uri.fromFile(new File(path));
		iv.setImageURI(uri);
	}
}
