package io.github.jojotastic777.hexwhatsit.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import io.github.jojotastic777.hexwhatsit.config.HexwhatsitConfig
import io.github.jojotastic777.hexwhatsit.networking.msg.*

fun HexwhatsitMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HexwhatsitConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
