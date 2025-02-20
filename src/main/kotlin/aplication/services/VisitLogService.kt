package aplication.services

import aplication.dto.visitLog.VisitLogDTO
import aplication.dto.visitLog.VisitLogPresentationModel
import domain.models.visitLog.VisitGraph
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

    suspend fun getVisitasTotais(): Result<Int>{
        return visitLogRepository.getVisitasTotais()
    }

    suspend fun getVisitantesUnicos(): Result<Int>{
        return visitLogRepository.getVisitantesUnicos()
    }

    suspend fun getGraficoTotalSemanal(): Result<List<VisitGraph>>{
        return visitLogRepository.getGraficoTotalSemanal()
    }

    suspend fun getGraficoTotalMensal(): Result<List<VisitGraph>>{
        return visitLogRepository.getGraficoTotalMensal()
    }

    suspend fun getGraficoTotalAnual(): Result<List<VisitGraph>>{
        return visitLogRepository.getGraficoTotalAnual()
    }

    suspend fun getGraficoUniqueIpSemanal(): Result<List<VisitGraph>>{
        return visitLogRepository.getGraficoUniqueIpSemanal()
    }

    suspend fun getGraficoUniqueIpMensal(): Result<List<VisitGraph>>{
        return visitLogRepository.getGraficoUniqueIpMensal()
    }

    suspend fun getGraficoUniqueIpAnual(): Result<List<VisitGraph>>{
        return visitLogRepository.getGraficoUniqueIpAnual()
    }



}