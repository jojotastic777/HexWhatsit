@file:JvmName("HexwhatsitAbstractions")

package io.github.jojotastic777.hexwhatsit

import dev.architectury.injectables.annotations.ExpectPlatform
import io.github.jojotastic777.hexwhatsit.registry.HexwhatsitRegistrar

fun initRegistries(vararg registries: HexwhatsitRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexwhatsitRegistrar<T>) {
    throw AssertionError()
}
