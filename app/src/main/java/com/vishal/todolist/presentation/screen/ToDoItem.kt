package com.vishal.todolist.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishal.todolist.data.room_database.TaskItem
import com.vishal.todolist.ui.theme.darkGrey
import com.vishal.todolist.ui.theme.grey

@Composable

fun ToDoItem(
    item: TaskItem,
    onEditClick:() -> Unit,
    onDeleteClick:() -> Unit,
    onCheckChange:(Boolean) ->Unit


){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            if (item.isDone) Color.LightGray.copy(0.5f) else Color.White),
        border = BorderStroke( 1.dp, grey)
    ){

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            IconButton(
                onClick = {
                    onCheckChange(!item.isDone)

                },
                modifier = Modifier.size(24.dp)
            ){
                Icon(
                    imageVector =

                        if (item.isDone) Icons.Filled.CheckCircle else Icons.Default.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = if(item.isDone) darkGrey.copy(alpha = 0.8f) else grey

                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = item.taskName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                color = if(item.isDone) grey else darkGrey,
                textDecoration = if(item.isDone) TextDecoration.LineThrough else null

            )

            Row(){
                IconButton(
                    onClick = onEditClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = grey,
                        modifier = Modifier.size(24.dp)

                    )

                }

                IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = grey,
                        modifier = Modifier.size(24.dp)

                    )

                }
            }
        }

    }
}