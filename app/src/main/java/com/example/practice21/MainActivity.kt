package com.example.practice21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnClick(view: View){
        val tv: TextView = findViewById(R.id.textView)
        val etv: EditText = findViewById(R.id.editTextNumber)
        val btnStop: Button = findViewById(R.id.button2)
        val btnStart :Button = findViewById(R.id.button)
        btnStart.isEnabled = false
        btnStop.isEnabled = true
        if (etv.text.toString() == ""){
            Toast.makeText(this, "Введите количество секунд", Toast.LENGTH_SHORT).show()
            etv.requestFocus()
        }
        else if (etv.text.toString().toInt() < 0 || etv.text.toString().toInt() > 60){
            Toast.makeText(this, "Введите секунды от 0 до 60", Toast.LENGTH_SHORT).show()
            etv.requestFocus()
        }
        else {
            var runnable: Runnable = Runnable {
                val sec: Int = etv.text.toString().toInt()
                for (i in sec downTo  0){
                    tv.post(Runnable {
                        tv.text = i.toString()
                    })
                    Thread.sleep(1000)
                }
            }
            var thread: Thread = Thread(runnable)
            thread.start()
            btnStop.setOnClickListener(){
                recreate()
                btnStart.isEnabled = true
                btnStop.isEnabled = false
            }
        }

    }
}