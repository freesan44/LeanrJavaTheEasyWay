package com.example.guessinggame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtGuess;
    private Button guessBtn;
    private TextView resultView;
    private int theNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        txtGuess = findViewById(R.id.guessText);
        guessBtn = findViewById(R.id.guessBtn);
        resultView = findViewById(R.id.resultTextView);
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessNum();
            }
        });
        theNumber = (int) (Math.random()*100+1);

    }

    public void guessNum() {
        Log.v("目标地址","目标数字"+theNumber);
        try {
            String guessStr = txtGuess.getText().toString();
            int guessNum = Integer.parseInt(guessStr);
            String msg = "";
            if (theNumber> guessNum)
            {
                msg = "低了";
            }
            else if (theNumber<guessNum)
            {
                msg = "高了";
            }else
            {
                msg = "正确答案!";
            }
            resultView.setText(msg);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {

        }
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
}
