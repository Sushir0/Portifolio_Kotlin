package aplication.services

import aplication.dto.visitLog.VisitLogDTO
import aplication.dto.visitLog.VisitLogPresentationModel
import domain.models.VisitLog
import domain.repository.VisitLogRepository

class VisitLogService(
    private val visitLogRepository: VisitLogRepository
) {
    suspend fun create(ipAddress: String, page: String): Result<String> {
        val visitLogDTO = VisitLogDTO(
            ipAddress = ipAddress,
            page = page,
        )

        visitLogRepository.create(visitLogDTO)
        return Result.success("Visit log created successfully")
    }

    suspend fun getAll(): Result<List<VisitLogPresentationModel>> {
        val visitLogsResult = visitLogRepository.getAll()
        if (visitLogsResult.isFailure){
            return Result.failure(visitLogsResult.exceptionOrNull()!!)
        }

        val visitLogs = visitLogsResult.getOrThrow()
        return Result.success(visitLogs.map { it.toPresentationModel() })
    }

}