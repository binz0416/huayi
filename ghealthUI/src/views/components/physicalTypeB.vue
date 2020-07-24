<template>
<div class="tab-pane" id="tab_3">
  <div>
    <!-- {{tempUltrasound}} -->
    <table class="TBclass">
      <tr>
        <td class="text"><strong>{{$t("placeholders.checkTime")}}：</strong></td>
        <td class="value">{{tempUltrasound.inspectionTimeDsp}}</td>
        </td>
        <td class="text"><strong>{{$t("placeholders.checkPoint")}}：</strong> </td>
        <td class="value">{{tempUltrasound.checkpoint}}</td>
        </td>
        <td class="text"><strong>{{$t("placeholders.clinicalDiagnosis")}}：</strong></td>
        <td class="value"> {{tempUltrasound.clinical_diagnosis}}</td>
        </td>
      </tr>
      <tr>
        <td colspan="6" style="width:100%">
          <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:5px 0px 5px 0px" />
        </td>
      </tr>
    </table>
  </div>
  <div>
    <div>
      <strong>{{$t("labels.uDescription")}}：</strong>
      <P>{{tempUltrasound.ul_description}}</P>
    </div>
    <div>
      <strong>{{$t("labels.uHints")}}：</strong>
      <P>{{tempUltrasound.ul_tip}}</P>
    </div>
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
      <textarea style="height:100px;width:98%;margin:3px" v-model="tempUltrasound.detailAdvise"></textarea>
    </div>
  </div>
  <div v-show="!isShow">
    <div style="color:red; ">{{$t("Titles.doctorTips")}}</div>
    <div id="suggestforWear" style="padding:0px 0px 0px 15px">
      {{tempUltrasound.detailAdvise}}
    </div>
  </div>
</div>
</template>

<script>
var tempUltrasound;
export default {
  data() {
    return {
      tempUltrasound: ''
    }
  },
   props: ['ultrasoundData','isShow'],
   watch: {
     'ultrasoundData': function(n, o) {
       this.tempUltrasound = n
     }
   }
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: left;
}

.text {
  width: 60px;
  padding: 0px 0px 0px 10px;
}
.value {
  width: 150px;
  padding: 0px 0px 0px 5px;
}

.textArea {
  height: 80px;
  width: 98%;
  margin: 5px 0px 5px 0px;
}


</style>
