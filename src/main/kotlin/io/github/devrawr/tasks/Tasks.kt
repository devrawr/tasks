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