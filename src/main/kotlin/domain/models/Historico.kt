package domain.models

import java.util.*

data class Historico(
    val id: Int,
    val name: String,
    val text: String,
    val createdAt: Date,
    val idProjeto: Int
)