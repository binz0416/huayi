<template>
<header class="main-header">
  <!-- Logo -->
  <a href="#" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini"></span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><b><font style="">{{$t("Titles.ProjectName")}}</font></b></span>
  </a>
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only"></span>
      </a>

    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- User Account: style can be found in dropdown.less -->
        <li class="user user-menu">
          <a class="dropdown-toggle">
            <span class="hidden-xs">{{$t("messages.welcome")}} {{fullname}}</span>
          </a>

        </li>
        <li>
          <a href="#" v-on:click="signout"><i class="fa fa-sign-out"></i></a>
        </li>
      </ul>
    </div>
  </nav>
</header>
</template>

<script>
import store from '@/utils/storage'

export default {
  data() {
    return {
      fullname: this.$store.getters.name
    }
  },
  methods: {
    signout: function() {
      store.clearSession()
      this.$router.replace({path: '/login'})
    }
  }
}
</script>

<style>

</style>
