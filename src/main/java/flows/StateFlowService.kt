package flows

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateFlowService {
    private val _stateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow


    fun receiveValue(value: String) {
        println("Emmiting $value")
        _stateFlow.value = value
        println("Emmited $value")
    }
}
