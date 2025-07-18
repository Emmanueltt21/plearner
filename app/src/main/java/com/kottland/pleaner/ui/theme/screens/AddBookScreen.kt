package com.kottland.pleaner.ui.theme.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

@Composable
fun AddBookScreen() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                copyPdfToInternalStorage(context, uri)
                splitPdf(context)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/pdf"
            }
            launcher.launch(intent)
        }) {
            Text(text = "Import PDF")
        }
    }
}

private fun copyPdfToInternalStorage(context: Context, uri: Uri) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.filesDir, "imported.pdf")
    val outputStream = FileOutputStream(file)
    inputStream?.copyTo(outputStream)
}

private fun splitPdf(context: Context) {
    val inputFile = File(context.filesDir, "imported.pdf")
    val pdfDocument = PdfDocument(PdfReader(inputFile))
    val outputDir = File(context.filesDir, "chapters")
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }
    for (i in 1..pdfDocument.numberOfPages) {
        val outputFile = File(outputDir, "chapter_$i.pdf")
        val newPdfDocument = PdfDocument(PdfWriter(outputFile))
        pdfDocument.copyPagesTo(i, i, newPdfDocument)
        newPdfDocument.close()
    }
    pdfDocument.close()
}
