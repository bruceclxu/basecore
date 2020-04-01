package test.com.bruce.di.providemodule

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides

@Module
class ProviderOtherModule {
    //这种情况其实没有必要，因为没有办法抽取公共工具类
    @Provides
    fun provideMutableLiveData(): MutableLiveData<String> {
        return MutableLiveData()
    }
}