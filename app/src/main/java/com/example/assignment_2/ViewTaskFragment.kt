package com.example.assignment_2

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ViewTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_task, container, false)
        val layout = view.findViewById<LinearLayout>(R.id.view_task_layout)



        for (task in AddTaskFragment.taskList) {
            val taskView = LayoutInflater.from(context).inflate(R.layout.task_layout, layout, false)
            val taskName: TextView = taskView.findViewById(R.id.textview_task_name)
            val taskDescription: TextView = taskView.findViewById(R.id.textview_task_description)
            val taskPriority: TextView = taskView.findViewById(R.id.textview_task_priority)
            val deleteText: Button = taskView.findViewById(R.id.deleteText)
            val updateBtn: Button = taskView.findViewById(R.id.updateText)

            taskName.text = task.name
            taskDescription.text = task.description
            taskPriority.text = "Priority: ${task.priority}"

            when (task.priority) {
                "Urgent" -> taskView.setBackgroundColor(Color.parseColor("#ff6b6c"))
                "Moderate" -> taskView.setBackgroundColor(Color.parseColor("#FFC145"))
                "Low" -> taskView.setBackgroundColor(Color.parseColor("#B8B8D1"))
            }

            layout.addView(taskView)

            deleteText.setOnClickListener {
                removeData(task.id)
            }

            updateBtn.setOnClickListener {
                val dialogFragment = CustomDialogFragment.newInstance(task.id, task.name, task.description, task.priority)
                dialogFragment.show(parentFragmentManager, "CustomDialogFragment")
            }
        }
        return view
    }

    private fun removeData(taskId: String) {
        AddTaskFragment.taskList.removeAll { it.id == taskId }
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.frame_layout, ViewTaskFragment()).commit()
    }
}
