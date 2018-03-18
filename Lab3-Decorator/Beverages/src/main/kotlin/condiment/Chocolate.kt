package condiment

import beverage.IBeverage

class Chocolate(beverage: IBeverage, private var mNumberOfPieces: Int) : Condiment(beverage) {

    companion object {
        private const val MIN_PIECES = 1
        private const val MAX_PIECES = 10
        private const val ONE_PIECE_COST = 3.5
    }

    init {
        mNumberOfPieces = when {
            mNumberOfPieces < MIN_PIECES -> MIN_PIECES
            mNumberOfPieces > MAX_PIECES -> MAX_PIECES
            else -> mNumberOfPieces
        }
    }

    override val condimentName: String
        get() = "$mNumberOfPieces pieces of chocolate"
    override val condimentCost: Double
        get() = ONE_PIECE_COST * mNumberOfPieces
}
