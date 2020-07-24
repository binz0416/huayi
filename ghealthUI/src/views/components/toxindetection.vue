<template>
<div class="tab-pane" id="tab_6">
  <div id="editor">
    <textarea id="froala-editor"></textarea>
  </div>
</div>
</template>

<script>
export default {
  data(){
    return{
      toxindetectionData:''
    }
  },
  props: ['toxindetection'],
  methods: {
    setToxin() {
      $('textarea#froala-editor').froalaEditor('html.set', '');
      $('textarea#froala-editor').froalaEditor('html.set', this.toxindetectionData);
    }
  },
  mounted: function() {
    $('textarea#froala-editor').froalaEditor({
      theme: 'royal',
      toolbarButtons: ['fullscreen'],
      tableEditButtons:[]
    })
  },
  watch: {
   'toxindetection': function (n, o) {
    this.toxindetectionData = n
    this.setToxin()
   }
  },
}
</script>

<style scoped>
.tdTitle {
  text-align: center;
  font-weight: bold;
  padding: 3px;
  font-size: 12px;
  border: 1px solid #3C8DBC;
}

.tdText {
  text-align: left;
  width: 40%;
  padding: 3px 0px 3px 25px;
  border: 1px solid #3C8DBC;
}

.tdValue {
  text-align: left;
  padding: 3px 0px 3px 25px;
  border: 1px solid #3C8DBC;
}

.tbTitle {
  text-align: left;
  font-weight: bold;
  padding: 1px 1px 1px 10px;
  color: white;
  font-size: 14px;
  background-color: #3C8DBC;

}
</style>
