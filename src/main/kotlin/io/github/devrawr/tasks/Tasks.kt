package io.github.devrawr.tasks

import io.github.devrawr.tasks.async.BukkitAsyncTaskScheduler
import io.github.devrawr.tasks.async.JavaAsyncTaskScheduler
import io.github.devrawr.tasks.sync.BukkitSyncTaskScheduler
import org.bukkit.Bukkit

object Tasks
{
    var plugin: Any? = null

    init
    {
        try
        {
            Class.forName("org.bukkit.Bukkit")

            val pluginManager = Bukkit.getPluginManager()
            val plugins = pluginManager.plugins

            this.plugin = plugins.first()
        } catch (ignored: java.lang.Exception)
        {
            this.plugin = null
        }
    }

    fun sync(): TaskScheduler<*>
    {
        if (this.plugin == null)
        {
            throw IllegalStateException("sync() may only be called from a bukkit context")
            // I don't think there's a way to schedule tasks without halting the thread it's called from,
            // therefore, there is no non-bukkit implementation for this.
            // Bukkit, afaik, ticks every task in the main server loop, which makes it possible.
            // unfortunately, because we wouldn't be able to (easily) override methods and tick our tasks,
            // I don't think it's possible.
            // If anyone has some kind of workaround, of course, feel free to open a pull request.
        }

        return BukkitSyncTaskScheduler
    }

    fun async(): TaskScheduler<*>
    {
        return if (this.plugin != null)
        {
            BukkitAsyncTaskScheduler
        } else
        {
            JavaAsyncTaskScheduler
        }
    }
}