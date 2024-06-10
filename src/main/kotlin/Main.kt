import flows.CommandProcessingService
import flows.CommandProcessor
import flows.ProcessorCallback
import flows.StateFlowService
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

fun main(args: Array<String>) {
    val commandProcessor = CommandProcessor()
    val stateFlowService = StateFlowService()
    runBlocking {
        val service = CommandProcessingService(commandProcessor, this)
//        launch {
//            service.startProcessingCommands()
//        }
        commandProcessor.registerCallback(object : ProcessorCallback {
            override fun onCommand(command: String) {
                stateFlowService.receiveValue(command)
            }

            override fun close() {
            }
        })

        launch(Dispatchers.IO) {
            while (isActive) {
                commandProcessor.acceptCommand(readln())
            }
        }

        launch {
            delay(6000)
            stateFlowService.stateFlow
                .collect {
                    println(it)
                }
        }
        launch {
            delay(5000)
            println("Subscribing second one")
            stateFlowService.stateFlow
                .map { "Second command $it" }
                .collect {
                    println(it)
                }
        }
    }

//    try {
//        runBlocking {
//            while (this.isActive) {
//                val command = readln()
//
//                when (command) {
//                    "exit" -> this.cancel()
//                    else -> println("Received command $command")
//                }
//                println(this)
//            }
//        }
//    } catch (e: Exception) {
//        println("Im finished")
//    }
}
