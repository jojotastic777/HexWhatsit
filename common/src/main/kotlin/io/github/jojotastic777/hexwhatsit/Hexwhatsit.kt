package io.github.jojotastic777.hexwhatsit

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import io.github.jojotastic777.hexwhatsit.config.HexwhatsitConfig
import io.github.jojotastic777.hexwhatsit.networking.HexwhatsitNetworking
import io.github.jojotastic777.hexwhatsit.registry.HexwhatsitActions

object Hexwhatsit {
    const val MODID = "hexwhatsit"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HexwhatsitConfig.init()
        initRegistries(
            HexwhatsitActions,
        )
        HexwhatsitNetworking.init()
    }
}
