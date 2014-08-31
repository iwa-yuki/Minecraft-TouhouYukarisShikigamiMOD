////////////////////////////////////////////////////////////////////////////////
// 紫の式神MOD

package mods.touhou_yukari_core;

import mods.touhou_alice_core.EntityAliceDoll;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * 紫の式神MOD コアクラス
 */
@Mod(
    modid = TouhouYukariCore.MODID,
    name = TouhouYukariCore.MODNAME,
    version = TouhouYukariCore.VERSION
    )
//@NetworkMod(
//    clientSideRequired = true,
//    serverSideRequired = false
//    )
public class TouhouYukariCore
{
	/** MODの識別子 */
    public static final String MODID = "touhou_yukari_core";
    /** MODの名前 */
    public static final String MODNAME = "Yukari's Core MOD";
    /** MODのバージョン */
    public static final String VERSION = "1.7.2-lma";

    /**
     * MODの唯一のインスタンス
     */
    @Instance("touhou_yukari_core")
    public static TouhouYukariCore instance;

    /**
     * サーバー・クライアントでの処理振り分け用プロキシ
     */
    @SidedProxy(
        clientSide = "mods.touhou_yukari_core.client.ClientProxy",
        serverSide = "mods.touhou_yukari_core.CommonProxy"
        )
    public static CommonProxy proxy;
    
    /**
     * 式神のEntityID
     */
	private int entityShikigamiID;

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

            this.entityShikigamiID = cfg.get(
                "entity", "EntityShikigamiID", 71).getInt();
        }
        catch (Exception e)
        {
            FMLLog.severe("%s Configuration load error!");
        }
        finally
        {
            cfg.save();
        }
        
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
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * アイテムの登録
     */
    private void registerItems()
    {
    }
    
    /**
     * エンティティの登録
     */
    private void registerEntities()
    {
        EntityRegistry.registerGlobalEntityID(
        		EntityShikigami.class, "Shikigami", this.entityShikigamiID, 0xFFFFFF, 0xFF33FF);
        EntityRegistry.registerModEntity(
                EntityShikigami.class, "Shikigami", this.entityShikigamiID,
                this, 128, 1, true);
    }

    /**
     * レシピの登録
     */
    private void registerRecipes()
    {
    }

}
