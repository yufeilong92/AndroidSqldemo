package com.example.dell.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyDbSqlite.java
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: YFL
 * @date: 2018/7/13 19:57
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/7/13 星期五
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class MyDbSqlite extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "student";
    public static final int TABLE_VERSION = 1;




    public MyDbSqlite(Context context) {
        super(context, TABLE_NAME, null, TABLE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("create table " + TABLE_NAME);
        buffer.append("(id INTEGER PRIMARY KEY AUTOINCREMENT");
        buffer.append(",pricet FLOAT");
        buffer.append(",name TEXT");
        buffer.append(",filesize LONG");
        buffer.append(",number INTEGER)");
        db.execSQL(buffer.toString());
        buffer = null;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
