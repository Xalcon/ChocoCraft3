package net.xalcon.chococraft.common.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xalcon.chococraft.common.entities.EntityChocobo;

import javax.annotation.Nullable;

public class PacketChocoboSprinting implements IMessage
{
    private boolean sprinting;

    @SuppressWarnings("unused")
    public PacketChocoboSprinting() { }

    public PacketChocoboSprinting(boolean sprinting)
    {
        this.sprinting = sprinting;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.sprinting = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(this.sprinting);
    }

    public static class Handler implements IMessageHandler<PacketChocoboSprinting, IMessage>
    {
        @Override @Nullable
        public IMessage onMessage(PacketChocoboSprinting message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                EntityPlayer player = ctx.getServerHandler().player;
                if(!player.isRiding()) return;

                Entity mount = player.getRidingEntity();
                if(!(mount instanceof EntityChocobo)) return;

                mount.setSprinting(message.sprinting);
            });
            return null;
        }
    }
}
