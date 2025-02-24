package infrastructure.db.supabase.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjetoSupabaseModel(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("text") val text: String?,
    @SerialName("github_url") val github_url: String?,
    @SerialName("image_path") val image_path: String?,
    @SerialName("tags") val tags: List<String>?,
    @SerialName("created_at") val created_at: String? = null,
    @SerialName("updated_at") val updated_at: String? = null,
    @SerialName("importance") val importance: Int?
)