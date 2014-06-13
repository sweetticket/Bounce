package com.example.bounce;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class MyView extends ImageView {
	
	Paint mPaint;
	Canvas mCanvas;
	
	public MyView(Context context) {
		super(context);
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		mCanvas = canvas;
	}
	
	/** Draws the ball */
	public void drawBall(float x, float y, float r) {
		mCanvas.drawCircle(x, y, r, mPaint);
	}

}
