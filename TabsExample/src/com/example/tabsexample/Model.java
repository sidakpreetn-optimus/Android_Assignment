package com.example.tabsexample;

import android.os.Parcel;
import android.os.Parcelable;



/**
 * @author optimus158
 * 
 * Model class for implementing ListView
 *
 */
public class Model implements Parcelable {
	String text;
	boolean check;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Model() {
	}

	public Model(String text, boolean check) {
		this.text = text;
		this.check = check;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeString(text);
		out.writeInt(check ? 1 : 0);
	}

	public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
		public Model createFromParcel(Parcel in) {
			return new Model(in);
		}

		public Model[] newArray(int size) {
			return new Model[size];
		}
	};

	private Model(Parcel in) {
		text = in.readString();
		check = in.readInt() == 1;
	}
}
