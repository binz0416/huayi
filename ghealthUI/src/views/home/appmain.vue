<template>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <LevelBar v-if="mainFlag"/>
  <!-- Main content -->
  <section class="content">
    <router-view/>
  </section>
  <!-- /.content -->
</div>
</template>

<script>
  import LevelBar from './levelbar'

  export default {
     name: 'AppMain',
     components: {
       LevelBar
     },
     created:function(){
      $('body').removeClass('sidebar-expanded-on-hover').addClass('sidebar-collapse')
     },
     computed:{
      mainFlag(){
        $('body').resize()
        return this.$store.state.main.mainFlag
      }
     },
     watch: {
      $route(to) {
        if (to.path === '/main') {
          this.$store.commit('invert',0)
          $('body').removeClass('sidebar-expanded-on-hover').addClass('sidebar-collapse')
        } else {
          this.$store.commit('invert',1)
        }
      }
    }
  }
</script>

<style scoped>

</style>
