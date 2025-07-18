package com.kottland.pleaner.ui.features.addbook

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor
import com.kottland.pleaner.data.local.Book
import com.kottland.pleaner.data.local.Chapter
import com.kottland.pleaner.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    fun importBook(context: Context, uri: Uri) {
        viewModelScope.launch {
            val file = copyPdfToInternalStorage(context, uri)
            val pdfDocument = PdfDocument(PdfReader(file))
            val book = Book(
                title = file.name,
                author = "",
                filePath = file.absolutePath,
                totalPages = pdfDocument.numberOfPages
            )
            val chapters = mutableListOf<Chapter>()
            for (i in 1..pdfDocument.numberOfPages) {
                val text = PdfTextExtractor.getTextFromPage(pdfDocument.getPage(i))
                chapters.add(
                    Chapter(
                        bookId = 0,
                        title = "Chapter $i",
                        startPage = i,
                        endPage = i,
                        content = text
                    )
                )
            }
            pdfDocument.close()
            bookRepository.insertBook(book, chapters)
        }
    }

    private fun copyPdfToInternalStorage(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.filesDir, "book.pdf")
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        return file
    }
}
