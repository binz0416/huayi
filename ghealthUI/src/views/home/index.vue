<template>
  <div class="wrapper">
    <Headbar/>
    <Siderbar/>
    <AppMain/>
    <Footerbar v-if="mainFlag"/>
  </div>
</template>

<script>
  import Headbar from './head'
  import Siderbar from './siderbar'
  import AppMain from './appmain'
  import Footerbar from './footer'

  export default {
     name: 'Home',
     components: {
       Headbar,
       Siderbar,
       AppMain,
       Footerbar
     },
     computed:{
      mainFlag(){
        return this.$store.state.main.mainFlag
      }
     }
 }
</script>

<style scoped>

</style>
