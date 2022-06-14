package com.example.mywechat.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

    private val createFriend = "create table Friend(" +
        "id integer primary key autoincrement," +
        "Image integer," +
        "Name text," +
        "Information text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createFriend)
        Toast.makeText(context, "Created succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}