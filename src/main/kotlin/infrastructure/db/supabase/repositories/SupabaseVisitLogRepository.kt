package infrastructure.db.supabase.repositories

import aplication.dto.visitLog.VisitLogDTO
import appModule.utils
import domain.models.VisitLog
import domain.repository.VisitLogRepository
import infrastructure.db.supabase.SupabaseService
import infrastructure.db.supabase.models.VisitLogSupabaseModel
import infrastructure.utils.Utils.toDate
import java.util.*

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
}