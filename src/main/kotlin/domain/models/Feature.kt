package domain.models

import java.util.Date

data class Feature(
    val id: Int,
    val name: String,
    val text: String,
    val imagePath: String,
    val createdAt: Date,
    val idProjeto: Int
)
