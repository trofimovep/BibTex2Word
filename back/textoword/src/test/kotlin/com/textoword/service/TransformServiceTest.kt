package com.textoword.service

import com.textoword.TextowordApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest(classes = [TextowordApplication::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TransformServiceTest(private val transformService: TransformService) {

    @Test
    fun `transform with invalid input text test`() {
        var result = transformService.transform("")
        assertEquals("Something went wrong!", result)

        result = transformService.transform("null")
        assertEquals("Something went wrong!", result)

        result = transformService.transform("...")
        assertEquals("Something went wrong!", result)

        result = transformService.transform("123qwe")
        assertEquals("Something went wrong!", result)
    }

    @Test
    fun `transform with invalid bibitem test`() {
        val bibtex = """
            @article{shmyrin2016note,
            	title={A Note on Heuristic Analog of Cline’s Formula},
            	author={Shmyrin, A.M. and Mishachev, N. M. and Trofimov, E.P.},
        """.trimIndent()
        val result = transformService.transform(bibtex)
        assertEquals("Please, enter the correct bibtex item!", result)
    }

    @Test
    fun `transform one book test`() {
        val bibtex = """
            @article{shmyrin2016note,
            	title={A Note on Heuristic Analog of Cline’s Formula},
            	author={Shmyrin, A.M. and Mishachev, N. M. and Trofimov, E.P.},
            	journal={Global Journal of Pure and Applied Mathematics},
            	volume={12},
            	number={4},
            	pages={3401--3405},
            	year={2016}
            }
        """.trimIndent()

        val word = """
            1. Shmyrin, A.M. and Mishachev, N. M. and Trofimov, E.P. A Note on Heuristic Analog of Cline’s Formula // Global Journal of Pure and Applied Mathematics 12.4, 2016, p. 3401--3405
        """.trimIndent()

        val result = transformService.transform(bibtex)
        assertEquals(word, result)
    }

    @Test
    fun `transform two book test`() {
        val bibtex = """
            @article{shmyrin2016note,
            	title={A Note on Heuristic Analog of Cline’s Formula},
            	author={Shmyrin, A.M. and Mishachev, N. M. and Trofimov, E.P.},
            	journal={Global Journal of Pure and Applied Mathematics},
            	volume={12},
            	number={4},
            	pages={3401--3405},
            	year={2016}
            }
            @ARTICLE{SMT-correction,
              author = {А.М. Шмырин, Н.М. Мишачев, Е.П. Трофимов},
              title = {Коррекция линейной окрестностной модели с учетом новых данных},
              journal = {Вестник Тамбовского университета},
              volume = {20},
              year = {2015},
              pages = {1544-1546},
              language = {russian},
              authorother = {true},
              authorconf = {true},
            }
        """.trimIndent()

        val word = """
            1. Shmyrin, A.M. and Mishachev, N. M. and Trofimov, E.P. A Note on Heuristic Analog of Cline’s Formula // Global Journal of Pure and Applied Mathematics 12.4, 2016, p. 3401--3405
           
            2. А.М. Шмырин, Н.М. Мишачев, Е.П. Трофимов Коррекция линейной окрестностной модели с учетом новых данных // Вестник Тамбовского университета 20., 2015, с. 1544-1546
        """.trimIndent()

        val result = transformService.transform(bibtex)
        assertEquals(word, result)
    }

}