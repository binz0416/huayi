<template>
<!-- /.tab-pane -->
<div class="tab-pane" id="tab_4">
<div>
  <table class="TBclass">
      	<tr>
        	<td class="proTextPro"><strong>项目</strong></td>
       		<td class="proTextPro"><strong>结果</strong></td>
       		<td class="proTextPro"><strong>参考值</strong></td>
      	</tr>
      	<tr>
        	<td colspan="3">
          		<hr style="height:1px;border:none;border-top:1px solid #555555;width:100%;margin:0px 0px 5px 0px" />
        	</td>
      	</tr>
      	<tr>
        	<td colspan="3" class="proType"><strong>器官问题来源分析</strong></td>
     	</tr>
      	<tr>
        	<td class="proName">肋膜、胸膜</td>
        	<td class="proValue">0.176</td>
      	</tr>
      	<tr>
        	<td colspan="3" class="proType"><strong>病例形态分析</strong></td>
      	</tr>
      	<tr>
        	<td class="proName">阵发性心动过速发展趋势</td>
        	<td class="proValue">1.869</td>
      	</tr>
      	<tr>
        	<td colspan="3" class="proType"><strong>微生物与寄生虫</strong></td>
      	</tr>
      	<tr>
        	<td class="proName">B型流感</td>
        	<td class="proValue">0.706</td>
      	</tr>
      	<tr>
        	<td colspan="3" class="proType"><strong>生化动态分析</strong></td>
      	</tr>
      	<tr>
        	<td class="proName">血浆磷脂酸化物</td>
        	<td class="proValue">1.844</td>
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
      <textarea style="height:100px;width:98%;margin:3px" v-model="tempSpace.detailAdvise"></textarea>
    </div>
  </div>
  <div v-show="!isShow" style="padding:20px 0px 0px 0px">
    <div style="color:red; ">{{$t("Titles.doctorTips")}}</div>
    <div id="suggestforWear" style="padding:0px 0px 0px 15px">
      {{tempSpace.detailAdvise}}
    </div>
  </div>
</div>
</template>

<script>
export default {
  data() {
    return {
      tempSpace: ''
    }
  },
  props: ['spaceCapsule', 'isShow'],
  watch: {
    'spaceCapsule': function(n, o) {
      this.tempSpace = n
    }
  }
}
</script>

<style scoped>
.TBclass {
  width: 100%;
  text-align: center;
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
.proTextPro {
  text-align: left;
  width: 22%;
  padding: 0px 0px 5px 25px;
}

</style>
