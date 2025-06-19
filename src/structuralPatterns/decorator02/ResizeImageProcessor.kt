package structuralPatterns.decorator02

class ResizeImageProcessor (
    wrappedProcessor: ImageProcessor,
) : ImageProcessorDecorator(wrappedProcessor) {
    override fun process(image: String): String {
        val processedImage = super.process(image)
        return "$processedImage + Redimensionada para 800x600"
    }
}