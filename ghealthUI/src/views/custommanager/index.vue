<template>
<div>
  <section class="content">
    <div class="row">
      <div class="col-xs-12">
        <div class="box box-primary">
          <!-- /.box-header -->
          <div class="box-body">
            <div id="user_table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
              <HeadBar :options="options" v-on:getparentpageList="getHeaderInfo" v-on:btnAddCustomer="btnAddCustomer"/>
              <div class="btn-group">
                <button class="btn btn-default" @click="openAdd()">{{$t("buttons.add")}}</button>
                <button class="btn btn-default" @click="openEdit()">{{$t("buttons.edit")}}</button>
              </div>
              <Datatable ref='datatable' :items="customers" :columns="columns" :totalCount="totalCount" v-on:getItem="getDataTableItem"/>
              <Pagination :totalpage="totalpage" :curPage="currentPage" :totalCount="totalCount" :pagesize="currentpageList" v-on:getPage="getCurrentPage" />
            </div>
          </div>
          <!-- /.box-body -->
        </div>
      </div>
      <!-- /.col -->
    </div>
    <!-- /.row -->
  </section>

</div>
</template>

<script>
import HeadBar from '../../components/headerbar'
import Pagination from '../../components/pagination'
import Datatable from '../../components/datatable'
import {
  selectCoutomAll
} from '@/api/custommanager'
export default {
  data() {
    return {
      customers: [],
      columns: [],
      userList:[],
      addUser: false,
      currentpageList: '10',
      currentPage: 1,
      totalCount: -1,
      options: {
        "searchbar": 1,
        "buttonbar": 1,
        "tips": this.$t("placeholders.searchEnter")
      },
      searchkey: "",
      errors:{},
      showlogicMessage:false,
      flag:false
    }
  },
  created: function() {
    this.getCustomers(this.searchkey)
  },
  methods: {
    async getHeaderInfo(page) {
      this.currentpageList = page.pageList;
      this.currentPage = 1;
      this.action = page.action;
      this.searchkey = page.searchkey;
      if (this.action === "search") {
        var res = await selectCoutomAll(this.currentPage, this.currentpageList, this.searchkey)
        this.customers = res.data.data.customerList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
      }
    },
    async btnAddCustomer(page) {
      // alert(123);
      // this.addUser = true;
    },
    getCurrentPage(page) {
      this.currentPage = page;
      this.getCustomers(this.searchkey)
    },
    getDataTableItem(){
      //this.$refs.datatable.deleteClass();
      this.flag = true;
      this.currentUser = user;
    },
    async getCustomers(paraName) {
      var res = await selectCoutomAll(this.currentPage, this.currentpageList, paraName)
      this.customers = res.data.data.customerList
      this.totalCount = res.data.data.totalCount
      this.columns = res.data.data.columns
    },
    cancelClick(){
      // this.$refs['formadd'].resetFields();
      // this.$refs.datatable.deleteClass();
      this.errors = {};
      this.showlogicMessage = false;
      this.formRoles = [];
      this.formInfoadd.organizationId ='';
      // this.flag = false;
      this.addUser = false;
    },
  },
  components: {
    HeadBar,
    Datatable,
    Pagination
  },
  computed: {
    totalpage() {
      return Math.ceil(this.totalCount / this.currentpageList) || 0
    }
  }
}
</script>

<style >
.ivu-modal-body {
    padding: 2px;
    font-size: 12px;
    line-height: 0.1;
}
.ivu-form-item {
    margin-bottom: 5px;
    vertical-align: top;
    zoom: 1;
}
.ivu-input {
    display: inline-block;
    width: 80%;
    height: 32px;
    line-height: 1.5;
    padding: 4px 7px;
    font-size: 12px;
    border: 1px solid #dddee1;
    border-radius: 4px;
    color: #495060;
    background-color: #fff;
    background-image: none;
    position: relative;
    cursor: text;
    -webkit-transition: border .2s ease-in-out,background .2s ease-in-out,-webkit-box-shadow .2s ease-in-out;
    transition: border .2s ease-in-out,background .2s ease-in-out,-webkit-box-shadow .2s ease-in-out;
    transition: border .2s ease-in-out,background .2s ease-in-out,box-shadow .2s ease-in-out;
    transition: border .2s ease-in-out,background .2s ease-in-out,box-shadow .2s ease-in-out,-webkit-box-shadow .2s ease-in-out;
}
.ivu-modal-header {
    border-bottom: 0px solid #e9eaec;
    padding: 14px 16px;
    line-height: 1;
}
.ivu-modal-footer {
    border-top: 0px solid #e9eaec;
    padding: 12px 18px 12px 18px;
    text-align: right;
}
</style>
