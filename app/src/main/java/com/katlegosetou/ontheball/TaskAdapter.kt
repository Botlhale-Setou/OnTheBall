package com.katlegosetou.ontheball

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter( private val taskList : MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    //Declaring View Holder for Task class; inheriting from RecyclerView's ViewHolder class.
    class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    //Linking TaskViewHolder to task_layout.xml using inflate() method; Returning a linked TaskViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.task_layout,
            parent,
            false
            )
        )
    }

    fun addTask(srcTask : Task){
        taskList.add(srcTask)
        notifyItemInserted(taskList.size - 1)
    }

    fun deleteTask(){
        taskList.removeAll { currentTask ->
            currentTask.isDone
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTask : TextView, cbDone : CheckBox){
        if(cbDone.isChecked){
            tvTask.paintFlags = tvTask.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //Updating UI when change is detected
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        //Assign current task to variable
        val currTask = taskList[position]

        //Assign layout views to variables
        val tvTask = holder.itemView.findViewById<TextView>(R.id.tvTask)
        val cbDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)

        //Update layout views' attributes
        tvTask.text = currTask.title
        cbDone.isChecked = currTask.isDone
        toggleStrikeThrough(tvTask, cbDone)
        cbDone.setOnCheckedChangeListener { _, _ ->
            toggleStrikeThrough(tvTask, cbDone)
            currTask.isDone = !currTask.isDone
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}