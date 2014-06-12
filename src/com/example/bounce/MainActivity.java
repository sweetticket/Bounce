package com.example.bounce;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private ImageView mImageView;
		private Canvas mCanvas;
		private Bitmap mBall;
		private int mWidth;
		private int mHeight;
		private Paint mPaint;
		private View mRootView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			mRootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			Handler circleHandler = new Handler();
			circleHandler.postDelayed(new Runnable(){
				@Override
				public void run(){
					drawBall(mRootView);
				}
			}, 500);
			
			Log.d("run", "done with run");
			Log.d("width height after run", "mWidth: " + mWidth + ", mHeight: " + mHeight);

			return mRootView;
		}
		
		public void drawBall(View rootView){
			mImageView = (ImageView) rootView.findViewById(R.id.backdrop);
			mWidth = mImageView.getWidth();
			mHeight = mImageView.getHeight();
			mBall = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(mBall);
			mPaint = new Paint();
			mPaint.setColor(Color.RED);
			
			Log.d("width height during run", "mWidth: " + mWidth + ", mHeight: " + mHeight);
			mCanvas.drawCircle(mWidth / 2, mHeight / 2, 60, mPaint);
			mImageView.setImageDrawable(new BitmapDrawable(getResources(),
					mBall));
			//mImageView.setImageResource(R.drawable.ic_launcher);
			Log.d("drawCircle", "done with drawCircle");
			rootView.invalidate();

		}
	}

}
