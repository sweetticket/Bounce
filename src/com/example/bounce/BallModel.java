package com.example.bounce;

import android.util.Log;

import com.example.bounce.MainActivity.PlaceholderFragment;

public class BallModel {

	private final static float GRAVITY = 3000.0f;
	private final static float ELASTICITY = 0.9f;
	private final static float FLOOR_FRICTION = 0.5f;
	public final static float RADIUS = 60;

	private float mMaxX;
	private float mMaxY;
	private float mMinX;
	private float mMinY;
	
	private float mCurrentX;
	private float mCurrentY;
	private float mPrevX;
	private float mPrevY;
	private float mVY; // current Y velocity
	private float mVX; // current X velocity

	public BallModel(int screenWidth, int screenHeight) {
		mCurrentX = screenWidth / 2;
		mCurrentY = screenHeight / 2;
		mVY = 0;
		mVX = 0;
		mMaxX = screenWidth - RADIUS;
		mMinX = RADIUS;
		mMaxY = screenHeight - RADIUS;
		mMinY = RADIUS;
	}

	/** Getter: current x coordinate */
	public float getCurrentX() {
		return mCurrentX;
	}

	/** Setter: set current x coordinate */
	public void setCurrentX(float x) {
		mCurrentX = x;
		mVX = 0;
	}

	/** Getter: current x coordinate */
	public float getCurrentY() {
		return mCurrentY;
	}

	/** Setter: set current y coordinate */
	public void setCurrentY(float y) {
		mCurrentY = y;
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
	}
	
	/** Getter: max x position */
	public float getMaxX(){
		return mMaxX;
	}
	
	/** Getter: min x position */
	public float getMinX(){
		return mMinX;
	}
	
	/** Getter: max y position */
	public float getMaxY(){
		return mMaxY;
	}
	
	/** Getter: min y position */
	public float getMinY(){
		return mMinY;
	}
	
	/** Change velocities */
	public void setVelocities(int time, int counter) {
		
		if (counter >= 10) {
			mVX = 0;
			mVY = 0;
		} else {
			mVX = (mCurrentX - mPrevX) / (time / 100.0f);
			mVY = (mCurrentY - mPrevY) / (time / 100.0f);
		}
		mPrevX = 0;
		mPrevY = 0;
	}

	/** Change location with velocities */
	public void step(int time, int screenHeight, int screenWidth) {
		mVY += GRAVITY * (time / 1000.0f);
		mCurrentY += mVY * (time / 1000.0f);

		mCurrentX += mVX * (time / 1000.0f);

		if (mCurrentX >= mMaxX) {
			mCurrentX = mMaxX;
			mVX *= -ELASTICITY;
		}

		if (mCurrentX <= mMinX) {
			mCurrentX = mMinX;
			mVX *= -ELASTICITY;
		}

		if (mCurrentY <= mMinY) {
			mCurrentY = mMinY;
			mVY *= -ELASTICITY;
		}

		if (mCurrentY >= mMaxY) {
			mCurrentY = mMaxY;
			if (Math.abs(mVY) > 10) {
				mVY *= -ELASTICITY;
			} else {
				mVY = 0;
				//PlaceholderFragment.setMoveEnabled(true);
				mVX *= FLOOR_FRICTION;
				if (Math.abs(mVX) < 10){
					PlaceholderFragment.setMoveEnabled(true);
					mVX = 0;
				}
			}
		}

	}
}
