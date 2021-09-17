package com.mikepenz.storyblok.di

import com.mikepenz.storyblok.ui.StoryblokViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { StoryblokViewModel(get()) }
}
