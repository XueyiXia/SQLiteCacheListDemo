package com.sqlite.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sqlite.bean.MessageBean;

import java.util.ArrayList;

/**
 * @author: xiaxueyi
 * @date: 2017-03-01
 * @time: 16:49
 * @说明: 数据库管理类
 */
public class DBManager {

    private DBHelper mDbHelper;

    private SQLiteDatabase db;

    public DBManager(Context context) {
        /**
         * 实例化数据库
         */
        mDbHelper=new DBHelper(context);

        /**
         * 获取实例
         */
        db =mDbHelper.getWritableDatabase();
    }


    /**
     * 使用数据库语句存储数据
     * @param message
     */
    public void insertList(ArrayList<MessageBean> message){
        /**
         * 开始事务，确保数据的准确
         */
        db.beginTransaction();
        for (MessageBean bean:message){
            String sql="insert into "+Constants.SQL_TABLE_NAME+" (name,content) values(?,?)";
            Object object[]=new Object[]{bean.getName(),bean.getContent()};
            db.execSQL(sql,object);

        }
        db.setTransactionSuccessful();
        db.close(); //设置成功之后，关闭数据库
    }


    /**
     * 使用ContentValues 方式存储数据
     * @param message
     */
    public void insert(ArrayList<MessageBean> message){
        /**
         * 开始事务，确保数据的准确
         */
        db.beginTransaction();

        for (MessageBean bean:message){
            ContentValues values=new ContentValues();   //设置ContentValues对象
            values.put("name",bean.getName());  //设置键值对
            values.put("content",bean.getContent());
            db.insert(Constants.SQL_TABLE_NAME,null,values);   //增加操作

        }

        db.setTransactionSuccessful();
    }


    /**
     * 插入单条数据
     * @param content
     * @param name
     */
    public void insert(String content,String name){
        db.beginTransaction();
        ContentValues values=new ContentValues();
        values.put("content",content);
        values.put("name",name);
        db.insert(Constants.SQL_TABLE_NAME,null,values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    /**
     * 使用数据库语句查询
     * @return
     */
    public ArrayList<MessageBean> query(){
        ArrayList<MessageBean> dataList=new ArrayList<>();
        String sql="select * from "+Constants.SQL_TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            MessageBean bean=new MessageBean();
            int nameIndex=cursor.getColumnIndex("name");
            String name=cursor.getString(nameIndex);

            int ageIndex=cursor.getColumnIndex("content");
            String content=cursor.getString(ageIndex);

            bean.setName(name);
            bean.setContent(content);

            dataList.add(bean);
        }

        return dataList;
    }


    /**
     * 使用数组方式查询
     * @return
     */
    public ArrayList<MessageBean> queryContent(){
        ArrayList<MessageBean> dataList=new ArrayList<>();

        String columns[]=new String[]{"id","name","content"};
        Cursor cursor=db.query(Constants.SQL_TABLE_NAME,columns,null,null,null,null,null);

        while (cursor.moveToNext()){
            MessageBean bean=new MessageBean();
            int nameIndex=cursor.getColumnIndex("name");
            String name=cursor.getString(nameIndex);

            int ageIndex=cursor.getColumnIndex("content");
            String age=cursor.getString(ageIndex);

            bean.setName(name);
            bean.setContent(age);

            dataList.add(bean);
        }

        return dataList;
    }
}

