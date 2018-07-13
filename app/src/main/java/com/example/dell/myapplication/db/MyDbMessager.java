package com.example.dell.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.dell.myapplication.Student;

import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyDbMessager.java
 * @Package com.example.dell.myapplication.db
 * @Description: todo
 * @author: YFL
 * @date: 2018/7/13 20:14
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/7/13 星期五
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class MyDbMessager {
    public Context mContext;
    private final String dou = ",";
    private final String douAfter = "',";
    private final String douBefore = ",'";
    private SQLiteDatabase mreadableDatabase;
    private SQLiteDatabase mRdDb;
    private static MyDbMessager mDBMessager;
    public static final String TAG = "MyDbMessager.class";

    public MyDbMessager(Context context) {
        mContext = context;
        getDb();
    }

    public static MyDbMessager getInstance(Context context) {
        if (mDBMessager == null)
            mDBMessager = new MyDbMessager(context);
        return mDBMessager;
    }

    public void getDb() {
        MyDbSqlite dbSqlite = new MyDbSqlite(mContext);
        if (mRdDb == null)
            mRdDb = dbSqlite.getReadableDatabase();

    }

    public void insterData(Student student) {
        if (isOnlyRead()) return;
        StringBuffer buffer = new StringBuffer();
        buffer.append("insert into " + MyDbSqlite.TABLE_NAME);
        buffer.append("(pricet,name,filesize,number) VALUES (");
        buffer.append(student.getPricet());
        buffer.append(douBefore + student.getName() + douAfter);
        buffer.append(student.getFileSize());
        buffer.append(dou);
        buffer.append(student.getNumber());
        buffer.append(")");
        mRdDb.execSQL(buffer.toString());
    }

    private boolean isOnlyRead() {
        if (mRdDb.isReadOnly()) {
            return true;
        }
        return false;
    }

    public void updataData(Student student) {
        if (isOnlyRead()) return;
        StringBuffer buffer = new StringBuffer();
        buffer.append("insert into " + MyDbSqlite.TABLE_NAME);
        buffer.append("(pricet,name,filesize,number) VALUES (");
        buffer.append(student.getPricet());
        buffer.append(douBefore + student.getName() + douAfter);
        buffer.append(student.getFileSize());
        buffer.append(dou);
        buffer.append(student.getNumber());
        buffer.append(")");
        mRdDb.execSQL(buffer.toString());
    }

    public ArrayList<Student> queryAllData() {
        StringBuffer buffer = getStringBuffer();
        buffer.append("select * from " + MyDbSqlite.TABLE_NAME);
        Cursor cursor = mRdDb.rawQuery(buffer.toString(), null);
        if (cursor == null)
            return null;
        if (!cursor.moveToFirst()) {
            return null;
        }
        ArrayList<Student> lists = new ArrayList<>();
        do {
            Student student = getStudent(cursor);
            lists.add(student);
        } while (cursor.moveToNext());

        return lists;
    }

    public Student queryPostion(int postion) {
        StringBuffer buffer = getStringBuffer();
        buffer.append("select * from " + MyDbSqlite.TABLE_NAME);
        buffer.append(" where id =?");
        Cursor cursor = mRdDb.rawQuery(buffer.toString(), new String[]{String.valueOf(postion)});
        if (cursor == null)
            return null;
        Student student = getStudent(cursor);
        return student;
    }

    @NonNull
    private Student getStudent(Cursor cursor) {
        Student student = new Student();
        String name = cursor.getString(cursor.getColumnIndex("name"));
        student.setName(name);
        float pricet = cursor.getFloat(cursor.getColumnIndex("pricet"));
        student.setPricet(pricet);
        long filesize = cursor.getLong(cursor.getColumnIndex("filesize"));
        student.setFileSize(filesize);
        int number = cursor.getInt(cursor.getColumnIndex("number"));
        student.setNumber(number);
        int id = cursor.getColumnIndex("id");
        int anInt = cursor.getInt(id);
        student.setId(anInt);
        return student;
    }

    public void updataData(int postion, Student student) {
        if (isOnlyRead()) return;
        StringBuffer buffer = getStringBuffer();
        buffer.append("select * from " + MyDbSqlite.TABLE_NAME);
        buffer.append(" where id =?");
        Cursor cursor = mRdDb.rawQuery(buffer.toString(), new String[]{String.valueOf(postion)});
        buffer = null;
        if (cursor == null) {
            return;
        }
        int position = cursor.getPosition();
        StringBuffer buffer1 = getStringBuffer();
        buffer1.append("updata " + MyDbSqlite.TABLE_NAME);
        buffer1.append(" set pricet=" + student.getPricet());
        buffer1.append(dou);
        buffer1.append(" name=" + douAfter + student.getName() + douBefore);
        buffer1.append(" filesize=" + student.getFileSize());
        buffer.append(dou);
        buffer.append(" number = " + student.getNumber());
        mRdDb.execSQL(buffer.toString());
    }

    public void updataAndroid(int postion, Student student) {
        if (isOnlyRead()) return;
        StringBuffer buffer = getStringBuffer();
        buffer.append("select * from " + MyDbSqlite.TABLE_NAME);
        buffer.append(" where id =?");
        Cursor cursor = mRdDb.rawQuery(buffer.toString(), new String[]{String.valueOf(postion)});
        buffer = null;
        if (cursor == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", student.getName());
        contentValues.put("pricet", student.getPricet());
        contentValues.put("filesize", student.getFileSize());
        contentValues.put("number", student.getNumber());
        int update = mRdDb.update(MyDbSqlite.TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(postion)});
        if (update == 1) {
            Log.e("===", "success: ");
        } else {
            Log.e("===", "error: ");
        }
    }

    private boolean isFiles(int postion) {
        StringBuffer buffer = getStringBuffer();
        buffer.append("select * from " + MyDbSqlite.TABLE_NAME);
        buffer.append(" where id =?");
        Cursor cursor = mRdDb.rawQuery(buffer.toString(), new String[]{String.valueOf(postion)});
        if (cursor == null) {
            return false;
        }
        if (!cursor.moveToFirst()) {
            return false;
        }
        return true;
    }

    private StringBuffer getStringBuffer() {
        return new StringBuffer();
    }

    public void delectAllAndroid() {
        mRdDb.delete(MyDbSqlite.TABLE_NAME, null, null);
    }

    public void delectAll() {
        StringBuffer buffer = getStringBuffer();
        buffer.append("delete  from " + MyDbSqlite.TABLE_NAME);
        mRdDb.execSQL(buffer.toString());

    }

    public void delectPositon(int postion) {
        StringBuffer buffer = getStringBuffer();
        buffer.append("delete  from " + MyDbSqlite.TABLE_NAME);
        buffer.append(" where  id =" + postion);
        mRdDb.execSQL(buffer.toString());

    }

    public void delectPositonAndroid(int postion) {
        int delete = mRdDb.delete(MyDbSqlite.TABLE_NAME, "id=?", new String[]{String.valueOf(postion)});
        if (delete == 1) {
            Log.e(TAG, "delectPositon: successs");
        } else {
            Log.e(TAG, "delectPositon: errror");
        }
    }

}
