<template>
<section class="content-header">
  <h1>
    {{levelList[0].name}}
    <small>{{levelList[1].name}}</small>
  </h1>
  <ol class="breadcrumb" v-if="!isHome">
    <li>
      <router-link to="/"><i class="fa fa-dashboard"></i> {{$t("messages.homepage")}}</router-link>
      <li class="active" v-for="level in levelList">{{level.name}} </li>
    </li>
  </ol>
</section>
</template>

<script>
export default {
  data() {
    return {
      isHome: true,
      levelList: []
    }
  },
  created: function() {
    this.levelList = this.$route.matched.filter(item => item.name)
    if (this.$route.path === '/main') {
      this.isHome = true
      this.$store.commit('invert',0)
    } else {
      this.isHome = false
      this.$store.commit('invert',1)
    }
  },
  watch: {
    $route(to) {
      if (to.path === '/main') {
        this.isHome = true
        this.$store.commit('invert',0)
      } else {
        this.isHome = false
        this.$store.commit('invert',1)
      }
      this.levelList = this.$route.matched.filter(item => item.name)
    }
  }
}
</script>

<style>

</style>
