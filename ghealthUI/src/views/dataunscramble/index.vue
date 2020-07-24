<template>
<div>
  <section class="content">
    <div class="row">
      <div class="col-xs-4">
        <div class="box box-primary">
          <CustomerList :columns="columns" :customers="customers" :totalCount="totalCount" :flag="flag" v-on:getItem="getSelectedItem" v-on:search="search" v-on:nextClick="nextClick" v-on:prevClick="prevClick" />
        </div>
      </div>

      <div class="col-xs-8">
        <div class="box box-primary">
          <div class="box-header with-border">
            <beaseInfo :customer="currentCustomer" />
          </div>
        </div>
        <div class="box box-primary">
          <div class="box-body ">
            <tabs ref="tabsControl" :spaceCapsule="spaceCapsule" :bloodData="bloodData" :urineData="urineData" :ultrasoundData="ultrasoundData" :wearableInfo="wearableInfo" :ecgData="ecgData" :customer="currentCustomer"/>
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
import CustomerList from '../components/leftCustomer'
import beaseInfo from '../components/customBaseInfo'
import tabs from '../components/tabsForData'
import config from '@/utils/config'

import {
  getCustomers,
  getReportByCustomerId,
  getEcgData,
  edit
} from '@/api/dataunscramble'

export default {
  name: 'dataunscramble',
  components: {
    beaseInfo,
    tabs,
    CustomerList
  },
  data() {
    return {
      customers: [],
      columns: [],
      totalCount: -1,
      currentCustomer: {},
      spinShow: false,
      wearableInfo: '',
      toxindetection: '',
      ecgData: [],
      flag: true,
      bloodData: '',
      urineData: '',
      ultrasoundData: '',
      spaceCapsule: '',
      isDisabled: true,
      archivesId: ''
    }
  },
  created: function() {
    this.ajaxGetCustomers(1, 10, '', '')
  },
  methods: {
    async ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime) {
      var res = await getCustomers(currentPage, currentpageList, customerNm, checkTime)
      this.customers = res.data.data.checkinInfos
      this.totalCount = res.data.data.totalCount
      this.columns = res.data.data.columns
      this.currentCustomer = ''
      this.initTabDatas()
    },
    getSelectedItem(item) {
      this.currentCustomer = item;
      this.initTabDatas()
      this.$refs.tabsControl.initShow();
      this.spinShow = true
      getEcgData(item.customerId).then((res) => {
        this.ecgData = res.data.data.ecgData
      })
      this.getBaseInfo(item);
    },
    async getBaseInfo(item) {
      var res = await getReportByCustomerId(item.customerId, item.customerLastCheckinDsp)
      setTimeout(() => {
        // console.log(JSON.stringify(res.data.data));
        this.archivesId = res.data.data.archivesId
        this.spinShow = false
        // 尿检 - TAB顺序1
        if (res.data.data.urineData !== undefined) {
          this.urineData = res.data.data.urineData;
          this.$refs.tabsControl.urineShow(true)
          this.isDisabled = false
        } else {
          this.$refs.tabsControl.urineShow(false)
        }
        // 血检 - TAB顺序2
        if (res.data.data.bloodData !== undefined) {
          this.bloodData = res.data.data.bloodData;
          this.$refs.tabsControl.bloodShow(true)
          this.isDisabled = false
        } else {
          this.$refs.tabsControl.bloodShow(false)
        }
        // 超声 - TAB顺序3
        if (res.data.data.ultrasoundData !== undefined) {
          this.ultrasoundData = res.data.data.ultrasoundData;
          this.$refs.tabsControl.ultrasoundShow(true)
          this.isDisabled = false
        } else {
          this.$refs.tabsControl.ultrasoundShow(false)
        }
        //太空舱 - TAB顺序4
        if (res.data.data.spacecapsuleData !== undefined) {
          this.spaceCapsule = res.data.data.spacecapsuleData;
          this.$refs.tabsControl.spaceCapsuleShow(true)
          this.isDisabled = false
        } else {
          this.$refs.tabsControl.spaceCapsuleShow(false)
        }
        // 穿戴式 - TAB顺序5
        if (res.data.data.wearableData !== undefined) {
          this.wearableInfo = res.data.data.wearableData;
          this.$refs.tabsControl.wearableShow(true)
          this.isDisabled = false
        } else {
          this.$refs.tabsControl.wearableShow(false)
        }
        // // 毒素 - TAB顺序5
        // if (res.data.data.toxindetection !== undefined) {
        //   this.toxindetection = res.data.data.toxindetection;
        //   this.$refs.tabsControl.toxinShow(true)
        // } else {
        //   this.$refs.tabsControl.toxinShow(false)
        // }
      }, config.loadingTime);
    },
    prevClick(currentPage, currentpageList, customerNm, checkTime) {
      this.spinShow = true
      this.$refs.tabsControl.initShow();
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    nextClick(currentPage, currentpageList, customerNm, checkTime) {
      this.$refs.tabsControl.initShow();
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    search(currentPage, currentpageList, customerNm, checkTime) {
      this.$refs.tabsControl.initShow();
      this.spinShow = true
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    async saveInput() {
      // alert(this.urineData.inspection_time);
      let res
      for (var i = 0; i < this.$refs.tabsControl.$children.length; i++) {
        if (this.$refs.tabsControl.$children[i].$el.className == "tab-pane active") {
          if (i == 0) {
            res = await edit(this.bloodData.detailId, this.archivesId, this.bloodData.inspection_time,
              this.bloodData.type, this.bloodData.blood_id, this.bloodData.detailAdvise)
          } else if (i == 1) {
            res = await edit(this.urineData.detailId, this.archivesId, this.urineData.inspection_time,
              this.urineData.type, this.urineData.urine_id, this.urineData.detailAdvise)
          } else if (i == 2) {
            res = await edit(this.ultrasoundData.detailId, this.archivesId, this.ultrasoundData.inspection_time,
              this.ultrasoundData.type, this.ultrasoundData.ultrasound_id, this.ultrasoundData.detailAdvise)
          } else if (i == 3) {
            res = await edit(this.spaceCapsule.detailId, this.archivesId, this.spaceCapsule.start_time,
              this.spaceCapsule.type, this.spaceCapsule.spacecapsule_id, this.spaceCapsule.detailAdvise)
          } else if (i == 4) {
            res = await edit(this.wearableInfo.detailId, this.archivesId, this.wearableInfo.startTime,
              this.wearableInfo.type, this.wearableInfo.heart_id, this.wearableInfo.detailAdvise)
          }
        }
      }
      if (!res.data.meta.success) {
        var errors = '<p>'+this.$t("messages.handleError")+'</p>';
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
    initTabDatas() {
      this.bloodData = '';
      this.urineData = '';
      this.ultrasoundData = '';
      this.wearableInfo = '';
      this.ecgData = '';
      this.spaceCapsule = '';
      this.isDisabled = true;
    }
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
