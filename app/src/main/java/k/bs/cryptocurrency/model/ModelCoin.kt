package k.bs.cryptocurrency.model

data class ModelCoin(
    val data: Data = Data(),
    val status: String = ""
) {
    data class Data(
        val base: Base = Base(),
        val coins: List<Coin> = listOf(),
        val stats: Stats = Stats()
    )

    data class Base(
        val sign: String = "",
        val symbol: String = ""
    )

    data class Coin(
        val allTimeHigh: AllTimeHigh = AllTimeHigh(),
        val approvedSupply: Boolean = false,
        val change: Double = 0.0,
        val circulatingSupply: Double = 0.0,
        val color: String = "",
        val confirmedSupply: Boolean = false,
        val description: String = "",
        val firstSeen: Long = 0,
        val history: List<String> = listOf(),
        val iconType: String = "",
        val iconUrl: String = "",
        val id: Int = 0,
        val links: List<Link> = listOf(),
        val marketCap: Long = 0,
        val name: String = "",
        val numberOfExchanges: Int = 0,
        val numberOfMarkets: Int = 0,
        val penalty: Boolean = false,
        val price: String = "",
        val rank: Int = 0,
        val slug: String = "",
        val socials: List<Social> = listOf(),
        val symbol: String = "",
        val totalSupply: Double = 0.0,
        val type: String = "",
        val uuid: String = "",
        val volume: Long = 0,
        val websiteUrl: String = ""
    )

    data class AllTimeHigh(
        val price: String = "",
        val timestamp: Long = 0
    )

    data class Link(
        val name: String = "",
        val type: String = "",
        val url: String = ""
    )

    data class Social(
        val name: String = "",
        val type: String = "",
        val url: String = ""
    )

    data class Stats(
        val base: String = "",
        val limit: Int = 0,
        val offset: Int = 0,
        val order: String = "",
        val total: Int = 0,
        val total24hVolume: Double = 0.0,
        val totalExchanges: Int = 0,
        val totalMarketCap: Double = 0.0,
        val totalMarkets: Int = 0
    )
}

