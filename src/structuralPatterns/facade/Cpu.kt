package structuralPatterns.facade

class Cpu {
    fun start() {
        println("CPU: Iniciando o processamento...")
    }

    fun execute() {
        println("CPU: Executando o instruções...")
    }

    fun stop() {
        println("CPU: Parando processamento...")
    }
}