package domain.models

import kotlinx.serialization.Serializable


@Serializable
data class UserSession(val username: String)

