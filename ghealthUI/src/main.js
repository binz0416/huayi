// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import jquery from 'jquery'

/* Bootstrap */
import '../node_modules/bootstrap/dist/css/bootstrap.css'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/bootstrap/dist/css/bootstrap-theme.css'
import '../node_modules/bootstrap/dist/css/bootstrap-theme.min.css'
import '../node_modules/bootstrap/dist/js/bootstrap.min.js'
/* Font Awesome */
import '../node_modules/font-awesome/css/font-awesome.min.css'
/* Ionicons */
import '../node_modules/ionicons/dist/css/ionicons.min.css'
/* AdminLTE */
import './assets/css/AdminLTE.css'
import './assets/css/skins/skin-blue.min.css'
import './assets/js/adminlte.js'

/* homepage */
/* jQuery UI 1.11.4 */
import './assets/js/jquery-ui.min.js'
/* Date Picker */
import './assets/css/bootstrap-datepicker.min.css'
/* datepicker */
import './assets/js/bootstrap-datepicker.min.js'
/* jvectormap */
import './assets/css/jquery-jvectormap.css'
import './assets/js/jquery-jvectormap-1.2.2.min.js'
import './assets/js/jquery-jvectormap-world-mill-en.js'
/* datepicker */
import './assets/js/bootstrap-datepicker.min.js'
/* Sparkline */
import './assets/js/jquery.sparkline.min.js'

import './assets/js/jquery.slimscroll.min.js'

import './assets/js/jquery.aPrintArea.js'
import './assets/js/dashboard.js'

import './assets/js/bootstrap-treeview.min.js'
/* iview */
import iView from 'iview';
import 'iview/dist/styles/iview.css';
Vue.use(iView);
import MenuUtils from '@/utils/MenuUtils'
import auth from '@/utils/auth'

/* Froala Editor Base */
import './assets/css/froalaEditor.css'
import '../node_modules/froala-editor/css/froala_editor.css'
import '../node_modules/froala-editor/css/froala_editor.pkgd.min.css'
import '../node_modules/froala-editor/css/froala_style.css'
import '../node_modules/froala-editor/js/froala_editor.min.js'
/* Froala Editor Theme */
import '../node_modules/froala-editor/css/themes/royal.css'
/* Froala Editor fullscreen */
import '../node_modules/froala-editor/css/plugins/fullscreen.min.css'
import '../node_modules/froala-editor/js/plugins/fullscreen.min.js'
/* Froala Editor Plugin: table */
import '../node_modules/froala-editor/css/plugins/table.css'
import '../node_modules/froala-editor/js/plugins/table.min.js'
/* Froala Editor Plugin: file */
import '../node_modules/froala-editor/css/plugins/file.min.css'
import '../node_modules/froala-editor/js/plugins/file.min.js'
/* Froala Editor Plugin: link */
import '../node_modules/froala-editor/js/plugins/link.min.js'
/* Froala Editor Plugin: lists */
import '../node_modules/froala-editor/js/plugins/lists.min.js'

//import './assets/js/jatoolsPrinter.js'

import echarts from 'echarts'
Vue.prototype.$echarts = echarts


import VueI18n from 'vue-i18n'
Vue.use(VueI18n)
// ES6模块方式
import Es6Promise from 'es6-promise'
Es6Promise.polyfill()

Vue.config.productionTip = false

let token = auth.getToken()
if (token !== null) {
  //这里是防止用户手动刷新页面，整个app要重新加载,动态新增的路由，会消失，所以我们重新add一次
  let routes = []
  MenuUtils(routes, JSON.parse(auth.getRouters()))
  router.addRoutes(routes)
  let userInfo = JSON.parse(auth.getUser())
  store.commit('SET_USERID', userInfo.username)
  store.commit('SET_NAME', userInfo.fullname)
}

/* 路由钩子执行前 */
router.beforeEach((to, from, next) => {
  let token = auth.getToken()
  if (token === null && to.path.toLowerCase() !== '/login') {
    next({
      path: '/login'
    })
  } else {
    next()
  }
});

const i18n = new VueI18n({
    locale: 'zh',    // 语言标识
    messages: {
        'zh': require('./assets/js/locale/zh'),
        'en': require('./assets/js/locale/en')
    }
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: {
    App
  }
})
