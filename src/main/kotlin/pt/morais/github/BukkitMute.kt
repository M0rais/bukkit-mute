package pt.morais.github

import org.bukkit.plugin.java.JavaPlugin
import pt.morais.github.command.MuteCommand
import pt.morais.github.command.UnMuteCommand
import pt.morais.github.dao.PlayerMuteDao
import pt.morais.github.listener.PlayerChatEvent

class BukkitMute : JavaPlugin() {

    override fun onEnable() {
        val dao = PlayerMuteDao()
        getCommand("mute")!!.setExecutor(MuteCommand(dao))
        getCommand("unmute")!!.setExecutor(UnMuteCommand(dao))
        server.pluginManager.registerEvents(PlayerChatEvent(dao), this)
    }

}