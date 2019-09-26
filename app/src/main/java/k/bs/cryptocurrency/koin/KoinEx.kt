package k.bs.cryptocurrency.koin

import org.koin.core.KoinComponent
import org.koin.core.inject

inline fun <reified T> getKoinInstance(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}