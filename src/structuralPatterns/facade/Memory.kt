package structuralPatterns.facade

class Memory {

    fun load(position: Long, data: ByteArray) {
        println("Memória: Carregando dados na posição : $position")
        data.forEachIndexed { index, byte ->}
    }

    fun clear() {
        println("Memória: Limpando dados.")
    }
}