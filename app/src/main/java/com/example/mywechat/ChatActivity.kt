package com.example.mywechat

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywechat.adapter.FriendAdapter
import com.example.mywechat.adapter.MsgAdapter
import com.example.mywechat.databinding.ActivityChatBinding
import com.example.mywechat.mode.Msg
import com.example.mywechat.sqlite.MyDatabaseHelper
import kotlin.properties.Delegates

class ChatActivity : AppCompatActivity() {

    private lateinit var information : String
    private lateinit var name : String
    private var id by Delegates.notNull<Int>()
    private lateinit var binding: ActivityChatBinding

    // recyclerView
    private val msgList = ArrayList<Msg>()
    private var adapter: MsgAdapter? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extraData = intent.getStringExtra("extra_data")
        binding.textview.setText("你正在与${extraData}聊天\n你发送的信息:")

        // recyclerView
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        binding.recyclerView.adapter = adapter

        // 创建数据库
        val dbHelper = MyDatabaseHelper(this,"Friend.db",1)
        val db = dbHelper.writableDatabase

        binding.btnSend.setOnClickListener {
            // 获取数据
            val etMessage = binding.etMessage.text.toString()

            val values = ContentValues().apply {
                // 开始组装数据
                put("Name",extraData)
                put("Information",etMessage)
            }
            db.insert("Friend",null,values)
            if (etMessage.isNotEmpty()) {
                val msg = Msg(etMessage, Msg.TYPE_SENT)
                msgList.add(msg)
                adapter?.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新RecyclerView中的显示
                binding.recyclerView.scrollToPosition(msgList.size - 1) // 将RecyclerView,定位到最后一行
                binding.etMessage.setText("") // 清空输入框中的内容
            }
            Toast.makeText(this, " send message success", Toast.LENGTH_SHORT).show()

        }

        binding.back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val cursor = db.query("Friend",null,null,null,null,null,null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
//                    name = cursor.getString(cursor.getColumnIndex("Name"))
//                    information = cursor.getString(cursor.getColumnIndex("Information"))
//                    id = cursor.getInt(cursor.getColumnIndex("id"))
//                    Log.d("MainActivity","friend name is ${name}")
//                    Log.d("MainActivity","friend information is ${information}")
                } while (cursor.moveToNext())
            }
//            cursor.close()
//            intent.putExtra("information",information)
//            intent.putExtra("name",name)
//            intent.putExtra("id",id)
            startActivity(intent)
        }
    }
    private fun initMsg() {
        val msg1 = Msg("Hello Boy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}