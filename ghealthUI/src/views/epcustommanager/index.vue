<template>
<div>
  <div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
      <li id="li_2" class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="true">{{$t("Titles.epCustomer")}}</a></li>
      <li id="li_1"><a href="#tab_2" data-toggle="tab" aria-expanded="false">{{$t("Titles.epContacts")}}</a></li>
      <li id="li_3"><a href="#tab_3" data-toggle="tab" aria-expanded="false">{{$t("Titles.epStatistics")}}</a></li>
    </ul>
    <div class="tab-content">
      <!-- tab1 -->
      <tab1 />
      <!-- tab2 -->
      <tab2 />
      <!-- tab3 -->
      <tab3 />

    </div>
    <!-- /.tab-content -->
  </div>
</div>
</template>

<script>
import tab1 from '../epcustommanager/customerTab'
import tab2 from '../epcustommanager/contactTab'
import tab3 from '../epcustommanager/tatisticTab'
import {
  selectCoutomAll
} from '@/api/epcustommanager'
export default {
  components: {
    tab1,
    tab2,
    tab3
  },
  data() {
    return {

    }
  },
  created: function() {
  //  this.getCustomers(this.searchkey)
  }
}
</script>

<style >

</style>
