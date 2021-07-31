package com.romainbechard.saturdaytest.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MyRepository(
    private val api: MyApi
) {

    private val scope = CoroutineScope(Dispatchers.IO)

}