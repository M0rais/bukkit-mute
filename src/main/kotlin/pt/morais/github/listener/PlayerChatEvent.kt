package pt.morais.github.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import pt.morais.github.dao.PlayerMuteDao

class PlayerChatEvent(private val dao: PlayerMuteDao) : Listener {

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        val player = event.player

        if (!dao.isMuted(player.uniqueId)) return

        if (dao.getTimeByUUID(player.uniqueId) < System.currentTimeMillis()) {
            player.sendMessage("§aYou got unmuted, finally!")
            dao.removeMute(player.uniqueId)
        } else {
            player.sendMessage("§cYou're muted, to sad!")
            event.isCancelled = true
        }

    }

}