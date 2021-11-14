package com.devandroid.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int [] randomImg = {R.drawable.c01, R.drawable.c02, R.drawable.c03, R.drawable.c04, R.drawable.c05,
            R.drawable.c06, R.drawable.c07, R.drawable.c08, R.drawable.c09, R.drawable.c10, R.drawable.c11,
            R.drawable.c12, R.drawable.c13, R.drawable.c14, R.drawable.m00, R.drawable.m01, R.drawable.m02,
            R.drawable.m03, R.drawable.m04, R.drawable.m05, R.drawable.m06, R.drawable.m07, R.drawable.m08,
            R.drawable.m09, R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14,
            R.drawable.m15, R.drawable.m16, R.drawable.m17, R.drawable.m18, R.drawable.m19, R.drawable.m20,
            R.drawable.m21, R.drawable.p01, R.drawable.p02, R.drawable.p03, R.drawable.p04, R.drawable.p05,
            R.drawable.p06, R.drawable.p07, R.drawable.p08, R.drawable.p09, R.drawable.p10, R.drawable.p11,
            R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.s01, R.drawable.s02, R.drawable.s03,
            R.drawable.s04, R.drawable.s05, R.drawable.s06, R.drawable.s07, R.drawable.s08, R.drawable.s09,
            R.drawable.s10, R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.w01,
            R.drawable.w02, R.drawable.w03, R.drawable.w04, R.drawable.w05, R.drawable.w06, R.drawable.w07,
            R.drawable.w08, R.drawable.w09, R.drawable.w10, R.drawable.w11, R.drawable.w12, R.drawable.w13,
            R.drawable.w14};

    int mode = 0;
    int DRAG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        for (int i = 0; i <= 77; i++){
            ImageView iv = new ImageView(this);

//            ImageView selectImg = findViewById(R.id.selectImg);

            iv.setImageResource(R.drawable.bb);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.leftMargin = (int)(i * 40 * this.getResources().getDisplayMetrics().density);
            params.width = (int)(100 * this.getResources().getDisplayMetrics().density);
            params.height = (int)(150 * this.getResources().getDisplayMetrics().density);
            iv.setLayoutParams(params);
            frameLayout.addView(iv);

            Integer numImg = randomInt(0,77);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv.setImageResource(randomImg[numImg]);
//                    selectImg.setImageResource(randomImg[numImg]);
                }
            });

            iv.setOnTouchListener(new View.OnTouchListener() {
                FrameLayout.LayoutParams params;
                float dx = 0, dy = 0, x = 0, y = 0;
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ImageView img = (ImageView) view;
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_DOWN:
                            params = (FrameLayout.LayoutParams)img.getLayoutParams();
                            dx = motionEvent.getRawX() - params.leftMargin;
                            dy = motionEvent.getRawY() - params.topMargin;
                            mode = DRAG;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (mode == DRAG){
                                x = motionEvent.getRawX();
                                y = motionEvent.getRawY();
                                params.leftMargin = (int) (x - dx);
                                params.topMargin = (int) (y - dy);

                                params.rightMargin = 0;
                                params.bottomMargin = 0;

                                params.rightMargin = params.leftMargin + (5 * params.width);
                                params.bottomMargin = params.topMargin + (10 * params.height);

                                img.setLayoutParams(params);
                            }
                    }
                    return false;
                }
            });
        }
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                frameLayout.removeAllViewsInLayout();
                for (int i = 0; i <= 77; i++){
                    ImageView iv = new ImageView(getApplication());

//            ImageView selectImg = findViewById(R.id.selectImg);

                    iv.setImageResource(R.drawable.bb);

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    params.leftMargin = (int)(i * 40 * getApplication().getResources().getDisplayMetrics().density);
                    params.width = (int)(100 * getApplication().getResources().getDisplayMetrics().density);
                    params.height = (int)(150 * getApplication().getResources().getDisplayMetrics().density);
                    iv.setLayoutParams(params);
                    frameLayout.addView(iv);

                    Integer numImg = randomInt(0,77);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            iv.setImageResource(randomImg[numImg]);
//                    selectImg.setImageResource(randomImg[numImg]);
                        }
                    });

                    iv.setOnTouchListener(new View.OnTouchListener() {
                        FrameLayout.LayoutParams params;
                        float dx = 0, dy = 0, x = 0, y = 0;
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            ImageView img = (ImageView) view;
                            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                                case MotionEvent.ACTION_DOWN:
                                    params = (FrameLayout.LayoutParams)img.getLayoutParams();
                                    dx = motionEvent.getRawX() - params.leftMargin;
                                    dy = motionEvent.getRawY() - params.topMargin;
                                    mode = DRAG;
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (mode == DRAG){
                                        x = motionEvent.getRawX();
                                        y = motionEvent.getRawY();
                                        params.leftMargin = (int) (x - dx);
                                        params.topMargin = (int) (y - dy);

                                        params.rightMargin = 0;
                                        params.bottomMargin = 0;

                                        params.rightMargin = params.leftMargin + (5 * params.width);
                                        params.bottomMargin = params.topMargin + (10 * params.height);

                                        img.setLayoutParams(params);
                                    }
                            }
                            return false;
                        }
                    });
                }
            }
        });
    }
    public static int randomInt (int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}