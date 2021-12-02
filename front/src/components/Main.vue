<template>
  <v-container class="tp-50">
    <v-row>
      <v-col>
        <v-card elevation="12" class="d-flex align-center justify-center">
          <div class="tex_container">
            <prism-editor class="bibtex-editor" v-model="bibItem" v-on:input="getTransformed" :highlight="highlighter"
                          line-numbers></prism-editor>
          </div>
        </v-card>
      </v-col>
      <v-col>
        <div class="word_container"> {{ word }}</div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {PrismEditor} from 'vue-prism-editor';
import 'vue-prism-editor/dist/prismeditor.min.css';
import {highlight, languages} from 'prismjs/components/prism-core';
import 'prismjs/components/prism-clike';
import 'prismjs/components/prism-latex';
import 'prismjs/themes/prism-tomorrow.css';
import 'prismjs-bibtex';
import axios from 'axios';


export default {
  name: "Main",
  components: {
    PrismEditor,
  },
  data: () => ({
    bibItem: "@inproceedings{shmyrin2018neighborhood,\n" +
        "\ttitle={Neighborhood systems priority identification and randomized Kaczmarz algorithm},\n" +
        "\tauthor={Shmyrin, AM and Mishachev, NM and Trofimov, EP},\n" +
        "\tbooktitle={2018 International Russian Automation Conference (RusAutoCon)},\n" +
        "\tpages={1--4},\n" +
        "\tyear={2018},\n" +
        "\torganization={IEEE}\n" +
        "}",
    word: "1. Shmyrin A. M., Mishachev N. M., Trofimov E. P. (2018) Neighborhood systems priority identification and " +
        "randomized Kaczmarz algorithm. Int. Russian Automation Conf. (RusAutoCon) (Sochi Russia), pp 1-4"
  }),
  methods: {
    highlighter(code) {
      return highlight(code, languages.bib)
    },
    getTransformed() {
      if (this.bibItem) {
        axios.post("http://localhost:8081/api/v1/transform", this.bibItem,
            {headers: {
                'Content-Type': 'text/html; charset=utf-8'
              }},
        ).then(response => this.word = response.data)
      }
    }
  },
}
</script>

<style scoped>
* {
  --max-elements-height: 80vh;
}

.tp-50 {
  margin-top: 50px;
}

.tex_container {
  max-height: var(--max-elements-height);
}

.bibtex-editor {
  max-height: var(--max-elements-height);
}

.word_container {
  max-height: var(--max-elements-height);
  overflow-y: auto;
  white-space: pre-line;
}

.bibtex-editor {
  /*background: #ccc;*/
  height: 100%;
  color: #ccc;
  font-family: Fira code, Fira Mono, Consolas, Menlo, Courier, monospace;
  font-size: 14px;
  line-height: 1.5;
  padding: 5px;
  border: none;
}

.prism-editor__textarea:focus {
  outline: none;
}
</style>