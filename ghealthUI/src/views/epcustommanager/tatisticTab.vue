
<!-- 图表 -->
<template>
<div class="tab-pane" id="tab_3">
  <div class="row">
    <div class="col-xs-12">
      <div class="box box-primary">
        <div class="box-body" style="width:100%;">
          <table class="TBclass" style="float:right;">
            <tr>
              <td class="text"><strong>{{$t("labels.selectUser")}}：</strong></td>
              <td class="value">
                <Select id="usersOption" v-model="condition.userId" style="width:80%">
                    <Option :value='0' :key="0">全部</Option>
                    <Option v-for="(item,index) in userList" :value="item.userid" :key="item.userid">{{ item.username }}</Option>
             </Select>
              </td>
              <td class="text"><strong>{{$t("labels.tatisticType")}}：</strong></td>
              <td class="value">
                <Select v-model="condition.type" style="width:80%">
            <Option :value='1' :key="1">{{$t("optionText.customTatis")}}</Option>
            <Option :value='2' :key="2">{{$t("optionText.sourceTatis")}}</Option>
            <Option :value='3' :key="3">{{$t("optionText.memberTatis")}}</Option>
            <Option :value='4' :key="4">{{$t("optionText.turnoverTatis")}}</Option>
            <Option :value='5' :key="5">{{$t("optionText.industryTatis")}}</Option>
          </Select>
              </td>
              <td class="textSmall"><strong>{{$t("labels.startDate")}}：</strong> </td>
              <td class="value">
                <DatePicker type="date" format="yyyy-MM-dd" @on-change="changeStartDate" v-bind:placeholder='$t("labels.lbStart")'></DatePicker>
              </td>
              <td class="textSmall"><strong>{{$t("labels.endDate")}}：</strong></td>
              <td class="value">
                <DatePicker type="date" format="yyyy-MM-dd" @on-change="changeEndDate" v-bind:placeholder='$t("labels.lbEnd")'></DatePicker>
              </td>
              <td>
                <button type="button" class="btn btn-primary" v-on:click="searchCustomer()"><i class="fa fa-refresh"></i> &nbsp;{{$t("buttons.search")}}</button>
              </td>
            </tr>
          </table>
        </div>
        <div id="dataList" style="width:100%;loat:right">
          <Datatable style="height:400px;overflow-y:visible ;overflow-x:hidden" ref='datatable' :items="dataList" :columns="columns" />
          <div class="totalDiv">
            <table>
              <tr>
                <td class="textTotal">共计：</td>
                <td class="textTotal">添加客户：{{tjTotal}}条</td>
                <td class="textTotal">有人负责：{{fzTotal}}条</td>
              </tr>
            </table>
          </div>
        </div>
        <!-- <div id="myChartContent" class="myChartContent"> -->
          <div id="myChart" class="myChart"></div>
        <!-- </div> -->
      </div>
    </div>
  </div>
  <!-- <div id="myChart" :style="{width: '600px', height: '300px'}"></div> -->
</div>
</template>
<script>
import Datatable from '../../components/datatable'
import {
  getTatisticInfo,
} from '@/api/eptatisticmanager'
// 引入基本模板
let echarts = require('echarts/lib/echarts')
// 引入柱状图组件
//require('echarts/lib/chart/bar')
// 引入饼状图组件
require('echarts/lib/chart/pie')
// 引入提示框和title组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
export default {
  data() {
    return {
      userList: [],
      columns: [],
      dataList: [],
      tlList: [],
      testData: [],
      title: '',
      tjTotal: '',
      fzTotal: '',
      condition: {
        userId: 0,
        type: 1,
        createTime1: '',
        createTime2: ''
      }
    }
  },
  created: function() {
    this.getTatisticInfo()
  },
  methods: {
    searchCustomer() {
      var dataList = document.getElementById("dataList");
      var echarts = document.getElementById("myChart");
      if (this.condition.type == 1) {
        dataList.style.display = "";
        echarts.style.display = "none";
      } else {
        dataList.style.display = "none";
        echarts.style.display = "";
      }
      this.getTatisticInfo()
    },
    changeStartDate(date) {
      this.condition.createTime1 = date
    },
    changeEndDate(date) {
      this.condition.createTime2 = date
    },
    async getTatisticInfo() {
      const para = {
        userId: this.condition.userId,
        type: this.condition.type,
        createTime1: this.condition.createTime1,
        createTime2: this.condition.createTime2,
        apiflag: 'getCustomTJ'
      }
      var res = await getTatisticInfo(para)
      this.userList = []
      this.userList = res.data.data.userInfoList
      this.columns = res.data.data.customtjcolumns
      this.tjTotal = res.data.data.tjTotal
      this.fzTotal = res.data.data.fzTotal
      this.dataList = res.data.data.dataList
      this.title = res.data.data.title
      if (this.condition.type !== 1) {
        this.dataList = []
        this.tlList = []
        this.tlList = res.data.data.tlList
        for (var i = 0; i < res.data.data.dataList.length; i++) {
          var item = res.data.data.dataList[i]
          this.dataList.push({
            name: item.name,
            value: item.value
          });
        }
        this.drawLine()
      }else{
        var echarts = document.getElementById("myChart");
        echarts.style.display = "none";
      }
    },
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        title: {
          text: this.title,

          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          x: 'left',
          // data: ["50人以上", "20-50人", "5--20人", "其它"]
          data: this.tlList
        },
        toolbox: {
          show: true,
          feature: {
            mark: {
              show: true
            },
            dataView: {
              show: true,
              readOnly: false
            },
            magicType: {
              show: true,
              type: ['pie', 'funnel'],
              option: {
                funnel: {
                  x: '25%',
                  width: '50%',
                  funnelAlign: 'left',
                  max: 1548
                }
              }
            },
            restore: {
              show: true
            },
            saveAsImage: {
              show: true
            }
          }
        },
        calculable: true,
        series: [{
          name: '占百分比',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: this.dataList

        }]
      });
    }
  },
  components: {
    Datatable
  },
}
</script>
<style scoped>
.text {
  width: 100px;
  padding: 0px 0px 0px 5px;
  text-align: right;
}
.textSmall {
  width: 20px;
  padding: 0px 0px 0px 5px;
  text-align: right;
}
.value {
  width: 150px;
  padding: 0px 0px 0px 5px;
  text-align: left;
}
.myChart {
  width: 60%;
  height: 500px;
  margin:0 auto;
}
.totalDiv{
  height: 25px;
}
.textTotal {
  width: 120px;
  padding: 0px 0px 0px 5px;
  text-align: left;
}
</style>
