package aplication.dto.historico

import java.util.*

data class HistoricoInputDTO (
    val name: String,
    val text: String,
    val createdAt: String? = null,
    val idProjeto: Int
)