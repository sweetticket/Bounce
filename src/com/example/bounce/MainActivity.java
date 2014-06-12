package com.example.bounce;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	public static final float mRadius = 60;
	public static float mCenterX;
	public static float mCenterY;

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
	public boolean onTouchEvent(MotionEvent event) {
		int action = MotionEventCompat.getActionMasked(event);
		float x = event.getX();
		float y = event.getY();

		boolean inC = inCircle(x, y, mCenterX, mCenterY, mRadius);
		Log.d("inCircle", "inCircle: "+inC);
		
		switch (action) {
		case (MotionEvent.ACTION_DOWN):{
			if (inCircle(x, y, mCenterX, mCenterY, mRadius)) {
				Log.d("motion event", "Action was DOWN");
			}
			return true;
		}
		case (MotionEvent.ACTION_MOVE):
			Log.d("motion event", "Action was MOVE");
			return true;
		case (MotionEvent.ACTION_UP):
			Log.d("motion event", "Action was UP");
			return true;
		default:
			return super.onTouchEvent(event);
		}
	}

	private boolean inCircle(float x, float y, float centerX, float centerY, float radius) {
		double dx = Math.pow(x - centerX, 2);
		double dy = Math.pow(y - centerY, 2);
		Log.d("inCircle", "dx: "+dx);
		Log.d("inCircle", "dy: "+dy);
		if ((dx + dy) < Math.pow(radius, 2)) {
			return true;
		} else {
			return false;
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
		public ImageView mImageView;
		public Canvas mCanvas;
		public Bitmap mBall;
		public int mWidth;
		public int mHeight;
		public Paint mPaint;
		public View mRootView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			mRootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			Handler circleHandler = new Handler();
			circleHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					drawBall(mRootView);
				}
			}, 500);

			return mRootView;
		}

		public void drawBall(View rootView) {
			mImageView = (ImageView) rootView.findViewById(R.id.backdrop);
			mWidth = mImageView.getWidth();
			mHeight = mImageView.getHeight();
			mBall = Bitmap.createBitmap(mWidth, mHeight,
					Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(mBall);
			mPaint = new Paint();
			mPaint.setColor(Color.RED);
			mCanvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mPaint);
			mCenterX = mWidth / 2;
			mCenterY = mHeight / 2;
			mImageView.setImageDrawable(new BitmapDrawable(getResources(),
					mBall));
			rootView.invalidate();

		}
	}

}
