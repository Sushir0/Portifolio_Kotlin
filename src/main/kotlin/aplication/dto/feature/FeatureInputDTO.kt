package aplication.dto.feature

import java.util.*

data class FeatureInputDTO(
    val name: String,
    val text: String,
    val imagePath: String,
    val createdAt: String? = null,
    val idProjeto: Int,
    val importance: Int
)
