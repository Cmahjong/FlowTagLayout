package com.yj.flowtaglayout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yj.library.bean.TagBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view.setNewData(
            mutableListOf(
                TagBean("聊天1", false),
                TagBean("聊天2", false),
                TagBean("聊天3", false),
                TagBean("聊天4", false),
                TagBean("聊天5", false),
                TagBean("聊天6", false),
                TagBean("聊天7", false),
                TagBean("聊天8", false),
                TagBean("聊天9", false),
                TagBean("聊天10", false),
                TagBean("聊天11", false),
                TagBean("聊天12", false),
                TagBean("聊天13", false),
                TagBean("聊天14", false),
                TagBean("聊天15", false)


            )
        )
        view.setClickItemListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
