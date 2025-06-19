package structuralPatterns.decorator02

fun main() {
    val minhaFoto = "foto_ferias.jpg"
    println("Imagem Original: $minhaFoto\n")

    // Cenário 1: Apenas processamento básico.
    var processor: ImageProcessor = BasicImageProcessor()
    println("Cenário 1 -> ${processor.process(minhaFoto)}")
    println("--------------------------------------------------------------------------")

    // Cenário 2: Processamento básico com marca d'água.
    processor = BasicImageProcessor()
    processor = WatermarkedImageProcessor(processor)
    println("Cenário 2 -> ${processor.process(minhaFoto)}")
    println("--------------------------------------------------------------------------")


    // Cenário 3: Processamento básico com redimensionamento.
    processor = BasicImageProcessor()
    processor = ResizeImageProcessor(processor)
    println("Cenário 3 -> ${processor.process(minhaFoto)}")
    println("--------------------------------------------------------------------------")


    // Cenário 4: Processamento completo, com redimensionamento e depois marca d'água.
    // A composição recursiva [00:00:12] em ação!
    processor = BasicImageProcessor()
    processor = ResizeImageProcessor(processor)
    processor = WatermarkedImageProcessor(processor)
    println("Cenário 4 -> ${processor.process(minhaFoto)}")
    println("--------------------------------------------------------------------------")
}
