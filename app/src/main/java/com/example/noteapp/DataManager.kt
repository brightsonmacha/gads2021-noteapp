package com.example.noteapp

object DataManager {
    private val courses = HashMap<String, CourseInfo>()

    fun courses(): HashMap<String, CourseInfo> {
        return  courses
    }
    val notes =  ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses(){
        var course =  CourseInfo("java", "Programming with Java")
        courses.set(course.courseId, course)

        course = CourseInfo("golang", "Programing with Golang")
        courses.set(course.courseId, course)

        course = CourseInfo("python", "Programing with Python")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes(){
        var note = NoteInfo(courses.getValue("java"), "Java Notes", "This is Java Notes")
        notes.add(note)

        note = NoteInfo(courses.getValue("golang"), "Golang Notes", "This is Golang Notes")
        notes.add(note)
    }
}