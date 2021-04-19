package fr.nemesis07.survival.mobs;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.PathfinderGoal;

public class PathfinderGoalPet extends PathfinderGoal {
	
	private final EntityInsentient a; // pet
	private EntityLiving b; // owner of pet
	
	private final double f; //pet speed
	private final float g; // distance pet/owner before teleport
	Random r = new Random();
	private int Xr = r.nextInt(10);
	private int Zr = r.nextInt(10);
	
	private double c; // x
	private double d; // y
	private double e; // z
	
	public PathfinderGoalPet(EntityInsentient a, double speed, float distance) {
		this.a = a;
		this.f = speed;
		this.g = distance;
		this.a(EnumSet.of(Type.MOVE));
	}
	
	
	@Override
	public boolean a() {
		this.b = this.a.getGoalTarget();
		if(this.b == null)
			return false;
		else if(this.a.getDisplayName() == null)
			return false;
		else if(this.b.h(this.a) > (double) (this.g * this.g)) {
			a.setPosition((Xr-5) + this.b.locX(), this.b.locY(), (Zr-5) + this.b.locZ());
			return false;
		} else {
			
			/*Vec3D vec = RandomPositionGenerator.a((EntityCreature) this.a, 16, 7, this.b.getPositionVector());
			
			if(vec == null)
				return false;*/
			
			this.c = this.b.locX();
			this.d = this.b.locY();
			this.e = this.b.locZ();
			return true;
		}
	}

	public void c() {
		this.a.getNavigation().a(this.c, this.d, this.e, this.f);
	}

	public boolean b() {
		return !this.a.getNavigation().m() && this.b.h(this.a) < (double) (this.g * this.g);
	}
	
	public void d() {
		this.b = null;
	}
	
}
