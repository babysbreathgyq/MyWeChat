package com.example.mywechat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywechat.adapter.FriendAdapter
import com.example.mywechat.databinding.ActivityMainBinding
import com.example.mywechat.mode.Friend

// kotlin中的类默认是不可以继承的，用open关键字使它能够继承
open class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private val friendList = ArrayList<Friend>()

    private var linearLayout1: LinearLayout? = null
    private var linearLayout2:LinearLayout? = null
    private var linearLayout3:LinearLayout? = null
    private var linearLayout4:LinearLayout? = null

    lateinit var receiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // javaClass表示获取当前实例的Class对象，然后再调用simpleName获取当前实例的类名
        Log.d("MainActivity",javaClass.simpleName)
        ActivityCollector.addActivity(this)
        initView()
        initEvent()
        linearLayout1?.setBackgroundColor(Color.rgb(155,155,155))

        initFriends() // 初始化朋友数据
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FriendAdapter(friendList)
        binding.recyclerView.adapter = adapter

        // 接收chatAcitivity传递过来的shuju
        val information = intent.getStringExtra("information")
        val name = intent.getStringExtra("name")


        // 强制下线功能，发送一条广播
        binding.forceOffline.setOnClickListener {
            val intent = Intent("com.example.mywechat.FORCE_OFFLINE")
            sendBroadcast(intent)
        }

    }

    /*fun removeData(position: Int) {
        friendList.set(position, Friend("lyl",R.drawable.lyl,"haha,xiugaile"))
    }*/

    override fun onStart() {
        super.onStart()
        binding.test.setOnClickListener {
            val friendInformation : TextView = findViewById(R.id.friend_information)
            friendInformation.text = "wori"
        }
    }

    // 注册广播
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.mywechat.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver, intentFilter)
    }

    // 取消注册
    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }


    private fun initFriends() {
        repeat(1) {
            friendList.add(Friend("lyl",R.drawable.lyl,"你这个年纪，你怎么睡的着的"))
            friendList.add(Friend("dy",R.drawable.dy,"你这个年纪，你怎么睡的着的"))
            friendList.add(Friend("lrh",R.drawable.lrh,"你这个年纪，你怎么睡的着的"))
            friendList.add(Friend("lj",R.drawable.lj,"你这个年纪，你怎么睡的着的"))
            friendList.add(Friend("gyq",R.drawable.gyq,"你这个年纪，你怎么睡的着的"))
            friendList.add(Friend("yhx",R.drawable.yhx,"你这个年纪，你怎么睡的着的"))
        }
    }

    private fun updateFriends() {

    }

    private fun initView() {
        linearLayout1= findViewById(R.id.linearLayout1)
        linearLayout2= findViewById(R.id.linearLayout2)
        linearLayout3= findViewById(R.id.linearLayout3)
        linearLayout4= findViewById(R.id.linearLayout4)
    }

    private fun initEvent() {
        linearLayout1?.setOnClickListener(this)
        linearLayout2?.setOnClickListener(this)
        linearLayout3?.setOnClickListener(this)
        linearLayout4?.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceOfflineReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, p1: Intent?) {
            AlertDialog.Builder(context).apply {
                setTitle("Warning")
                setMessage("你被强制下线了，请重新登录")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    ActivityCollector.finishAll() // 销毁所有Activity
                    val i = Intent(context, LoginActivity::class.java)
                    context.startActivity(i) // 重新启动LoginActivity
                }
                show()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.linearLayout1 -> {
                linearLayout3?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout2?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout4?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout1?.setBackgroundColor(Color.rgb(155,155,155))
            }
            R.id.linearLayout2 -> {
                linearLayout1?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout3?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout4?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout2?.setBackgroundColor(Color.rgb(155,155,155))
            }
            R.id.linearLayout3 -> {
                val intent = Intent(this,SunnyActivity::class.java)
                startActivity(intent)
                linearLayout1?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout2?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout4?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout3?.setBackgroundColor(Color.rgb(155,155,155))
            }
            R.id.linearLayout4 -> {
                linearLayout1?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout2?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout3?.setBackgroundColor(Color.rgb(244,244,244))
                linearLayout4?.setBackgroundColor(Color.rgb(155,155,155))
            }
        }
    }

}