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
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
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
		public ImageView mImageView;
		public Canvas mCanvas;
		public Bitmap mBall;
		public Paint mPaint;
		public View mRootView;
		public static final float mRadius = 60;
		public static int mScreenWidth;
		public static int mScreenHeight;
		public float mPrevX;
		public float mPrevY;
		
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
					mImageView = (ImageView) mRootView.findViewById(R.id.backdrop);
					mScreenWidth = mImageView.getWidth();
					mScreenHeight = mImageView.getHeight();
					mPrevX = mScreenWidth / 2;
					mPrevY = mScreenHeight / 2;
					mPaint = new Paint();
					mPaint.setColor(Color.RED);
					drawBall();
				}
			}, 500);
			
			mRootView.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View view, MotionEvent event) {
					int action = MotionEventCompat.getActionMasked(event);
					float x = event.getX();
					float y = event.getY();

					if (inCircle(x, y, mPrevX, mPrevY)) {
						switch (action) {
						case (MotionEvent.ACTION_DOWN): {
							Log.d("motion event", "Action was DOWN");
							return true;
						}
						case (MotionEvent.ACTION_MOVE):
							Log.d("motion event", "Action was MOVE");
							moveBall(x,y);
							return true;
						case (MotionEvent.ACTION_UP):
							Log.d("motion event", "Action was UP");
							return true;
						default:
							return true;
						}
					} else
						return false;
				}
			});

			return mRootView;
		}
		
		/** Draws the ball */
		public void drawBall() {
			mBall = Bitmap.createBitmap(mScreenWidth, mScreenHeight,
					Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(mBall);
			mCanvas.drawCircle(mPrevX, mPrevY, mRadius, mPaint);
			mImageView.setImageDrawable(new BitmapDrawable(getResources(),
					mBall));
			mRootView.invalidate();

		}
		
		/** Moves the ball in response to drag */
		public void moveBall(float x, float y){
			float offSetX = x - mPrevX;
			float offSetY = y - mPrevY;
			mPrevX = mPrevX + offSetX;
			mPrevY = mPrevY + offSetY;
			drawBall();
		}
		
		private boolean inCircle(float x, float y, float centerX, float centerY) {
			double dx = Math.pow(x - centerX, 2);
			double dy = Math.pow(y - centerY, 2);
			if ((dx + dy) <= Math.pow(mRadius, 2)) {
				return true;
			} else {
				return false;
			}
		}
	}

}
