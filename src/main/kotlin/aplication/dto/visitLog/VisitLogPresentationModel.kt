package aplication.dto.visitLog

data class VisitLogPresentationModel(
    val id: Int,
    val ipAddress: String,
    val page: String,
    val accessedAt: String
)
