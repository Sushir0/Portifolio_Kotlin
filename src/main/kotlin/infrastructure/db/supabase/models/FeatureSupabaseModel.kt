package infrastructure.db.supabase.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureSupabaseModel(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String?,
    @SerialName("text") val text: String?,
    @SerialName("image_path") val image_path: String?,
    @SerialName("created_at") val created_at: String? = null,
    @SerialName( "id_projeto") val id_projeto: Int? = null
)
