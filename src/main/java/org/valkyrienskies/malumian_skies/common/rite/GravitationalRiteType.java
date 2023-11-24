package org.valkyrienskies.malumian_skies.common.rite;

import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.core.systems.rites.MalumRiteEffect;
import com.sammy.malum.core.systems.rites.MalumRiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.sammy.malum.registry.common.SpiritTypeRegistry.*;


public class GravitationalRiteType extends MalumRiteType {
    public GravitationalRiteType() {
        super("gravitational_rite", ARCANE_SPIRIT, AERIAL_SPIRIT, INFERNAL_SPIRIT);
    }

    private BlockPos vectorBlockPosAdder(Vector3d vectorA, BlockPos vectorB) {
        return  new BlockPos(new Vec3(
                vectorA.x +vectorB.getX(),
                vectorA.y +vectorB.getY(),
                vectorA.z +vectorB.getZ()));
    }

    HashMap<Boolean, AABB> Auras = new HashMap<Boolean, AABB>();

    @Override
    public MalumRiteEffect getNaturalRiteEffect() {

        return new MalumRiteEffect() {
            public void riteEffect(TotemBaseBlockEntity totemBase) {
                if(totemBase.active) {
                    Vector3d VectorRange = new Vector3d(10, 10, 10);
                    Auras.put(false, new AABB(vectorBlockPosAdder(VectorRange, totemBase.getBlockPos()), vectorBlockPosAdder(VectorRange.mul(-1,-1,-1), totemBase.getBlockPos())));
                }
            }
        };
    }

    @Override
    public MalumRiteEffect getCorruptedEffect() {
        return new MalumRiteEffect() {
            public void riteEffect(TotemBaseBlockEntity totemBase) {
                if(totemBase.active) {
                    Vector3d VectorRange = new Vector3d(10, 10, 10);
                    Auras.put(true, new AABB(vectorBlockPosAdder(VectorRange, totemBase.getBlockPos()), vectorBlockPosAdder(VectorRange.mul(-1,-1,-1), totemBase.getBlockPos())));
                }
            }
        };
    }

    public HashMap<Boolean, AABB> getAuras() {
        return Auras;
    }
}
