<template>
<div>
  <section class="content">
    <div class="row">
      <div class="col-md-4">
        <div class="box box-primary">
          <CustomerList :columns="columns" :customers="customers" :totalCount="totalCount" :flag="flag" v-on:getItem="getSelectedItem" v-on:search="search" v-on:nextClick="nextClick" v-on:prevClick="prevClick"/>
        </div>
        <button type="button" class="btn btn-primary pull-right" v-on:click="saveInput()"><i class="fa fa-save"></i> &nbsp;{{$t("buttons.save")}}</button>
      </div>
      <div class="col-md-8">
        <div id="editor">
          <textarea id="froala-editor"></textarea>

        </div>

      </div>
    </div>
  </section>
  <Spin fix v-if="spinShow " class="spin-backgroud">
      <Icon type="load-c " size=30 class="demo-spin-icon-load"></Icon>
      <div><h1>Loading</h1></div>
    </Spin>
</div>
</template>

<script>
import UploadFile from '../../components/uploadfile'
import CustomerList from '../components/leftCustomer'
import config from '@/utils/config'
import {
  saveReport,
  getCustomers,
  getToxinReport
} from '@/api/toxindetection'

var rsltModule = '<table class="method" width="90%"><tr><td colspan="2" class="tbTitle"></td></tr>' +
  '<tr><td class="tdTitle">Sample Type样本类型</td><td class="tdTitle">Xenobiotics Detected毒素检测</td></tr>' +
  '<tr><td class="tdText"><strong></strong></td><td class="tdValue"><strong></br></strong></td></tr>' +
  '<tr><td class="tdText"><strong></strong></td><td class="tdValue"><strong></br></strong></td></tr>  </table>';

var ExtraordinaryModule = '<table class="abnormal" width="90%"><tr><td class="tbTitle_Extraordinary">异常项</td></tr>' +
  '<tr><td class="tdText"><strong></strong></td></tr>' +
  '<tr><td class="tdText"><strong></strong></td></tr></table>';

var SuggestModule = '<div class="suggest"><h4><strong>专家治疗建议：</strong></h4><p><br /></p></div><div class="food"><h4><strong>适宜食物：</strong></h4><p><br /></p></div>';


export default {
  components: {
    UploadFile,
    CustomerList
  },
  data() {
    return {
      customers: [],
      totalCount: -1,
      columns: [],
      currentCustomer: {},
      flag:true,
      spinShow:false,
      // returnData:{toxinDetected:'',toxinAbnormal:'',toxinSuggest:'',toxinFood:''},
      toxinDetected:[],
      toxinAbnormal:[],
      detectData:{method:'',sampleType:'',xenobiotics_detected:''},
      abnormalData:{result:''},
      tempSuggest:'专家治疗建议：',
      tempFood:'适宜食物：',
      toxinSuggest:'',
      toxinFood:''
    }
  },
  methods: {
    async ajaxGetCustomers(currentPage, currentpageList, customerNm,checkTime) {
      let res = await getCustomers(currentPage, currentpageList, customerNm,checkTime)
      this.customers = res.data.data.checkinInfos
      this.totalCount = res.data.data.totalCount
      this.columns = res.data.data.columns
      $('textarea#froala-editor').froalaEditor('html.set', '');
    },
    prevClick(currentPage, currentpageList, customerNm,checkTime) {
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

    },
    async getSelectedItem(item) {
      this.spinShow = true
      this.currentCustomer = item;
      let res = await getToxinReport(item.customerId)
      setTimeout(() => {
        this.spinShow = false
        $('textarea#froala-editor').froalaEditor('html.set', res.data.data.reportArea);
      }, config.loadingTime);


    },
    async saveInput() {
      this.getToxinDetailData();
      let data = {
        customerId: this.currentCustomer.customerId,
        path: 'test.pdf',
        reportArea: $('textarea#froala-editor').froalaEditor('html.get'),
        toxinDetected:this.toxinDetected,
        toxinAbnormal:this.toxinAbnormal,
        suggest:this.toxinSuggest,
        food:this.toxinFood
      }

      let res = await saveReport(data)
      if (!res.data.meta.success) {
        console.log(JSON.stringify(res.data.data))
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
    getToxinDetailData(){
      this.toxinDetected = [];
      this.toxinAbnormal = [];
      // this.returnData={toxinDetected:'',toxinAbnormal:'',toxinSuggest:'',toxinFood:''};
      let htmstr = $('textarea#froala-editor').froalaEditor('html.get')
      // alert(htmstr);
      this.toxinSuggest = '';
      this.toxinFood = '';
      for(var i=0;i<$(htmstr).length;i++){
        var element= $(htmstr).get(i);
        if(element.localName==='table'){
          if(element.className==='method'){
            if(element.childNodes.length===1){
              var tbodyNode = element.childNodes[0];
              var method = '';
              for(var j=0;j<tbodyNode.childNodes.length;j++){
                var trNode = tbodyNode.childNodes[j];
                if(j===0){
                  method = trNode.childNodes[0].innerText;
                }else if(j===1){
                  continue;
                }else{
                  if(trNode.childNodes.length>1){
                    this.detectData.method = method;
                    this.detectData.sampleType=trNode.childNodes[0].innerText;
                    this.detectData.xenobiotics_detected =trNode.childNodes[1].innerText;
                    this.toxinDetected.push(this.detectData);
                    this.detectData={method:'',sampleType:'',xenobiotics_detected:''}
                  }
                }
              }
            }

          }else if(element.className==='abnormal'){
            if(element.childNodes.length===1){
              var tbodyNode = element.childNodes[0];
              for(var j=1;j<tbodyNode.childNodes.length;j++){
                var trNode = tbodyNode.childNodes[j];
                if(trNode.childNodes.length>0){
                  this.abnormalData.result=trNode.childNodes[0].innerText;
                  this.toxinAbnormal.push(this.abnormalData);
                  this.abnormalData={result:''};
                }
              }
            }
          }
        }
        if(element.localName==='div'){
          if(element.className==='suggest'){
            var text = element.innerText;
            this.toxinSuggest += text.replace(this.tempSuggest,'');

          }else if(element.className==='food'){
            var text = element.innerText;
            this.toxinFood += text.replace(this.tempFood,'');
          }

        }
      }
    }
  },
  created: function() {
    this.ajaxGetCustomers(1, 10, '','')
  },
  mounted: function() {
    $.FroalaEditor.DefineIcon('insert', {
      NAME: 'table'
    });
    $.FroalaEditor.RegisterCommand('insert', {
      title: this.$t("Titles.model"),
      type: 'dropdown',
      focus: true,
      undo: true,
      refreshAfterCallback: true,
      options: {
        'v1': this.$t("Titles.resultModel"),
        'v2': this.$t("Titles.unnormalModel"),
        'v3': this.$t("Titles.suggestAndfood")
      },
      callback: function(cmd, val, params) {
        if ('v1' === val) {
          this.html.insert(rsltModule);
        } else if ('v2' === val) {
          this.html.insert(ExtraordinaryModule);
        }else if('v3' === val){
          this.html.insert(SuggestModule);
        }
      }
    })
    $('textarea#froala-editor').froalaEditor({
      theme: 'royal',
      toolbarButtons: ['bold', 'italic', 'undo', 'redo', 'formatOL', 'formatUL', 'insert', 'insertFile', 'fullscreen'],
      fileUploadURL: config.apiBaseURL+'toxindetection/upload',
      tableCellStyles: {
        tdText: 'Class 1',
        tdValue: 'Class 2'
      },
    })
  }
}
</script>

<style>
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

.demo-spin-icon-load {
  animation: ani-demo-spin 1s linear infinite;
}
.spin-backgroud {
  /*background-color: rgba(0, 0, 0, 0);*/
  opacity:0.8
}
ol li{
  list-style-type:decimal;
  list-style-position:inside;
}
ul li{
  list-style-type:disc;
  list-style-position:inside;
}
</style>
