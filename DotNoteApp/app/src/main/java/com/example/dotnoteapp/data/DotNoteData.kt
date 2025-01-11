package com.example.dotnoteapp.data

import com.example.dotnoteapp.model.Note

class DotDataSource {

    fun loadNotes(): List<Note>{
        return listOf(
            Note(title = "Happy Day", description = " Going for a walk then gym"),
            Note(title = "Happy Day1", description = " Going for a walk then gym1"),
            Note(title = "Happy Day2", description = " Going for a walk then gym2"),
            Note(title = "Happy Day3", description = " Going for a walk then gym3"),
            Note(title = "Happy Day4", description = " Going for a walk then gym4"),
            Note(title = "Happy Day5", description = " Going for a walk then gym5"),
        )
    }
}