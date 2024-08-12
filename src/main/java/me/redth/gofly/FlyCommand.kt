package me.redth.gofly

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object FlyCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isNotEmpty() && args[0] == "reload") return reload(sender)
        if (sender !is Player) return false
        if (args.isNotEmpty() && args[0] == "setlobby") return setLobby(sender)

        if (!sender.hasPermission("gofly.fly")) {
            sender.sendMessage(Config.noPermission)
            return false
        }
        if (!Core.canFly(sender)) {
            sender.sendMessage(Config.notInLobby)
            return false
        }

        sender.sendMessage(if (Core.toggle(sender)) Config.enableMessage else Config.disableMessage)
        return true
    }

    private fun reload(sender: CommandSender): Boolean {
        if (!sender.hasPermission("gofly.reload")) {
            sender.sendMessage(Config.noPermission)
            return false
        }
        Config.reload()
        sender.sendMessage(Config.configReloaded)
        return true
    }

    private fun setLobby(player: Player): Boolean {
        if (!player.hasPermission("gofly.setlobby")) {
            player.sendMessage(Config.noPermission)
            return false
        }
        Config.setLobby(player.location)
        player.sendMessage(Config.lobbySet)
        return true
    }
}
