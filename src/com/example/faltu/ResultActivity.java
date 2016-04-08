package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaKsa on 05.04.16.
 */
public class ResultActivity extends Activity {

    private static Map<Integer, Integer> list_result_request = new HashMap<Integer, Integer>(); //контейнер с результатами ответов

    public void setListRequest(Map<Integer, Integer> result_request) {
        this.list_result_request = result_request;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Button endTest = (Button) findViewById(R.id.result_profile);
        endTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // после завершения теста переходим к профилю
                Intent intent = new Intent(ResultActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        Button skipQuestion = (Button) findViewById(R.id.result_list_tests);
        skipQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // после завершения теста переходим к списку тестов
                Intent intent = new Intent(ResultActivity.this, ListTestsActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        MultiAutoCompleteTextView textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textResult);
        textQuestion.setText("Ну и дурак, что доклацался до результата, вот результат, радуйся потратил время зря и " +
                             "так ничего нового и не узнал. А что ты ожидал здесь увидеть? Картину? " +
                             "Так ее еще никто не нарисовал, чтобы показывать здесь.");
    }
}
