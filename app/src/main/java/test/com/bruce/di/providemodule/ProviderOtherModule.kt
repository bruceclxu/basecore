package test.com.bruce.di.providemodule

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides

@Module
class ProviderOtherModule {
    @Provides
    fun provideMutableLiveData(): MutableLiveData<String> {
        return MutableLiveData()
    }
}