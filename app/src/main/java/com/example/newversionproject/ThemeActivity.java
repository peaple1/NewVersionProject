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

public class ThemeActivity extends AppCompatActivity {
    public static final String EXTRA_DECKSID = "decksId";
    private SQLiteDatabase db;
    public static Cursor ThemeCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        ListView Themelist = (ListView) findViewById(R.id.theme_list);
        SQLiteOpenHelper themeDatabaseHelper = new ThemeDatabaseHelper(this);
        String nameText = MainActivity.getText();
        try {
            db = themeDatabaseHelper.getReadableDatabase();
            ThemeCursor = db.query("TEXT_THEME",
                    new String[]{"_id", "TAB", "THEME", "TEXT"},
                    "THEME = ?", new String[]{nameText}, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    ThemeCursor,
                    new String[]{"TEXT"},
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


                /*Toast ct =    Toast.makeText(ThemeActivity.this, "Кнопка нажата" + getText(), Toast.LENGTH_SHORT);
                ct.show();*/
               /* if(id==1){
                    Intent intent = new Intent(Themes.this,
                            QuestionActivity.class);

                    startActivity(intent);*/
                if(getText().equals("1")){
                         Intent i = new Intent(ThemeActivity.this, QSActivity.class);
                    i.putExtra(QSActivity.EXTRA_THEMEID, (int) id);
                startActivity(i);
            }
//startActivity(new Intent(MainActivity.this, number_1.class));



            }


        });

    }

    public static String getText(){
        String theme_name = ThemeCursor.getString(1);
        return theme_name;
    }
    public static String getCOM(){
        String com_name = ThemeCursor.getString(2);
        return com_name;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        ThemeCursor.close();
        db.close();
    }

}
