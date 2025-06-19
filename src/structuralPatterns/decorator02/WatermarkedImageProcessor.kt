package structuralPatterns.decorator02

class WatermarkedImageProcessor (wrappedProcessor: ImageProcessor) : ImageProcessorDecorator(wrappedProcessor) {
    override fun process(image: String): String {
        val processedImage = super.process(image)
        return "$processedImage + Marca D'ágia da Empresa"
    }

}