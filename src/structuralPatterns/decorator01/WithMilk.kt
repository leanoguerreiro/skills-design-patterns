package structuralPatterns.decorator01

class WithMilk(decoratedCoffee: Coffee) : CoffeeDecorator(decoratedCoffee) {
    override fun getCost(): Double {
        return super.getCost() + 1.5
    }
    override fun getDescription(): String {
        return super.getDescription() + ", com leite"
    }
}