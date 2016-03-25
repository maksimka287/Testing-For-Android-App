package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by MaKsa on 24.03.16.
 */
public class ListTestsActivity extends Activity {

    private static final String[] tests = { "Тест на эгоизм.",
                                            "Твоя самооценка.",
                                            "Какая твоя стихия.",
                                            "Кто ты по натуре.",
                                            "А ты не сумашедший?",
                                            "Тест на оптимизм.",
                                            "Какой у Вас характер.",
                                            "Ленивы ли Вы.",
                                            "Лидер ли Вы.",
                                            "Тест на практичность.",
                                            "Ваш стиль общения.",
                                            "...",
                                            "...",
                                            "...",
                                            "...",
                                            "...",
                                            "...",
                                            "...",
                                            "...",
                                            "..."};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tests_activity);

      /*  Button listTests = (Button) findViewById(R.id.list_tests);
        listTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTestsActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });*/

        // находим список
        ListView list_tests_view = (ListView) findViewById(R.id.list_tests_view);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tests);

        // присваиваем адаптер списку
        list_tests_view.setAdapter(adapter);

        list_tests_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionActivity q_activ = new QuestionActivity();
                q_activ.setNumberTest(id);
                Intent intent = new Intent(ListTestsActivity.this, QuestionActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

    }

}
