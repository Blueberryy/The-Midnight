package midnight.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SporeParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite sprite;
    private final double verticalAcceleration;

    protected SporeParticle(ClientWorld world, double x, double y, double z, float xvMul, float yvMul, float zvMul, double xv, double yv, double zv, float scaling, IAnimatedSprite particleSprite, double randomLighting, double r, double g, double b, int averageAge, double vertAcceleration, boolean collideable) {
        super(world, x, y, z, 0, 0, 0);
        verticalAcceleration = vertAcceleration;
        sprite = particleSprite;
        motionX *= xvMul;
        motionY *= yvMul;
        motionZ *= zvMul;
        motionX += xv;
        motionY += yv;
        motionZ += zv;

        float shade = (world.rand.nextFloat() * 2 - 1) * (float) randomLighting;
        particleRed = MathHelper.clamp((float) r + shade, 0, 1);
        particleGreen = MathHelper.clamp((float) g + shade, 0, 1);
        particleBlue = MathHelper.clamp((float) b + shade, 0, 1);

        particleScale *= 0.75 * scaling;

        maxAge = (int) (averageAge / (world.rand.nextDouble() * 0.8 + 0.2));
        maxAge *= scaling;
        maxAge = Math.max(maxAge, 1);

        canCollide = collideable;

        selectSpriteWithAge(particleSprite);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    public float getScale(float partialTicks) {
        return particleScale * MathHelper.clamp((age + partialTicks) / maxAge * 32, 0, 1);
    }

    @Override
    public void tick() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        if (age++ >= maxAge) {
            setExpired();
        } else {
            selectSpriteWithAge(sprite);
            motionY += verticalAcceleration;
            move(motionX, motionY, motionZ);
            if (posY == prevPosY) {
                motionX *= 1.1;
                motionZ *= 1.1;
            }

            motionX *= 0.96;
            motionY *= 0.96;
            motionZ *= 0.96;
            if (onGround) {
                motionX *= 0.7;
                motionZ *= 0.7;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteProvider;

        public Factory(IAnimatedSprite sprite) {
            spriteProvider = sprite;
        }

        @Override
        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double r, double g, double b) {
            return new SporeParticle(world, x, y, z, 0.5f, 0.5f, 0.5f, 0, 0, 0, 0.4f, spriteProvider, 0.1, r, g, b, 90, -0.01, true);
        }
    }
}
