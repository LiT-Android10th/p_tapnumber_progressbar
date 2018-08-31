package com.example.mizuki.akane;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    int[] hairetu;//問題となる数字が入る配列

    String mondai;//実際に表示させる問題　文字列

    int seikai;//４桁のうち今何桁目を解答しているか覚えておく変数

    int count;//正解数を覚えておく変数

    int current;

    Handler handler = new Handler();

    TextView textView;//問題を表示させる

    // 1問あたりの制限時間(sec)
    private int time = 10;

    // 残り時間を表示するプログレスバー
    private ProgressBar progress;

    private int rest_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);//関連付け
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(time * 100);
        start();


    }

    public void number1(View v) {
        if (hairetu[seikai] == 1) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;


            if (seikai == 4) {
                count = count + 1;
                start();
            }

        } else {
            Toast.makeText(this, "間違ってるよー", Toast.LENGTH_SHORT).show();
        }

    }

    public void number2(View v) {
        if (hairetu[seikai] == 2) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;

            if (seikai == 4) {
                count = count + 1;
                start();
            }

        } else {
            Toast.makeText(this, "間違ってるよー", Toast.LENGTH_SHORT).show();
        }
    }


    public void number3(View v) {
        if (hairetu[seikai] == 3) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;
            if (seikai == 4) {
                count = count + 1;
                start();
            }

        } else {
            Toast.makeText(this, "間違ってるよー", Toast.LENGTH_SHORT).show();
        }


    }

    public void number4(View v) {
        if (hairetu[seikai] == 4) {
            mondai = mondai.substring(1);
            textView.setText(mondai);
            seikai = seikai + 1;
            if (seikai == 4) {
                count = count + 1;
                start();

            }

        } else {
            Toast.makeText(this, "間違ってるよー", Toast.LENGTH_SHORT).show();
        }


    }

    public void start() {
        hairetu = new int[4];
        Random rand = new Random();
        hairetu[0] = rand.nextInt(4) + 1;
        hairetu[1] = rand.nextInt(4) + 1;
        hairetu[2] = rand.nextInt(4) + 1;
        hairetu[3] = rand.nextInt(4) + 1;
        mondai =
                Integer.toString(hairetu[0])
                        + Integer.toString(hairetu[1])
                        + Integer.toString(hairetu[2])
                        + Integer.toString(hairetu[3]);
        textView.setText(mondai);
        seikai = 0;
        startTimeLimitThread();

    }

    private void startTimeLimitThread() {

        rest_time = time * 100;
        progress.setProgress(rest_time);
        // このThreadが担当する問題番号
        final int local_current = current;
        Thread t = new Thread() {
            @Override
            public void run() {
                while (rest_time >= 0) {
                    if (local_current != current) {
                        // すでにボタンをタップして次の問題に進んでいる
                        return;
                    }
                    try {
                        // 10ミリ秒待機
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            rest_time -= 1;
                            progress.setProgress(rest_time);
                        }
                    });
                    if (rest_time <= 0) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                                i.putExtra("QUESTION", count);
                                startActivity(i);
                            }
                        });
                    }
                } ;
            }
        };
        t.start();
    }

}

