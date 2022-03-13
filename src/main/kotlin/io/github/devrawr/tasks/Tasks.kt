package io.github.devrawr.tasks

import io.github.devrawr.tasks.async.BukkitAsyncTask
import io.github.devrawr.tasks.async.JavaAsyncTask
import io.github.devrawr.tasks.sync.BukkitSyncTask
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

    fun sync(): Task<*>
    {
        if (this.plugin == null)
        {
            throw IllegalStateException("sync() may only be called from a bukkit instance")
        }

        return BukkitSyncTask
    }

    fun async(): Task<*>
    {
        return if (this.plugin != null)
        {
            BukkitAsyncTask
        } else
        {
            JavaAsyncTask
        }
    }
}