package blue.starry.steward

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object Steward: ListenerAdapter() {
    override fun onGuildMessageReactionAdd(event: GuildMessageReactionAddEvent) {

    }

    override fun onGuildMessageReactionRemove(event: GuildMessageReactionRemoveEvent) {

    }

    override fun onGuildMessageReactionRemoveAll(event: GuildMessageReactionRemoveAllEvent) {

    }
}
