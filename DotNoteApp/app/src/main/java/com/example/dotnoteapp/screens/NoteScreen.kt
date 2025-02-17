@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dotnoteapp.screens


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dotnoteapp.R
import com.example.dotnoteapp.components.DotInputText
import com.example.dotnoteapp.components.DotNoteButton
import com.example.dotnoteapp.data.DotDataSource
import com.example.dotnoteapp.model.Note
import java.time.format.DateTimeFormatter


@Composable
fun NoteScreen(
    modifier: Modifier= Modifier,
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier
        .padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
                          },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon")
            },
            colors = TopAppBarDefaults.topAppBarColors(Color.LightGray)
            )

        // Adding textfields
        Column( modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            DotInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 9.dp
                ),
                label = "Label",
                text = title,
                onTextChange = {
                    if(it.all {char ->
                        char.isLetter() || char.isWhitespace()
                        }) title = it
                })

            DotInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 9.dp
                ),
                label = "Description",
                text = description,
                onTextChange = {
                    if(it.all { char ->
                        char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            DotNoteButton(text = "Save",
                onClick = {
                if(title.isNotEmpty() && description.isNotEmpty())
                {
                    //save/add to list
                    onAddNote( Note(title= title, description = description))

                    Toast.makeText(context, "Note Added",
                        Toast.LENGTH_SHORT).show()

                    title = ""
                    description = ""
                }
            })
        }
        HorizontalDivider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes){ note ->
                NoteRow(
                    note = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                    })
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit){
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp,
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClicked(note)
                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title,
                style = MaterialTheme.typography.bodyMedium)
            Text(text = note.description,
                style = MaterialTheme.typography.bodyLarge)
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    NoteScreen(notes = DotDataSource().loadNotes(),
        onAddNote = {},
        onRemoveNote = {})
}