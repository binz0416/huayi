<template>
<div>
  <section class="content noPrint">
    <div class="row">
      <div class="col-xs-4">
        <div class="box box-primary">
          <CustomerList ref="datatable" :print="1" :columns="columns" :customers="customers" :totalCount="totalCount" :flag="flag" v-on:getItem="getSelectedItem" v-on:search="search" v-on:nextClick="nextClick" v-on:prevClick="prevClick" v-on:preview="preview" />
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
            <tabs ref="tabsControl" :spaceCapsule="spaceCapsule" :bloodData="bloodData" :urineData="urineData" :ultrasoundData="ultrasoundData" :wearableInfo="wearableInfo" :ecgData="ecgData" :toxindetection="toxindetection" :comprehensive="comprehensiveData" :customer="currentCustomer"/>
          </div>
        </div>
      </div>
    </div>
  </section>
  <Spin fix v-if="spinShow" class="spin-backgroud">
    <Icon type="load-c" size=30 class="demo-spin-icon-load"></Icon>
    <div>
      <h1>Loading</h1></div>
  </Spin>
  <Modal v-model="previewFlag" width="900" :closable="false">
    <div slot="footer" class="noPrint">
      <Button type="text" size="large" @click="cancelClick()">取消</Button>
      <Button type="primary" size="large" @click="print()">打印</Button>
    </div>
    <div id="printReport">
      <h1>华医云健康体检报告</h1>
      </table>
      <a style="font-size:18px;color:#ff0000;">血常规</a>
      <physicalBlood /><br>
      <a style="font-size:18px;color:#ff0000;">尿常规</a>
      <physicalUrine /><br>
      <a style="font-size:18px;color:#ff0000;">B超</a>
      <physicalTypeB /><br>
    </div>
  </Modal>
</div>
</template>

<script>
import beaseInfo from '../components/customBaseInfo'
import CustomerList from '../components/leftCustomer'
import tabs from '../components/tabsControl'
import config from '@/utils/config'
import physicalBlood from '../components/physicalBlood'
import physicalUrine from '../components/physicalUrine'
import physicalTypeB from '../components/physicalTypeB'
import {
  getCustomerArchives,
  getCustomers,
  getEcgData,
  getReport
} from '@/api/archivesmanager'

export default {
  name: 'archivesmanager',
  components: {
    beaseInfo,
    tabs,
    CustomerList,
    physicalBlood,
    physicalUrine,
    physicalTypeB
  },
  data() {
    return {
      spinShow: false,
      currentCustomer: '',
      wearableInfo: '',
      spaceCapsule: '',
      ecgData: [],
      toxindetection: '',
      customers: [],
      columns: [],
      totalCount: -1,
      flag: true,
      previewFlag: false,
      bloodData: '',
      urineData: '',
      ultrasoundData: '',
      comprehensiveData:''
    }
  },
  created: async function() {
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
      this.initTabDatas()
      this.$refs.tabsControl.initShow()
      // init报告
      this.currentCustomer = item
      this.spinShow = true
      getEcgData(item.customerId).then((res) => {
        this.ecgData = res.data.data.ecgData
      })
      this.getBaseInfo(item);
    },
    async getBaseInfo(item) {
      // TAB信息: 常规，太空舱，毒素
      let res = await getCustomerArchives(item.customerId)
      setTimeout(() => {
        this.spinShow = false
        //基本信息
        this.currentCustomer = item;
        console.log(JSON.stringify(res.data.data));
        // 尿检 - TAB顺序1
        if (res.data.data.urineData !== undefined) {
          this.urineData = res.data.data.urineData;
          this.$refs.tabsControl.urineShow(true)
        } else {
          this.$refs.tabsControl.urineShow(false)
        }
        // 血检 - TAB顺序2
        if (res.data.data.bloodData !== undefined) {
          this.bloodData = res.data.data.bloodData;
          this.$refs.tabsControl.bloodShow(true)
        } else {
          this.$refs.tabsControl.bloodShow(false)
        }
        // 超声 - TAB顺序3
        if (res.data.data.ultrasoundData !== undefined) {
          this.ultrasoundData = res.data.data.ultrasoundData;
          this.$refs.tabsControl.ultrasoundShow(true)
        } else {
          this.$refs.tabsControl.ultrasoundShow(false)
        }
        //太空舱 - TAB顺序4
        if (res.data.data.spacecapsuleData !== undefined) {
          this.spaceCapsule = res.data.data.spacecapsuleData;
          this.$refs.tabsControl.spaceCapsuleShow(true)
        } else {
          this.$refs.tabsControl.spaceCapsuleShow(false)
        }
        // 穿戴式 - TAB顺序5
        if (res.data.data.wearableData !== undefined) {
          this.wearableInfo = res.data.data.wearableData;
          this.$refs.tabsControl.wearableShow(true)
        } else {
          this.$refs.tabsControl.wearableShow(false)
        }
        // 毒素 - TAB顺序6
        if (res.data.data.toxindetection !== undefined) {
          this.toxindetection = res.data.data.toxindetection;
          this.$refs.tabsControl.toxinShow(true)
        } else {
          this.$refs.tabsControl.toxinShow(false)
        }
        // 综合- TAB顺序7
        if (res.data.data.comprehensiveData !== undefined) {
          this.comprehensiveData = res.data.data.comprehensiveData;
          this.$refs.tabsControl.compShow(true)
        } else {
          this.$refs.tabsControl.compShow(false)
        }
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
      this.spinShow = true
      this.$refs.tabsControl.initShow();
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    search(currentPage, currentpageList, customerNm, checkTime) {
      this.spinShow = true
      this.$refs.tabsControl.initShow();
      setTimeout(() => {
        this.spinShow = false
        this.ajaxGetCustomers(currentPage, currentpageList, customerNm, checkTime)
      }, config.loadingTime);

    },
    preview() {
      // $('#printReport').slimScroll({
      //   height: '500px'
      // });
      // this.previewFlag = true;

      // var option = {
      //   mode: "popup",
      //   popClose: true
      // };
      // $("#printReport").printArea();
      if(this.currentCustomer.customerId!==""&& this.currentCustomer.customerId!=undefined){
        getReport(this.currentCustomer.customerId).then((res) => {
            if(res.data.meta.success===true){
                if(res.data.data.path===""){
                  this.$Modal.warning({
                    title: this.$t("messages.warningTitle"),
                    content: this.$t("messages.noReport")
                  });
                }else{
                 window.open(config.apiBaseURL + res.data.data.path)
                 // linux系统访问pdf路径
                  // window.open(config.apiBaseUrlForPDF + res.data.data.path)
                }
            }else{
              //todo
            }
        })
      }else{
        this.$Modal.warning({
            title: this.$t("messages.warningTitle"),
            content: this.$t("messages.printWarning")
        });
      }


    },
    initTabDatas() {
      this.bloodData = '';
      this.urineData = '';
      this.ultrasoundData = '';
      this.wearableInfo = '';
      this.ecgData = '';
      this.toxindetection = '';
      this.spaceCapsule = '';
      this.comprehensiveData='';
    },
    print() {
      // window.print()
      // var option = {
      //   mode: "popup",
      //   popClose: true
      // };
      $("#printReport").printArea();

      // var new_html = document.getElementById("printReport").innerHTML;
      // alert(new_html)
      // var new_page = window.open(new_html);
      // new_page.print();


      // var headstr = "<html><head><title></title></head><body>";
      // var footstr = "</body>";
      // var printData = document.getElementById("printReport").innerHTML;
      // var new_html = headstr+printData+footstr;
      // // alert(new_html)
      // // var new_page = window.open(new_html);
      // // new_page.print();
      //  var newWindow = window.open("_blank");
      // //要打印的div的内容

      // newWindow.document.write(printData);
      // //关闭文档
      // newWindow.document.close();
      // //调用打印机
      // newWindow.print();
      // //关闭newWindow页面
      // newWindow.close();
    },
    cancelClick() {
      this.previewFlag = false;
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
