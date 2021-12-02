<template>
  <Main></Main>
</template>

<script>
import Main from './components/Main';

export default {
  name: 'App',
  mounted() {
    document.title = "b2w";
  },
  components: {
    Main
  }
};
</script>

