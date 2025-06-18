package creationalPatterns.factory

object CurrencyFactory {

    fun newCurrency(country: Country) : Currency {
        return when (country) {
            Country.BRAZIL -> Real()
            Country.USA -> UsDollar()
            else -> throw IllegalArgumentException("Unknown currency code")
        }
    }
}