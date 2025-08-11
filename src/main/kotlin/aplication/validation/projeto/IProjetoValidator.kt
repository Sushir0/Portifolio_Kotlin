package aplication.validation.projeto

interface IProjetoValidator {
    fun validarCreate(
        name: String,
        description: String,
        text: String,
        gitHubUrl: String,
        downloadUrl: String?,
        imageOriginalName: String,
        tags: List<String>,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String>

    fun validarUpdate(
        id: Int,
        name: String,
        description: String,
        text: String,
        gitHubUrl: String?,
        downloadUrl: String?,
        imageOriginalName: String?,
        tags: List<String>,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String>
}