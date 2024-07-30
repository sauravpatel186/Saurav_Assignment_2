package com.example.assignment_2

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class CustomDialogFragment : DialogFragment() {

    private lateinit var taskName: EditText
    private lateinit var taskDesc: EditText
    private lateinit var priorityRadioGroup: RadioGroup
    private lateinit var taskId: String

    companion object {


        fun newInstance(id: String, name: String, description: String, priority: String): CustomDialogFragment {
            val fragment = CustomDialogFragment()
            val args = Bundle()
            args.putString("task_id", id)
            args.putString("task_name", name)
            args.putString("task_description", description)
            args.putString("task_priority", priority)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialogfragment, container, false)

        taskName = view.findViewById(R.id.task_name)
        taskDesc = view.findViewById(R.id.task_description)
        priorityRadioGroup = view.findViewById(R.id.priority_radio)

        val name = arguments?.getString("task_name")
        val description = arguments?.getString("task_description")
        val priority = arguments?.getString("task_priority")
        taskId = arguments?.getString("task_id") ?: ""

        taskName.setText(name)
        taskDesc.setText(description)

        when (priority) {
            "Urgent" -> priorityRadioGroup.check(R.id.urgent_priority)
            "Moderate" -> priorityRadioGroup.check(R.id.moderate_priority)
            "Low" -> priorityRadioGroup.check(R.id.low_priority)
        }

        val updateButton: Button = view.findViewById(R.id.update_task_button)
        val cancelButton: Button = view.findViewById(R.id.cancel_task_button)

        updateButton.setOnClickListener {
            updateTask()
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }

        return view
    }

    private fun updateTask() {
        val updatedName = taskName.text.toString()
        val updatedDescription = taskDesc.text.toString()
        val updatedPriority = when (priorityRadioGroup.checkedRadioButtonId) {
            R.id.urgent_priority -> "Urgent"
            R.id.moderate_priority -> "Moderate"
            else -> "Low"
        }

        (activity as? MainActivity)?.updateTask(Task(taskId, updatedName, updatedDescription, updatedPriority))
    }
}
