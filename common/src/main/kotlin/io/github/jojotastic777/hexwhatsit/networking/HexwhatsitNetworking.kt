package io.github.jojotastic777.hexwhatsit.networking

import dev.architectury.networking.NetworkChannel
import io.github.jojotastic777.hexwhatsit.Hexwhatsit
import io.github.jojotastic777.hexwhatsit.networking.msg.HexwhatsitMessageCompanion

object HexwhatsitNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(Hexwhatsit.id("networking_channel"))

    fun init() {
        for (subclass in HexwhatsitMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
