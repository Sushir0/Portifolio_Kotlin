package domain.repository

import aplication.dto.visitLog.VisitLogDTO
import domain.models.VisitLog

interface VisitLogRepository {
    suspend fun create(visitLogDTO: VisitLogDTO)
    suspend fun getAll(): Result<List<VisitLog>>
}