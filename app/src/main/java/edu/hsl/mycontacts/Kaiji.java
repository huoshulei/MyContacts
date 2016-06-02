package edu.hsl.mycontacts;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Kaiji extends AppCompatActivity {
    ImageView imageView;
    int duration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaiji);
        imageView = (ImageView) findViewById(R.id.iv_kaiji);
        /*帧动画*/
        final AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        //动画开始
        imageView.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        });
        //获得动画时间
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            duration += animationDrawable.getDuration(i);
        }
        //动画完成执行跳转
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Kaiji.this, MyContacts.class);
                startActivity(intent);
                finish();
            }
        }, duration);
    }
}
