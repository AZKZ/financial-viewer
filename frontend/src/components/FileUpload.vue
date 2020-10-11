<template>
  <div class="file-upload">
    <form method="post" action="">
      <input type="file">
      <input type="submit">
    </form>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class FileUpload extends Vue {
  @Prop() private fileName!: string;
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
