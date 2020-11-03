package com.appwork.mvvmloginmodule.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    //This will have method as a parameter and perform on main thread
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main)
            .launch {
                work()
            }
}