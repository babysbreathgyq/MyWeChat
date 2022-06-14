package com.example.mywechat.adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mywechat.ChatActivity
import com.example.mywechat.R
import com.example.mywechat.mode.Friend
import kotlin.properties.Delegates

class FriendAdapter(val friendList: List<Friend>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    private lateinit var information : String
    private lateinit var name : String
    private var id by Delegates.notNull<Int>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val friendImage : ImageView = view.findViewById(R.id.iv_friend)
        val friendName : TextView = view.findViewById(R.id.friend_name)
        val friendInformation : TextView = view.findViewById(R.id.friend_information)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_item,parent,false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val friend = friendList[position]
            val intent = Intent(parent.context, ChatActivity::class.java)
            intent.putExtra("extra_data",friend.name)
//            intent.putExtra("image",friend.imageId)
            parent.context.startActivity(intent)
            Toast.makeText(parent.context, " you click view ${friend.name}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = friendList[position] // 获取当前Friend的实例
        holder.friendImage.setImageResource(friend.imageId)
        holder.friendName.text = friend.name
        holder.friendInformation.text = friend.information
    }

    override fun getItemCount() = friendList.size
}