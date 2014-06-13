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
		
		private static final int FRAME_RATE = 10;
		
		private MyView mMyView;
		private Paint mPaint;
		private View mRootView;
		private BallModel mBall;
		private static boolean mMoveEnabled;
		private Handler mCircleHandler;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			mRootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			mMoveEnabled = false;
			mCircleHandler = new Handler();
			mCircleHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mMyView = (MyView) mRootView
							.findViewById(R.id.backdrop);

					mBall = new BallModel(); // instantiate a new ball
					mPaint = new Paint();
					mPaint.setColor(Color.RED);
					mCircleHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							if (!mMoveEnabled) {
								mBall.step(FRAME_RATE);
								
							}
							mMyView.drawBall(mBall.getPrevX(), mBall.getPrevY(), mBall.getRadius());
							mCircleHandler.postDelayed(this, FRAME_RATE);
						}

					}, FRAME_RATE);
				}
			}, 500);

			mRootView.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View view, MotionEvent event) {
					int action = MotionEventCompat.getActionMasked(event);
					float x = event.getX();
					float y = event.getY();

					switch (action) {
					case (MotionEvent.ACTION_DOWN): {
						if (inCircle(x, y, mBall.getPrevX(), mBall.getPrevY())) {
							mMoveEnabled = true;
							Log.d("motion event", "Action was DOWN");
						}
						return true;
					}
					case (MotionEvent.ACTION_MOVE):
						if (mMoveEnabled) {
							moveBall(x, y);
						}
						Log.d("motion event", "Action was MOVE");
						return true;
					case (MotionEvent.ACTION_UP):
						mMoveEnabled = false;
						Log.d("motion event", "Action was UP");
						return true;
					default:
						return true;
					}

				}
			});

			return mRootView;
		}
		
		/** Setter: move enabled */
		public static void setMoveEnabled(boolean b){
			mMoveEnabled = b;
		}
		
		/**Getter: move enabled */
		public static boolean getMoveEnabled(){
			return mMoveEnabled;
		}

		/** Moves the ball in response to drag */
		public void moveBall(float x, float y) {
			float offSetX = x - mBall.getPrevX();
			float offSetY = y - mBall.getPrevY();
			mBall.setPrevX(mBall.getPrevX() + offSetX);
			mBall.setPrevY(mBall.getPrevY() + offSetY);
		}

		private boolean inCircle(float x, float y, float centerX, float centerY) {
			double dx = Math.pow(x - centerX, 2);
			double dy = Math.pow(y - centerY, 2);
			if ((dx + dy) <= Math.pow(mBall.getRadius(), 2)) {
				return true;
			} else {
				return false;
			}
		}
	}

}
