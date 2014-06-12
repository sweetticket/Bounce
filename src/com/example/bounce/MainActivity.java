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
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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
    	
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            
            mImageView = (ImageView)rootView.findViewById(R.id.backdrop);
            mBall = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
            return rootView;
        }
        
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState){
        	 mCanvas = new Canvas(mBall);
             mPaint = new Paint();
             mPaint.setColor(Color.RED);
             mWidth = mImageView.getWidth();
             mHeight = mImageView.getHeight();
             Log.d("width height", "mWidth: "+mWidth+", mHeight: "+mHeight);
             mCanvas.drawCircle(mWidth/2, -mHeight/2, 20, mPaint);
             
             mImageView.setImageDrawable(new BitmapDrawable(getResources(), mBall));
        }
    }

}
