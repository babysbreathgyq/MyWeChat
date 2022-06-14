package com.example.mywechat

import android.app.Activity

// 单例类ActivityCollector作为Activity的集合
object ActivityCollector {
    private val activites = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activites.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activites.remove(activity)
    }

    fun finishAll() {
        for (activity in activites) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activites.clear()
    }
}