<template>
<div class="row">
  <div class="col-sm-12">
    <div class="dataTables_filter" id="searchDiv">
      <input v-if="options.searchbar" v-bind:placeholder="options.tips" class="form-control" ref="key" />
      <div v-if="options.searchbar" class="btn-group">
        <button type="button" class="btn btn-primary" v-on:click="actionButtonClick('search')">{{$t("buttons.search")}}</button>
        <button type="button" class="btn btn-default" v-on:click="clear()">{{$t("buttons.reset")}}</button>
      </div>
      <div v-if="options.buttonbar" class="btn-group">
        <button v-for="button in buttons" class="btn btn-default" type="button" v-on:click="actionButtonClick(button.action)">{{button.actionName}}</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  props: ['dropdowns', 'options', 'buttons'],
  data() {
    return {
      page: {
        "pageList": '10',
        "currentpage": 1,
        "action": "",
        "searchkey": ""
      }

    }
  },
  methods: {
    getSelectedPage(aa) {
      this.page.pageList = aa.target.value;
      this.$emit("getparentpageList", this.page)
    },
    actionButtonClick(aa) {
      this.page.action = aa
      this.page.searchkey = this.$refs.key.value
      this.$emit("getparentpageList", this.page)
    },
    clear() {
      this.$refs.key.value = "";
    }
  }
}
</script>

<style scoped>
div.dataTables_filter {
  text-align: right;
}

div.dataTables_paginate {
  margin: 0;
  white-space: nowrap;
  text-align: right;
  height: 41px;
}

div.dataTables_paginate ul.pagination {
  margin: 2px 0;
  white-space: nowrap;
}
</style>
