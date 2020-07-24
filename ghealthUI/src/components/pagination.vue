<template>
<div class="row">
  <div class="col-sm-5">
    <div class="dataTables_info" id="user_table_info" role="status" aria-live="polite">
      {{$t("labels.pagefrom")}} {{infoBegin}} {{$t("labels.pageTo")}} {{infoEnd}} {{$t("labels.pageInfo")}} {{totalCount}} {{$t("labels.pageEnd")}}
    </div>
  </div>
  <div class="col-sm-7">
    <div class="dataTables_paginate paging_full_numbers" id="user_table_paginate">
      <ul class="pagination">
        <li class="paginate_button first" id="user_table_first">
          <a v-on:click="firstClick()" aria-controls="user_table" data-dt-idx="0" tabindex="0">{{$t("labels.firstPage")}}</a></li>
        <li class="paginate_button previous disabled" id="user_table_previous">
          <a v-on:click="prevClick()" aria-controls="user_table" data-dt-idx="1" tabindex="0">{{$t("labels.previousPage")}}</a></li>
        <li v-for="index in pages" v-bind:class="{ active: currentPage === index}">
          <a v-on:click="btnClick(index)">{{ index }}</a>
        </li>

        <li class="paginate_button next" id="user_table_next">
          <a v-on:click="nextClick()" aria-controls="user_table" data-dt-idx="4" tabindex="0">{{$t("labels.nextPage")}}</a></li>
        <li class="paginate_button last" id="user_table_last">
          <a v-on:click="lastClick()" aria-controls="user_table" data-dt-idx="5" tabindex="0">{{$t("labels.lastPage")}}</a></li>
      </ul>
      <div hidden>{{countControll}}{{curPageControll}}</div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  props: ['totalpage', 'curPage', 'totalCount', 'pagesize'],
  data(){
    return{
      currentPage:-1
    }
  },
  created:function(){
    this.currentPage = this.curPage;
  },
  methods: {
    firstClick() {
      this.currentPage = 1;
      $("#user_table_previous").addClass("disabled");
      if(this.totalpage !== 1){
        $("#user_table_next").removeClass("disabled");
      }     
      this.$emit("getPage", this.currentPage)
    },
    prevClick() {
      if (!$("#user_table_previous").hasClass("disabled")) {
        this.currentPage -= 1;
        if (this.currentPage === 1) {
          $("#user_table_previous").addClass("disabled");
        } else {
          $("#user_table_previous").removeClass("disabled");
        }

        $("#user_table_next").removeClass("disabled");
        this.$emit("getPage", this.currentPage)
     }
    },
    btnClick(index) {
      this.currentPage = index;
      if (this.currentPage === 1) {
        $("#user_table_previous").addClass("disabled");
      } else {
        $("#user_table_previous").removeClass("disabled");
      }
      if (this.currentPage === this.currentPage) {
        $("#user_table_next").addClass("disabled");
      } else {
        $("#user_table_next").removeClass("disabled");
      }
      this.$emit("getPage", this.currentPage)
    },
    nextClick() {
      if (!$("#user_table_next").hasClass("disabled")) {
        this.currentPage += 1;
        $("#user_table_previous").removeClass("disabled");

        if (this.currentPage === this.totalpage) {
          $("#user_table_next").addClass("disabled");
        } else {
          $("#user_table_next").removeClass("disabled");
        }
        this.$emit("getPage", this.currentPage)
      }
      
    },
    lastClick() {
      this.currentPage = this.totalpage;
      if(this.totalpage !== 1){
        $("#user_table_previous").removeClass("disabled");
      }    
      $("#user_table_next").addClass("disabled");
      this.$emit("getPage", this.currentPage)
    }
  },
  computed: {
    pages: function() {
      var pag = [];
      if(this.totalCount===0){
        return pag;
      }
      if (this.currentPage < 5) { //如果当前的激活的项 小于要显示的条数
        //总页数和要显示的条数那个大就显示多少条
        var i = Math.min(5, this.totalpage);
        while (i) {
          pag.unshift(i--);
        }
      } else { //当前页数大于显示页数了
        var middle = this.currentPage - Math.floor(5 / 2), //从哪里开始
          i = 5;
        if (middle > (this.totalpage - 5)) {
          middle = (this.totalpage - 5) + 1
        }
        while (i--) {
          pag.push(middle++);
        }
      }
      return pag
    },
    countControll: function(){
      if(this.totalCount===0){
        $("#user_table_previous").addClass("disabled");
        $("#user_table_next").addClass("disabled");
        $("#user_table_first").addClass("disabled");
        $("#user_table_last").addClass("disabled");
      }else{
        $("#user_table_first").removeClass("disabled");
        $("#user_table_last").removeClass("disabled");
      }
    },
    curPageControll:function(){
      if(this.totalpage !== 0){
        if(this.currentPage===1){
          $("#user_table_previous").addClass("disabled");
        }else{
          $("#user_table_previous").removeClass("disabled");
        }
        if(this.currentPage === this.totalpage){
          $("#user_table_next").addClass("disabled");
        }else{
          $("#user_table_next").removeClass("disabled");
        }
      }
      
    },
    infoBegin() {
      return this.totalCount===0?0:((this.currentPage - 1) * this.pagesize + 1)
    },
    infoEnd() {
      if (this.totalpage === this.currentPage) {
        
        if(this.totalCount % this.pagesize === 0){
          return this.totalCount===0?0:(Number(this.infoBegin) + Number(this.pagesize) - 1)
        }else{
          return this.totalCount===0?0:(Number(this.infoBegin) + Number(this.totalCount % this.pagesize) - 1)
        }
      } else {
        return this.totalCount===0?0:(Number(this.infoBegin) + Number(this.pagesize) - 1)
      }
    }
  }
}
</script>

<style scoped>
div.dataTables_filter {
  text-align: right;
}

div.dataTables_paginate {
  margin: 0;
  white-space: nowrap;
  text-align: right;
  height: 41px;
}

div.dataTables_paginate ul.pagination {
  margin: 2px 0;
  white-space: nowrap;
}
</style>
