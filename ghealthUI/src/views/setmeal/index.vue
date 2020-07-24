<template>
<div>
  <section class="content">
    <div class="row">
      <div class="col-md-4">
        <div class="box box-primary">
          <CustomerList :columns="columns" :customers="customers" :totalCount="totalCount" :flag="flag" v-on:getItem="getSelectedItem" v-on:search="search" v-on:nextClick="nextClick" v-on:prevClick="prevClick" />
        </div>
      </div>
      <div class="col-md-8">
        <div class="box box-primary">
          <div class="box-body">
            <beaseInfo :customer="currentCustomer" />
          </div>
        </div>
        <div class="box box-primary">
          <div class="box-body">
            <div class="box-body">
              <h4><strong>{{$t("Titles.setMeal")}}：</strong></h4>
              <div v-show="isShow" style="margin-to:20px">
                <table style="width:100%">
                  <tr>
                    <td style="padding:20px;width:10%">
                      <strong>{{$t("labels.breakfast")}}：</strong>
                    </td>
                    <td>
                      <strong> {{mealsData.breakfast}}</strong>
                    </td>
                    <td>
                      <button type="button" class="btn btn-primary" v-on:click="printMeal(mealsData.breakfast)"><i class="fa fa-print"></i> &nbsp;{{$t("buttons.print")}}</button>
                    </td>
                  </tr>
                  <tr>
                    <td style="padding:20px;width:10%">
                      <strong>  {{$t("labels.lunch")}}：</strong>
                    </td>
                    <td>
                      <strong> {{mealsData.lunch}}</strong>
                    </td>
                    <td>
                      <button type="button" class="btn btn-primary" v-on:click="printMeal(mealsData.lunch)"><i class="fa fa-print"></i> &nbsp;{{$t("buttons.print")}}</button>
                    </td>
                  </tr>
                  <tr>
                    <td style="padding:20px;width:10%">
                      <strong>  {{$t("labels.dinner")}}：  </strong>
                    </td>
                    <td>
                      <strong>{{mealsData.dinner}}</strong>
                    </td>
                    <td>
                      <button type="button" class="btn btn-primary" v-on:click="printMeal(mealsData.dinner)"><i class="fa fa-print"></i> &nbsp;{{$t("buttons.print")}}</button>
                    </td>
                  </tr>
                </table>
                <div id="printDiv" style="display:none">
                  <div id='page1'>

                  </div>
                  <object ID="jatoolsPrinter" CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255"
                  codebase="jatoolsPrinter.cab#version=8,6,0,0" width="0px" height="0px"></object>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <Spin fix v-if="spinShow" class="spin-backgroud">
    <Icon type="load-c " size=30 class="demo-spin-icon-load "></Icon>
    <div>
      <h1>Loading</h1></div>
  </Spin>
</div>
</template>

<script>
import CustomerList from '../components/leftCustomer'
import beaseInfo from '../components/customBaseInfo'
import config from '@/utils/config'

import {
  saveReport,
  getCustomers,
  getCustomerMealInfos
} from '@/api/setmeal'

var customers;
export default {
  name: 'dataunscramble',
  components: {
    beaseInfo,
    CustomerList
  },
  data() {
    return {
      spinShow: false,
      customers: '',
      totalCount: -1,
      columns: [],
      currentCustomer: {},
      mealsData: '',
      errors: {},
      showlogicMessage: false,
      flag: true,
      isShow: false
    }
  },
  methods: {
    async ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime) {
      var res = await getCustomers(currentPage, currentpageList, customerNm, checkTime)
      this.customers = res.data.data.checkinInfos
      this.totalCount = res.data.data.totalCount
      this.columns = res.data.data.columns
      this.currentCustomer = {}
      this.formInfo = {}
    },
    async getSelectedItem(item) {
      this.spinShow = true
      var res = await getCustomerMealInfos(item.customerId)
      setTimeout(() => {
        this.spinShow = false
        this.isShow = true;
        this.currentCustomer = item;
        this.mealsData = res.data.data.mealsData;
      }, config.loadingTime);
    },
    printMeal(printMealInfo) {
      //var arr = printMealInfo.split('，');
      $("#page1").empty();
        $("#page1").append(printMealInfo);
       //$("#mealDiv").printArea();
      var myDoc = {
        settings: {
          leftMargin:20,
          copies: 1
        },
          documents: document,
          copyrights: '杰创软件拥有版权  www.jatools.com'
      };

      document.getElementById("jatoolsPrinter").print(myDoc,false);
      //document.getElementById("jatoolsPrinter").printPreview(myDoc);
    },
    prevClick(currentPage, currentpageList, customerNm, checkTime) {
      alert(checkTime)
      this.spinShow = true
      this.isShow = false;
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    nextClick(currentPage, currentpageList, customerNm, checkTime) {
      this.spinShow = true
      this.isShow = false;
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    search(currentPage, currentpageList, customerNm, checkTime) {
      this.spinShow = true
      this.isShow = false;
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    }
  },
  created: function() {
    this.ajaxGetCustomers(1, 10, '', '')
  }
}
</script>


<style scoped>
.demo-spin-icon-load {
  animation: ani-demo-spin 1s linear infinite;
}

.spin-backgroud {
  /*background-color: rgba(0, 0, 0, 0);*/
  opacity: 0.8
}
</style>
