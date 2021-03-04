package pt.morais.github.command

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import pt.morais.github.dao.PlayerMuteDao

class UnMuteCommand(private val dao: PlayerMuteDao) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (args.size < 1) {
            sender.sendMessage("§cWrong syntax! Use /unmute {player}")
            return false
        }

        val target = Bukkit.getPlayer(args[0]) ?: return false.also {
            sender.sendMessage("§cPlayer not found")
        }

        dao.removeMute(target.uniqueId)
        sender.sendMessage("§aPlayer unmuted!")

        return false
    }

}