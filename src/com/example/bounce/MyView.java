package com.example.bounce;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class MyView extends ImageView {
	
	private Paint mPaint;
	private int mScreenWidth;
	private int mScreenHeight;
	private float mXPos;
	private float mYPos;	

	
	public MyView(Context context) {
		super(context);
		init();
	}
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	public void init(){
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mScreenWidth = getWidth();
		mScreenHeight = getHeight();
		mXPos = mScreenWidth / 2;
		mYPos = mScreenHeight / 2;
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		drawBall();
	}
	
	/** Draws the ball */
	public void drawBall() {
		if (mScreenWidth == 0){
			initScreenDims();
		}
		Bitmap canvasBit = Bitmap.createBitmap(mScreenWidth, mScreenHeight,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(canvasBit);
		canvas.drawCircle(mXPos,mYPos,
				BallModel.getRadius(), mPaint);
		setImageDrawable(new BitmapDrawable(getResources(),
				canvasBit));
		this.invalidate();
	}
		
	public void setPosition(float x, float y){
		mXPos = x;
		mYPos = y;
	}

	public int getScreenWidth(){
		return mScreenWidth;
	}
	
	public void setScreenWidth(int width){
		mScreenWidth = width;
	}
	
	public int getScreenHeight(){
		return mScreenHeight;
	}
	
	public void setScreenHeight(int height){
		mScreenHeight = height;
	}
	
	public void initScreenDims(){
		mScreenHeight = getHeight();
		mScreenWidth = getWidth();
		mXPos = mScreenWidth / 2;
		mYPos = mScreenHeight / 2;
	}
}
