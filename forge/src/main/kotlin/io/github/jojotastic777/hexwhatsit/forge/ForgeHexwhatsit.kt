package io.github.jojotastic777.hexwhatsit.forge

import dev.architectury.platform.forge.EventBuses
import io.github.jojotastic777.hexwhatsit.Hexwhatsit
import net.minecraft.data.DataProvider
import net.minecraft.data.DataProvider.Factory
import net.minecraft.data.PackOutput
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hexwhatsit.MODID)
class HexwhatsitForge {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hexwhatsit.MODID, this)
            addListener(ForgeHexwhatsitClient::init)
            addListener(::gatherData)
        }
        Hexwhatsit.init()
    }

    private fun gatherData(event: GatherDataEvent) {
        event.apply {
            // TODO: add datagen providers here
        }
    }
}

fun <T : DataProvider> GatherDataEvent.addProvider(run: Boolean, factory: (PackOutput) -> T) =
    generator.addProvider(run, Factory { factory(it) })
