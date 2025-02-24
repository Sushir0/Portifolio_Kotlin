package aplication.validation.feature

interface IFeatureValidator {
    fun validarCreate(
        name: String,
        text: String,
        idProjeto: Int,
        imageOriginalName: String,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String>

    fun validarUpdate(
        id: Int,
        name: String,
        text: String,
        imageOriginalName: String?,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String>

}