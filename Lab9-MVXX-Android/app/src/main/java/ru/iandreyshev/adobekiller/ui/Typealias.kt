package ru.iandreyshev.adobekiller.ui

internal typealias OnTouchCallback = (x: Float, y: Float) -> Unit
internal typealias OnTouchMoveCallback = (lastX: Float?, lastY: Float?, newX: Float, newY: Float) -> Unit
