package io.github.devrawr.tasks.type

import io.github.devrawr.tasks.TaskScheduler
import io.github.devrawr.tasks.Tasks
import io.github.devrawr.tasks.Task
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class BukkitTask(
    private val id: Int,
    override val context: TaskScheduler<*>
) : Task()
{
    constructor(task: BukkitTask, context: TaskScheduler<*>) : this(task.taskId, context)

    private val plugin: JavaPlugin = Tasks.plugin as JavaPlugin
    private val scheduler = plugin.server.scheduler

    override fun cancel()
    {
        this.scheduler.cancelTask(this.id)
    }
}