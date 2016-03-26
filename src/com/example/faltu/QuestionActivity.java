package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by MaKsa on 24.03.16.
 */
public class QuestionActivity extends Activity {

    private static long number_test = -1;
    private MainActivity main = new MainActivity();

    private RadioButton radio_request1;
    private RadioButton radio_request2;
    private RadioButton radio_request3;
    private int         resultRequest   = -1;

    public void setNumberTest(long numTest) {
        this.number_test = numTest;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        Button end_test = (Button) findViewById(R.id.end_test);
        end_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        Button skip_question = (Button) findViewById(R.id.skip_question);
        skip_question.setOnClickListener(new View.OnClickListener() {
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

        RadioGroup radio_requests = (RadioGroup) findViewById(R.id.radio_requests);

        radio_request1 = (RadioButton) findViewById(R.id.radio_request1);
        radio_request1.setText("Да");

        radio_request2 = (RadioButton) findViewById(R.id.radio_request2);
        radio_request2.setText("Нет");

        radio_request3 = (RadioButton) findViewById(R.id.radio_request3);
        radio_request3.setText("Возможно");

        radio_request1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultRequest = 1;
                getRequest(1);
            }
        });

        radio_request2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //resultRequest = 2;
                getRequest(2);
            }
        });

        radio_request3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultRequest = 3;
                //getRequest(3);
            }
        });

     /*   radio_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                if (radio_request1.isChecked()) {
                    resultRequest = 1;
                };

                if (radio_request2.isChecked()) {
                    resultRequest = 2;
                };

                if (radio_request3.isChecked()) {
                    resultRequest = 3;
                };
                } catch (Exception e) {
                    main.getMessage(e.toString());
                }

            }
        });*/

    }

    public void getRequest(int id) {
        try {
        String message = "";

        switch (id) {
            case 1 : {
                main.getMessage("Ответ ДА");
            }
            break;
            case 2 : {
                main.getMessage("Ответ НЕТ");
            }
            break;
            case 3 : {
                main.getMessage("Ответ ВОЗМОЖНО");
            }
            break;
            default: {
                main.getMessage("Ответ не ясен");
            }
            break;
        }

        //main.getMessage(message);
        } catch (Exception e) {
            main.getMessage(e.toString());
        }

    }

}
