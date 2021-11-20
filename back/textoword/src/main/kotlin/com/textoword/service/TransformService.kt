package com.textoword.service

import com.google.common.base.Optional
import com.optimaize.langdetect.LanguageDetectorBuilder
import com.optimaize.langdetect.i18n.LdLocale
import com.optimaize.langdetect.ngram.NgramExtractors
import com.optimaize.langdetect.profiles.LanguageProfileReader
import org.jbibtex.BibTeXEntry
import org.jbibtex.BibTeXEntry.*
import org.jbibtex.BibTeXParser
import org.springframework.stereotype.Service
import java.io.StringReader


@Service
class TransformService {

    private val pageSymbols = mapOf("en" to "p.", "ru" to "с.")
    private val languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
            .withProfiles(LanguageProfileReader().readAllBuiltIn())
            .build()

    fun transform(bibItems: String): String =
            kotlin.runCatching { val parsed = BibTeXParser().parse(StringReader(bibItems))
                        .entries.map { it.value }.mapIndexed { index, bib -> "${index + 1}. ${bib.toWord()}" }
                        .joinToString("\n\n")
                return parsed.ifBlank { "Something went wrong!" }
            }.getOrElse { "Please, enter the correct bibtex item!" }


    private fun BibTeXEntry.toWord(): String {
        val fields = this.fields
        val authors = fields[KEY_AUTHOR]?.toUserString() ?: ""
        val title = fields[KEY_TITLE]?.toUserString() ?: ""
        val journal = fields[KEY_JOURNAL]?.toUserString() ?: ""
        val volume = fields[KEY_VOLUME]?.toUserString() ?: ""
        val number = fields[KEY_NUMBER]?.toUserString() ?: ""
        val year = fields[KEY_YEAR]?.toUserString() ?: ""
        val pages = fields[KEY_PAGES]?.toUserString() ?: ""
        val pageSymbol = pageSymbols[detectLang(title, authors, journal)]
        return "$authors $title // $journal $volume.$number, $year, $pageSymbol $pages"
    }

    private fun detectLang(title: String, authors: String, journal: String): String =
            languageDetector.detect(title)
                    .or(languageDetector.detect(authors))
                    .or(languageDetector.detect(journal))
                    .or(Optional.of(LdLocale.fromString("en")))
                    .get().language

}