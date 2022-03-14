package io.github.devrawr.tasks.async

import io.github.devrawr.tasks.TaskScheduler
import io.github.devrawr.tasks.type.JavaTask
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object JavaAsyncTaskScheduler : TaskScheduler<JavaTask>
{
    private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    override fun repeating(delay: Long, period: Long, action: () -> Unit): JavaTask
    {
        return JavaTask(
            this.executor.scheduleAtFixedRate(action, delay, period, TimeUnit.MILLISECONDS),
            this
        )
    }

    override fun delay(delay: Long, action: () -> Unit): JavaTask
    {
        return JavaTask(
            this.executor.schedule(action, delay, TimeUnit.MILLISECONDS),
            this
        )
    }

    override fun call(action: () -> Unit): JavaTask
    {
        return JavaTask(
            this.executor.submit(action),
            this
        )
    }
}