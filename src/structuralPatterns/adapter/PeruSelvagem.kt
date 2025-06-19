package structuralPatterns.adapter

class PeruSelvagem : Peru {
    override fun gooble() {
        println("Glu Glu Glu Glu")
    }
    override fun fly() {
        println("Estou voando uma curta distancia, como um peru.")
    }
}