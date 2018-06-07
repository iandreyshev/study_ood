package ru.iandreyshev.canvas.style

interface IConstStyle {

    val fillColor: Color
    val strokeColor: Color
    val strokeSize: Float

    fun clone(): Style

}
