<template>
<div class="box-body">
  <!-- Custom Tabs -->
  <h4><strong>{{$t("Titles.detailInfo")}}：</strong></h4>
  <!-- Custom Tabs -->
  <br/>
  <div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
      <li id="li_2" v-show="tabUrineShow"><a href="#tab_2" data-toggle="tab" aria-expanded="false">{{$t("Titles.normalUrine")}}</a></li>
      <li id="li_1" v-show="tabBloodShow"><a href="#tab_1" data-toggle="tab" aria-expanded="false">{{$t("Titles.normalBlood")}}</a></li>
      <li id="li_3" v-show="tabUltrasoundShow"><a href="#tab_3" data-toggle="tab" aria-expanded="false">{{$t("Titles.normalTypeB")}}</a></li>
      <li id="li_4" v-show="tabSpaceCapsuleShow"><a href="#tab_4" data-toggle="tab" aria-expanded="false">{{$t("Titles.spaceCapsule")}}</a></li>
      <li id="li_5" v-show="tabWearableShow"><a href="#tab_5" data-toggle="tab" aria-expanded="false">{{$t("Titles.wearable")}}</a></li>
    </ul>
    <div class="tab-content">
      <!-- tab1 -->
      <tab1 v-show="tabBloodShow" :bloodData="bloodData" :isShow="true" :customer="customer"/>
      <!-- tab2 -->
      <tab2 v-show="tabUrineShow" :urineData="urineData" :isShow="true"/>
      <!-- tab3 -->
      <tab3 v-show="tabUltrasoundShow" :ultrasoundData="ultrasoundData" :isShow="true"/>
      <!-- tab4 -->
      <tab4 v-show="tabSpaceCapsuleShow" :spaceCapsule="spaceCapsule" :isShow="true"/>
      <!-- tab5 -->
      <tab5 v-show="tabWearableShow" :wearableInfo="wearableInfo" :ecgData="ecgData"/>
    </div>
    <!-- /.tab-content -->
  </div>
  <!-- nav-tabs-custom -->
  <div></div>
</div>
</template>

<script>
import tab1 from '../components/physicalBlood'
import tab2 from '../components/physicalUrine'
import tab3 from '../components/physicalTypeB'
import tab4 from '../components/spaceCapsule'
import tab5 from '../dataunscramble/wearable'

export default {
  data() {
    return {
      tabBloodShow: false,
      tabUrineShow: false,
      tabUltrasoundShow: false,
      tabSpaceCapsuleShow: false,
      tabWearableShow: false,
      isActive: false
    }
  },
  components: {
    tab1,
    tab2,
    tab3,
    tab4,
    tab5
  },
  props: ['bloodData', 'urineData', 'ultrasoundData', 'wearableInfo', 'ecgData', 'spaceCapsule','customer'],
  methods: {
    bloodShow(isShow) {
      this.tabBloodShow = isShow
      if (isShow && !this.isActive) {
        this.setActiveTab(1)
      }
    },
    urineShow(isShow) {
      this.tabUrineShow = isShow
      if (isShow && !this.isActive) {
        this.setActiveTab(2)
      }
    },
    ultrasoundShow(isShow) {
      this.tabUltrasoundShow = isShow
      if (isShow && !this.isActive) {
        this.setActiveTab(3)
      }
    },
    spaceCapsuleShow(isShow) {
      this.tabSpaceCapsuleShow = isShow
      if (isShow && !this.isActive) {
        this.setActiveTab(4)
      }
    },
    wearableShow(isShow) {
      this.tabWearableShow = isShow
      if (isShow && !this.isActive) {
        this.setActiveTab(5)
      }
    },
    initShow() {
      this.tabBloodShow = false
      this.tabUrineShow = false
      this.tabUltrasoundShow = false
      this.tabSpaceCapsuleShow = false
      this.tabWearableShow = false
      this.isActive = false
      for (var i = 1; i <= 6; i++) {
        $('#li_' + i).attr('class', '')
        $('#tab_' + i).attr('class', 'tab-pane')
      }
    },
    setActiveTab(id) {
      $('#li_' + id).attr('class', 'active')
      $('#tab_' + id).attr('class', 'tab-pane active')
      this.isActive = true
    }
    // ,
    // initTabs() {
    //   this.$refs.tab6.setToxin();
    // }
  }
}
</script>

<style scoped>

</style>
