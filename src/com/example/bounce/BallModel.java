package com.example.bounce;

import android.util.Log;

import com.example.bounce.MainActivity.PlaceholderFragment;

public class BallModel {

	private final static float GRAVITY = 3000.0f;
	private final static float ELASTICITY = 0.9f;

	private final float mRadius = 60;

	private float mPrevX;
	private float mPrevY;
	private float mVY; // current Y velocity
	private MyView mMyView;

	public BallModel(MyView view) {
		mMyView = view;
		mPrevX = mMyView.getScreenWidth() / 2;
		mPrevY = mMyView.getScreenHeight() / 2;
		mVY = 0;
	}

	/** Getter: previous x coordinate */
	public float getPrevX() {
		return mPrevX;
	}

	/** Setter: set previous x coordinate */
	public void setPrevX(float x) {
		mPrevX = x;
	}

	/** Getter: previous x coordinate */
	public float getPrevY() {
		return mPrevY;
	}

	/** Setter: set previous y coordinate */
	public void setPrevY(float y) {
		mPrevY = y;
		mVY = 0;
	}

	/** Getter: ball radius */
	public float getRadius() {
		return mRadius;
	}

	/** Increase mDY */
	public void step(int time) {
		mVY += GRAVITY * (time / 1000.0f);
		mPrevY += mVY * (time / 1000.0f);
		
		if (mPrevY >= mMyView.getScreenHeight() - mRadius) {
			mPrevY = mMyView.getScreenHeight() - mRadius;
			Log.d("step sinking", "sinking: abs(mVY) = "+Math.abs(mVY));
			if (Math.abs(mVY) > 10) {
				mVY *= -ELASTICITY;
			} else {
				PlaceholderFragment.setMoveEnabled(true);
			}
		}
	}
}
