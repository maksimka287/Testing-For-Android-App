package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.faltu.question.QuestionActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by MaKsa on 24.03.16.
 */
public class ListTestsActivity extends Activity {

    private Utility util = new Utility();  //класс утилит
    private ArrayAdapter<String> adapter;  //адаптер для списка тестов
    private ListView list_tests_view;      //объект список тестов

    private List<String> test_titles = new LinkedList<String>();                                        //названия тестов
    private static int quantity_test;                                                                   //количество тестов в контейнере
    private Map<String, String> list_quest_request = new HashMap<String, String>();                                  //контейнер вопросов и вариантов ответа
    private Map<Integer, Map<String, String>> list_tests = new HashMap<Integer, Map<String, String>>(); //контейнер тестов

    //передача количества тестов из парсера
    public void setQuantityTest(int index_test) {
        this.quantity_test=index_test;
    }

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

        //String str = tests_1[2];

        try{
            // находим список
            list_tests_view = (ListView) findViewById(R.id.list_tests_view);
        } catch (Exception e) {
            util.getMessage(getApplicationContext(), e.toString());
        }

        // парсим xml ресурс с тестами
        list_tests.clear();
        list_tests = util.parsingXML(getApplicationContext(),this);
        QuestionActivity question_act = new QuestionActivity();
        //передает тесты форме вопросов чтобы не парсить еще раз
        question_act.setListTests(list_tests);
        list_quest_request.clear();
        test_titles.clear();
        for (int index_test=0;index_test<quantity_test;index_test++) {
            // достаем содержимое выбранного теста
            list_quest_request = list_tests.get(index_test);
            // достаем название теста
            if ( list_quest_request.get("title") != null ) {
                //создаем список названий тестов
                test_titles.add(list_quest_request.get("title"));
            }

        }

        try{
            // загрузка списка тестов из new_tests.xml
            //tests_1 = getResources().getStringArray(R.array.tests1);
            // создаем адаптер
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test_titles);
        } catch (Exception e) {
            util.getMessage(getApplicationContext(), e.toString());
        }

        try{
            // присваиваем адаптер списку
            if (adapter != null) {
                list_tests_view.setAdapter(adapter);
            }

            if (list_tests_view != null) {
                list_tests_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        QuestionActivity q_activ = new QuestionActivity();
                        q_activ.setNumberTest((int)id);

                        Intent intent = new Intent(ListTestsActivity.this, QuestionActivity.class);
                        intent.addFlags(1073741824);
                        startActivity(intent);
                    }
                });
            }
        }catch (Exception e) {
            util.getMessage(getApplicationContext(), e.toString());
        }

    }

}
