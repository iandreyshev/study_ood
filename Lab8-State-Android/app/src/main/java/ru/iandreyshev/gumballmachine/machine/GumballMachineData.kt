package ru.iandreyshev.gumballmachine.machine

data class GumballMachineData(
        val ballsCount: Int,
        val insertedCoinsCount: Int,
        val totalCoinsCount: Int,
        val maxCoinsCount: Int
)
