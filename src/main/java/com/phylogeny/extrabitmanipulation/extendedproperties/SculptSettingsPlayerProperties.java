package com.phylogeny.extrabitmanipulation.extendedproperties;

import com.phylogeny.extrabitmanipulation.ExtraBitManipulation;
import com.phylogeny.extrabitmanipulation.helper.ItemStackHelper;
import com.phylogeny.extrabitmanipulation.packet.PacketSyncAllSculptingData;
import com.phylogeny.extrabitmanipulation.reference.Configs;
import com.phylogeny.extrabitmanipulation.reference.NBTKeys;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SculptSettingsPlayerProperties implements IExtendedEntityProperties
{
	private static final String ID = "SculptSettingsPlayerProperties";
	public int mode, direction, shapeTypeCurved, shapeTypeFlat, sculptSemiDiameter, wallThickness;
	public boolean targetBitGridVertexes, sculptHollowShapeWire, sculptHollowShapeSpade, openEnds;
	public ItemStack setBitWire, setBitSpade;
	
	public void syncAllData(EntityPlayerMP player)
	{
		ExtraBitManipulation.packetNetwork.sendTo(new PacketSyncAllSculptingData(mode, direction, shapeTypeCurved,
				shapeTypeFlat, targetBitGridVertexes, sculptSemiDiameter, sculptHollowShapeWire,
				sculptHollowShapeSpade, openEnds, wallThickness, setBitWire, setBitSpade), player);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(NBTKeys.MODE, mode);
		nbt.setInteger(NBTKeys.DIRECTION, direction);
		nbt.setInteger(NBTKeys.SHAPE_TYPE_CURVED, shapeTypeCurved);
		nbt.setInteger(NBTKeys.SHAPE_TYPE_FLAT, shapeTypeFlat);
		nbt.setBoolean(NBTKeys.TARGET_BIT_GRID_VERTEXES, targetBitGridVertexes);
		nbt.setInteger(NBTKeys.SCULPT_SEMI_DIAMETER, sculptSemiDiameter);
		nbt.setBoolean(NBTKeys.SCULPT_HOLLOW_SHAPE_WIRE, sculptHollowShapeWire);
		nbt.setBoolean(NBTKeys.SCULPT_HOLLOW_SHAPE_SPADE, sculptHollowShapeSpade);
		nbt.setBoolean(NBTKeys.OPEN_ENDS, openEnds);
		nbt.setInteger(NBTKeys.WALL_THICKNESS, wallThickness);
		ItemStackHelper.saveStackToNBT(nbt, setBitWire, NBTKeys.SET_BIT_WIRE);
		ItemStackHelper.saveStackToNBT(nbt, setBitSpade, NBTKeys.SET_BIT_SPADE);
		compound.setTag(ID, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(ID);
		mode = nbt.getInteger(NBTKeys.MODE);
		direction = nbt.getInteger(NBTKeys.DIRECTION);
		shapeTypeCurved = nbt.getInteger(NBTKeys.SHAPE_TYPE_CURVED);
		shapeTypeFlat = nbt.getInteger(NBTKeys.SHAPE_TYPE_FLAT);
		targetBitGridVertexes = nbt.getBoolean(NBTKeys.TARGET_BIT_GRID_VERTEXES);
		sculptSemiDiameter = nbt.getInteger(NBTKeys.SCULPT_SEMI_DIAMETER);
		sculptHollowShapeWire = nbt.getBoolean(NBTKeys.SCULPT_HOLLOW_SHAPE_WIRE);
		sculptHollowShapeSpade = nbt.getBoolean(NBTKeys.SCULPT_HOLLOW_SHAPE_SPADE);
		openEnds = nbt.getBoolean(NBTKeys.OPEN_ENDS);
		wallThickness = nbt.getInteger(NBTKeys.WALL_THICKNESS);
		setBitWire = ItemStackHelper.loadStackFromNBT(nbt, NBTKeys.SET_BIT_WIRE);
		setBitSpade = ItemStackHelper.loadStackFromNBT(nbt, NBTKeys.SET_BIT_SPADE);
	}
	
	@Override
	public void init(Entity entity, World world)
	{
		mode = Configs.sculptMode.getDefaultValue();
		direction = Configs.sculptDirection.getDefaultValue();
		shapeTypeCurved = Configs.sculptShapeTypeCurved.getDefaultValue();
		shapeTypeFlat = Configs.sculptShapeTypeFlat.getDefaultValue();
		targetBitGridVertexes = Configs.sculptTargetBitGridVertexes.getDefaultValue();
		sculptSemiDiameter = Configs.sculptSemiDiameter.getDefaultValue();
		sculptHollowShapeWire = Configs.sculptHollowShapeWire.getDefaultValue();
		sculptHollowShapeSpade = Configs.sculptHollowShapeSpade.getDefaultValue();
		openEnds = Configs.sculptOpenEnds.getDefaultValue();
		wallThickness = Configs.sculptWallThickness.getDefaultValue();
		setBitWire = Configs.sculptSetBitWire.getDefaultValue();
		setBitSpade = Configs.sculptSetBitSpade.getDefaultValue();
	}
	
	public static SculptSettingsPlayerProperties get(Entity entity)
	{
		return (SculptSettingsPlayerProperties) entity.getExtendedProperties(ID);
	}
	
	public static void register(Entity entity)
	{
		entity.registerExtendedProperties(ID, new SculptSettingsPlayerProperties());
	}
	
}