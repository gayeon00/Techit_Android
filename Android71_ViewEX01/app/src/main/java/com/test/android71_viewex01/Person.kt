package com.test.android71_viewex01

class Data {
    companion object {
        val dataList = mutableListOf<Person>()
    }
}

data class Person(val name: String, val age: Int, val hobby: String) {

}
