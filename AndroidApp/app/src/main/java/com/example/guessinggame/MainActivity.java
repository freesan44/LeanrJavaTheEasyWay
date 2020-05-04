package com.example.guessinggame;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.preference.PreferenceManager;
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
    private TextView titleView;
    private int theNumber;
    private int range = 100;//随机范围

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
        titleView = findViewById(R.id.titleView);
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessNum();
            }
        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        range = preferences.getInt("range",100);
        NewGame();

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

    public void NewGame()
    {
        theNumber = (int) (Math.random()*range+1);
        titleView.setText("输入数字1-"+range);
        txtGuess.setText(""+range/2);
        txtGuess.requestFocus();
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
        switch (id){
            case R.id.action_settings:
                selectRangeAlert();
                return true;
            case R.id.action_gamestarts:
                return true;
            case R.id.action_newgame:
                NewGame();
                return true;
            case R.id.action_about:
                aboutAlert();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void aboutAlert()
    {
        AlertDialog aboutAl = new AlertDialog.Builder(MainActivity.this).create();
        aboutAl.setTitle("关于猜数游戏");
        aboutAl.setMessage("2020.5.4 猜数游戏");
        aboutAl.setButton(AlertDialog.BUTTON_NEUTRAL, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        aboutAl.show();
    }
    public void selectRangeAlert(){
        Log.v("test","弹框");
        final CharSequence[] items = {"1 到 10","1 到 100", "1 到 1000"};
        final AlertDialog.Builder rangeAl = new AlertDialog.Builder(this);
        rangeAl.setTitle("选择随机范围");
        rangeAl.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        range = 10;
                        break;
                    case 1:
                        range = 100;
                        break;
                    case 2:
                        range = 1000;
                        break;
                    default:
                        range = 100;
                }
                NewGame();
                storeRange();
                dialog.dismiss();
            }
        });
        AlertDialog alert = rangeAl.create();
        alert.show();
    }
    public void storeRange(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("range",range);
        editor.apply();
    }
}
