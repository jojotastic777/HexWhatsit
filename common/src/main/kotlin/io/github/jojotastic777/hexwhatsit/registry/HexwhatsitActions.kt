package io.github.jojotastic777.hexwhatsit.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexItems
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import io.github.jojotastic777.hexwhatsit.casting.actions.spells.OpCrystallizeMedia
import net.minecraft.world.item.Items

object HexwhatsitActions : HexwhatsitRegistrar<ActionRegistryEntry>(
    HexRegistries.ACTION,
    { HexActions.REGISTRY },
) {
    val LESSER_CRYSTALLIZE_MEDIA = make("lesser_crystallize_media", HexDir.EAST, "wawaadda") {
        OpCrystallizeMedia(HexItems.AMETHYST_DUST)
    }
    val CRYSTALLIZE_MEDIA = make("crystallize_media", HexDir.EAST, "wawaaddaedwwwdwwwddadadaadada") {
        OpCrystallizeMedia(Items.AMETHYST_SHARD)
    }
    val GREATER_CRYSTALLIZE_MEDIA = make("greater_crystallize_media", HexDir.EAST, "wawaaddaedwwwdwwwwdwwwwddadadadaadadadaadada") {
        OpCrystallizeMedia(HexItems.CHARGED_AMETHYST)
    }


    private fun make(name: String, startDir: HexDir, signature: String, action: Action) =
        make(name, startDir, signature) { action }

    private fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}
