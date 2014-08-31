////////////////////////////////////////////////////////////////////////////////
// アリスの人形MOD

package mods.touhou_alice_core;

import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.FMLLog;

import java.util.logging.Level;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import mods.touhou_alice_core.dolls.*;
import mods.touhou_alice_core.AI.*;
import mods.touhou_alice_core.chunkloader.*;

/**
 * アリスの人形MOD コアクラス
 */
@Mod(
    modid = TouhouAliceCore.MODID,
    name = TouhouAliceCore.MODNAME,
    version = TouhouAliceCore.VERSION
    )
//@NetworkMod(
//    clientSideRequired = true,
//    serverSideRequired = false
//    )
public class TouhouAliceCore
{
	/** MODの識別子 */
    public static final String MODID = "touhou_alice_core";
    /** MODの名前 */
    public static final String MODNAME = "Alice's Core MOD";
    /** MODのバージョン */
    public static final String VERSION = "1.7.2-koi";

    /**
     * MODの唯一のインスタンス
     */
    @Instance("touhou_alice_core")
    public static TouhouAliceCore instance;

    /**
     * サーバー・クライアントでの処理振り分け用プロキシ
     */
    @SidedProxy(
        clientSide = "mods.touhou_alice_core.client.ClientProxy",
        serverSide = "mods.touhou_alice_core.CommonProxy"
        )
    public static CommonProxy proxy;

    /**
     * 初期化前処理
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // コンフィグ読み込み
        Configuration cfg = new Configuration(
            event.getSuggestedConfigurationFile());
        try
        {
            cfg.load();

            this.entityAliceDollID = cfg.get(
                "entity", "EntityAliceDollID", 68).getInt();
            this.itemDollCoreID = cfg.get(
                "item", "itemDollCoreID", 5000).getInt();
            this.itemAliceDollID = cfg.get(
                "item", "itemAliceDollID", 5001).getInt();
        }
        catch (Exception e)
        {
            FMLLog.severe("%s Configuration load error!");
        }
        finally
        {
            cfg.save();
        }

        // 素体人形の登録
        DollRegistry.addDoll(0, new DollBase());
        DollRegistry.addDoll(10, new DollShortBase());
        DollRegistry.addDoll(20, new DollTallBase());
        DollRegistry.addDoll(30, new DollGrandeBase());
        
        // アイテムの登録
        registerItems();
    }

    /**
     * 初期化処理
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // エンティティの登録
        registerEntities();

        // レンダラの登録
        proxy.registerRenderers();

        // レシピの登録
        registerRecipes();

        // チャンクローダーの初期化
        chunkloader = new DollChunkLoader(this);
        
        // 人形の初期化
        DollRegistry.initialize();
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * アイテムの登録
     */
    private void registerItems()
    {
        // ドールコア
        this.itemDollCore = new ItemDollCore();
    	GameData.getItemRegistry().addObject(itemDollCoreID, "dollcore", this.itemDollCore);

        // 人形
        this.itemAliceDoll = new ItemAliceDoll();
        GameData.getItemRegistry().addObject(itemAliceDollID, "alicedoll", this.itemAliceDoll);
    }
    
    /**
     * エンティティの登録
     */
    private void registerEntities()
    {
        EntityRegistry.registerGlobalEntityID(
            EntityAliceDoll.class, "AliceDoll", this.entityAliceDollID);
        EntityRegistry.registerModEntity(
            EntityAliceDoll.class, "AliceDoll", this.entityAliceDollID,
            this, 128, 2, true);
    }

    /**
     * レシピの登録
     */
    private void registerRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(this.itemDollCore),
                               "# $",
                               "#d$",
                               " # ",
                               '#', Items.redstone,
                               '$', new ItemStack(Items.dye, 1, 4),
                               'd', Items.diamond);
    }

    ////////////////////////////////////////////////////////////////////////////
    /**
     * 人形のエンティティID
     */
    private int entityAliceDollID;

    /**
     * ドールコアのアイテムID
     */
    private int itemDollCoreID;

    /**
     * 人形のアイテムID
     */
    private int itemAliceDollID;

    /**
     * ドールコアアイテム
     */
    public Item itemDollCore;

    /**
     * 人形アイテム
     */
    public Item itemAliceDoll;

    /**
     * チャンクローダー
     */
    public DollChunkLoader chunkloader;
}
