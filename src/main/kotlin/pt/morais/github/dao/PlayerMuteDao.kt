package pt.morais.github.dao

import java.util.*
import java.util.concurrent.TimeUnit

class PlayerMuteDao {

    val mutes = mutableMapOf<UUID, Long>()

    fun getTimeByUUID(uuid: UUID) = mutes.get(uuid)!!

    fun isMuted(uuid: UUID) = mutes.containsKey(uuid)

    fun setMute(uuid: UUID, minutes: Long) {
        mutes[uuid] = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(minutes)
    }

    fun removeMute(uuid: UUID) {
        mutes.remove(uuid)
    }

}