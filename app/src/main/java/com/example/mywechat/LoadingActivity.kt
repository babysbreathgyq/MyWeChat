package com.example.mywechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.*
import kotlin.concurrent.thread

class LoadingActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // 使用Handler延时跳转
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                // 实现页面跳转
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        }.sendEmptyMessageDelayed(0,0) // 第二个参数为延时时间（默认单位为ms）
    }


}
