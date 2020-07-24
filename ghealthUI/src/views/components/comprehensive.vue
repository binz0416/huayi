<template>
<div class="tab-pane" id="tab_7">
  <div id="editor">
    <textarea id="froala-editor-comp"></textarea>
  </div>
</div>
</template>

<script>
export default {
  data(){
    return{
      comprehensiveData:''
    }
  },
  props: ['comprehensive'],
  methods: {
    setComp() {
     $('textarea#froala-editor-comp').froalaEditor('html.set', '');
     $('textarea#froala-editor-comp').froalaEditor('html.set', this.comprehensiveData);
    }
  },
  mounted: function() {
    $('textarea#froala-editor-comp').froalaEditor({
      theme: 'royal',
      toolbarButtons: ['fullscreen'],
      tableEditButtons:[]
    })
  },
  watch: {
   'comprehensive': function (n, o) {
    this.comprehensiveData = n
    this.setComp()
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
