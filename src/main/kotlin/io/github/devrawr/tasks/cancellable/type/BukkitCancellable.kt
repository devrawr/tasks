package io.github.devrawr.tasks.cancellable.type

import io.github.devrawr.tasks.Tasks
import io.github.devrawr.tasks.cancellable.Cancellable
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class BukkitCancellable(private val id: Int) : Cancellable
{
    constructor(task: BukkitTask) : this(task.taskId)

    private val plugin: JavaPlugin = Tasks.plugin as JavaPlugin
    private val scheduler = plugin.server.scheduler

    override fun cancel()
    {
        this.scheduler.cancelTask(this.id)
    }
}