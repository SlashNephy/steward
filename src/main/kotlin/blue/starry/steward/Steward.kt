package blue.starry.steward

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object Steward: ListenerAdapter() {
    private val tagSyntaxRegex = "\\[(.+)@(\\d+)]".toRegex()
    private val bindings = mutableListOf<Binding>()

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        logger.info { event.message.contentRaw }

        tagSyntaxRegex.findAll(event.message.contentRaw).forEach {
            val (reaction, channelId) = it.destructured

            val channel = StewardJDA.getTextChannelById(channelId) ?: return@forEach
            event.message.addReaction(reaction.trim()).queue {
                bindings += Binding(
                    event.messageIdLong, channel.idLong, event.guild.idLong, reaction.trim()
                )
            }
        }
    }

    override fun onGuildMessageUpdate(event: GuildMessageUpdateEvent) {

    }

    override fun onGuildMessageReactionAdd(event: GuildMessageReactionAddEvent) {
        // 自分自身のイベントを無視
        if (event.member == StewardJDA.selfUser) {
            return
        }
    }

    override fun onGuildMessageReactionRemove(event: GuildMessageReactionRemoveEvent) {

    }

    override fun onGuildMessageReactionRemoveAll(event: GuildMessageReactionRemoveAllEvent) {

    }
}
