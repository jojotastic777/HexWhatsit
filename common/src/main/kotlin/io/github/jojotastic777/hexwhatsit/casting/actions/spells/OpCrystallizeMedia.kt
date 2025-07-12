package io.github.jojotastic777.hexwhatsit.casting.actions.spells

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getPositiveIntUnderInclusive
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.common.lib.HexItems
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3

data class OpCrystallizeMedia(val mediaItem: Item) : SpellAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val target = args.getVec3(0, argc)
        env.assertVecInRange(target)

        val count = args.getPositiveIntUnderInclusive(1, 64, argc)

        val costFactor = when (mediaItem) {
            (HexItems.AMETHYST_DUST) -> MediaConstants.DUST_UNIT * 1.05
            (Items.AMETHYST_SHARD) -> MediaConstants.SHARD_UNIT * 1.10
            (HexItems.CHARGED_AMETHYST) -> MediaConstants.CRYSTAL_UNIT * 1.15
            else -> throw Exception("TODO: figure out the right kind of exception for 'bad item type'")
        }.toLong()

        return SpellAction.Result(
            Spell(target, count, mediaItem),
            count * costFactor,
            listOf(ParticleSpray.cloud(target, 1.0))
        )
    }

    private data class Spell(val target: Vec3, val count: Int, val mediaType: Item) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val stack = ItemStack(mediaType, count)

            val ent = ItemEntity(
                env.world.level,
                target.x, target.y, target.z,
                stack,
                0.0, 0.0, 0.0
            )

            env.world.addFreshEntity(ent)
        }
    }
}