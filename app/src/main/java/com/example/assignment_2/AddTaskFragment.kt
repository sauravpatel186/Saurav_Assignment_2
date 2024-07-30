import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.assignment_2.R
import com.example.assignment_2.Task
import com.example.assignment_2.ViewTaskFragment
import java.util.*

class AddTaskFragment : Fragment() {
    private lateinit var taskName: EditText
    private lateinit var taskDescription: EditText
    private lateinit var taskPriority: RadioGroup
    private lateinit var taskAddButton: Button

    companion object {
        val taskList = mutableListOf<Task>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)
        taskName = view.findViewById(R.id.task_name)
        taskDescription = view.findViewById(R.id.task_description)
        taskAddButton = view.findViewById(R.id.add_task_button)
        taskPriority = view.findViewById(R.id.priority_radio)

        taskAddButton.setOnClickListener {
            val selectedId: Int = taskPriority.checkedRadioButtonId
            val name = taskName.text.toString()
            val description = taskDescription.text.toString()

            if (selectedId != -1 && name.isNotEmpty() && description.isNotEmpty()) {
                val selectedRadioButton = view.findViewById<RadioButton>(selectedId)
                val prioritySelected = selectedRadioButton.text.toString()

                val uniqueKey = UUID.randomUUID().toString()
                taskList.add(Task(uniqueKey, name, description, prioritySelected))
                changeFragment(ViewTaskFragment())
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields and select a priority", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = fragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.frame_layout, fragment)?.commit()
    }
}
