package com.example.myapplication.di


import com.example.myapplication.data.api.ApiHelper
import dagger.Component

@CustomActivityScope
@Component(modules = [ApiHelperModule::class])
interface ActivityComponent {

    /**
     * Injects required dependencies into the specified TvHomeViewModel.
     * @param TvHomeViewModel in which to inject the dependencies
     */
    fun apiHelper(): ApiHelper

}