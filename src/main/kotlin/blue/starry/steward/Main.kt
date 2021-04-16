package blue.starry.steward

import mu.KotlinLogging

internal val logger = KotlinLogging.createFeedchimeLogger("steward")

fun main() {
    logger.info { "Application started!" }

    StewardJDA.awaitReady()
}
