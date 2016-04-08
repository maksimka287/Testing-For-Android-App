package com.example.faltu;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaKsa on 27.03.16.
 */
public class Utility {

    public void getMessage(Context context, String text) {
        //создаем и отображаем текстовое уведомление
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /*<contact>                // имя тега contact начало
    <firstname>Кот</firstname> // имя тега firstname содержимое тега Кот
    <lastname>Рыжик</lastname> // имя тега lastname содержимое тега Рыжик
    <phone                     // имя тега phone содержит 2 атрибута
        home="4516585"         // имя атрибута home содержимое атрибута текст 4516585
        work="1111" />         // имя атрибута work содержимое атрибута текст 1111
    </contact>*/               // конец тега contact

    //xpp.getName();            // имя тега
    //xpp.getAttributeCount();  // число атрибутов тега
    //xpp.getDepth();           // уровень вложенности
    //xpp.getText();            // содержимое тега
    //xpp.getAttributeName(1);  // имя первого атрибута
    //xpp.getAttributeValue(1); // значение первого атрибута

    // парсим xml ресурс с тестом
    public Map<Integer, Map<String, String>> parsingXML(Context context, Activity activity) {
        Map<String, String> list_quest_request = new HashMap<String, String>();;
        Map<Integer, Map<String, String>> list_tests = new HashMap<Integer, Map<String, String>>();
        try {
            // get a new XmlPullParser object from Factory
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = activity.getResources().getXml(R.xml.test_new_1);//factory.newPullParser();

            String name_tag ="";
            String value_tag ="";
            String attribute_name_tag ="";
            String attribute_value_tag ="";
            String question_key ="";

            int index_tests = 0;
            int index_question = 0;
            int index_request = 0;

            // get event type
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    case XmlPullParser.START_DOCUMENT: { // начало файла
                        //getMessage(context, "start read file = test_new_1");
                    }
                    break;
                    case XmlPullParser.START_TAG: { // начало тега
                        if (xpp.getName()!=null) {
                            name_tag = xpp.getName();
                        }
                        if ( name_tag.equals("requests") ) {
                            // запоминаем ответы на вопрос
                            for (int i = 0; i < xpp.getAttributeCount(); i++) {
                                attribute_name_tag = xpp.getAttributeName(i);
                                attribute_value_tag = xpp.getAttributeValue(i);
                                //ответы в том же массиве что и вопрос только ниже вопроса
                                if ( attribute_name_tag.indexOf("request") != -1 ) {
                                    try {
                                        String request_key = "r"+Integer.toString(index_request);
                                        list_quest_request.put(question_key+request_key,attribute_value_tag);
                                        index_request++;
                                    } catch (Exception e) {
                                        getMessage(context, "add attr exception = " + e.toString());
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG: { // конец тега
                        // в закрывающем теге test сохраням тест в контейнер
                        if ( xpp.getName().equals("test") ) {
                            list_tests.put(index_tests, list_quest_request);
                            list_quest_request = new HashMap<String, String>();
                            index_tests++;
                            index_question = 0;
                            index_request = 0;
                            ListTestsActivity list_tests_act = new ListTestsActivity();
                            list_tests_act.setQuantityTest(index_tests);
                        }
                    }
                    break;
                    case XmlPullParser.TEXT: { // содержимое тега
                        if (xpp.getText()!=null) {
                            value_tag = xpp.getText();
                        }
                        if ( name_tag.equals("title") ) {
                            // запоминаем имя теста
                            list_quest_request.put(name_tag,value_tag);
                            //index_tests++;
                        } else if ( name_tag.equals("question") ) {
                            // запоминаем вопросы
                            index_request = 0;
                            question_key = "q"+Integer.toString(index_question);
                            list_quest_request.put(question_key,value_tag);
                            index_question++;
                        } else if ( name_tag.equals("test") ) {
                            //создаем новый контейнер теста
                            //list_quest_request = new HashMap<String, String>();
                            //list_quest_request.clear();
                        }
                    }
                    break;
                    default:

                    break;
                }
                // jump to next event
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            getMessage(context, "Read xml parse exception = " + e.toString());
        } catch (IOException e) {
            getMessage(context, "Read xml IO exception = " + e.toString());
        } catch (Exception e) {
            getMessage(context, "Read xml exception = " + e.toString());
        }
        return list_tests;
    }

}
