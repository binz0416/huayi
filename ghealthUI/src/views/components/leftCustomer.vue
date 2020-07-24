<template>
<div class="box-body">
  <div id="table_wrapper">
    <!-- 检索条件行开始 -->
    <div class="row">
      <div class="col-sm-8">
        <div class="dataTables_filter " id="searchDiv" style="">
          <div class="has-feedback form-group">
            <input type="search" v-bind:placeholder='$t("placeholders.searchEnter")' name="sendSubject" class="form-control input-sm" id="sendSubject" operator="like" likeoption="true" v-model="searchKey">
            <!--     <span class="glyphicon glyphicon-search form-control-feedback"></span> -->
          </div>
        </div>
      </div>
      <div class="col-sm-4" margin:20px>
        <button type="button" class="btn btn-primary" v-on:click="searchCustomer()"><i class="fa fa-refresh"></i> &nbsp;{{$t("buttons.search")}}</button>
      </div>
    </div>
    <div class="row" style="padding:0px 0px 10px 0px">
      <div class="col-sm-8">
        <DatePicker type="date" format="yyyy-MM-dd" @on-change="change" placeholder="入住时间"></DatePicker>
      </div>
      <div class="col-sm-4">
        <button v-if="print" class="btn" v-on:click="openModal()"><i class="fa fa-print"></i> &nbsp;打印</button></div>
    </div>

    <!--  -->


    <!-- 检索条件行结束 -->
    <!-- 数据显示开始 -->
    <!-- <div class="box box-primary"> -->
    <div class="row">
      <div class="col-sm-12">
        <table id="table" class="table table-bordered table-striped table-hover dataTable no-footer" style="width: 100%;" role="grid" aria-describedby="table_info">
          <thead>
            <tr role="row">
              <th class="text-center sorting_disabled" rowspan="1" colspan="1" v-for="column in columns" v-if="column.showflag">{{column.showname}}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item,index) in customers" role="row" v-bind:class="{selected:currentIndex===index}" v-on:click="rowClick(item,index)">
              <td class="text-center" v-for="key in columns" v-if="key.showflag">
                {{key.column==='index'?index+1:item[key.column]}}</td>
            </tr>
            <tr v-if="totalCount===0">
              <td colspan="100" class="emptyData">{{$t("messages.nodata")}}</td>
            </tr>
          </tbody>
        </table>
        <div id="fa-chevron-left" style="float:left">
          <ul class="pagination">
            <li class="paginate_button previous disabled" id="table_previous">
              <a v-on:click="prevClick()" data-dt-idx="1" tabindex="0"><i class="fa fa-chevron-left"></i></a></li>
          </ul>
        </div>
        <div id="fa-chevron-right" style="float:right">
          <ul class="pagination">
            <li class="paginate_button next" id="table_next">
              <a v-on:click="nextClick()" data-dt-idx="4" tabindex="0"><i class="fa fa-chevron-right"></i></a></li>
          </ul>
        </div>
      </div>
      <!-- </div> -->

    </div>
    <!-- 数据显示结束 -->
  </div>
</div>
</template>

<script>
export default {
  name: 'CustomerSearch',
  props: [
    'customers',
    'totalCount',
    'flag',
    'columns',
    'print'
  ],
  data() {
    return {
      totalpage:0,
      currentIndex: -1,
      searchKey: '',
      checkTime: '',
      pageList: '10',
      currentPage: 1,
      searchFlag: false
    }
  },
  // computed: {
  //   totalpage() {
  //     return Math.ceil(this.totalCount / this.pageList) || 0
  //   }
  // },
  methods: {
    rowClick(item, index) {
      this.searchFlag = false
      this.currentIndex = index
      this.$emit("getItem", item)
    },
    deleteClass() {
      this.currentIndex = -1;
    },
    change(date) {
      this.checkTime = date
    },
    editClass(id, action) {
      if (action === 'add') {
        $("#" + id).addClass("disabled");
      }
      if (action === 'remove') {
        $("#" + id).removeClass("disabled");
      }
    },
    prevClick() {
      this.deleteClass()
      this.searchFlag = false
      if (!$("#table_previous").hasClass("disabled")) {
        this.currentPage -= 1;
        if (this.currentPage === 1) {
          $("#table_previous").addClass("disabled");
        } else {
          $("#table_previous").removeClass("disabled");
        }
        $("#table_next").removeClass("disabled");
        //this.checkTime=;
        this.$emit("prevClick", this.currentPage, this.pageList, this.searchKey, this.checkTime)
      }
    },
    nextClick() {
      this.deleteClass()
      this.searchFlag = false
      if (!$("#table_next").hasClass("disabled")) {
        this.currentPage += 1;
        $("#table_previous").removeClass("disabled");
        if (this.currentPage === this.totalpage) {
          $("#table_next").addClass("disabled");
        } else {
          $("#table_next").removeClass("disabled");
        }
        this.$emit("nextClick", this.currentPage, this.pageList, this.searchKey, this.checkTime)
      }
    },
    searchCustomer() {
      this.currentPage = 1
      this.searchFlag = true
      $("#table_previous").addClass("disabled");
      this.deleteClass()
      this.$emit("search", this.currentPage, this.pageList, this.searchKey, this.checkTime)
    },
    initail() {
      $("#table_previous").addClass("disabled");
      if (this.totalpage == 1 || this.totalpage === 0) {
        $("#table_next").addClass("disabled");
      } else {
        $("#table_next").removeClass("disabled");
      }
      this.searchFlag = false
    },
    openModal(){
      this.$emit("preview")
      // var option = { mode: "popup", popClose: true };
      // $("#printReport").printArea(option);
    }
  },
  watch: {
    'searchFlag': function(n, o) {
      this.totalpage = Math.ceil(this.totalCount / this.pageList) || 0
      if (n === true) {
        this.initail();
      }

    },
    'totalCount':function(n){
      if (n === 0) {
        this.totalpage = 0
        this.initail();
      }else{
        this.totalpage = Math.ceil(this.totalCount / this.pageList) || 0
        this.initail();
      }
    }
  }
}
</script>

<style scoped>
.selected {
  background-color: #08C !important;
  color: white;
}

td.emptyData {
  color: red;
  padding-left: 30px;
  font-size: 20px;
  background-Color: #FFF;
  font-style: italic;
}
</style>
