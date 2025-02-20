package infrastructure.db.supabase.repositories

import aplication.dto.visitLog.VisitLogDTO
import domain.models.visitLog.VisitCount
import domain.models.visitLog.VisitGraph
import domain.models.visitLog.VisitLog
import domain.repository.VisitLogRepository
import infrastructure.db.supabase.SupabaseService
import infrastructure.db.supabase.models.VisitLogSupabaseModel
import infrastructure.utils.Utils.toDate
import kotlinx.coroutines.runBlocking

class SupabaseVisitLogRepository(): VisitLogRepository {
    override suspend fun create(visitLogDTO: VisitLogDTO) {
        try {
            val visitLogSupabaseModel = VisitLogSupabaseModel(
                ipAddress = visitLogDTO.ipAddress,
                page = visitLogDTO.page
            )

            SupabaseService.supabaseVisitLogs.insert(visitLogSupabaseModel)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override suspend fun getAll(): Result<List<VisitLog>> {
        try {
            val responseList = SupabaseService.supabaseVisitLogs.select().decodeList<VisitLogSupabaseModel>()

            val visitLogs = responseList.map { response ->
                VisitLog(
                    id = response.id ?: 0,
                    ipAddress = response.ipAddress,
                    page = response.page,
                    accessedAt = toDate(response.accessedAt!!)
                )
            }

            return Result.success(visitLogs)
        } catch (e: Exception) {
            return Result.failure(e)

        }
    }

    override suspend fun getVisitantesUnicos(): Result<Int> {
        return try{
            val result = SupabaseService.supabaseViewCountUniqueIp.select().decodeList<VisitCount>()
            Result.success(result.firstOrNull()?.count ?: 0)
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun getVisitasTotais(): Result<Int> {
        return try {
            val result = SupabaseService.supabaseViewCountTotalVisits.select().decodeList<VisitCount>()
            Result.success(result.firstOrNull()?.count ?: 0)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getGraficoTotalSemanal(): Result<List<VisitGraph>> {
        return try {
            val result = SupabaseService.supabaseViewGraficoTotalSemanal.select().decodeList<VisitGraph>()
            Result.success(result)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getGraficoTotalMensal(): Result<List<VisitGraph>> {
        return try {
            val result = SupabaseService.supabaseViewGraficoTotalMensal.select().decodeList<VisitGraph>()
            Result.success(result)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getGraficoTotalAnual(): Result<List<VisitGraph>> {
        return try {
            val result = SupabaseService.supabaseViewGraficoTotalAnual.select().decodeList<VisitGraph>()
            Result.success(result)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getGraficoUniqueIpSemanal(): Result<List<VisitGraph>> {
        return try {
            val result = SupabaseService.supabaseViewGraficoUniqueIpSemanal.select().decodeList<VisitGraph>()
            Result.success(result)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getGraficoUniqueIpMensal(): Result<List<VisitGraph>> {
        return try {
            val result = SupabaseService.supabaseViewGraficoUniqueIpMensal.select().decodeList<VisitGraph>()
            Result.success(result)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getGraficoUniqueIpAnual(): Result<List<VisitGraph>> {
        return try {
            val result = SupabaseService.supabaseViewGraficoUniqueIpAnual.select().decodeList<VisitGraph>()
            Result.success(result)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }
}