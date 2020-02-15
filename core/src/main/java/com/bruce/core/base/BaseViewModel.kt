package com.bruce.core.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.bruce.core.BaseApplication
import com.bruce.core.network.APIService
/**
 * @Author bruce
 */
open class BaseViewModel : ViewModel() {

    var apiService: APIService = BaseApplication.coreComponent.apiService()

}