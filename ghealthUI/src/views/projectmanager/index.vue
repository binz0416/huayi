<template>
<div>
  <section class="content">
    <div class="row">
      <div class="col-md-4">
        <div class="box box-primary">
          <CustomerList :columns="columns" :customers="customers" :totalCount="totalCount" :flag="flag" v-on:getItem="getSelectedItem" v-on:search="search" v-on:nextClick="nextClick" v-on:prevClick="prevClick"/>
        </div>
      </div>
      <div class="col-md-8">
        <div class="box box-primary">
          <div class="box-body">
            <beaseInfo  :customer="currentCustomer"/>
          </div>
        </div>
        <div class="box box-primary">
          <div class="box-body">
            <div class="box-body">
            <h4><strong>{{$t("Titles.mealInfo")}}</strong></h4>
            <div class="ivu-modal-body">
              <div v-if="showlogicMessage">
                <Alert type="error">{{errors.logic}}</Alert>
              </div>
              <Form ref="formedit" :model="formInfo" :label-width="100">
                <div class="col-md-11">
                  <FormItem v-bind:label='$t("labels.breakfast")' prop="breakfast" v-bind:error='errors.physicalTime'>
                    <Input v-model="formInfo.breakfast" v-bind:placeholder='$t("placeholders.differentTypes")'></Input>
                  </FormItem>
                  <FormItem v-bind:label='$t("labels.lunch")' prop="lunch" v-bind:error='errors.lunch'>
                    <Input v-model="formInfo.lunch" v-bind:placeholder='$t("placeholders.differentTypes")'></Input>
                  </FormItem>
                  <FormItem v-bind:label='$t("labels.dinner")' prop="dinner" v-bind:error='errors.dinner'>
                    <Input v-model="formInfo.dinner" v-bind:placeholder='$t("placeholders.differentTypes")'></Input>
                  </FormItem>
                  <FormItem v-bind:label='$t("labels.remark")' prop="memo" >
                    <Input v-model="formInfo.memo" v-bind:placeholder='$t("placeholders.remark")'></Input>
                  </FormItem>
                </div>
              </Form>
            </div>
            <div style="float:right;padding:0px 30px 5px 0px ">
              <button type="button" class="btn btn-primary" v-on:click="saveInput()"><i class="fa fa-save"></i> &nbsp;{{$t("buttons.save")}}</button>
            </div>
          </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <Spin fix v-if="spinShow" class="spin-backgroud">
      <Icon type="load-c " size=30 class="demo-spin-icon-load "></Icon>
      <div><h1>Loading</h1></div>
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
} from '@/api/projectmanager'

var customers;
export default {
  name: 'dataunscramble',
  components: {
    beaseInfo,
    CustomerList
  },
  data() {
    return {
      spinShow:false,
      customers: '',
      totalCount: -1,
      columns: [],
      currentCustomer: {},
      formInfo: {
        setMealId:'',
        customerId:'',
        breakfast:'',
        lunch: '',
        dinner: '',
        memo: ''
      },
      errors:{},
      showlogicMessage:false,
      flag:true
    }
  },
  methods: {
    async ajaxGetCustomers(currentPage, currentpageList, customerNm,checkTime) {
      var res = await getCustomers(currentPage, currentpageList, customerNm,checkTime)
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
        this.currentCustomer = item;
        this.formInfo.setMealId=res.data.data.customerMealInfos.setMealId;
        this.formInfo.customerId=item.customerId;
        this.formInfo.breakfast=res.data.data.customerMealInfos.breakfast;
        this.formInfo.lunch = res.data.data.customerMealInfos.lunch;
        this.formInfo.dinner = res.data.data.customerMealInfos.dinner;
        this.formInfo.memo = res.data.data.customerMealInfos.memo;
      }, config.loadingTime);
    },
    async saveInput() {
      let res = await saveReport(this.formInfo)
      if (!res.data.meta.success) {
        this.errors = res.data.data;
        if(this.errors.logic !== undefined && this.errors.logic !==''){
          this.showlogicMessage = true;
        }else{
          this.showlogicMessage = false;
        }
      } else {
        this.formInfo.setMealId=res.data.data.setMealId;
        this.showlogicMessage = false;
        this.$Modal.success({
          title: this.$t("messages.successTitle"),
          content: this.$t("messages.successMessage")
        });
      }
    },
    prevClick(currentPage, currentpageList, customerNm,checkTime) {
      alert(checkTime)
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm,checkTime)
      }, config.loadingTime);

    },
    nextClick(currentPage, currentpageList, customerNm,checkTime) {
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm,checkTime)
      }, config.loadingTime);

    },
    search(currentPage, currentpageList, customerNm,checkTime) {
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm,checkTime)
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
  opacity:0.8
}
</style>
