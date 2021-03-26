package blue.starry.steward

import kotlinx.coroutines.coroutineScope
import mu.KotlinLogging

val logger = KotlinLogging.createFeedchimeLogger("steward")

suspend fun main(): Unit = coroutineScope {
    logger.info { "Application started!" }
}
