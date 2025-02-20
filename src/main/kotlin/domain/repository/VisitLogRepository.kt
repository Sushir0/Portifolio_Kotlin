package domain.repository

import aplication.dto.visitLog.VisitLogDTO
import domain.models.visitLog.VisitGraph
import domain.models.visitLog.VisitLog

interface VisitLogRepository {
    suspend fun create(visitLogDTO: VisitLogDTO)
    suspend fun getAll(): Result<List<VisitLog>>
    suspend fun getVisitantesUnicos(): Result<Int>
    suspend fun getVisitasTotais(): Result<Int>
    suspend fun getGraficoTotalSemanal(): Result<List<VisitGraph>>
    suspend fun getGraficoTotalMensal(): Result<List<VisitGraph>>
    suspend fun getGraficoTotalAnual(): Result<List<VisitGraph>>
    suspend fun getGraficoUniqueIpSemanal(): Result<List<VisitGraph>>
    suspend fun getGraficoUniqueIpMensal(): Result<List<VisitGraph>>
    suspend fun getGraficoUniqueIpAnual(): Result<List<VisitGraph>>

}