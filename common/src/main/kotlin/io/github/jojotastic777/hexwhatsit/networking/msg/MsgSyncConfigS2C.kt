package io.github.jojotastic777.hexwhatsit.networking.msg

import io.github.jojotastic777.hexwhatsit.config.HexwhatsitConfig
import net.minecraft.network.FriendlyByteBuf

data class MsgSyncConfigS2C(val serverConfig: HexwhatsitConfig.ServerConfig) : HexwhatsitMessageS2C {
    companion object : HexwhatsitMessageCompanion<MsgSyncConfigS2C> {
        override val type = MsgSyncConfigS2C::class.java

        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
            serverConfig = HexwhatsitConfig.ServerConfig.decode(buf),
        )

        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
            serverConfig.encode(buf)
        }
    }
}
