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
import android.widget.Toast;

public class QSActivity extends AppCompatActivity {
    public static final String EXTRA_THEMEID = "qsId";
    private SQLiteDatabase db;
    public static Cursor ThemeCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsactivity);
        ListView Themelist = (ListView) findViewById(R.id.theme_list);
        SQLiteOpenHelper themeDatabaseHelper = new ThemeDatabaseHelper(this);
        String nameText = ThemeActivity.getCOM();
        try {
            db = themeDatabaseHelper.getReadableDatabase();
            ThemeCursor = db.query("QS_TABLE",
                    new String[]{"_id", "TAB", "THEME", "QS","TR","COM" },
                    "THEME = ?", new String[]{nameText}, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    ThemeCursor,
                    new String[]{"QS"},
                    new int[]{android.R.id.text1},
                    0);
            Themelist.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable1", Toast.LENGTH_SHORT);
            toast.show();
        }


        Themelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {




            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                /**/
               /* if(id==1){
                    Intent intent = new Intent(Themes.this,
                            QuestionActivity.class);

                    startActivity(intent);*/
             /*   if(getText().equals("1")){
                    Intent i = new Intent(ThemeActivity.this, QSActivity.class);
                    i.putExtra(QSActivity.EXTRA_THEMEID, (int) id);
                    startActivity(i);
                }*/
//startActivity(new Intent(MainActivity.this, number_1.class));



                if(getCOM().equals("1")){
                    if (getTR().equals("true")){
                        Toast ct =    Toast.makeText(QSActivity.this, "Вы прошли эту тему", Toast.LENGTH_SHORT);
                        ct.show();
                        startActivity(new Intent(QSActivity.this, MainActivity.class));
                    }
                    else{
                        Toast ct1 =    Toast.makeText(QSActivity.this, "Ошибка,е подумайте получше", Toast.LENGTH_SHORT);
                        ct1.show();
                    }
                }

            }


        });

    }

    public String getTR(){
        String theme_name = ThemeCursor.getString(4);
        return theme_name;
    }
    public String getCOM(){
        String theme_name = ThemeCursor.getString(5);
        return theme_name;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ThemeCursor.close();
        db.close();
    }

}