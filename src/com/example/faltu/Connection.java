package com.example.faltu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MaKsa on 20.03.16.
 */
public class Connection extends SQLiteOpenHelper {

    private final String TABLE_NAME = "user";

    public Connection(Context context) {
        /* конструктор суперкласса создания базы данных
         * класс создатель    context
         * имя базы данных    psychological_tests_application
         * параметр курсора   null
         * версия базы данных 1
         */
        super(context, "psychological_tests", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицы с полями

        //таблица с пользователями
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                   " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   " login TEXT, " +
                   " password TEXT, "+
                   " role integer);");
        //таблица с тестами
        db.execSQL("CREATE TABLE stat_tests (" +
                   " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   " test_id INTEGER, " +
                   " completed INTEGER, " +
                   " results INTEGER);");
        //таблица с вопросами
        db.execSQL("CREATE TABLE stat_question (" +
                   " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   " question_id INTEGER, " +
                   " completed INTEGER, " +
                   " results INTEGER);");

        // создаем объект для данных
        ContentValues cv = new ContentValues();
        //создаем демо пользователя
        cv.put("login", "demo");
        cv.put("password", "demo");
        cv.put("role", 3);
        // вставляем запись и получаем ее ID
        long rowID = db.insert("user", null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
