<template>
<div class="login-box">
  <div class="login-logo">
    <span class="logo-lg"><b>{{$t("Titles.ProjectName")}}</b></span>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">{{$t("messages.tip")}}</p>

    <div class="form-group has-feedback">
      <input id="userName" type="text" class="form-control" placeholder="Full name" value="">
      <span class="glyphicon glyphicon-user form-control-feedback"></span>
    </div>
    <div class="form-group has-feedback">
      <input id="passWd" type="password" class="form-control" placeholder="Password" value="">
      <span class="glyphicon glyphicon-lock form-control-feedback"></span>
    </div>
    <div class="row">

      <!-- /.col -->
      <div class="col-xs-4">
        <button class="btn btn-primary btn-block btn-flat" id="btnLogin" v-on:click="loginClick();">{{$t("buttons.login")}}</button>
      </div>
      <!-- /.col -->
    </div>
  </div>
  <!-- /.login-box-body -->
</div>
</template>

<script>
import MenuUtils from '@/utils/MenuUtils'
import auth from '@/utils/auth'

var menuRouters = []

export default {
  methods: {
    loginClick: async function() {
      let param = {
        userName: $("#userName").val(),
        passWd: $("#passWd").val()
      }
      await this.$store.dispatch('LoginByUsername', param).then(() => {
        MenuUtils(menuRouters, JSON.parse(auth.getRouters()))
        this.$router.addRoutes(menuRouters)
        this.$router.push({path: '/main'})
      })
    }
  }
}
</script>

<style scoped>

</style>
