package flows

import kotlinx.coroutines.yield

class CommandProcessor {
    private val subscribers = mutableListOf<ProcessorCallback>()

    fun registerCallback(callback: ProcessorCallback) {
        subscribers.add(callback)
    }

    fun removeCallback(callback: ProcessorCallback) {
        callback.close()
        subscribers.remove(callback)
    }

    suspend fun acceptCommand(command: String) {
        subscribers.forEach {
            it.onCommand(command)
        }
        yield()
    }
}

interface ProcessorCallback {
    fun onCommand(command: String)
    fun close()
}
