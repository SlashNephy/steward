package blue.starry.steward

import kotlinx.serialization.Serializable

@Serializable
data class Binding(
    val messageId: Long,
    val channelId: Long,
    val guildId: Long,
    val reaction: String
)
