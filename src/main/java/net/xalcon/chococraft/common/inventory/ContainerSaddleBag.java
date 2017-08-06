package net.xalcon.chococraft.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.xalcon.chococraft.common.entities.EntityChocobo;

public class ContainerSaddleBag extends Container
{
    public ContainerSaddleBag(EntityChocobo chocobo, EntityPlayer player)
    {
        bindPlayerInventory(player);

        switch(chocobo.getSaddleType())
        {
            case SADDLE_BAGS:
                bindInventorySmall(chocobo.chocoboInventory);
                break;
            case PACK:
                bindInventoryBig(chocobo.chocoboInventory);
                break;
        }

        this.addSlotToContainer(new SlotChocoboSaddle(chocobo.saddleItemStackHandler, 0, -16, 18));
    }

    private void bindInventorySmall(IItemHandler inventory)
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 5; col++)
            {
                this.addSlotToContainer(new SlotItemHandler(inventory, row * 5 + col, 80 + col * 18, 80 + row * 18));
            }
        }
    }

    private void bindInventoryBig(IItemHandler inventory)
    {

    }

    private void bindPlayerInventory(EntityPlayer player)
    {
        for (int row = 0; row < 3; ++row)
        {
            for (int col = 0; col < 9; ++col)
            {
                this.addSlotToContainer(new Slot(player.inventory, col + row * 9 + 9, 8 + col * 18, 122 + row * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 180));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}