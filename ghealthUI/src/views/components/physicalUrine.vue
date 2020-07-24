<template>
<div class="tab-pane" id="tab_2">
  <div>
    <!-- {{urineData}} -->
    <table class="TBclass">
      <tr>
        <td class="text"><strong>{{$t("placeholders.checkTime")}}：</strong></td>
        <td class="value">{{tempUrine.inspectionTimeDsp}}</td>
      </tr>
      <tr>
        <td colspan="2" style="width:100%">
          <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:5px 0px 5px 0px" />
        </td>
      </tr>
    </table>
    <table class="TBclass">
      <tr>
        <td class="proTextPro"><strong>{{$t("labels.projectName")}}</strong></td>
        <td class="proEnMode"><strong>{{$t("labels.enText")}}</strong></td>
        <td class="proTextResult"><strong>{{$t("labels.result")}}</strong></td>
        <td class="proTextResult"><strong>{{$t("labels.unit")}}</strong></td>
        <td class="proTextContrast"><strong>{{$t("labels.rValue")}}</strong></td>
      </tr>
      <tr>
        <td colspan="6">
          <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:0px 0px 5px 0px" />
        </td>
      </tr>
      <!-- <tr>
        <td colspan="6" class="proType"><strong>乙肝两对半定性</strong></td>
      </tr> -->
      <tr>
        <td class="proName">{{$t("labels.protein")}}</td>
        <td class="proValue">PRO </td>
        <td class="proValue">{{tempUrine.pro}}</td>
        <td class="proValue"></td>
        <td class="proValue">{{$t("labels.negative")}}</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.occultBlood")}}</td>
        <td class="proValue">BLD </td>
        <td class="proValue">{{tempUrine.bld}}</td>
        <td class="proValue"></td>
        <td class="proValue">{{$t("labels.negative")}}</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.nitrite")}}</td>
        <td class="proValue">NIT </td>
        <td class="proValue">{{tempUrine.nit}}</td>
        <td class="proValue"></td>
        <td class="proValue">{{$t("labels.negative")}}</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.glucose")}}</td>
        <td class="proValue">GLU </td>
        <td class="proValue">{{tempUrine.glu}}</td>
        <td class="proValue"></td>
        <td class="proValue">{{$t("labels.negative")}}</td>
      </tr>
      <tr>
        <td class="proName">{{$t("labels.ketone")}}</td>
        <td class="proValue">KET </td>
        <td class="proValue">{{tempUrine.ket}}</td>
        <td class="proValue"></td>
        <td class="proValue">{{$t("labels.negative")}}</td>
      </tr>
    </table>
    <!-- {{tempUrine}} -->
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
      <textarea style="height:100px;width:98%;margin:3px " v-model="tempUrine.detailAdvise"></textarea>
    </div>
  </div>
  <div v-show="!isShow">
    <div style="color:red; ">{{$t("Titles.doctorTips")}}</div>
    <div id="suggestforWear" style="padding:0px 0px 0px 15px">
      {{tempUrine.detailAdvise}}
    </div>
  </div>
</div>
</template>

<script>
var tempUrine
export default {
  data() {
    return {
      tempUrine: ''
    }
  },
  props: ['urineData', 'isShow'],
  watch: {
    'urineData': function(n, o) {
      this.tempUrine = n
    }
  }
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: left;
}

.proTextPro {
  text-align: left;
  width: 22%;
  padding: 0px 0px 5px 25px;
}

.proEnMode {
  text-align: left;
  width: 150px;
  padding: 0px 0px 5px 10px;
}

.proTextResult {
  text-align: left;
  width: 150px;
  padding: 0px 0px 5px 10px;
}

.proTextContrast {
  text-align: left;
  width: 150px;
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
  padding: 5px 0px 5px 15px;

}

.inputStyle {
  text-align: left;
  width: 120px;
  margin: 5px 0px 5px 0px;
}

.text {
  width: 10%;
  padding: 0px 0px 0px 10px;
}

.value {
  width: 90%;
  padding: 0px 0px 0px 5px;
}
</style>
