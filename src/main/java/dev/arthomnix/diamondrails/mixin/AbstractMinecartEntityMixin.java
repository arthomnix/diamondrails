package dev.arthomnix.diamondrails.mixin;

import dev.arthomnix.diamondrails.DiamondRails;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {
    private double maxSpeed = 8.0;

    public AbstractMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean checkForNewPoweredRailTypes(BlockState state, Block block) {
        return state.isIn(DiamondRails.TAG_POWERED_RAILS);
    }

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 5))
    private Vec3d increaseAccelForNewRails(Vec3d vec, double x, double y, double z) {
        Vec3d newvec = vec.add(x, y, z);
        BlockState blockState = this.world.getBlockState(this.getBlockPos());
        if (blockState.isOf(DiamondRails.DIAMOND_RAIL)) {
            return newvec.multiply(4);
        } else if (blockState.isOf(DiamondRails.ENHANCED_DIAMOND_RAIL)) {
            return newvec.multiply(90 / 8d);
        } else if (blockState.isOf(DiamondRails.NETHERITE_RAIL)) {
            return newvec.multiply((159d + (2d/3d)) / 8d);
        }
        return newvec;
    }

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D"))
    private double increaseSpeedCap(double a, double b) {
        return Math.min(8.0, b);
    }

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getMaxSpeed()D"))
    public double increaseMaxSpeedOnNewRails(AbstractMinecartEntity instance) {
        double speed = maxSpeed;
        BlockState blockState = this.world.getBlockState(this.getBlockPos());
        if (blockState.isOf(Blocks.POWERED_RAIL)) {
            speed = 8.0;
        } else if (blockState.isOf(DiamondRails.DIAMOND_RAIL)) {
            speed = 32.0;
        } else if (blockState.isOf(DiamondRails.ENHANCED_DIAMOND_RAIL)) {
            speed = 90.0;
        } else if (blockState.isOf(DiamondRails.NETHERITE_RAIL)) {
            speed = 159.0 + (2d/3d);
        }
        maxSpeed = speed;
        return speed / (this.isTouchingWater() ? 40.0 : 20.0);
    }
}
