@file:JvmName("HexwhatsitAbstractionsImpl")

package io.github.jojotastic777.hexwhatsit.forge

import io.github.jojotastic777.hexwhatsit.registry.HexwhatsitRegistrar
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

fun <T : Any> initRegistry(registrar: HexwhatsitRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}
