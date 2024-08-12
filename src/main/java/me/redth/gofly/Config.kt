package me.redth.gofly

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.Location

object Config {
    lateinit var lobbyLocation: Location
    var lobbyRadius: Int = 200
    lateinit var prefix: String
    lateinit var lobbySet: String
    lateinit var enableMessage: String
    lateinit var disableMessage: String
    lateinit var notInLobby: String
    lateinit var configReloaded: String
    lateinit var noPermission: String

    fun load() {
        GoFly.instance.saveDefaultConfig()
        val config = GoFly.instance.config

        lobbyLocation = config.get("lobby-location", Bukkit.getWorlds().first().spawnLocation) as Location
        lobbyRadius = config.getInt("lobby-radius")
        prefix = color(config.getString("prefix"))
        lobbySet = prefix + color(config.getString("lobby-set"))
        enableMessage = prefix + color(config.getString("enabled"))
        disableMessage = prefix + color(config.getString("disabled"))
        notInLobby = prefix + color(config.getString("not-in-lobby"))
        configReloaded = prefix + color(config.getString("reload"))
        noPermission = prefix + color(config.getString("no-permission"))
    }

    fun reload() {
        GoFly.instance.reloadConfig()
        load()
    }

    fun setLobby(location: Location) {
        val config = GoFly.instance.config
        lobbyLocation = location
        config.set("lobby-location", location)
        GoFly.instance.saveConfig()
    }

    private fun color(text: String): String =
        ChatColor.translateAlternateColorCodes('&', text)
}
