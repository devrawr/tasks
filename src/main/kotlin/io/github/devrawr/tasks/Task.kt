package io.github.devrawr.tasks

abstract class Task
{
    abstract val context: TaskScheduler<*>

    abstract fun cancel()

    /**
     * Cancel after a provided period of time
     *
     * @param period the amount of time to wait before
     *               cancelling the task
     * @return the current [Task] instance
     */
    open fun cancelAfter(period: Long): Task
    {
        return this.apply {
            this.context.delay(period) {
                this.cancel()
            }
        }
    }

    /**
     * Cancel if a statement is true
     *
     * @param bool the statement to check for
     * @return the current [Task] instance
     */
    open fun cancelIf(bool: Boolean): Task
    {
        return this.apply {
            if (bool)
            {
                this.cancel()
            }
        }
    }
}