<template>
<div class="tab-pane active" id="tab_1">
  <div>
    <table class="TBclass">
      <tr>
        <td class="text"><strong>{{$t("placeholders.checkTime")}}：</strong></td>
        <td class="value">
          <DatePicker type="date" format="yyyy-MM-dd" v-bind:placeholder='$t("placeholders.checkTime")' v-model="bloodData.inspectionTime"></DatePicker>
        </td>
        </td>
        <td class="text"><strong>{{$t("placeholders.checkPoint")}}：</strong> </td>
        <td class="value"><input v-bind:placeholder='$t("placeholders.checkPoint")' class="form-control input-sm inputStyle" v-model="bloodData.blSpecimen"></td>
        </td>
        <td class="text"><strong>{{$t("placeholders.clinicalDiagnosis")}}：</strong></td>
        <td class="value"> <input v-bind:placeholder='$t("placeholders.checkPoint")' class="form-control input-sm inputStyle" v-model="bloodData.blCondition"></td>
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
        <td class="proText"><strong>项目</strong></td>
        <td class="checkTime"><strong>检测方法</strong></td>
        <td class="checkTime"><strong>结果</strong></td>
        <td class="unitText"><strong>单位</strong></td>
        <td class="unitText"><strong>提示</strong></td>
        <td class="checkTime"><strong>参考值</strong></td>
      </tr>
      <tr>
        <td colspan="6">
          <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:5px 0px 5px 0px" />
        </td>
      </tr>
      <tr>
        <td colspan="6" class="proType"><strong>胆红素三项(TBIL,DBIL,IBIL)</strong></td>
      </tr>
      <tr>
        <td class="proName">总胆红素(TBIL)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.tbilMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.tbil"></td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="bloodData.tbil > 21.0" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤21.0</td>
      </tr>
      <tr>
        <td class="proName">直接胆红素(TBIL)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.dbilMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.dbil"></td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="bloodData.dbil > 6.5" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤6.5</td>
      </tr>
      <tr>
        <td class="proName">间接胆红素(TBIL)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ibilMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ibil"></td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="bloodData.ibil > 17.1" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤17.1</td>
      </tr>
      <tr>
        <td colspan="6" class="proType"><strong>血脂四项(CHOL,TG,HDL-CH,LDL-CH)</strong></td>
      </tr>
      <tr>
        <td class="proName">甘油三酯(TG)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.tgMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.tg"></td>
        <td class="proValue">mmol/L</td>
        <td class="proValue"><i v-if="bloodData.tg >= 2.26" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">
          <2.26 </td>
      </tr>
      <tr>
        <td class="proName">胆固醇(CHOL)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.cholMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.chol"></td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="bloodData.chol >= 5.20" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">
          <5.20</td>
      </tr>
      <tr>
        <td class="proName">高密度脂蛋白胆固醇(HDL-CH)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.hdlChMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.hdlCh"></td>
        <td class="proValue">umol/L</td>
        <td class="proValue">
          <i v-if="customer.customerSex=='0' &&　bloodData.hdlCh <= 0.90" class="fa fa-long-arrow-down"></i>
          <i v-if="customer.customerSex=='1' &&　bloodData.hdlCh > 1.15" class="fa fa-long-arrow-down"></i>
        </td>
        <td class="proValue">男||>0.90<br />女||>1.15</td>
      </tr>
      <tr>
        <td class="proName">低密度脂蛋白胆固醇(LDL-CH)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ldlChMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ldlCh"></td>
        <td class="proValue">umol/L</td>
        <td class="proValue"><i v-if="bloodData.ldlCh > 4.13" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤4.13</td>
      </tr>
      <tr>
        <td colspan="6" class="proType"><strong>肝功七项(ALT,AST,AST/ALT,TP,ALB,GLOB,A/G,GGT,ALP,LDH)</strong></td>
      </tr>
      <tr>
        <td class="proName">谷丙转氨酶(ALT)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.altMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.alt"></td>
        <td class="proValue">U/L</td>
        <td class="proValue">
          <i v-if="customer.customerSex=='0' &&　bloodData.alt > 41" class="fa fa-long-arrow-up"></i>
          <i v-if="customer.customerSex=='1' &&　bloodData.alt > 31" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||≤41<br/>女||≤31</td>
      </tr>
      <tr>
        <td class="proName">谷草转氨酶(AST)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.astMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ast"></td>
        <td class="proValue">umol/L</td>
        <td class="proValue">
          <i v-if="customer.customerSex=='0' &&　bloodData.ast >= 38" class="fa fa-long-arrow-up"></i>
          <i v-if="customer.customerSex=='1' &&　bloodData.ast >= 32" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||< 38<br/>女||<32</td>
      </tr>
      <tr>
        <td class="proName">谷丙转氨酶/谷草转氨酶</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.altAstMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.altAst"></td>
        <td class="proValue">Ratio</td>
        <td class="proValue"><i v-if="bloodData.altAst >1.50" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">0.00-1.50
        </td>
      </tr>
      <tr>
        <td class="proName">碱性磷酸酶(ALP)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.alpMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.alp"></td>
        <td class="proValue">U/L</td>
        <td class="proValue">
          <i v-if="customer.customerSex=='0' &&　bloodData.alp < 40" class="fa fa-long-arrow-down"></i>
          <i v-if="customer.customerSex=='0' &&　bloodData.alp > 129" class="fa fa-long-arrow-up"></i>
          <i v-if="customer.customerSex=='1' &&　bloodData.alp < 35" class="fa fa-long-arrow-down"></i>
          <i v-if="customer.customerSex=='1' &&　bloodData.alp > 104" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||40-129<br/>女||35-104</td>
      </tr>
      <tr>
        <td class="proName">乳酸脱氢酶(LDH)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ldhMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ldh"></td>
        <td class="proValue">U/L</td>
        <td class="proValue"><i v-if="bloodData.ldh >250" class="fa fa-long-arrow-up"></i></td>
        <td class="proValue">≤250</td>
      </tr>
      <tr>
        <td class="proName">总蛋白(TP)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.tpMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.tp"></td>
        <td class="proValue">g/L</td>
        <td class="proValue">
          <i v-if="bloodData.tp!='' && bloodData.tp <65.0" class="fa fa-long-arrow-down"></i>
          <i v-if="bloodData.tp >85.0" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">65.0-85.0</td>
      </tr>
      <tr>
        <td class="proName">白蛋白(ALB)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.albMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.alb"></td>
        <td class="proValue">g/L</td>
        <td class="proValue">
          <i v-if="bloodData.alb!='' &&  bloodData.alb <40.0" class="fa fa-long-arrow-down"></i>
          <i v-if="bloodData.alb >55.0" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">40.0-55.0</td>
      </tr>
      <tr>
        <td class="proName">Y-谷氨转肽酶(GGT)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ggtMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.ggt"></td>
        <td class="proValue">U/L</td>
        <td class="proValue">
          <i v-if="bloodData.ggt >60" class="fa fa-long-arrow-up"></i>
          <i v-if="bloodData.ggt >40" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">男||<60<br/>女||<40</td>
      </tr>
      <tr>
        <td class="proName">球蛋白(GLOB)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.globMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.glob"></td>
        <td class="proValue">g/L</td>
        <td class="proValue">
          <i v-if="bloodData.glob!='' && bloodData.glob <20.0" class="fa fa-long-arrow-down"></i>
          <i v-if="bloodData.glob >40.0" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">20.0-40.0</td>
      </tr>
      <tr>
        <td class="proName">白蛋白/球蛋白(A/G)</td>
        <td><input v-bind:placeholder='$t("placeholders.checkMode")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.aGMethod"></td>
        <td><input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject" class="form-control input-sm inputStyle" v-model="bloodData.aG"></td>
        <td class="proValue">Ratio</td>
        <td class="proValue">
          <i v-if="bloodData.aG!='' && bloodData.aG <1.2" class="fa fa-long-arrow-down"></i>
          <i v-if="bloodData.aG >2.4" class="fa fa-long-arrow-up"></i>
        </td>
        <td class="proValue">1.2-2.4</td>
      </tr>
    </table>
  </div>

</div>
</template>

<script>
export default {
  props: ['bloodData', 'customer'],

}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: center;
}

.timeText {
  width: 70px;
  text-align: left;
  padding: 0px 0px 0px 10px;
}

.timeValue {
  width: 100px;
  text-align: left;
  padding: 0px 0px 0px 5px;
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
  width: 22%;
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

.inputStyle {
  text-align: left;
  width: 120px;
  margin: 5px 0px 5px 0px;
}

.text {
  width: 60px;
  padding: 0px 0px 0px 5px;
  text-align: left;
}

.value {
  width: 150px;
  padding: 0px 0px 0px 5px;
  text-align: left;
}

.display {
  display: none
}
</style>
