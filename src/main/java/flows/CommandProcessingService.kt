package flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalCoroutinesApi::class)
class CommandProcessingService(
    commandProcessor: CommandProcessor,
    private val scope: CoroutineScope
) {

    private val consoleCommandsFlow = callbackFlow {
        val callback = object : ProcessorCallback {
            override fun onCommand(command: String) {
                offer(command)
            }

            override fun close() {
                this@callbackFlow.close()
            }
        }
        commandProcessor.registerCallback(callback)

        awaitClose { commandProcessor.removeCallback(callback) }
    }.buffer(capacity = 2, onBufferOverflow = BufferOverflow.DROP_LATEST)

    suspend fun startProcessingCommands() {
        scope.launch {
            consoleCommandsFlow
                .map { "Received command $it" }
                .collect {
                    delay(1000)
                    println(it)
                }
        }
    }
}
