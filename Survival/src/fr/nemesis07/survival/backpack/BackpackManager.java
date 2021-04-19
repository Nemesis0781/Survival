package fr.nemesis07.survival.backpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.nemesis07.survival.Main;
import fr.nemesis07.survival.utils.Config;

public class BackpackManager {
  private final Map<UUID, Backpack> map;
  
  public BackpackManager() {
    this.map = new HashMap<>();
    load();
  }
  
  public Backpack getBackpack(UUID uuid) {
    if (this.map.containsKey(uuid))
      return this.map.get(uuid); 
    Backpack backpack = new Backpack(uuid);
    this.map.put(uuid, backpack);
    return backpack;
  }
  
  public void setBackpack(UUID uuid, Backpack backpack) {
    this.map.put(uuid, backpack);
  }
  
  private void load() {
    Config config = Main.getInstance().getConfiguration();
    List<String> uuids = config.getConfig().getStringList("backpacks");
    uuids.forEach(s -> {
          UUID uuid = UUID.fromString(s);
          String base64 = config.getConfig().getString("backpack." + s);
          try {
            this.map.put(uuid, new Backpack(uuid, base64));
          } catch (IOException e) {
            e.printStackTrace();
          } 
        });
  }
  
  public void save() {
    Config config = Main.getInstance().getConfiguration();
    List<String> uuids = new ArrayList<>();
    for (UUID uuid : this.map.keySet())
      uuids.add(uuid.toString()); 
    config.getConfig().set("backpacks", uuids);
    this.map.forEach((uuid, backpack) -> config.getConfig().set("backpack." + uuid.toString(), backpack.toBase64()));
  }
}
