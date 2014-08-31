////////////////////////////////////////////////////////////////////////////////
// 紫の式神MOD

package mods.touhou_yukari_core.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import mods.touhou_alice_core.EntityAliceDoll;
import mods.touhou_yukari_core.*;

/**
 * クライアント側のみの処理を行うクラス
 */
public class ClientProxy extends CommonProxy
{
    /**
     * レンダラの登録
     */
    @Override
    public void registerRenderers()
    {
    	RenderingRegistry.registerEntityRenderingHandler(
            EntityShikigami.class, new RenderShikigami());
    }
}
