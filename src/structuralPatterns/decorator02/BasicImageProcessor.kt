package structuralPatterns.decorator02

class BasicImageProcessor : ImageProcessor {
    override fun process(image: String): String {
        return "Processamento básico da imagem '$image'"
    }
}