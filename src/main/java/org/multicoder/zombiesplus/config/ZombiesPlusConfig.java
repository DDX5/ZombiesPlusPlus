package org.multicoder.zombiesplus.config;

import net.neoforged.fml.loading.FMLLoader;
import org.multicoder.zombiesplus.ZombiesPlus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.Properties;

public class ZombiesPlusConfig
{
    public static boolean NIGHTMARE_MODE = false;
    public static boolean SKELETON_MODULE = false;
    public static boolean CREEPER_MODULE = false;
    public static String Version = "1.2.0";
    public static Properties Config;

    public static void FetchConfig()
    {
        ZombiesPlus.LOG.info("Fetching Config");
        String Dir = FMLLoader.getGamePath().toAbsolutePath() + "\\zombies-plus-plus.properties";
        File F = new File(Dir);
        Config = new Properties();
        if(F.exists())
        {
            ZombiesPlus.LOG.info("Config Found, Reading File");
            try
            {
                Config.load(new FileInputStream(F));
                if(!Objects.equals(Config.getProperty("Version"), Version))
                {
                    ZombiesPlus.LOG.warn("Old Config File Detected. Reading Old Config, Then Creating new");
                    NIGHTMARE_MODE = Boolean.parseBoolean(Objects.requireNonNullElse(Config.getProperty("Nightmare-Mode"),"false"));
                    SKELETON_MODULE = Boolean.parseBoolean(Objects.requireNonNullElse(Config.getProperty("Skeleton-Module"),"false"));
                    CREEPER_MODULE = Boolean.parseBoolean(Objects.requireNonNullElse(Config.getProperty("Creeper-Module"),"false"));
                    Migrate(F);
                }
                else
                {
                    NIGHTMARE_MODE = Boolean.parseBoolean(Objects.requireNonNullElse(Config.getProperty("Nightmare-Mode"),"false"));
                    SKELETON_MODULE = Boolean.parseBoolean(Objects.requireNonNullElse(Config.getProperty("Skeleton-Module"),"false"));
                    CREEPER_MODULE = Boolean.parseBoolean(Objects.requireNonNullElse(Config.getProperty("Creeper-Module"),"false"));
                }
            }
            catch (Exception e)
            {
                ZombiesPlus.LOG.error("Error reading config\nLoading Default Values",e);
            }
        }
        else
        {
            ZombiesPlus.LOG.info("Config Not Found, Creating File");
            try{
                Config.clear();
                Config.setProperty("Nightmare-Mode",Boolean.toString(NIGHTMARE_MODE));
                Config.setProperty("Skeleton-Module",Boolean.toString(SKELETON_MODULE));
                Config.setProperty("Creeper-Module",Boolean.toString(CREEPER_MODULE));
                Config.setProperty("Version", Version);
                Config.store(new FileOutputStream(F),"Zombies++ Config File");
            }
            catch (Exception e){
                ZombiesPlus.LOG.error("Error creating config",e);
            }
        }
    }
    public static void Migrate(File F)
    {
        try{
            Config.clear();
            Config.setProperty("Nightmare-Mode","false");
            Config.setProperty("Version",Version);
            Config.store(new FileOutputStream(F),"Zombies++ Config File");
        }
        catch (Exception e)
        {
            ZombiesPlus.LOG.error("Error Migrating Config",e);
            boolean Deleted = F.delete();
            ZombiesPlus.LOG.info("Has File Been Deleted {}",Deleted);
        }
    }
}
