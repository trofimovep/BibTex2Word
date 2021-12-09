package com.textoword.api

import com.textoword.TextowordApplication
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(classes = [TextowordApplication::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TexToWordControllerTest(val mvc: MockMvc) {

    @Test
    fun `controller test`() {
        val body = """
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

        val result = """
            1. Shmyrin, A.M. and Mishachev, N. M. and Trofimov, E.P. A Note on Heuristic Analog of Cline’s Formula // Global Journal of Pure and Applied Mathematics 12.4, 2016, p. 3401--3405
        """.trimIndent()

        mvc.perform(post("/api/v1/transform").content(body))
                .andExpect(status().is2xxSuccessful)
                .andExpect(content().string(`is`(result)))
    }

}