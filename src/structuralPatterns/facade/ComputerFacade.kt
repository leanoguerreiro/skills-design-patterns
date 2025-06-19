package structuralPatterns.facade

class ComputerFacade {

    private val cpu = Cpu()
    private val memory = Memory()
    private val hardDrive = HardDrive()

    fun startComputer() {
        println(">>> Iniciando o computador (via Facade)...")
        cpu.start()
        val bootData = hardDrive.read(0L, 1024)
        memory.load(0L, bootData)
        val bootData2  = hardDrive.write(0L, bootData)
        memory.load(0L, bootData2)
        cpu.execute()
        println(">>> Computador pronto para uso!")
        println("------------------------------------")
        println()
    }

    fun shutdownComputer() {
        println(">>> Desligando o computador (via Facade)...")
        cpu.stop()
        memory.clear()
        println(">>> Computador desligado.")
        println("------------------------------------")
    }
}