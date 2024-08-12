package me.redth.gofly

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

object Core: Listener {
    @EventHandler
    fun onMove(event: PlayerMoveEvent) { // in spectator mode, teleport happen before setFly, that would make it cancel
        val player = event.player
        if (!player.flyingOrFlyable) return
        if (canFly(player)) return
        player.flyingOrFlyable = false
    }

    private var Player.flyingOrFlyable: Boolean
        get() = allowFlight || isFlying
        set(value) {
            allowFlight = value
            isFlying = value
        }

    fun canFly(player: Player): Boolean {
        return when (player.gameMode) {
            GameMode.CREATIVE, GameMode.SPECTATOR -> true
            else -> Config.lobbyLocation.distanceSquared(player.location) < Config.lobbyRadius * Config.lobbyRadius
        }
    }

    fun toggle(player: Player): Boolean {
        val new = !player.flyingOrFlyable
        player.flyingOrFlyable = new
        return new
    }
}