package domain.models

import java.util.*

data class Projeto (
    val id: Int,
    val name: String,
    val description: String,
    val text: String,
    val gitHubUrl: String,
    val tags: List<String> = emptyList(),
    val imagePath: String,
    val historicos: List<Historico> = emptyList(),
    var features: List<Feature> = emptyList(),
    val createdAt: Date,
    val updatedAt: Date,
    val importance: Int
    )