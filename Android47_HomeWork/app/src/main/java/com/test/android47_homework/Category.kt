package com.test.android47_homework

import android.os.Parcel
import android.os.Parcelable

data class Category(val title: String, val memoList: ArrayList<Memo> = ArrayList()) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        arrayListOf<Memo>().apply {
            parcel.readTypedList(this, Memo.CREATOR)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(memoList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}

data class Memo(val title: String, val content: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Memo> {
        override fun createFromParcel(parcel: Parcel): Memo {
            return Memo(parcel)
        }

        override fun newArray(size: Int): Array<Memo?> {
            return arrayOfNulls(size)
        }
    }
}
