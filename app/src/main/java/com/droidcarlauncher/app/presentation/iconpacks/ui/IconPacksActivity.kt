/*
 * IconPacksActivity.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 25/9/23 14:17
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.presentation.iconpacks.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.droidcarlauncher.app.domain.model.applications.AppModel
import com.droidcarlauncher.app.presentation.main.ui.FullScreenComponentActivity
import com.droidcarlauncher.app.presentation.main.ui.MainViewModel
import com.droidcarlauncher.app.presentation.main.ui.theme.DroidcarLauncherTheme
import com.droidcarlauncher.app.presentation.main.utils.Utils.parcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IconPacksActivity : FullScreenComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = intent.extras?.parcelable<AppModel>(EXTRA_APP_MODEL)

        setContent {
            val appearanceMode by viewModel.appearanceMode.collectAsState()

            DroidcarLauncherTheme(darkThemeMode = appearanceMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IconPacksMainScreen(app = app)
                }
            }
        }
    }

    companion object {

        const val PARAM_CUSTOM_ICON_NAME = "param-custom-icon-name"

        private const val EXTRA_APP_MODEL = "extra-app-model"

        fun getIntent(context: Context, app: AppModel): Intent {
            return Intent(context, IconPacksActivity::class.java).apply {
                putExtra(EXTRA_APP_MODEL, app)
            }
        }
    }

}
