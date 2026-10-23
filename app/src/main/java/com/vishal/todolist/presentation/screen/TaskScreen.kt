package com.vishal.todolist.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishal.todolist.data.room_database.TaskItem
import com.vishal.todolist.ui.theme.darkGrey
import com.vishal.todolist.ui.theme.grey
import com.vishal.todolist.viewmodel.TaskViewModel


@Composable
fun ToDoListScreen(viewModel: TaskViewModel) {

    val tasks by viewModel.allTasks.collectAsState()

    var taskToEdit by remember {
        mutableStateOf<TaskItem?>(null)
    }

    var showEditorDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    taskToEdit = null
                    showEditorDialog = true
                },
                shape = RoundedCornerShape(20.dp),
                containerColor = darkGrey,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

                Text(
                    text = "New Task",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {

            Text(
                text = "My Tasks",
                modifier = Modifier.padding(top = 32.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = darkGrey
            )

            Text(
                text = "${tasks.count { !it.isDone }} remaining today tasks",
                color = grey
            )

            if (tasks.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Tasks",
                        color = grey
                    )
                }

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {

                    items(
                        items = tasks,
                        key = { it.id }
                    ) { task ->

                        ToDoItem(
                            item = task,

                            onEditClick = {
                                taskToEdit = task
                                showEditorDialog = true
                            },

                            onDeleteClick = {
                                viewModel.deleteTask(task)
                            },

                            onCheckChange = { checked ->
                                viewModel.updateTask(
                                    task.copy(isDone = checked)
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    if (showEditorDialog) {

        TaskEditor(
            task = taskToEdit,

            onSave = { newName ->

                if (taskToEdit == null) {

                    viewModel.addTask(
                        TaskItem(
                            taskName = newName,
                            isDone = false
                        )
                    )

                } else {

                    taskToEdit?.let { currentTask ->

                        viewModel.updateTask(
                            currentTask.copy(
                                taskName = newName
                            )
                        )
                    }
                }

                showEditorDialog = false
                taskToEdit = null
            },

            onCancel = {
                showEditorDialog = false
                taskToEdit = null
            }
        )
    }
}