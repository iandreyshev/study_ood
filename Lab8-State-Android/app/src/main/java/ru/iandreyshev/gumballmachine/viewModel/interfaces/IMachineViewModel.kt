package ru.iandreyshev.gumballmachine.viewModel.interfaces

interface IMachineViewModel {
    fun updateBallsCount(newCount: Int)
    fun updateInsertedCoisCount(newCount: Int)
    fun updateTotalCoinsCount(newCount: Int)
    fun onError(error: String)
}
