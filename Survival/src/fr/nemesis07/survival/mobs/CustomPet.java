package fr.nemesis07.survival.mobs;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumMoveType;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R3.Vec3D;

public class CustomPet extends EntityCreature {
	
	static Field jumpField;
	static {
		try {
			jumpField = EntityLiving.class.getDeclaredField("jumping");
			jumpField.setAccessible(true);
		} catch (NoSuchFieldException ignore) {
		}
	}
	
	public CustomPet(Location loc, Player p, EntityTypes<EntityCreature> type) {
		super(type, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
//		if(type instanceof Ageable) {
//			this.setBaby(true);
//		}
		this.setInvulnerable(true);
		this.setGoalTarget((((CraftPlayer)p).getHandle()), TargetReason.CUSTOM, true);
	}
	
	@Override
	public void initPathfinder() {
		this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
		this.goalSelector.a(2, new PathfinderGoalFloat(this));
		this.goalSelector.a(3, new PathfinderGoalPet(this, 1, 15));
		this.goalSelector.a(4, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
	}

	@Override
	protected boolean cT() { //affected by fluids
		return false;
	}

	
	@Override
	public boolean bt() { //ridable in water
		return true;
	}
	
	@Override
	// each movement update
	public void movementTick(){
		super.movementTick();

		if (this.passengers.isEmpty() || !(this.passengers.get(0) instanceof EntityHuman)){
			return;
		}
		EntityHuman rider = (EntityHuman) this.passengers.get(0);
		Vector forwardDir = rider.getBukkitEntity().getLocation().getDirection();
		
		if (jumpField != null){
			try {
				boolean jumped = jumpField.getBoolean(rider);
				if (jumped){

					Location loc = this.getBukkitEntity().getLocation();
					loc.add(forwardDir.clone().multiply(10).setY(-1));
				}
			} catch (IllegalArgumentException | IllegalAccessException ignore){
			}
		}
		
		this.setYawPitch(180 + rider.yaw, rider.pitch);
		this.setHeadRotation(rider.pitch);
		
		double speeder = 0.5D;
		double fwSpeed = rider.aT * speeder;
		double sideSpeed = -1 * rider.aR * speeder;
		
		Vector sideways = forwardDir.clone().crossProduct(new Vector(0,1,0));
    
		Vector total = forwardDir.multiply(fwSpeed).add(sideways.multiply(sideSpeed));
		this.move(EnumMoveType.SELF, new Vec3D(total.getX(), total.getY(), total.getZ()));
        
	}
	
}
