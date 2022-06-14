package com.example.mywechat.mode

// 消息的实体类
class Msg(val content: String, val type: Int) {
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}
/***
Msg类中只有两个字段：content表示消息的内容，type表示消息的类型。其中消息类型有两
个值可选：TYPE_RECEIVED表示这是一条收到的消息，TYPE_SENT表示这是一条发出的消
息。将TYPE_RECEIVED和TYPE_SENT定义成了常量，定义常量的关键字是const，
注意只有在单例类、companion object 或顶层方法中才可以使用const关键字
*/
