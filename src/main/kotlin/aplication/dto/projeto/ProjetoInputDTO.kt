package aplication.dto.projeto

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class ProjetoInputDTO(
    val id: Int? = null,
    val name: String,
    val description: String,
    val text: String,
    val githubUrl: String,
    val tags: List<String>,
    val imagePath: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)