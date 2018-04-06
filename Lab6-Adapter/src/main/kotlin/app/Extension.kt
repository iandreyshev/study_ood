package app

import modernGraphicsLib.RGBAColor

fun Int.toRGBAColor(): RGBAColor {
    val r = and(0xff) / 255.0f
    val g = shl(8).and(0xff) / 255.0f
    val b = shl(16).and(0xff) / 255.0f
    return RGBAColor(r, g, b, 1f)
}
