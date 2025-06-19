package structuralPatterns.decorator02

abstract class ImageProcessorDecorator (
    protected val wrappedProcessor: ImageProcessor
) : ImageProcessor {
    override fun process(image: String): String {
        return wrappedProcessor.process(image)
    }
}