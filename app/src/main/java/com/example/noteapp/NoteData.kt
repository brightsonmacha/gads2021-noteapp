package com.example.noteapp

class CourseInfo(val courseId:String, val courseTitle: String) {
    override fun toString(): String {
        return courseTitle
    }
}

class NoteInfo(var course:CourseInfo, var noteTitle: String) {
    override fun toString(): String {
        return noteTitle
    }
}