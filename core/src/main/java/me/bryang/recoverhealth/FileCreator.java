package me.bryang.recoverhealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileCreator {

    private ConfigurationNode configurationNode;

    private final String fileName;
    private YamlConfigurationLoader configLoader;

    public FileCreator(String fileName){
        this.fileName = fileName;
        start();
    }

    public void start(){

        configLoader = YamlConfigurationLoader
                .builder()
                .path(Path.of(fileName + ".yml"))
                .build();

        try {
            configurationNode = configLoader.load();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void reload(){

        try {
            configurationNode = configLoader.load();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getString(@NotNull String path) {

        String text = configurationNode
                .node((Object) path.split("\\."))
                .getString();

        if (text == null) {
            return "Error: The path is null.";
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }


    public List<String> getStringList(@NotNull String path) {

        List<String> text;

        try {
             text = configurationNode
                     .node((Object) path.split("\\."))
                     .getList(String.class, new ArrayList<>());

        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

        if (text.isEmpty()) {
            return null;
        }

        text.replaceAll(message -> ChatColor.translateAlternateColorCodes('&', message));
        return text;
    }

    public int getInt(@NotNull String path) {

        return configurationNode.node((Object) path.split("\\.")).getInt(Integer.MIN_VALUE);
    }

    public Material getMaterial(@NotNull String materialName){

        Material material = Material.getMaterial(getString(materialName).toUpperCase());

        if (material == null){
            Bukkit.getLogger().info("Error: The material doesn't exist. Please check your path.");
            return null;
        }


        return material;
    }

    public ItemStack getItemStack(@NotNull String itemFormat){

        Material material = Material.getMaterial(getString(itemFormat + ".id").toUpperCase());

        if (material == null){
            return null;
        }

        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(getString("item.name"));

        List<String> lore;
        try {
            lore = configurationNode.node(itemFormat, "lore").getList(String.class, new ArrayList<>());
        } catch (Exception exception){
            lore = null;
        }

        if (lore != null) {
            itemMeta.setLore(lore);
        }

        itemStack.setItemMeta(itemMeta);


        return itemStack;
    }
}