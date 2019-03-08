package com.yitong.keyboarddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.yitong.keyboarddemo.utils.KeyBoardUtil;
import com.yitong.keyboarddemo.utils.LockableScrollView;
import com.yitong.keyboarddemo.utils.PanHideUtil;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    private LockableScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PanHideUtil.getInstance().attachActivity(this);
        edit = findViewById(R.id.edit);
        edit.setMaxLines(4);
        scrollView = findViewById(R.id.scrollView);
        scrollView.setScrollingEnabled(false);
        KeyBoardUtil.getInstance().setOnKeyBoardPopListener(this, new KeyBoardUtil.OnKeyBoardPopListener() {
            @Override
            public void keyBoardShow(int i) {
                Toast.makeText(MainActivity.this, "键盘弹出", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void keyBoardHide(int i) {
                Toast.makeText(MainActivity.this, "键盘收起", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

