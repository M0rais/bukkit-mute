package pt.morais.github.command

import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import pt.morais.github.dao.PlayerMuteDao

class MuteCommand(private val dao: PlayerMuteDao) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (args.size < 2) {
            sender.sendMessage("§cWrong syntax! Use /mute {player} {minutes}")
            return false
        }

        val target = Bukkit.getPlayer(args[0]) ?: return false.also {
            sender.sendMessage("§cPlayer not found")
        }

        val minutes = if (StringUtils.isNumeric(args[1])) args[1].toLong() else return false.also {
            sender.sendMessage("§cThe value must be a number!")
        }

        dao.setMute(target.uniqueId, minutes)
        sender.sendMessage("§aPlayer muted for $minutes minutes.")

        return false
    }

}