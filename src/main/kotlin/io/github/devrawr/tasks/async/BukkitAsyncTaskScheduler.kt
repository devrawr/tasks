package io.github.devrawr.tasks.async

import io.github.devrawr.tasks.TaskScheduler
import io.github.devrawr.tasks.Tasks
import io.github.devrawr.tasks.type.BukkitTask
import org.bukkit.plugin.java.JavaPlugin

object BukkitAsyncTaskScheduler : TaskScheduler<BukkitTask>
{
    private val plugin: JavaPlugin = Tasks.plugin as JavaPlugin
    private val scheduler = plugin.server.scheduler

    override fun repeating(delay: Long, period: Long, action: () -> Unit): BukkitTask
    {
        return BukkitTask(
            scheduler.scheduleAsyncRepeatingTask(this.plugin, action, delay, period),
            this
        )
    }

    override fun delay(delay: Long, action: () -> Unit): BukkitTask
    {
        return BukkitTask(
            scheduler.runTaskLaterAsynchronously(this.plugin, action, delay),
            this
        )
    }

    override fun call(action: () -> Unit): BukkitTask
    {
        return BukkitTask(
            scheduler.runTaskAsynchronously(this.plugin, action),
            this
        )
    }
}