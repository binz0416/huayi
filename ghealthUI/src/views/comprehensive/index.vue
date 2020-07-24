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
      <Icon type="load-c " size=30 class="demo-spin-icon-load "></Icon>
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
  getComprehensiveReport
} from '@/api/comprehensive'

var rsltModule = '<table width="90%"><tr><td colspan="2" class="tbTitle"></td></tr>' +
  '<tr><td class="tdTitle">Sample Type样本类型</td><td class="tdTitle">Xenobiotics Detected毒素检测</td></tr>' +
  '<tr><td class="tdText"><strong></strong></td><td class="tdValue"><strong></br></strong></td></tr>' +
  '<tr><td class="tdText"><strong></strong></td><td class="tdValue"><strong></br></strong></td></tr>  </table>';

var ExtraordinaryModule = '<table width="90%"><tr><td class="tbTitle_Extraordinary">异常项</td></tr>' +
  '<tr><td class="tdText"><strong></strong></td></tr>' +
  '<tr><td class="tdText"><strong></strong></td></tr></table>';

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
      spinShow:false
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
      let res = await getComprehensiveReport(item.customerId)
      setTimeout(() => {
        this.spinShow = false
        $('textarea#froala-editor').froalaEditor('html.set', res.data.data.reportArea);
      }, config.loadingTime);


    },
    async saveInput() {
      let data = {
        customerId: this.currentCustomer.customerId,
        path: 'test.pdf',
        reportArea: $('textarea#froala-editor').froalaEditor('html.get')
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
        'v2': this.$t("Titles.unnormalModel")
      },
      callback: function(cmd, val, params) {
        if ('v1' === val) {
          this.html.insert(rsltModule);
        } else if ('v2' === val) {
          this.html.insert(ExtraordinaryModule);
        }
      }
    })
    $('textarea#froala-editor').froalaEditor({
      theme: 'royal',
      toolbarButtons: ['bold', 'italic', 'undo', 'redo', 'formatOL', 'formatUL', 'insert', 'insertFile', 'fullscreen'],
      fileUploadURL: 'http://i.froala.com/upload',
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
