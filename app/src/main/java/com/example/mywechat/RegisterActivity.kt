package com.example.mywechat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.mywechat.databinding.ActivityRegisterBinding

class RegisterActivity : MainActivity(), TextWatcher, View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    // val fromAlum = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 添加editText的事件监听
        binding.edName.addTextChangedListener(this)
        binding.edPassword.addTextChangedListener(this)
        binding.edPhoneNumber.addTextChangedListener(this)
        binding.cbAgree.setOnClickListener(this)

        // 将数据存储到SharedPreferences中
        binding.register.setOnClickListener {
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("phoneNumber","123456")
            editor.putString("password","123456")
        }
    }



    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    // 只有三个editText中内容不为空时，才可以点击按钮
    override fun afterTextChanged(p0: Editable?) {
        if (binding.edName.length() > 0 && binding.edPassword.length() > 0 && binding.edPhoneNumber.length() > 0) {
                binding.register.setEnabled(true)
        } else {
            binding.register.setEnabled(false)
        }

        val strPhone: String = binding.edPhoneNumber.text.toString()
        // regex为正则表达式字符串
        val regex = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}\$*"
        if (strPhone.matches(regex.toRegex())) {

        }
        else {
            binding.edPhoneNumber.setError("请输入正确的手机号")
        }
    }

    // 只有复选框为选中状态时，再点击按钮，才可以跳转
    override fun onClick(p0: View?) {
        if (binding.cbAgree.isChecked) {
            binding.register.setOnClickListener {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"账号注册成功，请开始你的愉快之旅吧",Toast.LENGTH_SHORT).show()
            }
        }
    }

}