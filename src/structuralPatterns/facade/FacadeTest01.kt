package structuralPatterns.facade

fun main() {
    val computer = ComputerFacade()

    computer.startComputer()

    println("... Usando o computador por algumas horas ...\n")

    computer.shutdownComputer()
}