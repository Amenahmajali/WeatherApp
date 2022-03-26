package com.kaizenplus.weatherapp.ui

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object View {

    const val DEBOUNCE_SEARCH_DELAY = 1000L


    inline fun EditText.doOnDebounceQueryChange(
        lifecycleOwner: LifecycleOwner,
        crossinline action: (query: String) -> Unit
    ) {
        var searchFor = ""
        doOnTextChanged { text, _, _, _ ->
            val searchText = text.toString().trim()
            if (searchText == searchFor)
                return@doOnTextChanged

            searchFor = searchText

            lifecycleOwner.lifecycleScope.launch {
                delay(DEBOUNCE_SEARCH_DELAY)
                if (searchText != searchFor)
                    return@launch
                action(text.toString())
            }
        }
    }
}