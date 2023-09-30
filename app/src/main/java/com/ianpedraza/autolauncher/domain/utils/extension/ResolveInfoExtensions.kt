/*
 * ResolveInfoExtensions.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 18/9/23 15:07
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.domain.utils.extension

import android.content.pm.ApplicationInfo
import android.content.pm.ResolveInfo

object ResolveInfoExtensions {

    val ResolveInfo.system: Boolean
        get() = activityInfo.applicationInfo.flags and
                (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
}
