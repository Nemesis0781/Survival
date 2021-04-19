package fr.nemesis07.survival.backpack;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import fr.nemesis07.survival.utils.Base64;

public class Backpack {
  private final UUID uuid;
  
  private final Inventory inventory;
  
  public Backpack(UUID uuid) {
    this.uuid = uuid;
    this.inventory = Bukkit.createInventory(null, 9, "§6[§cBackpack§6]");
  }
  
  public Backpack(UUID uuid, String base64) throws IOException {
    this.uuid = uuid;
    this.inventory = Bukkit.createInventory(null, 9, "§6[§cBackpack§6]");
    this.inventory.setContents(Base64.itemStackArrayFromBase64(base64));
  }
  
  public UUID getUuid() {
    return this.uuid;
  }
  
  public Inventory getInventory() {
    return this.inventory;
  }
  
  public String toBase64() {
    return Base64.itemStackArrayToBase64(this.inventory.getContents());
  }
}

