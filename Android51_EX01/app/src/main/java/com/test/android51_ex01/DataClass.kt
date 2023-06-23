package com.test.android51_ex01

class DataClass {
    companion object {
        val humanList = mutableListOf<Human>()
    }
}

data class Human(var name:String, var date:String, var gender:String, var hobbyList:MutableList<String>)