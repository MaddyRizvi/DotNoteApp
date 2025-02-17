package com.example.dotnoteapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.dotnoteapp.data.DotDataSource
import com.example.dotnoteapp.model.Note

class NoteViewModel: ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    init{
        noteList.addAll(DotDataSource().loadNotes())
    }

    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note>{
        return noteList
    }
}