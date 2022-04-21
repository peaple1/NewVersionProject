package com.example.newversionproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    public static Cursor themeCursor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView themelist = (ListView) findViewById(R.id.list_theme);
        SQLiteOpenHelper deckDatabaseHelper = new ThemeDatabaseHelper(this);
        try {
            db = deckDatabaseHelper.getReadableDatabase();
            themeCursor = db.query("THEMETEXT",
                    new String[]{"_id", "THEME"},
                    null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    themeCursor,
                    new String[]{"THEME"},
                    new int[]{android.R.id.text1},
                    0);
            themelist.setAdapter(listAdapter);
        } catch(SQLiteException e) {

        }
        //Создание слушателя
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long id) {
                        //Напиток, выбранный пользователем, передается DrinkActivity
                        /*Intent intent = new Intent(MainActivity.this,
                                ThemeActivity.class);
                        intent.putExtra(ThemeActivity.EXTRA_DECKSID, (int) id);
                        startActivity(intent);*/
                        Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
                        intent.putExtra(ThemeActivity.EXTRA_DECKSID, (int) id);
                        startActivity(intent);
                    }

                };

        //Слушатель связывается со списковым представлением
        themelist.setOnItemClickListener(itemClickListener);

    }

    public static String getText(){
        String color_name = themeCursor.getString(1);
        return color_name;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        themeCursor.close();
        db.close();
    }

}

