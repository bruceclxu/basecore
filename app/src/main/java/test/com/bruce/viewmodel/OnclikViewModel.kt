package test.com.bruce.viewmodel

import androidx.databinding.ObservableField
import test.com.bruce.model.data.Onclick

/**
 * @author bruce
 *	@desc 处理数据V(MVVM 中的V),负责提供View中需要处理的数据
 *
 */
class OnclikViewModel(val onlick: Onclick) {
    /******data******/
    val info = ObservableField<String>("\"${onlick.who}点击了${onlick.count}次\"")

    /******binding******/
    fun click() {
        onlick.count++
        info.set("\"${onlick.who}点击了${onlick.count}次\"")
        
    }

}