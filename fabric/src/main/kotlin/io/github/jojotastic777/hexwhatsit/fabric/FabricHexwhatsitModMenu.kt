package io.github.jojotastic777.hexwhatsit.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import io.github.jojotastic777.hexwhatsit.HexwhatsitClient

object FabricHexwhatsitModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HexwhatsitClient::getConfigScreen)
}
