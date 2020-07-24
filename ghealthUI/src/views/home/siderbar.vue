<template>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">

    <ul class="sidebar-menu" data-widget="tree">
      <li class="header">MENU</li>
      <li class="treeview" v-for="menu in menus">
        <a href='#'>
           <i :class="menu.icon"></i> <span>{{menu.name}}</span>
           <span class="pull-right-container">
             <i class="fa fa-angle-left pull-right"></i>
           </span>
         </a>
        <ul class="treeview-menu">
          <li v-for="menuItem in menu.children">
            <router-link :to="menu.path + '/' + menuItem.path"><i class="fa fa-sort-down"></i>{{menuItem.name}}</router-link>
          </li>
        </ul>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>
</template>

<script>
import store from '@/utils/storage'

export default {
  data() {
    return {
      menus: JSON.parse(store.getSessionItem('routers'))
    }

  },
  mounted : function(){
    $(window).resize()
    $.fn.tree.call($('[data-widget="tree"]'))
  }
}

</script>

<style>
.sidebar-menu > li {
  list-style: none;
  position: relative;
  margin: 0;
  padding: 0;
}
.treeview-menu li{
  list-style: none;
  position: relative;
  margin: 0;
  padding: 0;
}
</style>
