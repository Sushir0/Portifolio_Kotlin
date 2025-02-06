package infrastructure.db.supabase.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoricoSupabaseModel(
    @SerialName("id") val id: Int? = null,
    @SerialName("id_projeto") val id_projeto: Int? = null,
    @SerialName("text") val text: String?,
    @SerialName("created_at") val created_at: String? = null,
    @SerialName("name") val name: String?
)
