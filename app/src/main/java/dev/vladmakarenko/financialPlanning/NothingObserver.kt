package dev.vladmakarenko.financialPlanning

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NothingObserver(private val coroutineScope: CoroutineScope, private val observer: Observer) {
    interface Observer{
        fun doSomething()
    }

    fun nothingHappened(){
        coroutineScope.launch(IO) {
            observer.doSomething()
        }
    }
}