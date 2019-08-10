package ru.anikey.mymindcards.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardModel(val title: String, val question: String, val answer: String) : Parcelable