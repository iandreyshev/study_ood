package ru.iandreyshev.gumballmachine.machine

data class GumballMachineData(
        val name: String,
        val stateName: String,
        val ballsCount: Int,
        val insertedCoinsCount: Int,
        val totalCoinsCount: Int,
        val maxCoinsCount: Int
)
