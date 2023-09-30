/*
 * IconPacksMocks.kt
 * AutoLauncher Android
 * Created by ian.pedraza on 29/9/23 17:03
 * Copyright © 2023 AutoLauncher. All rights reserved.
 */

package com.ianpedraza.autolauncher.mocks

import com.ianpedraza.autolauncher.domain.model.iconpacks.IconPackModel

object IconPacksMocks {

    fun getIconPackModel() = IconPackModel(
        id = "3243dweed3-e394309me-3493u4m9-dksk9",
        packageName = "com.package.pack1",
        label = "pack label",
        activityName = "activity name"
    )

    fun getIconPacksList() = listOf(
        IconPackModel(
            id = "3243dweed3-e394309me-3493u4m9-dksk9",
            packageName = "com.package.pack1",
            label = "pack label",
            activityName = "activity name"
        ),
        IconPackModel(
            id = "3mo43999ed98-e394309me-3493u4m9-sm93999wds837",
            packageName = "com.package.pack2",
            label = "pack label",
            activityName = "activity name"
        ),
        IconPackModel(
            id = "eow948n8d9-e394309me-3493u4m9-dksk9",
            packageName = "com.package.pack3",
            label = "pack label",
            activityName = "activity name"
        ),
    )

}
