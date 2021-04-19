package fr.nemesis07.survival.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	private final File file;
	  
	  private final YamlConfiguration config;
	  
	  public Config() {
	    File dir = new File("./plugins/Survival");
	    if (!dir.exists())
	      dir.mkdirs(); 
	    this.file = new File(dir, "config.yml");
	    if (!this.file.exists())
	      try {
	        this.file.createNewFile();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }  
	    this.config = YamlConfiguration.loadConfiguration(this.file);
	  }
	  
	  public File getFile() {
	    return this.file;
	  }
	  
	  public YamlConfiguration getConfig() {
	    return this.config;
	  }
	  
	  public void save() {
	    try {
	      this.config.save(this.file);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	  }

}
