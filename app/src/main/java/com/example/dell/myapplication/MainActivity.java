package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication.db.MyDbMessager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private Button mButton4;
    private Button mButton5;
    private Button mButton3;
    private Button mButton6;
    private Button mButton7;
    private TextView mTextView;
    private MyDbMessager mDb;
    private EditText mEt;
    private Button mButton8;
    private Button mButton9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        intData();

    }

    private void intData() {
        mDb = MyDbMessager.getInstance(this);

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton7 = (Button) findViewById(R.id.button7);
        mTextView = (TextView) findViewById(R.id.textView);

        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mEt = (EditText) findViewById(R.id.et);
        mEt.setOnClickListener(this);
        mButton8 = (Button) findViewById(R.id.button8);
        mButton8.setOnClickListener(this);
        mButton9 = (Button) findViewById(R.id.button9);
        mButton9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                int a = 10;
                for (int i = 0; i < a; i++) {
                    Student student = new Student();
                    student.setName(i % 2 == 0 ? "孙悟空" : "琪琪");
                    student.setNumber(i);
                    student.setFileSize(i % 2 == 0 ? 222 : 222);
                    student.setPricet(i % 2 == 0 ? 2.22f : 333.22f);
                    mDb.insterData(student);
                }

                break;
            case R.id.button2:
                ArrayList<Student> students = mDb.queryAllData();
                if (students == null || students.isEmpty()) {
                    mTextView.setText(null);
                    Toast.makeText(MainActivity.this, "表为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    buffer.append(student.getId() + "==id/" + student.getNumber() + "/" + student.getFileSize() + "/");
                    buffer.append(student.getPricet() + "/" + student.getName() + "\n=====\n");

                }
                mTextView.setText(buffer.toString());
                buffer = null;
                break;
            case R.id.button4:
                int b = 2;
                for (int i = 0; i < b; i++) {
                    Student student = new Student();
                    student.setName(i % 2 == 0 ? "贝吉塔" : "老婆");
                    student.setNumber(i);
                    student.setFileSize(i % 2 == 0 ? 222 : 222);
                    student.setPricet(i % 2 == 0 ? 2.22f : 333.22f);
                    mDb.insterData(student);
                }
                break;
            case R.id.button5://delectAll
                mDb.delectAll();
                break;
            case R.id.button3://delectAllAndrod
                mDb.delectAllAndroid();
                break;
            case R.id.button6://delectOne
                if (getPositon()) return;
                String string = getString();
                mDb.delectPositon(Integer.parseInt(string));
                break;
            case R.id.button7://updata
                if (getPositon()) return;
                String string1 = getString();
                mDb.delectPositonAndroid(Integer.parseInt(string1));
                break;
            case R.id.button8://更新
                if (getPositon()) return;
                String string2 = getString();
                Student student = new Student();
                student.setFileSize(123465789);
                student.setName("布尔玛");
                student.setNumber(8542);
                student.setPricet(125.5f);
                mDb.updataData(Integer.parseInt(string2), student);
                break;
            case R.id.button9://Android 更新
                if (getPositon()) return;
                String string22 = getString();
                Student student1 = new Student();
                student1.setFileSize(123465789);
                student1.setName("布拉");
                student1.setNumber(8542);
                student1.setPricet(125.5f);
                mDb.updataAndroid(Integer.parseInt(string22),student1);
                break;
        }
    }

    private boolean getPositon() {
        String trim = getString();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(MainActivity.this, "请输入坐标", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @NonNull
    private String getString() {
        return mEt.getText().toString().trim();
    }


}
