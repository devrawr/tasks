package io.github.devrawr.tasks

interface TaskScheduler<T : Task>
{
    /**
     * Run a repeating task
     *
     * @param delay the delay before starting the first execution
     * @param period the amount of time between execution
     * @param action the action to execute
     */
    fun repeating(delay: Long = 0L, period: Long, action: () -> Unit): T

    /**
     * Execute an action after a certain delay
     *
     * @param delay the amount of time it takes before executing the action
     * @param action the action to execute
     */
    fun delay(delay: Long, action: () -> Unit): T

    /**
     * Call in the context of the current scheduler
     *
     * @param action the action to execute
     */
    fun call(action: () -> Unit): T
}