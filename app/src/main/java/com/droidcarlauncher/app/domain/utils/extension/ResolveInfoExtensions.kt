/*
 * ResolveInfoExtensions.kt
 * DroidcarLauncher Android
 * Created by ian.pedraza on 18/9/23 15:07
 * Copyright © 2023 DroidcarLauncher. All rights reserved.
 */

package com.droidcarlauncher.app.domain.utils.extension

import android.content.pm.ApplicationInfo
import android.content.pm.ResolveInfo

object ResolveInfoExtensions {

    val ResolveInfo.system: Boolean
        get() = activityInfo.applicationInfo.flags and
                (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
}
