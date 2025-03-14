package domain.models.visitLog

import kotlinx.serialization.Serializable

@Serializable
data class VisitGraph(
    val start_date: String,
    val view_count: Int
)
