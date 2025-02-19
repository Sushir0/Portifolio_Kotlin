package aplication.dto.visitLog


data class VisitLogDTO (
    val id: Int? = null,
    val ipAddress: String,
    val page: String,
    val accessedAt: Long? = null
)