<template>
<div class="tab-pane" id="tab_2">
  <div>
    <!-- {{urineData}} -->
    <table class="TBclass">
      <tr>
        <td class="text"><strong>{{$t("placeholders.checkTime")}}：</strong></td>
        <td class="value">  <DatePicker type="date" format="yyyy-MM-dd" v-bind:placeholder='$t("placeholders.checkTime")'
          v-model="urineData.inspectionTime"></DatePicker>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:5px 0px 5px 0px" />
        </td>
      </tr>
    </table>
    <table class="TBclass">
      <tr>
        <td class="proTextPro"><strong>项目</strong></td>
        <td class="proEnMode"><strong>英文对照</strong></td>
        <td class="proTextResult"><strong>结果</strong></td>
        <td class="proTextResult"><strong>单位</strong></td>
        <td class="proTextContrast"><strong>参考值</strong></td>
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
        <td class="proName">蛋白质</td>
        <td class="proValue">PRO </td>
        <td class="">
          <input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject"
          class="form-control input-sm inputStyle" v-model="urineData.pro"></td>
        <td class="proValue"></td>
        <td class="proValue">阴性</td>
      </tr>
      <tr>
        <td class="proName">潜血</td>
        <td class="proValue">BLD </td>
        <td class="">
          <input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject"
          class="form-control input-sm inputStyle" v-model="urineData.bld"></td>
        <td class="proValue"></td>
        <td class="proValue">阴性</td>
      </tr>
      <tr>
        <td class="proName">亚硝酸盐</td>
        <td class="proValue">NIT </td>
        <td class="">
          <input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject"
          class="form-control input-sm inputStyle" v-model="urineData.nit"></td>
        <td class="proValue"></td>
        <td class="proValue">阴性</td>
      </tr>
      <tr>
        <td class="proName">葡萄糖</td>
        <td class="proValue">GLU </td>
        <td class="">
          <input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject"
          class="form-control input-sm inputStyle" v-model="urineData.glu"></td>
        <td class="proValue"></td>
        <td class="proValue">阴性</td>
      </tr>
      <tr>
        <td class="proName">酮体</td>
        <td class="proValue">KET </td>
        <td class="">
          <input v-bind:placeholder='$t("placeholders.resultValue")' name="sendSubject"
          class="form-control input-sm inputStyle" v-model="urineData.ket"></td>
        <td class="proValue"></td>
        <td class="proValue">阴性</td>
      </tr>
    </table>
  </div>

</div>
</template>

<script>
export default {
   props: ['urineData']
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: center;
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
  width: 8%;
  padding: 0px 0px 0px 5px;
  text-align: left;
}
.value {
  width: 92%;
  padding: 0px 0px 0px 5px;
  text-align: left;
}
</style>
