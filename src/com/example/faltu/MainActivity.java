package com.example.faltu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.SQLDataException;

public class MainActivity extends Activity {
	private GestureDetector gestureDetector;
    private static final String SUCCESS_REG = "Поздравляем, вы зарегистрированы!";
    private static final String FAIL_REG = "Проверьте правильность логина и пароля, \n увы нет такого пользователя";
    private Connection     db_connect; //соединение с БД
    private SQLiteDatabase db;         //подключение к БД
    private Cursor         c_user;
    private boolean        do_register = false;
    EditText edit_login;
    EditText edit_pass;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // создаем объект для создания и управления версиями БД
        db_connect = new Connection(this);

        edit_login = (EditText) findViewById(R.id.e_login);
        edit_pass = (EditText) findViewById(R.id.e_pass);

        //кнопка входа в приложение
        Button enter = (Button) findViewById(R.id.entry);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                // подключаемся к БД
                try {
                    db = db_connect.getWritableDatabase();
                } catch (SQLiteException ex) {
                    db = db_connect.getReadableDatabase();
                }
                String[] columns ={"login","password","role"};
                String selection = "login = '" + edit_login.getText().toString() + "' and password = '" + edit_pass.getText().toString() +"'";

                //проверка пользователя
                    c_user = db.query("user", columns, selection, null, null, null, null);

                if ( (c_user != null) && (c_user.getCount() > 0) ) {

                    do_register = true;
                }

                if (do_register) {
                    getMessage(SUCCESS_REG);
                } else {
                    getMessage(FAIL_REG);
                }

                if (do_register) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.addFlags(1073741824);
                    startActivity(intent);
                }

                // закрываем подключение к БД
                db_connect.close();
                } catch(Exception e) {
                    getMessage(e.toString());
                }
            }

        });

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

    public void getMessage(String text) {
        //создаем и отображаем текстовое уведомление
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
    }

}
