package structuralPatterns.adapter

class PernaDePato : Pato {
    override fun quack() {
        println("Quack! Quack!")
    }

    override fun fly() {
        println("Estou voando como um pato!")
    }
}