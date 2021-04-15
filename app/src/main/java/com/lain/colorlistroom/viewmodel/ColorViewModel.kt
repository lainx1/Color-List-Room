package com.lain.colorlistroom.viewmodel

import androidx.lifecycle.*
import com.lain.colorlistroom.entity.Color
import com.lain.colorlistroom.repository.ColorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColorViewModel(private val colorRepository: ColorRepository) : ViewModel() {

    private val _allColors = MutableLiveData<List<Color>>()
    val allColors : LiveData<List<Color>> get() = _allColors

    private val _onDelete = MutableLiveData<Boolean>()
    val onDelete : LiveData<Boolean> get() = _onDelete

    /**
     * Retrieve all colors
     */
    fun findAll(){
        viewModelScope.launch(Dispatchers.IO){
            val colors = colorRepository.findAll()
            colors.let {
                withContext(Dispatchers.Main){
                    _allColors.value = it
                }
            }
        }
    }

    fun insert(color: Color){
        viewModelScope.launch(Dispatchers.IO){
            colorRepository.insert(color = color)
        }
    }

    fun update(color: Color){
        viewModelScope.launch(Dispatchers.IO){
            colorRepository.update(color = color)
        }
    }

    fun delete(color: Color){
        viewModelScope.launch(Dispatchers.IO){
            colorRepository.delete(color = color)
            withContext(Dispatchers.Main){
                _onDelete.value = true
            }
        }
    }
}

class ColorViewModelFactory(private val colorRepository: ColorRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ColorViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ColorViewModel(colorRepository = colorRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}