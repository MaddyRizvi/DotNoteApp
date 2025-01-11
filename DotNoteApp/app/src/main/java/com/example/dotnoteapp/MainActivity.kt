package com.example.dotnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dotnoteapp.screens.NoteScreen
import com.example.dotnoteapp.screens.NoteViewModel
import com.example.dotnoteapp.ui.theme.DotNoteAppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DotNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val noteViewModel: NoteViewModel by viewModels()
                     DotApp(noteViewModel = noteViewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DotApp(noteViewModel: NoteViewModel = viewModel(), padding: Modifier){

    val notesList = noteViewModel.getAllNotes()
    NoteScreen(
        modifier = Modifier,
        notes = notesList,
        onRemoveNote = { noteViewModel.removeNote(it) },
        onAddNote = { noteViewModel.addNote(it) }
    )
}
