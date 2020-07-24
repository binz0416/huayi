<template>
<div class="row">
  <div class="col-sm-12">
    <table id="table" class="table table-bordered table-striped table-hover dataTable no-footer" style="width: 100%;" role="grid" aria-describedby="table_info">
      <thead>
        <tr role="row">
          <th class="text-center sorting_disabled" rowspan="1" colspan="1" v-for="column in columns" v-if="column.showflag">{{column.showname}}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item,index) in items" role="row" v-bind:class="{selected:currentIndex===index}" v-on:click="rowClick(item,index)">
          <td class="text-center" v-for="key in columns" v-if="key.showflag">
          {{key.column==='index'?index+1:item[key.column]}}</td>
        </tr>
        <tr v-if="totalCount===0">
          <td colspan="100" class="emptyData">{{$t("messages.nodata")}}</td>
        </tr>
      </tbody>
    </table>

  </div>
</div>
</template>

<script>
export default {
  data(){
    return{
      currentIndex:-1
    }
  },
  props: [
    'items',
    'columns',
    'totalCount'
  ],
  methods:{
    rowClick(item,index){
      this.currentIndex = index
      this.$emit("getItem", item)
    },
    deleteClass(){
      this.currentIndex = -1;
    }
  }
}
</script>

<style scoped>
.selected{
  background-color : #08C !important;
  color:white;
}
td.emptyData{
  color:red;
  padding-left:30px;
  font-size:20px;
  background-Color:#FFF;
  font-style: italic;
}
</style>
