package ru.iandreyshev.gumballmachine.viewModel.interfaces

interface IMachineViewModel {
    fun updateMachineName(name: String)
    fun updateBallsCount(newCount: Int)
    fun updateInsertedCoinsCount(newCount: Int)
    fun updateTotalCoinsCount(newCount: Int)
    fun onError(error: String)
}
