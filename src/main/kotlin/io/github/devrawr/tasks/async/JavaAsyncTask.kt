package io.github.devrawr.tasks.async

import io.github.devrawr.tasks.Task
import io.github.devrawr.tasks.cancellable.type.JavaCancellable
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object JavaAsyncTask : Task<JavaCancellable>
{
    private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    override fun repeating(delay: Long, period: Long, action: () -> Unit): JavaCancellable
    {
        return JavaCancellable(
            this.executor.scheduleAtFixedRate(action, delay, period, TimeUnit.MILLISECONDS)
        )
    }

    override fun delay(delay: Long, action: () -> Unit): JavaCancellable
    {
        return JavaCancellable(
            this.executor.schedule(action, delay, TimeUnit.MILLISECONDS)
        )
    }

    override fun call(action: () -> Unit): JavaCancellable
    {
        return JavaCancellable(
            this.executor.submit(action)
        )
    }
}