package com.example.faltu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private GestureDetector gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

       /* Button Enter = (Button) findViewById(R.id.ok);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });*/

		gestureDetector = new GestureDetector(new SwipeGestureDetector());
		
	}
	 @Override
	  public boolean onTouchEvent(MotionEvent event) {
	    if (gestureDetector.onTouchEvent(event)) {
	      return true;
	    }
	    return super.onTouchEvent(event);
	  }

	  private void onLeftSwipe() {
		  Intent i = new Intent(getApplicationContext(),Left.class);
		  startActivity(i);
		  finish();
	    // Do something
	  }

	  private void onRightSwipe() {
		  Intent i = new Intent(getApplicationContext(),Right.class);
		  startActivity(i);
		  finish();
	    // Do something
	  }

	  // Private class for gestures
	  private class SwipeGestureDetector
	          extends SimpleOnGestureListener {
	    // Swipe properties, you can change it to make the swipe
	    // longer or shorter and speed
	    private static final int SWIPE_MIN_DISTANCE = 120;
	    private static final int SWIPE_MAX_OFF_PATH = 200;
	    private static final int SWIPE_THRESHOLD_VELOCITY = 200;



	    public boolean onFling(MotionEvent e1, MotionEvent e2,
	                         float velocityX, float velocityY) {
	      try {
	        float diffAbs = Math.abs(e1.getY() - e2.getY());
	        float diff = e1.getX() - e2.getX();

	        if (diffAbs > SWIPE_MAX_OFF_PATH)
	          return false;


	        // Left swipe
	      /*  if (diff > SWIPE_MIN_DISTANCE
	        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	           MainActivity.this.onLeftSwipe();*/

	        // Right swipe
	      /*  } else if (-diff > SWIPE_MIN_DISTANCE
	        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	 MainActivity.this.onRightSwipe();
	        }*/
	      } catch (Exception e) {
	        Log.e("YourActivity", "Error on gestures");
	      }
	      return false;
	    }
	  }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
    }

}
