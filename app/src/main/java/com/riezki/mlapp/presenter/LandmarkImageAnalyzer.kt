package com.riezki.mlapp.presenter

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.riezki.mlapp.domain.Classification
import com.riezki.mlapp.domain.LandmarkClassfier

/**
 * @author riezkymaisyar
 */

class LandmarkImageAnalyzer(
    private val classifier: LandmarkClassfier,
    private val onResults: (List<Classification>) -> Unit
) : ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 60 == 0) {
            val rotationDegress = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(321, 321)

            val results = classifier.classify(bitmap, rotationDegress)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }
}