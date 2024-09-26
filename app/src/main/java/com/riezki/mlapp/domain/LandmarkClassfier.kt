package com.riezki.mlapp.domain

import android.graphics.Bitmap

/**
 * @author riezkymaisyar
 */

interface LandmarkClassfier {
    fun classify(bitmap: Bitmap, rotation: Int) : List<Classification>
}