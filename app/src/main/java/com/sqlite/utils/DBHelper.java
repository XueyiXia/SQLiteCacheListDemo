package com.sqlite.utils;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author: xiaxueyi
 * @date: 2017-03-01
 * @time: 14:04
 * @说明: 创建数据
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, Constants.SQL_NAME, null, Constants.SQL_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {   //数据库第一次创建的时候调用
        String sql="create table if not exists "+Constants.SQL_TABLE_NAME+" (id integer primary key autoincrement, name varchar(100) ,content varchar(2147483647))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {  //当数据库版本不相同的时候，调用此方式，更新数据库
        String sql="drop table if not exists person";

        sqLiteDatabase.execSQL(sql);

    }
}

