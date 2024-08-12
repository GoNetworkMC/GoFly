package me.redth.gofly

import org.bukkit.plugin.java.JavaPlugin

class GoFly : JavaPlugin() {
    override fun onEnable() {
        instance = this
        Config.load()
        server.pluginManager.registerEvents(Core, this)
        getCommand("fly").executor = FlyCommand
    }

    companion object {
        lateinit var instance: GoFly
    }
}
