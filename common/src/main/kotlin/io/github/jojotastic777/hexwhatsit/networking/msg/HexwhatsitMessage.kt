package io.github.jojotastic777.hexwhatsit.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import io.github.jojotastic777.hexwhatsit.Hexwhatsit
import io.github.jojotastic777.hexwhatsit.networking.HexwhatsitNetworking
import io.github.jojotastic777.hexwhatsit.networking.handler.applyOnClient
import io.github.jojotastic777.hexwhatsit.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HexwhatsitMessage

sealed interface HexwhatsitMessageC2S : HexwhatsitMessage {
    fun sendToServer() {
        HexwhatsitNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HexwhatsitMessageS2C : HexwhatsitMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HexwhatsitNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HexwhatsitNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HexwhatsitMessageCompanion<T : HexwhatsitMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                Hexwhatsit.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HexwhatsitMessageC2S -> msg.applyOnServer(ctx)
                    else -> Hexwhatsit.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                Hexwhatsit.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HexwhatsitMessageS2C -> msg.applyOnClient(ctx)
                    else -> Hexwhatsit.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
