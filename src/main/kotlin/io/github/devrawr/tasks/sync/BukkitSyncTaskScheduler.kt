package io.github.devrawr.tasks.sync

import io.github.devrawr.tasks.TaskScheduler
import io.github.devrawr.tasks.Tasks
import io.github.devrawr.tasks.type.BukkitTask
import org.bukkit.plugin.java.JavaPlugin

object BukkitSyncTaskScheduler : TaskScheduler<BukkitTask>
{
    private val plugin: JavaPlugin = Tasks.plugin as JavaPlugin
    private val scheduler = plugin.server.scheduler

    override fun repeating(delay: Long, period: Long, action: () -> Unit): BukkitTask
    {
        return BukkitTask(
            scheduler.scheduleSyncRepeatingTask(this.plugin, action, delay, period),
            this
        )
    }

    override fun delay(delay: Long, action: () -> Unit): BukkitTask
    {
        return BukkitTask(
            scheduler.runTaskLater(this.plugin, action, delay),
            this
        )
    }

    override fun call(action: () -> Unit): BukkitTask
    {
        return BukkitTask(
            scheduler.runTask(this.plugin, action),
            this
        )
    }
}