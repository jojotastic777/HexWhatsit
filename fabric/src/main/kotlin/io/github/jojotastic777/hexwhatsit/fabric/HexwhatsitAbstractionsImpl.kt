@file:JvmName("HexwhatsitAbstractionsImpl")

package io.github.jojotastic777.hexwhatsit.fabric

import io.github.jojotastic777.hexwhatsit.registry.HexwhatsitRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HexwhatsitRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
