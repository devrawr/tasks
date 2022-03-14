package io.github.devrawr.tasks.type

import io.github.devrawr.tasks.TaskScheduler
import io.github.devrawr.tasks.Task
import java.util.concurrent.Future

class JavaTask(
    private val future: Future<*>,
    override val context: TaskScheduler<*>
) : Task()
{
    override fun cancel()
    {
        this.future.cancel(true)
    }
}