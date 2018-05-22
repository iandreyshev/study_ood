package ru.iandreyshev.localstorage.entity

import android.graphics.Bitmap

interface IImageEntity {

    val id: Long
    val x: Float
    val y: Float
    val width: Float
    val height: Float
    val image: Bitmap?

}
