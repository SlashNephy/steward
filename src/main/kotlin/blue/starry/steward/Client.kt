package blue.starry.steward

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag

val StewardJDA by lazy {
    JDABuilder
        .createDefault(
            Env.DISCORD_TOKEN,
            GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_EMOJIS
        )
        .disableCache(CacheFlag.VOICE_STATE)
        .addEventListeners(Steward)
        .build()
}
