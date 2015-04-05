/**
 * @author Yuxi Jin 
 * school: CMU
 * the date submitted: Oct 30, 2014
 */
package util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;

	public ImageAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(12, 12, 12, 12);
		} else {
			imageView = (ImageView) convertView;
		}
		return imageView;
	}
}
