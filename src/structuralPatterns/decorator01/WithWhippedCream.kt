package structuralPatterns.decorator01

class WithWhippedCream(decoratedCoffee: Coffee) : CoffeeDecorator(decoratedCoffee) {

    override fun getCost(): Double {
        return super.getCost() + 2.5
    }

    override fun getDescription(): String {
        return super.getDescription() + ", com chantilly"
    }
}