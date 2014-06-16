package com.example.bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class MyView extends ImageView {
	
	private Paint mPaint;
	private Canvas mCanvas;
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
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		mCanvas = canvas;
		mScreenWidth = getWidth();
		mScreenHeight = getHeight();
	}
	
	/** Draws the ball */
	public void drawBall(float x, float y, float r) {
		mCanvas.drawCircle(x, y, r, mPaint);
	}
		
	public void setPosition(float x, float y){
		
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
}
