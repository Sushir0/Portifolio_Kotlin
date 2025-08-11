package aplication.validation.projeto

object ProjetoValidatorMock: IProjetoValidator {
    override fun validarCreate(
        name: String,
        description: String,
        text: String,
        gitHubUrl: String,
        imageOriginalName: String,
        tags: List<String>,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String> {
        return emptyList()
    }

    override fun validarUpdate(
        id: Int,
        name: String,
        description: String,
        text: String,
        gitHubUrl: String?,
        imageOriginalName: String?,
        tags: List<String>,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String> {
        return emptyList()
    }

}