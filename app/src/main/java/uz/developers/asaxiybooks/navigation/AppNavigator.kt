package com.example.uzummarketclient.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections

interface AppNavigator {

    suspend fun navigateTo(directions: NavDirections)
    suspend fun navigateTo(@IdRes  directions: Int)
    suspend fun navigateTo(@IdRes  directions: Int,bundle: Bundle)

    suspend fun popBackStack()
}