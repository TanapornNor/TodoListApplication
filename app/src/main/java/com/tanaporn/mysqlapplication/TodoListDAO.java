package com.tanaporn.mysqlapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Mint on 10/2/2560.
 */

public class TodoListDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public TodoListDAO (Context context){
        //get context and sent to helper
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database =dbHelper.getReadableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public ArrayList<TodoList> getAlltodoList() {
        ArrayList<TodoList> todoList = new ArrayList<TodoList>();
        Cursor cursor = database.rawQuery("SELECT * FROM tbtodo_list;",null);
        cursor.moveToFirst();

        TodoList todoList1;
        while (!cursor.isAfterLast()){

            todoList1 = new TodoList();
            todoList1.setTaskid(cursor.getInt(0));
            todoList1.setTaskname(cursor.getString(1));

            todoList.add(todoList1);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }

    public void add(TodoList todoList) {
        TodoList newTodoList = new TodoList();
        newTodoList = todoList;
        ContentValues values = new ContentValues();

        values.put("taskname", newTodoList.getTaskname());
        this.database.insert("tbtodo_list",null,values);
        Log.d("Todo List Demo :::","Add OK");
    }
    public void update(TodoList todoList){
        TodoList updateTodoList = todoList;
        ContentValues values = new ContentValues();
        values.put("Taskname", updateTodoList.getTaskname());
        values.put("Taskid", updateTodoList.getTaskid());
        String where = "taskid=" + updateTodoList.getTaskid();

        this.database.update("tbtodo_list", values, where, null);
        Log.d("Todo_List_Demo::","Update OK!!!");
    }

    public void delete(TodoList todoList){
        TodoList delTodolist = todoList;
        String sqlText = "DELE FROM tbtodo_list WHERE taski=" + delTodolist.getTaskid();
        this.database.execSQL(sqlText);
    }

}
