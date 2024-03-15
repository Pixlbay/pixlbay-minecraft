package io.pixlbay.pixlbay;

import java.net.HttpURLConnection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * pixlbay java plugin
 */
public class Plugin extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("pixlbay");

  public void onEnable() {
    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
      @Override
      public void run() {
        getConfig().addDefault("API_URL", "https://api.pixlbay.io");
        getConfig().addDefault("API_KEY", "your-api-key");
        getConfig().options().copyDefaults(true);
        saveConfig();

        if (getConfig().getString("API_KEY").isEmpty() || getConfig().getString("API_KEY").equals("your-api-key")) {
          LOGGER.severe("API_KEY is not set. Please set your API_KEY in the config.yml file");
          return;
        }
        if (getConfig().getString("API_KEY").startsWith("PXB_S_") == false) {
          LOGGER.severe("Invalid API_KEY. Please check your API_KEY in the config.yml file.");
          return;
        }

        try {
          HttpURLConnection connection = (HttpURLConnection) new java.net.URL(
              getConfig().getString("API_URL", "https://api.pixlbay.io") + "/v1/monitoring/start")
              .openConnection();

          connection.setRequestMethod("POST");
          connection.setRequestProperty("Content-Type", "application/json");
          connection.setRequestProperty("Authorization", "Bearer " + getConfig().getString("API_KEY"));
          connection.setDoOutput(true);
          connection.getOutputStream().write("{}".getBytes());

          int responseCode = connection.getResponseCode();
          if (responseCode == 201) {
            LOGGER.info("Monitoring started");
          } else if (responseCode == 403) {
            LOGGER.severe("Invalid API key");
          } else if (responseCode == 409) {
            LOGGER.warning(
                "Conflict: Either the monitoring is already enabled, or the server is not monitored. Please check your server status in the dashboard.");
          } else {
            LOGGER.warning("Pixlbay Internal error (HTTP:" + responseCode + ")");
          }
        } catch (Exception e) {
          LOGGER.severe(e.getMessage());
        }
      }
    });
  }

  public void onDisable() {
    LOGGER.info("pixlbay disabled");
  }
}
