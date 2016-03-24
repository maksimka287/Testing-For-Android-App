package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

/**
 * Created by MaKsa on 24.03.16.
 */
public class QuestionActivity extends Activity {

    private static long number_test = -1;

    public void setNumberTest(long numTest) {
        this.number_test = numTest;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        Button question = (Button) findViewById(R.id.question);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        TextView getNumTest = (TextView) findViewById(R.id.getNumTest);
        getNumTest.setText("Выбран тест №" + number_test);

        MultiAutoCompleteTextView textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textQuestion);
        textQuestion.setText("Первый вопрос, как выдумаете зачем " +
                             "\n содается так много машин, заводов " +
                             "\n и разной другой живности. " +
                             "\n Наверно просто жывотные хотят что-то делать.");

    }

}
