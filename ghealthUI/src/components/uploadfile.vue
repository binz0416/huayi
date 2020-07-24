<template>
  <div class="example-drag">
    <div class="upload">
      <ul v-if="files.length">
        <li v-for="(file, index) in files" :key="file.id">
          <span>{{file.name}}</span> -
          <span>{{file.size}}</span> -
          <span v-if="file.error">{{file.error}}</span>
          <span v-else-if="file.success">success</span>
          <span v-else-if="file.active">active</span>
          <span v-else></span>
          <a href="javascript:;" v-if="file.success" v-on:click="DownLoadFile(index)">下载</a>
          <a href="javascript:;" v-on:click="RemoveFile(index)">删除</a>
        </li>
      </ul>
      

      <div class="example-btn">
        <file-upload
          class="btn btn-primary"
          post-action="http://172.18.130.247:8080/ghealth/upload/post"
          :multiple="true"
          :drop="false"
          :drop-directory="false"
          v-model="files"
          ref="upload">
          <i class="fa fa-plus"></i>
          选择文件
        </file-upload>
        <button type="button" class="btn btn-success" v-if="!$refs.upload || !$refs.upload.active" @click.prevent="$refs.upload.active = true">
          <i class="fa fa-arrow-up" aria-hidden="true"></i>
          开始上传
        </button>
        <button type="button" class="btn btn-danger"  v-else @click.prevent="$refs.upload.active = false">
          <i class="fa fa-stop" aria-hidden="true"></i>
          中止上传
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import FileUpload from 'vue-upload-component'
export default {
  components: {
    FileUpload,
  },

  data() {
    return {
      files: [],
    }
  },
  methods:{
    RemoveFile(index){
      this.files.splice(index, 1);
    },
    DownLoadFile(index){
      alert('下载');
    }
  }
}
</script>
<style>
.example-drag label.btn {
  margin-bottom: 0;
  margin-right: 1rem;
}


.example-drag .drop-active {
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  position: fixed;
  z-index: 9999;
  opacity: .6;
  text-align: center;
  background: #000;
}

.example-drag .drop-active h3 {
  margin: -.5em 0 0;
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  font-size: 40px;
  color: #fff;
  padding: 0;
}
</style>