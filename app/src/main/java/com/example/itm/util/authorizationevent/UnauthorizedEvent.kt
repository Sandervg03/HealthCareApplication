package com.example.itm.util.authorizationevent

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object UnauthorizedEvent {

    private val _unauthorizedEvent = MutableSharedFlow<Unit>()
    val unauthorizedEvent = _unauthorizedEvent.asSharedFlow()

    suspend fun triggerUnauthorizedEvent() {
        _unauthorizedEvent.emit(Unit)
    }
}