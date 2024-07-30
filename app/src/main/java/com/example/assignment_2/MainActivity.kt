package com.example.assignment_2

import AddTaskFragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,AddTaskFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task_selector,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_task ->{
                changeFragment(R.id.frame_layout,AddTaskFragment())
            }
            R.id.view_task ->{
                changeFragment(R.id.frame_layout,ViewTaskFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun changeFragment(containerId:Int,fragment: Fragment){

        supportFragmentManager.beginTransaction().replace(containerId,fragment).commit()

    }
    fun updateTask(updatedTask: Task) {
        val index = AddTaskFragment.taskList.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            AddTaskFragment.taskList[index] = updatedTask
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, ViewTaskFragment())
                .commit()
        }
    }
}