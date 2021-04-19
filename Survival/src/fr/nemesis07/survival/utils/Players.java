package fr.nemesis07.survival.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Players {
	
	private final File file;
	  
	  private final YamlConfiguration playersConfig;
	  
	  public Players() {
	    File dir = new File("./plugins/Survival");
	    if (!dir.exists())
	      dir.mkdirs(); 
	    this.file = new File(dir, "players.yml");
	    if (!this.file.exists())
	      try {
	        this.file.createNewFile();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }  
	    this.playersConfig = YamlConfiguration.loadConfiguration(this.file);
	  }
	  
	  public File getFile() {
	    return this.file;
	  }
	  
	  public YamlConfiguration getPlayersConfig() {
	    return this.playersConfig;
	  }
	  
	  public void save() {
	    try {
	      this.playersConfig.save(this.file);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	  }

}
