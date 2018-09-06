package groentved.andreas.soitisnowsquat.inject.module

import dagger.Module
import dagger.Provides
import groentved.andreas.soitisnowsquat.domain.PositionHandler
import groentved.andreas.soitisnowsquat.inject.PerActivity

@Module
class ActivityModule {


    @PerActivity
    @Provides
    fun providesPositionHandler(): PositionHandler = PositionHandler()


}