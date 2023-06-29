package com.test.android60_fragmentex02

open class Student(val name: String, val team: String)

class BaseballPlayer(name: String, val battingAvg: Double, val hCount: Int, val aCount: Int) :
    Student(name, "야구")

class SoccerPlayer(name: String, val goalCount: Int, val helpCount: Int) : Student(name, "축구")

class SwimPlayer(name: String, val swimType: String) : Student(name, "수영")






