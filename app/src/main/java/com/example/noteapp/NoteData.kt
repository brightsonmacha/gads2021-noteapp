package com.example.noteapp

class CourseInfo(val courseId:String, val courseTitle: String) {
    override fun toString(): String {
        return courseTitle
    }
}

class NoteInfo(var course:CourseInfo? = null, var noteTitle: String? = null, var noteDesc: String? = null) {
    override fun toString(): String {
        return noteTitle ?: ""
    }
}