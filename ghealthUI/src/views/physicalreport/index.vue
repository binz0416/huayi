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
          <beaseInfo :customer="currentCustomer" />
        </div>
        <div class="box box-primary">
          <div class="box-body">
            <h4><strong>{{$t("Titles.detailInfo")}}</strong></h4>
            <!-- Custom Tabs -->
            <br/>
            <!-- <h4><strong>{{$t("Titles.report")}}</strong></h4> -->
            <div class="nav-tabs-custom">
              <ul class="nav nav-tabs">
                <li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="true">{{$t("Titles.normalBlood")}}</a></li>
                <li class=""><a href="#tab_2" data-toggle="tab" aria-expanded="false">{{$t("Titles.normalUrine")}}</a></li>
                <li class=""><a href="#tab_3" data-toggle="tab" aria-expanded="false">{{$t("Titles.normalTypeB")}}</a></li>
              </ul>
              <div class="tab-content">
                <!-- tab1 -->
                <tab1 :bloodData="bloodData" :customer="currentCustomer" />
                <!-- tab1 -->
                <tab2 :urineData="urineData" />
                <!-- tab1 -->
                <tab3 :ultrasoundData="ultrasoundData" v-on:uploadReport="uploadReport"/>
              </div>
            </div>
            <div style="float:right;padding:0px 30px 5px 0px ">
              <button id="btnSave" v-bind:disabled="isDisabled" type="button" class="btn btn-primary" v-on:click="saveInput()"><i class="fa fa-save"></i> &nbsp;{{$t("buttons.save")}}</button>
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
import tab1 from '../physicalreport/physicalBloodEdit'
import tab2 from '../physicalreport/physicalUrineEdit'
import tab3 from '../physicalreport/physicalTypeBEdit'
import CustomerList from '../components/leftCustomer'
import beaseInfo from '../components/customBaseInfo'
import config from '@/utils/config'

import {
  saveReport,
  getCustomers,
  getReportByCustomerId,
  reportUpload
} from '@/api/physicalReport'

var customers;
var bloodData;
var urineData;
var ultrasoundData;
export default {
  name: 'dataunscramble',
  components: {
    beaseInfo,
    CustomerList,
    tab1,
    tab2,
    tab3
  },
  data() {
    return {
      spinShow: false,
      isDisabled: true,
      customers: '',
      totalCount: -1,
      columns: [],
      currentCustomer: {},
      flag: true,
      bloodData: {inspectionTime:''},
      urineData: {inspectionTime:''},
      ultrasoundData: {inspectionTime:''}
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
      var res = await getReportByCustomerId(item.customerId, item.checkintime)
      this.currentCustomer = item;

      setTimeout(() => {
        this.isDisabled = false;
        this.spinShow = false;
        //血检
        this.bloodData = res.data.data.bloodData;
        //尿检
        this.urineData = res.data.data.urineData;
        //超声检测
        this.ultrasoundData = res.data.data.ultrasoundData;
      }, config.loadingTime);
    },
    async saveInput() {
      const data = {
        bloodData: this.bloodData,
        urineData: this.urineData,
        ultrasoundData: this.ultrasoundData,
        customerId: this.currentCustomer.customerId
      }
      let res = await saveReport(data)
      if (!res.data.meta.success) {
        var errors = '<p>' + this.$t("messages.handleError") + '</p>';
        for (var i = 0; i < res.data.data.length; i++) {
          errors += '<p>' + (i + 1) + ' ' + res.data.data[i].error + '</p>'
        }
        this.$Modal.error({
          title: this.$t("messages.errorTitle"),
          content: errors
        });
      } else {
        this.$Modal.success({
          title: this.$t("messages.successTitle"),
          content: this.$t("messages.successMessage")
        });
      }
    },
    prevClick(currentPage, currentpageList, customerNm, checkTime) {
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    nextClick(currentPage, currentpageList, customerNm, checkTime) {
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    search(currentPage, currentpageList, customerNm, checkTime) {
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);
    },

    uploadReport(item) {
      if (item === undefined) {
        this.$Modal.warning({
          title: this.$t("messages.warningTitle"),
          content: this.$t("messages.fileUpload")
        });
      } else {
        this.spinShow = true
        var fd = new FormData();
        fd.append("uploadfile", item);
        reportUpload(fd).then((res) => {
          this.ultrasoundData.inspectionTime = res.data.data.report.inspectionTime;
          this.ultrasoundData.checkpoint = res.data.data.report.checkpoint;
          this.ultrasoundData.clinicalDiagnosis = res.data.data.report.clinicalDiagnosis;
          this.ultrasoundData.ulDescription = res.data.data.report.ulDescription;
          this.ultrasoundData.ulTip = res.data.data.report.ulTip;
          this.spinShow = false; 
        },(err) => {
          this.spinShow = false; 
          this.$Modal.warning({
          title: this.$t("messages.warningTitle"),
          content: this.$t("messages.octTimeout")
        });
        })

      }
    }
  },
  created: function() {
    $('#temp').css({
      "disabled": "disabled"
    });
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
