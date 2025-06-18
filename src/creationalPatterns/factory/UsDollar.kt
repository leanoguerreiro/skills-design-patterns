package creationalPatterns.factory

class UsDollar : Currency {
    override fun getSymbol(): String {
        return "$"
    }
}