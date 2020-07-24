<template>
<div>
  <section class="content">
    <div class="row">
      <div class="col-md-4">
        <div class="box box-primary">
          <div class="box-body">
            <div id="role_table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
              <div class="row">
                <div class="col-sm-12">
                  <div class="dataTables_filter">
                  <button class="btn btn-default" data-btn-type="add" @click="openAddGroup()">
                    <i class="fa fa-plus"></i>
                  </button>
                </div>
                </div>
              </div>
              <Datatable :items="groups" :columns="columns" v-on:getItem="getDataTableUser"/>
            </div>
          </div>
          <!-- /.box-body -->
        </div>
      </div>
      <div class="col-md-8">
        <div class="row">
          <div class="col-md-6">
            <div class="box box-primary" style="height: auto;">
              <div class="box-body box-profile">
                <div>
                  <div class="row">
                    <div class="col-sm-12">
                      <div class="box-header with-border" style="padding: 0px;" id="searchDiv_ff">
                        <h5 id="funcName" class="pull-left">{{$t("Titles.roleMenu")}}</h5>
                      </div>
                    </div>
                  </div>
                </div>
                <Tree :data="auth" ref="Tree" show-checkbox v-bind:empty-text='$t("messages.noRolemenu")' @on-select-change='showDetail()'></Tree>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="box box-primary">
              <div class="box-body" style="min-height: 420px">
                <div id="ff_table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                  <div class="row">
                    <div class="col-sm-12">
                      <div class="box-header with-border" style="padding: 0px;" id="searchDiv_ff">
                        <h5 id="funcName" style="font-size:14px" class="pull-left">{{$t("Titles.configAuth")}}</h5>
                        <div class="dataTables_filter">
                        <div class="btn-group">
                          <button class="btn btn-primary" v-on:click="openModal()">{{$t("buttons.add")}}</button>
                          <button class="btn btn-default" v-on:click="deleteAuth()">{{$t("buttons.delete")}}</button>
                        </div>
                      </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div v-if='showActionFlag'>
                  <table id="user_table" class="table table-bordered table-striped table-hover dataTable no-footer" style="width: 100%;" role="grid" aria-describedby="user_table_info">
                    <thead>
                      <tr role="row">
                        <th class="text-center sorting_disabled" rowspan="1" colspan="1" v-for="action in actionColumns">{{action}}</th>
                      </tr>
                    </thead>
                      <tbody>
                        <tr role="row" v-for="(action,index) in actions" v-bind:class="{selected:currentActionIndex === index}" v-on:click="getAuth(index)">
                          <td class="text-center">{{index+1}}
                          <td class="text-center">{{action}}
                          </td>
                        </tr>
                      </tbody>
                    </table>
                </div>
              </div>
              <div class="box-footer text-center">
                <button class="btn btn-primary" data-btn-type="save" @click="save()"><i class="fa fa-save">&nbsp;{{$t("buttons.save")}}</i></button>
              </div>
                  <!-- /.box-body -->
            </div>
                <!-- /.box -->
              </div>
            </div>
          </div>
        </div>

  </section>
  <Modal v-bind:title='$t("Titles.addAuth")' v-model="addAuthFlag" class-name="vertical-center-modal">
    <div slot="footer">
          <Button type="text" size="large" @click="authCancleClick()">{{$t("buttons.cancel")}}</Button>
          <Button type="primary" size="large" @click="addAuth()">{{$t("buttons.submit")}}</Button>
        </div>
    <Form ref="formaddauth" :label-width="100">
    <FormItem v-bind:label='$t("labels.auth")'>
      <Select multiple v-model="formItem" style="width:260px">
        <Option v-for="item in auths" :value="item.action_id" :key="item.value">{{ item.action_name }}</Option>
      </Select>
    </FormItem>
  </Form>
  </Modal>

  <Modal v-bind:title='$t("Titles.addRole")' v-model="addRoleFlag" class-name="vertical-center-modal">
        <div v-if="showlogicMessage">
          <Alert type="error">{{errors.logic}}</Alert>
        </div>
        <div slot="footer">
          <Button type="text" size="large" @click="roleCancleClick()">{{$t("buttons.cancel")}}</Button>
          <Button type="primary" size="large" @click="addRole()">{{$t("buttons.submit")}}</Button>
        </div>
        <Form ref="formaddrole" :model="formRole" :label-width="100">
          <FormItem v-bind:label='$t("labels.roleName")' prop="roleName" v-bind:error="errors.roleName">
              <Input v-model="formRole.roleName" v-bind:placeholder='$t("placeholders.enterRolename")'></Input>
          </FormItem>
          <FormItem v-bind:label='$t("labels.roleShortname")' prop="roleShortname" v-bind:error='errors.roleShortname'>
              <Input v-model="formRole.roleShortname" v-bind:placeholder='$t("placeholders.enterShortname")'></Input>
          </FormItem>
          <FormItem v-bind:label='$t("labels.roleDescription")' prop="roleDescription">
              <Input v-model="formRole.roleDescription" v-bind:placeholder='$t("placeholders.enterRoleDescription")'></Input>
          </FormItem>
        </Form>
    </Modal>
</div>
</template>

<script>
import HeadBar from '../../components/headerbar'
import Datatable from '../../components/datatable'
import {
  getUserAuths,
  addAuth,
  editAuth
} from '@/api/auth'
var treeData = [];
export default {
  data() {
    return {
      auths:[],
      groups: [],
      auth:[],
      actions:[],
      columns: [],
      actionColumns:[this.$t("columns.index"),this.$t("columns.role")],
      currentGroup:{},
      currentActionIndex:-1,
      showActionFlag:false,
      addAuthFlag:false,
      addRoleFlag:false,
      showlogicMessage:false,
      errors:[],
      formItem:[],
      formRole:{roleId:'',roleName:'',roleShortname:'',roleDescription:''},
    }
  },
  created: function() {
    this.getAllUserAuths('')
  },
  methods: {
    async getAllUserAuths(roleId) {
      var res = await getUserAuths(roleId)
      this.groups = res.data.data.roles;
      this.columns = res.data.data.columns;
      this.currentGroup = this.groups[0];
      this.auths = res.data.data.actions;
      if(roleId !== ''){
        this.auth = res.data.data.roleMenus
      }

    },
    async getAllAuths(roleId) {
      var res = await getUserAuths(roleId)
      this.auth = res.data.data.roleMenus
    },
    getDataTableUser(role){
      this.currentGroup = role;
      this.getAllAuths(role.role_id)
      this.currentActionIndex = -1
      this.showActionFlag = false;
    },
    showDetail(){
      this.actions = [];
      if(this.$refs.Tree.getSelectedNodes()[0].checked){
        var temp = this.$refs.Tree.getSelectedNodes()[0].actions;
        if(temp==='-' || temp ===''){
          this.actions = [];
        }else{
          var arr = temp.split('|');
          for(var i=0;i<arr.length;i++){
            this.actions.push(arr[i]);
          }
        }
      }else{
        this.actions = [];
        this.$refs.Tree.getSelectedNodes()[0].actions = '';
      }

      this.currentActionIndex = -1
      this.showActionFlag = true;
    },
    async save(){
      if(this.actions.length === 0){
        if(this.$refs.Tree.getSelectedNodes()[0] !== undefined){
          this.$refs.Tree.getSelectedNodes()[0].actions = '';
        }
      }else{
        this.$refs.Tree.getSelectedNodes()[0].actions = this.actions.join('|');
      }
      var roleData = {
        roleId: this.currentGroup.role_id,
        roleName:this.currentGroup.role_name,
        roleShortname:this.currentGroup.role_shortname,
        roleMenus: this.auth
      }


      var res = await editAuth(roleData)
      if(!res.data.meta.success){
        this.$Modal.error({
                            title: this.$t("messages.errorTitle"),
                            content: this.$t("messages.errorMessage")
                        });
      }else{
        this.$Modal.success({
                            title: this.$t("messages.successTitle"),
                            content: this.$t("messages.successMessage")
                        });
        this.getAllUserAuths('')
        this.actions = []
      }
    },
    openModal(){
      if(this.showActionFlag){
        this.addAuthFlag = true;
      }else{
        this.$Modal.warning({
                            title: this.$t("messages.warningTitle"),
                            content: this.$t("messages.warningSelectMenu")
                        });
      }

    },
    addAuth(){
      // alert(JSON.stringify(this.formItem));
      for(var i=0; i<this.formItem.length;i++){
        if(this.actions.indexOf(this.formItem[i]) >-1){
          this.$Modal.warning({
                            title: this.$t("messages.warningTitle"),
                            content: this.$t("messages.warningcheck") + this.formItem[i]
                        });

          this.addAuthFlag = true;
          return;
        }
      }
      for(var j=0; j<this.formItem.length;j++){
        this.actions.push(this.formItem[j]);
      }
      // alert(JSON.stringify(this.actions));
      this.$refs.Tree.getSelectedNodes()[0].actions = this.actions.join("|");
      this.formItem = [];
      this.addAuthFlag = false;

    },
    authCancleClick(){
      this.formItem = [];
      this.addAuthFlag = false;
    },
    getAuth(index){
      this.currentActionIndex = index;
    },
    deleteAuth(){
      if(this.currentActionIndex === -1){
        this.$Modal.warning({
                            title: this.$t("messages.warningTitle"),
                            content: this.$t("messges.warningSelectDelete")
                        });
      }else{
        this.actions.splice(this.currentActionIndex,1);
      }
    },
    openAddGroup(){
      this.$refs['formaddrole'].resetFields();
      this.formRole = {roleId:'',roleName:'',roleShortname:'',roleDescription:''};
      this.addRoleFlag = true;
    },
    async addRole(){
      var res = await addAuth(this.formRole);
      if(!res.data.meta.success){
        this.errors = res.data.data
        if(this.errors.logic !== undefined && this.errors.logic !==''){
          this.showlogicMessage = true;
        }else{
          this.showlogicMessage = false;
        }
      }else{
        this.showlogicMessage = false;
        this.$Modal.success({
                            title: this.$t("messages.successTitle"),
                            content: this.$t("messages.successMessage")
                        });
        this.getAllUserAuths('')
        this.formRole = {roleId:'',roleName:'',roleShortname:'',roleDescription:''};
        this.addRoleFlag = false;

      }

    },
    roleCancleClick(){
      this.$refs['formaddrole'].resetFields();
      this.formRole = {roleId:'',roleName:'',roleShortname:'',roleDescription:''};
      this.addRoleFlag = false;
    }
  },
  components: {
    HeadBar,
    Datatable
  }
}
</script>

<style scoped>
div.dataTables_filter {
  text-align: right;
}

div.dataTables_paginate ul.pagination {
  margin: 2px 0;
  white-space: nowrap;
}

div.dataTables_paginate {
  margin: 0;
  white-space: nowrap;
  text-align: right;
}

table.dataTable thead>tr>th {
  padding-right: 30px;
}

.table-bordered>thead>tr>th,
.table-bordered>thead>tr>td {
  border-bottom-width: 2px;
}

table.table-bordered.dataTable {
  border-collapse: separate !important;
}

.text-center {
  text-align: center;
}

.table>thead:first-child>tr:first-child>th {
  border-top: 0;
}

table.table-bordered.dataTable {
  border-collapse: separate !important;
}

table.dataTable {
  clear: both;
  margin-top: 6px !important;
  margin-bottom: 6px !important;
  max-width: none !important;
}

.table-bordered {
  border: 1px solid #f4f4f4;
}

.list-group {
  padding-left: 0;
  margin-bottom: 20px;
}

ol,
ul {
  margin-top: 0;
  margin-bottom: 10px;
}

.treeview .list-group-item {
  cursor: pointer;
}

.list-group-item:first-child {
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
}

.node-tree {}

.list-group-item {
  position: relative;
  display: block;
  padding: 10px 15px;
  margin-bottom: -1px;
  background-color: #fff;
  border: 1px solid #ddd;
}

.treeview span.indent {
  margin-left: 10px;
  margin-right: 10px;
}

.treeview span.icon {
  width: 12px;
  margin-right: 5px;
}

.glyphicon {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.selected{
  background-color : #08C !important;
}
</style>
