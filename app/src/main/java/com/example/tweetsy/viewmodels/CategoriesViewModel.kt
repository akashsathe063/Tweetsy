package com.example.tweetsy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * view models are basically the model classes for our view
 * meaning we have a model class which is keep the data of the view
 * in android whenever configuration change our activity and fragment are recreate
 * so we want that the data stored inside them is intact.so our data for activity and fragment we can stored in view model
 * the view models remain active until our activity and fragment are complete destroyed
 */
@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: TweetRepository) :
    ViewModel() {
    val category: StateFlow<List<String>>
        get() = repository.categories

    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }
}