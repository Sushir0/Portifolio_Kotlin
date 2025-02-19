package domain.models

import aplication.dto.visitLog.VisitLogPresentationModel
import infrastructure.utils.Utils
import java.util.Date

data class VisitLog(
    val id: Int,
    val ipAddress: String,
    val page: String,
    val accessedAt: Date
){
    fun toPresentationModel(): VisitLogPresentationModel{
        return VisitLogPresentationModel(
            id = id,
            ipAddress = ipAddress,
            page = page,
            accessedAt = Utils.formatarDataParaIso(accessedAt)
        )
    }
}
