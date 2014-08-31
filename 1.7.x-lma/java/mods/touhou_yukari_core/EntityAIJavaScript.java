package mods.touhou_yukari_core;

import java.io.*;
import javax.script.*;

import cpw.mods.fml.client.FMLFolderResourcePack;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.ResourceLocation;

public class EntityAIJavaScript extends EntityAIBase {

	protected EntityShikigami theShikigami;

	public EntityAIJavaScript(EntityShikigami shikigami, ResourceLocation resource)
	{
		this.theShikigami = shikigami;
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Compilable compilableEngine = (Compilable)engine;
		CompiledScript compiledScript = null;
		
		try{
			String currentDir = new File(".").getAbsoluteFile().getParent();
			String scriptPath = currentDir+"/"+resource.getResourceDomain()+"/"+resource.getResourcePath();
			FMLLog.info("Load \"%s\".", scriptPath);
			
			FileInputStream is = new FileInputStream(scriptPath);
			InputStreamReader isReader = new InputStreamReader(is);
			
			compiledScript = compilableEngine.compile(isReader);
			
			compiledScript.eval();

			isReader.close();
		}
		catch(FileNotFoundException e) {
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch(SecurityException e) {
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch(IOException e) {
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
		catch (ScriptException e) {
			FMLLog.warning(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		return false;
	}
}
