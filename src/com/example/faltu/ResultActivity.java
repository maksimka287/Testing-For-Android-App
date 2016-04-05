package com.example.faltu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MultiAutoCompleteTextView;

/**
 * Created by MaKsa on 05.04.16.
 */
public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);



        MultiAutoCompleteTextView textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textResult);
        textQuestion.setText("Результат теста, " +
                             "который что-то должен " +
                             "рассказать тебе.");
    }
}
