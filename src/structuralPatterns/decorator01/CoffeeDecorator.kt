package structuralPatterns.decorator01

abstract class CoffeeDecorator (protected val decoratedCoffee: Coffee) : Coffee {
    override fun getCost() : Double {
        return decoratedCoffee.getCost()
    }

    override fun getDescription(): String {
        return decoratedCoffee.getDescription()
    }
}