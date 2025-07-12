package io.github.jojotastic777.hexwhatsit.fabric

import io.github.jojotastic777.hexwhatsit.HexwhatsitClient
import net.fabricmc.api.ClientModInitializer

object FabricHexwhatsitClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexwhatsitClient.init()
    }
}
