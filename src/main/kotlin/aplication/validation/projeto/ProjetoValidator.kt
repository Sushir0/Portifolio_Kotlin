package aplication.validation.projeto


object ProjetoValidator: IProjetoValidator {
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
        val errors = mutableListOf<String>()
        errors.addAll(validarName(name))
        errors.addAll(validarDescription(description))
        errors.addAll(validarText(text))
        errors.addAll(validarGitHubUrl(gitHubUrl))
        errors.addAll(validarImageOriginalName(imageOriginalName))
        errors.addAll(validarTags(tags))
        errors.addAll(validarImageBytes(imageBytes))
        errors.addAll(validarImportance(importance))
        return errors
    }

    override fun validarUpdate(
        id: Int,
        name: String,
        description: String,
        text: String,
        gitHubUrl: String,
        imageOriginalName: String?,
        tags: List<String>,
        imageBytes: ByteArray?,
        importance: Int?
    ): List<String> {
        val errors = mutableListOf<String>()
        errors.addAll(validarName(name))
        errors.addAll(validarDescription(description))
        errors.addAll(validarText(text))
        errors.addAll(validarGitHubUrl(gitHubUrl))
        errors.addAll(validarTags(tags))
        errors.addAll(validarImportance(importance))

        if (imageOriginalName != null){
            errors.addAll(validarImageOriginalName(imageOriginalName))
            errors.addAll(validarImageBytes(imageBytes))
        }
        return errors
    }

    private fun validarImportance(importance: Int?): List<String> {
        //todo
        return emptyList()
    }

    fun validarName(name: String): List<String> {
        //todo
        return emptyList()
    }

    fun validarDescription(description: String): List<String> {
        //todo
        return emptyList()
    }

    fun validarText(text: String): List<String> {
        //todo
        return emptyList()
    }

    fun validarGitHubUrl(gitHubUrl: String): List<String> {
        //todo
        return emptyList()
    }

    fun validarImageOriginalName(imageOriginalName: String): List<String> {
        //todo
        return emptyList()
    }

    fun validarTags(tags: List<String>): List<String> {
        //todo
        return emptyList()
    }

    fun validarImageBytes(imageBytes: ByteArray?): List<String> {
        val errors = mutableListOf<String>()
        if (imageBytes == null) {
            errors.add("Image bytes cannot be null")
        }
        return errors
    }

}