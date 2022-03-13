package io.github.devrawr.tasks.cancellable.type

import io.github.devrawr.tasks.cancellable.Cancellable
import java.util.concurrent.Future

class JavaCancellable(
    private val future: Future<*>
) : Cancellable
{
    override fun cancel()
    {
        this.future.cancel(true)
    }
}