package creationalPatterns.factory

class Real : Currency {
    override fun getSymbol(): String {
        return "R$"
    }
}

