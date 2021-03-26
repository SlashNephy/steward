package blue.starry.steward

import kotlin.properties.ReadOnlyProperty

object Env {
    val LOG_LEVEL by stringOrNull
    val DRYRUN by boolean { false }

    val DISCORD_TOKEN by string
}

private fun boolean(default: () -> Boolean): ReadOnlyProperty<Env, Boolean> = ReadOnlyProperty { _, property ->
    System.getenv(property.name)?.toBooleanFuzzy() ?: default()
}

private fun String?.toBooleanFuzzy(): Boolean {
    return when (this) {
        null -> false
        "1", "yes" -> true
        else -> toLowerCase().toBoolean()
    }
}

private val string: ReadOnlyProperty<Env, String>
    get() = ReadOnlyProperty { _, property ->
        System.getenv(property.name) ?: error("Env: ${property.name} is not present.")
    }

private val stringOrNull: ReadOnlyProperty<Env, String?>
    get() = ReadOnlyProperty { _, property ->
        System.getenv(property.name)
    }

private fun string(default: () -> String): ReadOnlyProperty<Env, String> = ReadOnlyProperty { _, property ->
    System.getenv(property.name) ?: default()
}

private fun int(default: () -> Int): ReadOnlyProperty<Env, Int> = ReadOnlyProperty { _, property ->
    System.getenv(property.name)?.toIntOrNull() ?: default()
}

private fun long(default: () -> Long): ReadOnlyProperty<Env, Long> = ReadOnlyProperty { _, property ->
    System.getenv(property.name)?.toLongOrNull() ?: default()
}

private val longList: ReadOnlyProperty<Env, List<Long>>
    get() = ReadOnlyProperty { _, property ->
        System.getenv(property.name)?.split(",")?.mapNotNull { it.trim().toLongOrNull() }.orEmpty()
    }

private val stringList: ReadOnlyProperty<Env, List<String>>
    get() = ReadOnlyProperty { _, property ->
        System.getenv(property.name)?.split(",")?.map { it.trim() }.orEmpty()
    }
