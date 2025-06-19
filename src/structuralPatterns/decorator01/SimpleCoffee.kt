package structuralPatterns.decorator01

class SimpleCoffee : Coffee {
    override fun getCost(): Double {
        return 5.0
    }
    override fun getDescription(): String {
        return "Café simples"
    }
}