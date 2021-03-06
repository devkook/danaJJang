package com.diginori.danajjang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etSubject;
    private EditText etContent;
    private Button btnOK;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("my_note_file", MODE_PRIVATE);
        editor = pref.edit();

        etSubject = (EditText) findViewById(R.id.et_subject_fmain);
        etContent = (EditText) findViewById(R.id.editText);
        btnOK = (Button) findViewById(R.id.btn_ok);

        alert = new AlertDialog.Builder(MainActivity.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etSubject.getText().toString();
                String c = etContent.getText().toString();

                System.out.println("제목:" + s);
                System.out.println("내용:" + c);

                editor.putString("etSubject", s);
                editor.putString("etContent", c);



                if(editor.commit()){
                    alert.setMessage("성공");
                }else{
                    alert.setMessage("실패");
                }

                alert.show();
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

        String s = pref.getString("etSubject", "-");
        String c = pref.getString("etContent", "-");

        etSubject.setText(s);
        etContent.setText(c);
    }


}
