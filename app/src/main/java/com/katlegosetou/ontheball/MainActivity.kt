package com.katlegosetou.ontheball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,)
        setContentView(R.layout.activity_main)

        taskAdapter = TaskAdapter(mutableListOf())

        //Assign MainActivity.kt views to variables
        val rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val etTask = findViewById<EditText>(R.id.etTask)

        rvTasks.adapter = taskAdapter
        rvTasks.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val taskTitle = etTask.text.toString()
            if(taskTitle.isNotEmpty()){
                val task = Task(taskTitle)
                taskAdapter.addTask(task)
                etTask.text.clear()
            }
        }

        btnClear.setOnClickListener {
            taskAdapter.deleteTask()
        }
    }
}