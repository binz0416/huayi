<template>
<div class="tab-pane" id="tab_3">
  <form enctype="multipart/form-data" method="post">
    <div>
      <table>
        <tr>
          <td>
            <!-- <input type="file" name="uploadfile" id="btn_file"> -->
            <div class="inputFileWrapper">
              <label for="inputFile">
                <input type="file" id="inputFile" name="uploadfile" v-on:change="handleChange"/>
                <span class="custorm-style">
                  <span class="left-button">{{$t("buttons.selectFile")}}</span>
                  <span class="right-text" id="rightText"></span>
                </span>
              </label>
            </div>
          </td>
          <td><button type="button" class="pull-right" v-on:click="upload()"><i class="fa fa-upload" aria-hidden="true"></i> &nbsp;{{$t("buttons.ocr")}}</button></td>
        </tr>
      </table>


    </div>
  </form>
  <div>
    <table class="TBclass">
      <tr>
        <td class="text"><strong>{{$t("placeholders.checkTime")}}：</strong></td>
        <td class="value">
          <DatePicker type="date" format="yyyy-MM-dd" v-bind:placeholder='$t("placeholders.checkTime")' v-model="ultrasoundData.inspectionTime"></DatePicker>
        </td>
        </td>
        <td class="text"><strong>{{$t("placeholders.checkPoint")}}：</strong> </td>
        <td class="value"><input v-bind:placeholder='$t("placeholders.checkPoint")' class="form-control input-sm inputStyle" v-model="ultrasoundData.checkpoint"></td>
        </td>
        <td class="text"><strong>{{$t("placeholders.clinicalDiagnosis")}}：</strong></td>
        <td class="value"> <input v-bind:placeholder='$t("placeholders.clinicalDiagnosis")' class="form-control input-sm inputStyle" v-model="ultrasoundData.clinicalDiagnosis"></td>
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
      <textarea class="textArea" v-model="ultrasoundData.ulDescription"></textarea>

    </div>
    <div>
      <strong>{{$t("labels.uHints")}}：</strong>
      <textarea class="textArea" v-model="ultrasoundData.ulTip"></textarea>
    </div>
  </div>
</div>
</template>


<script>

export default {
  props: ['ultrasoundData'],
  methods: {
    upload() {
      let item = $("input[name=uploadfile]")[0].files[0];
      this.$emit("uploadReport", item)
    },
    handleChange(){
      let file = $("input[name=uploadfile]")[0].files[0];
      $('#rightText')[0].innerText = file.name;
    }
  }
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: left;
}

.textArea {
  height: 80px;
  width: 98%;
  margin: 5px 0px 5px 0px;
}

.inputStyle {
  text-align: left;
  width: 180px;
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

.inputFileWrapper label{
            display: block;
            float: left;
            position: relative;
}
.inputFileWrapper input[type="file"]{
  position: absolute;
            width: 1px;
            height: 1px;
            clip:rect(0,0,0,0);
}
.inputFileWrapper .custorm-style{
  display: block;
  width: 270px;
  height: 25px;
}
.inputFileWrapper .custorm-style .left-button{
  width: 70px;
  line-height: 25px;
  background: #008ac7;
  color: #fff;
  display: block;
  text-align: center;
  float: left;
}
.inputFileWrapper .custorm-style .right-text{
  width: 200px;
  height: 25px;
  line-height: 25px;
  display: block;
  float: right;
  padding: 4px;
  border: 1px solid #008ac7;
  overflow: hidden;
  -ms-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
