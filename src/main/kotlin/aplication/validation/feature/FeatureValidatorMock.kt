package aplication.validation.feature

object FeatureValidatorMock: IFeatureValidator {
    override fun validarCreate(
        name: String,
        text: String,
        idProjeto: Int,
        imageOriginalName: String,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String> {
        return emptyList()
    }

    override fun validarUpdate(
        id: Int,
        name: String,
        text: String,
        imageOriginalName: String?,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String> {
        return emptyList()
    }
}