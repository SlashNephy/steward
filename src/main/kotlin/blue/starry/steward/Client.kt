package blue.starry.steward

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus

val StewardJDA by lazy {
    JDABuilder.createDefault(Env.DISCORD_TOKEN)
        .setStatus(OnlineStatus.IDLE)
        .addEventListeners(Steward)
        .build()
}
