package structuralPatterns.adapter

fun main() {
    val patoReal = PernaDePato()
    val peruSelvagem = PeruSelvagem()

    val peruAdapter: Pato = PeruAdapter(peruSelvagem)

    println("----- Testando o Pato Real -----")
    testarPato(patoReal)

    println("----- Testando o Peru Selvagem -----")
    println("Um peru não pode ser passado diretamente para o metodo testarPato().")
    // testarPato(peruSelvagem) // -> Erro de Compilação

    peruSelvagem.gooble()
    peruAdapter.fly()
    println()

    println("----- Testando o Peru Adaptado para Pato -----")
    testarPato(peruAdapter)
    println()
}

fun testarPato(pato: Pato) {
    println("O 'pato' faz: ")
    pato.quack()
    pato.fly()
}