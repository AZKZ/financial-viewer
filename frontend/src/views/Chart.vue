<template>
  <div class="chart">
    <h1>Chart</h1>
    <file-upload></file-upload>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import FileUpload from '@/components/FileUpload.vue'; // @ is an alias to /src

@Component({
  components:{
    FileUpload
  },
})
export default class Chart extends Vue {}
</script>