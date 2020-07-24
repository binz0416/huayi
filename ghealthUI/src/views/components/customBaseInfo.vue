<template>
  <div class="box-body">
    <h4><strong>{{$t("Titles.baseInfo")}}</strong></h4>
    <table width="100%" style="margin:15px 0px 10px 0px">
      <tr>
        <td class="tdText1"><strong>{{$t("labels.name")}}：</strong></td>
        <td class="tdValue1"><strong>{{customer.customerName}}</br></strong></td>
        <td class="tdText1"><strong>{{$t("labels.gender")}}：</strong></td>
        <td class="tdValue1"><strong>{{customer.customerSexDsp}}</strong></td>
        <td class="tdText1"><strong>{{$t("labels.age")}}：</strong></td>
        <td class="tdValue1"><strong>{{customer.customerAge}}</strong></td>
      </tr>
      <tr>
        <td class="tdText1"><strong>{{$t("labels.birthDay")}}：</strong></td>
        <td class="tdValue1"><strong>{{customer.customerBirthday}}</strong></td>
        <td class="tdText1"><strong>{{$t("labels.mobile")}}：</strong></td>
        <td class="tdValue1"><strong>{{customer.customerMobile}}</strong></td>
      </tr>
    </table>

  </div>
</template>

<script>
export default {

props:['customer']
}
</script>

<style scoped>
.tdText1 {
  text-align: right;
  width: 11%;
  padding: 3px 0px 3px 0px;
  /*border: 1px solid #3C8DBC;*/
}

.tdValue1 {
  width: 20%;
  text-align: left;
  padding: 3px 0px 3px 25px;
  /*border: 1px solid #3C8DBC;*/
}
</style>
