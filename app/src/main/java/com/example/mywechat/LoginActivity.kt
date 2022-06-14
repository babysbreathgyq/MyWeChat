package com.example.mywechat



import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mywechat.databinding.ActivityLoginBinding


class LoginActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password",false)
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            val username = prefs.getString("username","")
            val password = prefs.getString("password","")
            binding.edtUsername.setText(username)
            binding.edtPassword.setText(password)
            binding.cbAutoLogin.isChecked = true
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            // 如果账号是13713911332且密码是123456，就认为登录成功
            if (username == "13713911332" && password == "123456") {
                val editor = prefs.edit()   // 获取一个SharedPreferences.Editor对象
                if (binding.cbAutoLogin.isChecked) { // 检查复选框是否被选中
                    editor.putBoolean("remember_password", true)
                    editor.putString("username",username)
                    editor.putString("password",password)
                } else {
                    editor.clear()  // 如果没有被选中，就将SharedPreferences文件中的数据全部清理掉
                }
                editor.apply() // 将添加的数据进行提交
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "username or password is invalid",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            val editor = prefs.edit()
            editor.clear() // 将SharedPreferences文件中的数据全部清理掉
            binding.cbAutoLogin.isChecked = false
            binding.edtUsername.setText("")
            binding.edtPassword.setText("")
        }

        binding.btnCancel.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确定退出App") // 为对话框添加标题
                setMessage("Are you sure") // 为对话框添加信息
                setCancelable(false) // 可否使用back键关闭对话框
                setPositiveButton("确定") {dialog, which:Int -> ActivityCollector.finishAll()} // 添加ok按钮
                setNegativeButton("取消") {dialog, which:Int ->} // 添加cancel按钮
                show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}