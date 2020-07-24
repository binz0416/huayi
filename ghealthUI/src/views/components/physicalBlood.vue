<template>
<div class="tab-pane" id="tab_1">
  <!-- {{customer}} -->
  <div>
    <table class="TBclass">
      <tr>
        <td class="text"><strong>{{$t("placeholders.checkTime")}}：</strong></td>
        <td class="value">{{tempBlood.inspectionTimeDsp}}</td>
        </td>
        <td class="text"><strong>{{$t("placeholders.checkPoint")}}：</strong> </td>
        <td class="value">{{tempBlood.bl_specimen}}</td>
        </td>
        <td class="text"><strong>{{$t("placeholders.clinicalDiagnosis")}}：</strong></td>
        <td class="value"> {{tempBlood.bl_condition}}</td>
        </td>
      </tr>
      <tr>
        <td colspan="6" style="width:100%">
          <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:5px 0px 5px 0px" />
        </td>
      </tr>
    </table>
    <table class="TBclass">
      <tr>
        <td class="proText"><strong>{{$t("labels.projectName")}}</strong></td>
        <td class="checkTime"><strong>{{$t("labels.testMode")}}</strong></td>
        <td class="checkTime"><strong>{{$t("labels.result")}}</strong></td>
        <td class="unitText"><strong>{{$t("labels.unit")}}</strong></td>
        <td class="unitText"><strong>{{$t("labels.prompt")}}</strong></td>
        <td class="checkTime"><strong>{{$t("labels.rValue")}}</strong></td>
      </tr>
      <tr>
        <td colspan="6">
          <hr />
        </td>
      </tr>
      <tr>
        <td colspan="6" class="proType"><strong>{{$t("labels.btbil")}}</strong></td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.btbil")}}</td>
        <td class="proValue">{{tempBlood.tbil_method}}</td>
        <td class="proValue">{{tempBlood.tbil}}</td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="tempBlood.tbil > 21.0" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤21.0</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bdbil")}}</td>
        <td class="proValue">{{tempBlood.dbil_method}}</td>
        <td class="proValue">{{tempBlood.dbil}}</td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="tempBlood.dbil > 6.5" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤6.5</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bibil")}}</td>
        <td class="proValue">{{tempBlood.ibil_method}}</td>
        <td class="proValue">{{tempBlood.ibil}}</td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="tempBlood.ibil > 17.1" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤17.1</td>
      </tr>
      <tr>
        <td colspan="6" class="proType"><strong>{{$t("labels.bcthl")}}</strong></td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.btg")}}</td>
        <td class="proValue">{{tempBlood.tg_method}}</td>
        <td class="proValue">{{tempBlood.tg}}</td>
        <td class="proValue">mmol/L</td>
        <td class="proValue"><i v-if="tempBlood.tg >= 2.26" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">
          <2.26 </td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bchol")}}</td>
        <td class="proValue">{{tempBlood.chol_method}}</td>
        <td class="proValue">{{tempBlood.chol}}</td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="tempBlood.chol >= 5.20" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">
          <5.20</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bhdl")}}</td>
        <td class="proValue">{{tempBlood.hdl_ch_method}}</td>
        <td class="proValue">{{tempBlood.hdl_ch}}</td>
        <td class="proValue">umol/L</td>
        <td class="proValue">
          <i v-if="tempCustomer.customerSex=='0' && tempBlood.hdl_ch <= 0.90" class="fa fa-long-arrow-down"></i>
          <i v-if="tempCustomer.customerSex=='1' && tempBlood.hdl_ch > 1.15" class="fa fa-long-arrow-down"></i>
        </td>
        <td class="proValue">男||>0.90<br />女||>1.15</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bldl")}}</td>
        <td class="proValue">{{tempBlood.ldl_ch_method}}</td>
        <td class="proValue">{{tempBlood.ldl_ch}}</td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="tempBlood.ldl_ch > 4.13" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤4.13</td>
      </tr>
      <tr>
        <td colspan="6" class="proType"><strong>{{$t("labels.bal")}}</strong></td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.balt")}}</td>
        <td class="proValue">{{tempBlood.alt_method}}</td>
        <td class="proValue">{{tempBlood.alt}}</td>
        <td class="proValue">U/L</td>
        <td class="proValue">
          <i v-if="tempCustomer.customerSex=='0' && tempBlood.alt > 41" class="fa fa-long-arrow-up"></i>
          <i v-if="tempCustomer.customerSex=='1' && tempBlood.alt > 31" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||≤41<br/>女||≤31</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bast")}}</td>
        <td class="proValue">{{tempBlood.ast_method}}</td>
        <td class="proValue">{{tempBlood.ast}}</td>
        <td class="proValue">umol/L</td>
        <td class="proValue">
          <i v-if="tempCustomer.customerSex=='0' && tempBlood.ast >= 38" class="fa fa-long-arrow-up"></i>
          <i v-if="tempCustomer.customerSex=='1' && tempBlood.ast >= 32" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||< 38<br/>女||<32</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.baltast")}}</td>
        <td class="proValue">{{tempBlood.alt_ast_method}}</td>
        <td class="proValue">{{tempBlood.alt_ast}}</td>
        <td class="proValue">Ratio</td>
        <td class="proValue"><i v-if="tempBlood.alt_ast >1.50" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">0.00-1.50
        </td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.balp")}}</td>
        <td class="proValue">{{tempBlood.alp_method}}</td>
        <td class="proValue">{{tempBlood.alp}}</td>
        <td class="proValue">U/L</td>
        <td class="proValue">
          <i v-if="tempCustomer.customerSex=='0' &&tempBlood.alp < 40" class="fa fa-long-arrow-down"></i>
          <i v-if="tempCustomer.customerSex=='0' &&tempBlood.alp > 129" class="fa fa-long-arrow-up"></i>
          <i v-if="tempCustomer.customerSex=='1' &&tempBlood.alp < 35" class="fa fa-long-arrow-down"></i>
          <i v-if="tempCustomer.customerSex=='1' &&tempBlood.alp > 104" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||40-129<br/>女||35-104</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bldh")}}</td>
        <td class="proValue">{{tempBlood.ldh_method}}</td>
        <td class="proValue">{{tempBlood.ldh}}</td>
        <td class="proValue">U/L</td>
        <td class="proValue"><i v-if="tempBlood.ldh >250" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤250</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.btp")}}</td>
        <td class="proValue">{{tempBlood.tp_method}}</td>
        <td class="proValue">{{tempBlood.tp}}</td>
        <td class="proValue">g/L</td>
        <td class="proValue">
          <i v-if="tempBlood.tp!='' && tempBlood.tp <65.0" class="fa fa-long-arrow-down"></i>
          <i v-if="tempBlood.tp >85.0" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">65.0-85.0</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.balb")}}</td>
        <td class="proValue">{{tempBlood.alb_method}}</td>
        <td class="proValue">{{tempBlood.alb}}</td>
        <td class="proValue">g/L</td>
        <td class="proValue">
          <i v-if="tempBlood.alb!='' &&  tempBlood.alb <40.0" class="fa fa-long-arrow-down"></i>
          <i v-if="tempBlood.alb >55.0" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">40.0-55.0</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bggt")}}</td>
        <td class="proValue">{{tempBlood.ggt_method}}</td>
        <td class="proValue">{{tempBlood.ggt}}</td>
        <td class="proValue">U/L</td>
        <td class="proValue">
          <i v-if="tempBlood.ggt >60" class="fa fa-long-arrow-up"></i>
          <i v-if="tempBlood.ggt >40" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||<60<br/>女||<40</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.balob")}}</td>
        <td class="proValue">{{tempBlood.glob_method}}</td>
        <td class="proValue">{{tempBlood.glob}}</td>
        <td class="proValue">g/L</td>
        <td class="proValue">
          <i v-if="tempBlood.glob!='' && tempBlood.glob <20.0" class="fa fa-long-arrow-down"></i>
          <i v-if="tempBlood.glob >40.0" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">20.0-40.0</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.bag")}}</td>
        <td class="proValue">{{tempBlood.a_g_method}}</td>
        <td class="proValue">{{tempBlood.a_g}}</td>
        <td class="proValue">Ratio</td>
        <td class="proValue">
          <i v-if="tempBlood.aG!='' && tempBlood.a_g <1.2" class="fa fa-long-arrow-down"></i>
          <i v-if="tempBlood.aG >2.4" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">1.2-2.4</td>
      </tr>
    </table>
  </div>
  <table class="TBclass">
    <tr>
      <td style="width:100%">
        <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:5px 0px 5px 0px" />
      </td>
    </tr>
  </table>
  <div v-show="isShow">
    <div style="color:red; ">{{$t("Titles.doctorTips")}}</div>
    <div id="suggestforWear">
      <textarea style="height:100px;width:98%;margin:3px" v-model="tempBlood.detailAdvise"></textarea>
    </div>
  </div>
  <div v-show="!isShow">
    <div style="color:red; ">{{$t("Titles.doctorTips")}}</div>
    <div id="suggestforWear" style="padding:0px 0px 0px 15px">
      {{tempBlood.detailAdvise}}
    </div>
  </div>
</div>
</template>

<script>
var tempBlood;
export default {
  data() {
    return {
      tempBlood: '',
      tempCustomer:''
    }
  },
  props: ['bloodData', 'isShow','customer'],
  watch: {
    'bloodData': function(n, o) {
      this.tempBlood = n
    },
    'customer': function(n, o) {
      this.tempCustomer = n
    }
  }
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: left;
}

.proText {
  text-align: left;
  width: 24%;
  padding: 0px 0px 5px 10px;
}

.proType {
  text-align: left;
  padding: 5px 0px 5px 5px;
}

.proName {
  text-align: left;
  width: 25%;
  padding: 5px 0px 5px 15px;
}

.proValue {
  text-align: left;
  padding: 5px 0px 5px 10px;
}

.checkTime {
  width: 150px;
  text-align: left;
  padding: 0px 0px 5px 10px
}

.unitText {
  width: 100px;
  text-align: left;
  padding: 0px 0px 5px 10px
}
.text {
  width: 60px;
  padding: 0px 0px 0px 10px;
}
.value {
  width: 150px;
  padding: 0px 0px 0px 5px;
}
</style>
