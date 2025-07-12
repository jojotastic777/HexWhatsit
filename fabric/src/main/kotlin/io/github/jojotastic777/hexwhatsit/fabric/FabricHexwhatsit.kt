package io.github.jojotastic777.hexwhatsit.fabric

import io.github.jojotastic777.hexwhatsit.Hexwhatsit
import net.fabricmc.api.ModInitializer

object FabricHexwhatsit : ModInitializer {
    override fun onInitialize() {
        Hexwhatsit.init()
    }
}
