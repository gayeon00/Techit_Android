package com.test.android60_fragmentex02

abstract class Student(val name: String)

class BaseballPlayer(name: String, val battingAvg: Double, val hCount: Int, val aCount: Int) :
    Student(name)

class SoccerPlayer(name: String, val goalCount: Int, val helpCount: Int) : Student(name)

class SwimPlayer(name: String, val swimType: String) : Student(name)






