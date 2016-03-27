package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by MaKsa on 24.03.16.
 */
public class ListTestsActivity extends Activity {

    private Utility util = new Utility();
    private ArrayAdapter<String> adapter;
    private ListView list_tests_view;

    private String[] tests_1;

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

        try{
            // загрузка списка тестов из new_tests.xml
            tests_1 = getResources().getStringArray(R.array.tests1);
            // создаем адаптер
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tests_1);
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
                        q_activ.setNumberTest(id);
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
