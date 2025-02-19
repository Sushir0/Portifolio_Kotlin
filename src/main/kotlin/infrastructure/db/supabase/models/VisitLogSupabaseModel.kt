package infrastructure.db.supabase.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VisitLogSupabaseModel(
    @SerialName("id") val id: Int? = null,
    @SerialName("ip_address") val ipAddress: String,
    @SerialName("page") val page: String,
    @SerialName("accessed_at") val accessedAt: String? = null
)
