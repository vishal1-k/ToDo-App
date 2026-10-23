package com.vishal.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.vishal.todolist.presentation.screen.ToDoListScreen
import com.vishal.todolist.ui.theme.ToDoListTheme
import com.vishal.todolist.viewmodel.TaskViewModel
import com.vishal.todolist.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    private val videoModel: TaskViewModel by viewModels{
        TaskViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {

                ToDoListScreen(videoModel)

                }
            }
        }
    }

