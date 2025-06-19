package structuralPatterns.facade



class HardDrive {

    fun read(lba: Long, size: Int): ByteArray {
        println("HD: Lendo $size bytes do setor LBA $lba.")
        return ByteArray(size)
    }

    fun write(lba: Long, data: ByteArray) : ByteArray {
        println("HD: Escrevendo ${data.size} bytes no setor LBA $lba.")
        return ByteArray(data.size)
    }
}