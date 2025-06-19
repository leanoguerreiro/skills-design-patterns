package structuralPatterns.decorator01

fun main() {

    var myCoffee: Coffee = SimpleCoffee()
    println("Pedido 1: ${myCoffee.getDescription()} | Custo: R$ ${myCoffee.getCost()}")
    println("--------------------------------------------------")

    myCoffee = SimpleCoffee()
    myCoffee = WithMilk(myCoffee)
    println("Pedido 2: ${myCoffee.getDescription()} | Custo: R$ ${myCoffee.getCost()}")
    println("--------------------------------------------------")

    myCoffee = SimpleCoffee()
    myCoffee = WithMilk(myCoffee)
    myCoffee = WithWhippedCream(myCoffee)
    println("Pedido 3: ${myCoffee.getDescription()} | Custo: R$ ${myCoffee.getCost()}")
    println("--------------------------------------------------")

    myCoffee = SimpleCoffee()
    myCoffee = WithMilk(myCoffee)
    myCoffee = WithWhippedCream(myCoffee)
    myCoffee = WithWhippedCream(myCoffee)
    println("Pedido 4: ${myCoffee.getDescription()} | Custo: R$ ${myCoffee.getCost()}")
    println("--------------------------------------------------")

}