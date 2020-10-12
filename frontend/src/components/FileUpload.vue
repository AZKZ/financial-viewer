<template>
  <div class="file-upload">
    <form method="post" action="">
      <input type="file" @change="setSelectedFile" accept="text/csv" />
      <input type="button" @click="uploadToS3" value="アップロード" />
    </form>
  </div>
</template>

<script lang="ts">
import { Amplify, Storage } from "aws-amplify";
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class FileUpload extends Vue {
  @Prop()
  private uploadFile?: File;

  /**
   * S3にファイルをアップロードする
   */
  public uploadToS3(): void {
    if (this.uploadFile == null) {
      alert("ファイルが選択されていません。");
      return;
    }

    const fileName = Date.now() + "_incomestatement.csv";

    Storage.put(fileName, this.uploadFile)
      .then((result) => alert("success"))
      .catch((error) => alert("error"));
  }

  /**
   * 選択したファイルをメンバ変数に設定
   * @param {Event} event - change event
   */
  public setSelectedFile(event: Event): void {
    const element = event.target as HTMLInputElement;
    const files = element.files;

    // nullの場合は処理しない;
    if (files == null) {
      return;
    }

    this.uploadFile = files[0];
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
