package com.diginori.danajjang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etSbject;
    private EditText etContent;
    private Button btnOK;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("my_note_file", MODE_PRIVATE);
        editor = pref.edit();

        etSbject = (EditText) findViewById(R.id.et_subject_fmain);
        etContent = (EditText) findViewById(R.id.editText);
        btnOK = (Button) findViewById(R.id.btn_ok);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etSbject.getText().toString();
                String c = etContent.getText().toString();

                System.out.println("제목:" + s);
                System.out.println("내용:" + c);

                editor.putString("키_제목",s);
                editor.putString("키_내용",c);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("나켜짐");

        String s = pref.getString("키_제목", "공백");
        String c = pref.getString("키_내용", "공백");

        etSbject.setText(s);
        etContent.setText(c);
    }


}
