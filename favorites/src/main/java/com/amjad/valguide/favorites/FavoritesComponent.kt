package com.amjad.valguide.favorites

import android.content.Context
import com.amjad.valguide.di.FavoritesModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoritesModuleDependencies::class])
interface FavoritesComponent {

    fun inject(activity: FavoritesActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoritesModuleDependencies: FavoritesModuleDependencies): Builder
        fun build(): FavoritesComponent
    }
}