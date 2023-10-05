/*
 * IconPack.kt
 * CardroidLauncher Android
 * Created by ian.pedraza on 20/9/23 11:19
 * Copyright © 2023 CardroidLauncher. All rights reserved.
 */

package com.cardroidlauncher.app.data.framework.iconpacks

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.cardroidlauncher.app.data.framework.iconpacks.manager.cache.CustomIconsDataCache
import com.cardroidlauncher.app.domain.model.iconpacks.CustomIcon
import com.cardroidlauncher.app.domain.utils.extension.DataTypesExtensions.value
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.util.Locale

internal class IconPack(
    val packageName: String,
    val label: String,
    val activityName: String
) {

    private var iconPackResources: Resources? = null
    private var loaded: Boolean = false
    private val packagesDrawables: MutableMap<String, MutableSet<String>> = mutableMapOf()
    private val backImages: MutableList<Bitmap> = mutableListOf()
    private var maskImage: Bitmap? = null
    private var frontImage: Bitmap? = null
    private var factor: Float = DEFAULT_FACTOR

    @SuppressLint("DiscouragedApi")
    fun load(context: Context) {
        val packageManager = context.packageManager

        try {
            iconPackResources = packageManager.getResourcesForApplication(packageName)
            iconPackResources?.let { resources ->
                val filterId = resources.getIdentifier("appfilter", "xml", packageName)
                val xmlPullParser: XmlPullParser? = if (filterId > 0) {
                    resources.getXml(filterId)
                } else {
                    try {
                        val filterStream = resources.assets.open("appfilter.xml")
                        val factory = XmlPullParserFactory.newInstance().apply {
                            isNamespaceAware = true
                        }
                        factory.newPullParser().apply {
                            setInput(filterStream, "utf-8")
                        }
                    } catch (e: Throwable) {
                        null
                    }
                }

                xmlPullParser?.let { parser ->
                    var eventType = parser.eventType
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            val attributeCount = parser.attributeCount
                            when (parser.name) {
                                "iconback" -> {
                                    for (i in 0 until attributeCount) {
                                        val attributeName = parser.getAttributeName(i)
                                        val attributeValue = parser.getAttributeValue(i)
                                        if (attributeName.startsWith("img")) {
                                            loadBitmap(attributeValue)?.let { backImages.add(it) }
                                        }
                                    }
                                }

                                "iconmask" -> {
                                    val attributeName = parser.getAttributeName(0)
                                    val attributeValue = parser.getAttributeValue(0)
                                    if (attributeCount > 0 && attributeName == "img1") {
                                        maskImage = loadBitmap(attributeValue)
                                    }
                                }

                                "iconupon" -> {
                                    val attributeName = parser.getAttributeName(0)
                                    val attributeValue = parser.getAttributeValue(0)
                                    if (attributeCount > 0 && attributeName == "img1") {
                                        frontImage = loadBitmap(attributeValue)
                                    }
                                }

                                "scale" -> {
                                    val attributeName = parser.getAttributeName(0)
                                    val attributeValue = parser.getAttributeValue(0)
                                    if (attributeCount > 0 && attributeName == "factor") {
                                        factor = attributeValue?.toFloatOrNull() ?: DEFAULT_FACTOR
                                    }
                                }

                                "item" -> {
                                    var componentName: String? = null
                                    var drawableName: String? = null

                                    for (i in 0 until attributeCount) {
                                        val attributeName = parser.getAttributeName(i)
                                        val attributeValue = parser.getAttributeValue(i)

                                        when (attributeName) {
                                            "component" -> {
                                                componentName = attributeValue
                                            }

                                            "drawable" -> {
                                                drawableName = attributeValue
                                            }
                                        }
                                    }

                                    if (componentName != null && drawableName != null) {
                                        if (!packagesDrawables.containsKey(componentName)) {
                                            packagesDrawables[componentName] = mutableSetOf()
                                        }
                                        packagesDrawables[componentName]?.add(drawableName)
                                    }
                                }
                            }
                        }
                        eventType = parser.next()
                    }
                }
                loaded = true
            }
        } catch (e: Throwable) {
            // Cannot load icon pack
        }
    }

    fun getAllIcons(context: Context): List<CustomIcon> {
        if (loaded.not()) load(context)
        return packagesDrawables
            .flatMap { it.value }
            .toSet()
            .mapNotNull { drawableName ->
                loadCustomIcon(drawableName)
            }
    }

    fun getSuggestedIcons(context: Context, appPackageName: String): List<CustomIcon> {
        if (loaded.not()) load(context)

        val packageManager = context.packageManager
        val launchIntent = packageManager.getLaunchIntentForPackage(appPackageName)
        val componentName: String? = launchIntent?.component?.toString()
        val drawableList = componentName?.let { getDrawableList(it) }

        if (!drawableList.isNullOrEmpty()) {
            return drawableList
        }

        if (!componentName.isNullOrEmpty()) {
            val start = componentName.indexOf("{") + 1
            val end = componentName.indexOf("}", start)
            if (end > start) {
                val newComponentName = componentName.substring(start, end)
                    .lowercase(Locale.getDefault())
                    .replace(".", "_")
                    .replace("/", "_")
                return getDrawableList(newComponentName)
            }
        }

        return emptyList()
    }

    private fun getDrawableList(componentName: String): List<CustomIcon> {
        return packagesDrawables[componentName]
            ?.mapNotNull { drawableName -> loadCustomIcon(drawableName) } ?: emptyList()
    }

    private fun createCustomIcon(
        drawableName: String,
        drawable: Drawable,
    ): CustomIcon = CustomIcon(
        drawable = drawable,
        drawableName = drawableName,
        packageName = packageName,
    )

    @SuppressLint("DiscouragedApi")
    private fun loadBitmap(drawableName: String): Bitmap? {
        try {
            if (drawableName.isBlank()) return null
            return iconPackResources?.let { resources ->
                val id = resources.getIdentifier(drawableName, "drawable", packageName)
                if (id > 0) {
                    val drawable = resources.getDrawable(id, null)
                    (drawable as? BitmapDrawable)?.bitmap
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            return null
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun loadCustomIcon(drawableName: String): CustomIcon? {
        try {
            if (drawableName.isBlank()) return null

            val id = CustomIcon.getName(packageName, drawableName)

            val cachedDrawable = CustomIconsDataCache.getCached(id)
            if (cachedDrawable != null) {
                return cachedDrawable
            }

            return iconPackResources?.let { resources ->
                val id = resources.getIdentifier(drawableName, "drawable", packageName)
                if (id > 0) {
                    resources.getDrawable(id, null)?.let { drawable ->
                        createCustomIcon(drawableName, drawable).also {
                            CustomIconsDataCache.saveCached(it)
                        }
                    }
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            return null
        }
    }

    companion object {
        private const val DEFAULT_FACTOR = 1.0f
    }

    /*
   fun getIconForPackage(
       context: Context,
       appPackageName: String,
       default: Bitmap? = null
   ): Bitmap? {
       if (loaded.not()) load(context)

       val packageManager = context.packageManager
       val launchIntent = packageManager.getLaunchIntentForPackage(appPackageName)
       var componentName: String? = null

       if (launchIntent != null) {
           componentName = launchIntent.component.toString()
       }

       var drawable = packagesDrawables[componentName]

       if (drawable != null) {
           return loadBitmap(drawable)
       } else {
           if (componentName != null && iconPackResources != null) {
               val start = componentName.indexOf("{") + 1
               val end = componentName.indexOf("}", start)
               if (end > start) {
                   drawable = componentName.substring(start, end)
                       .lowercase(Locale.getDefault())
                       .replace(".", "_")
                       .replace("/", "_")

                   if (iconPackResources!!.getIdentifier(drawable, "drawable", packageName) > 0) {
                       return loadBitmap(drawable)
                   }
               }
           }
       }

       return generateBitmap(appPackageName, default)
   }

   private fun generateBitmap(appPackageName: String, default: Bitmap?): Bitmap? {
       val earmark = "$packageName:$appPackageName"

       if (backImages.isEmpty()) return default
       val backImageId = Random.nextInt(backImages.size)
       val backImage = backImages[backImageId]
       val width = backImage.width
       val height = backImage.height

       val result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
       val canvas = Canvas(result).apply {
           drawBitmap(backImage, 0f, 0f, null)
       }

       if (default != null && (default.width > width || default.height > height)) {
           Bitmap.createScaledBitmap(
               default,
               (width * factor).toInt(),
               (height * factor).toInt(),
               false
           )
       }

       if (maskImage != null) {
           val mutableMask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
           val maskCanvas = Canvas(mutableMask).apply {
               drawBitmap(maskImage!!, 0f, 0f, Paint())
           }

           val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
               xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
           }

           canvas.apply {
               default?.let {
                   drawBitmap(
                       default,
                       (width - default.width) / 2f,
                       (height - default.height) / 2f,
                       null
                   )
               }

               drawBitmap(mutableMask, 0f, 0f, paint)
           }
           paint.xfermode = null
       } else {
           default?.let {
               canvas.drawBitmap(
                   default,
                   (width - default.width) / 2f,
                   (height - default.height) / 2f,
                   null
               )
           }
       }

       if (frontImage != null) {
           canvas.drawBitmap(frontImage!!, 0f, 0f, null)
       }

       return result
   }
   */
}
