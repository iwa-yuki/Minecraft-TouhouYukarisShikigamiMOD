package mods.touhou_yukari_core;

import java.io.*;

import javax.script.*;

import cpw.mods.fml.client.FMLFolderResourcePack;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityAIJavaScript extends EntityAIBase {

	protected EntityShikigami theShikigami;
	protected World theWorld;
	public boolean isEnable;
	
	ScriptEngineManager manager;
	ScriptEngine engine;
	Compilable compilableEngine;
	CompiledScript compiledScript;

	public EntityAIJavaScript(EntityShikigami shikigami, ResourceLocation resource)
	{
		this.theShikigami = shikigami;
		this.theWorld = shikigami.worldObj;
		this.isEnable = true;
		
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("js");
		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		bindings.put("theShikigami", this.theShikigami);
		bindings.put("theWorld", this.theWorld);
		
		compilableEngine = (Compilable)engine;
		compiledScript = null;
		
		try{
			String currentDir = new File(".").getAbsoluteFile().getParent();
			String scriptPath = currentDir+"/"+resource.getResourceDomain()+"/"+resource.getResourcePath();
			FMLLog.info("Load \"%s\".", scriptPath);
			
			FileInputStream is = new FileInputStream(scriptPath);
			InputStreamReader isReader = new InputStreamReader(is);
			
			compiledScript = compilableEngine.compile(isReader);
			
			compiledScript.eval();

			Invocable invocable = (Invocable)(compiledScript.getEngine());
			invocable.invokeFunction("OnInit");

			isReader.close();
		}
		catch(FileNotFoundException e) {
			this.isEnable = false;
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch(SecurityException e) {
			this.isEnable = false;
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch(IOException e) {
			this.isEnable = false;
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch (ScriptException e) {
			this.isEnable = false;
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			this.isEnable = false;
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean shouldExecute() {
		return isEnable;
	}
	
	@Override
    public void updateTask() {
		Invocable invocable = (Invocable)(compiledScript.getEngine());
		try {
			invocable.invokeFunction("OnUpdate");
		}
		catch (NoSuchMethodException e) {
			this.isEnable = false;
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch (ScriptException e) {
			this.isEnable = false;
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
	}
}
