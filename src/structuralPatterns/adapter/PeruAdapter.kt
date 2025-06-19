package structuralPatterns.adapter

class PeruAdapter(private val peru: Peru) : Pato {
    override fun quack() {
        peru.gooble()
    }

    override fun fly() {
        println("Simulando um voo longo: ")
        (0..4).forEach { i ->
            peru.fly()
        }
    }
}