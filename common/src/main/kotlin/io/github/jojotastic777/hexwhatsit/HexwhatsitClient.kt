package io.github.jojotastic777.hexwhatsit

import io.github.jojotastic777.hexwhatsit.config.HexwhatsitConfig
import io.github.jojotastic777.hexwhatsit.config.HexwhatsitConfig.GlobalConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HexwhatsitClient {
    fun init() {
        HexwhatsitConfig.initClient()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(GlobalConfig::class.java, parent).get()
    }
}
