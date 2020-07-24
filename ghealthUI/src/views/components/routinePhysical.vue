<template>
<div class="tab-pane active" id="tab_1">
  <table  class="TBclass">
    <tr>
      <td class="proText">{{$t("labels.physicalTime")}}：</td>
      <td class="valueText">{{physicalInfo.physicalTimeDsp}}</td>
      <td class="proText">{{$t("labels.height")}}：</td>
      <td class="valueText">{{physicalInfo.height}}</td>
    </tr>
    <tr>
      <td class="proText">{{$t("labels.weight")}}：</td>
      <td class="valueText">{{physicalInfo.weight}}</td>
      <td class="proText">{{$t("labels.tbil")}}：</td>
      <td class="valueText">{{physicalInfo.tbil}}</td>
    </tr>
    <tr>
      <td class="proText">{{$t("labels.sbp")}}：</td>
      <td class="valueText">{{physicalInfo.sbp}}</td>
      <td class="proText">{{$t("labels.dbp")}}：</td>
      <td class="valueText">{{physicalInfo.dbp}}</td>
    </tr>
    <tr>
      <td class="proText">{{$t("labels.bk")}}：</td>
      <td class="valueText">{{physicalInfo.bk}}</td>
      <td class="proText">{{$t("labels.scre")}}：</td>
      <td class="valueText">{{physicalInfo.scre}}</td>
    </tr>
  </table>

  <!-- <div class="box-body" >
      <Form   :label-width="100">
        <div class="col-md-6">
          <FormItem label="体检日期:">
             <label >{{physicalInfo.physicalTimeDsp}}</label>
          </FormItem>
          <FormItem label="身高(cm):">
               <label >{{physicalInfo.height}}</label>
          </FormItem>
          <FormItem label="体重(kg):">
             <label >{{physicalInfo.weight}}</label>
          </FormItem>
          <FormItem label="总胆红素:">
             <label >{{physicalInfo.tbil}}</label>
          </FormItem>

        </div>
        <div class="col-md-6">
          <FormItem label="收缩压(mmHg):">
             <label >{{physicalInfo.sbp}}</label>
          </FormItem>
          <FormItem label="舒张压(mmHg):">
             <label >{{physicalInfo.dbp}}</label>
          </FormItem>
          <FormItem label="血钾浓度:">
             <label >{{physicalInfo.bk}}</label>
          </FormItem>
          <FormItem label="血清肌酐:">
             <label >{{physicalInfo.scre}}</label>
          </FormItem>
        </div>
      </Form>
  </div> -->
</div>
</template>

<script>
export default {
  props: ['physicalInfo']
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: center;
  padding: 5px;
}

.proText {
  text-align: left;
  width: 10%;
  padding: 10px 0px 15px 5px;
}

.valueText {
  text-align: left;
  padding: 10px 0px 15px 5px;
  width: 20%;
}
</style>
