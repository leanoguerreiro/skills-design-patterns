package creationalPatterns.factory

fun main () {

    val currencyBrazil = CurrencyFactory.newCurrency(Country.BRAZIL)
    println("Moeda do ${Country.BRAZIL}: ${currencyBrazil.getSymbol()}")

    val currencyUSA = CurrencyFactory.newCurrency(Country.USA)
    println("Moeda do ${Country.USA}: ${currencyUSA.getSymbol()}")

    val currencyParaguay = CurrencyFactory.newCurrency(Country.PARAGUAY)
    println("Moeda do ${Country.PARAGUAY}: ${currencyParaguay.getSymbol()}")

}