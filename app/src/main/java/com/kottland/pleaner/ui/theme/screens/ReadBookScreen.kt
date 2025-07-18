package com.kottland.pleaner.ui.theme.screens

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor
import java.io.File
import java.util.Locale

@Composable
fun ReadBookScreen() {
    val context = LocalContext.current
    val chaptersDir = File(context.filesDir, "chapters")
    val chapters = chaptersDir.listFiles()?.map { it.name } ?: emptyList()
    val highlightedText = remember { mutableStateOf("") }
    val tts = remember {
        TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.language = Locale.US
                    tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(utteranceId: String?) {}
                        override fun onDone(utteranceId: String?) {}
                        override fun onError(utteranceId: String?) {}
                        override fun onRangeStart(
                            utteranceId: String?,
                            start: Int,
                            end: Int,
                            frame: Int
                        ) {
                            highlightedText.value = utteranceId?.substring(start, end) ?: ""
                        }
                    })
                }
            }
        })
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(chapters) { chapter ->
            val file = File(chaptersDir, chapter)
            val pdfDocument = PdfDocument(PdfReader(file))
            val text = PdfTextExtractor.getTextFromPage(pdfDocument.getPage(1))
            pdfDocument.close()

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        // Open chapter
                    }
            ) {
                Text(
                    text = buildAnnotatedString {
                        val highlight = highlightedText.value
                        val startIndex = text.indexOf(highlight)
                        if (startIndex != -1) {
                            append(text.substring(0, startIndex))
                            withStyle(style = SpanStyle(backgroundColor = Color.Yellow)) {
                                append(highlight)
                            }
                            append(text.substring(startIndex + highlight.length))
                        } else {
                            append(text)
                        }
                    }
                )
                Button(onClick = {
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, text)
                }) {
                    Text(text = "Play")
                }
            }
        }
    }
}
