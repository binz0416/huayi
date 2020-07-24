import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login'
import Home from '@/views/home'
import Main from '@/views/Main'

Vue.use(Router)

//所有权限通用路由表
//如首页和登录页和一些不用权限的公用页面
export const constantRouterMap = [
  {
    path: '/',
    component: Home,
    redirect: '/main',
    name: '华医云健康',
    leaf: false,
    hidden: true,
    children: [
      {
        path: '/main',
        component: Main,
        name: '首页',
        leaf: true,
        hidden: true
    }]
  },
  {
    path: '/login',
    component: Login,
    name: 'login',
    leaf: true,
    hidden: true
  }
]

export default new Router({
  //mode: 'history',
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRouterMap
})
