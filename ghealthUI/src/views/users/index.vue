<template>
<div class="row">
  <div class="col-xs-12">
    <div class="box box-primary">
      <div class="box-body">
        <div class="box-body">
          <div id="user_table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
            <div class="row" align="right">
              <div class="col-sm-12">
                <div class="dataTables_filter" id="searchDiv">
                  <Input v-model="searchKey" v-bind:placeholder='$t("placeholders.searchEnter")' class="searchClass"/>
                  <div class="btn-group">
                    <button class="btn btn-primary" v-on:click="search()">{{$t("buttons.search")}}</button>
                    <button class="btn btn-default" v-on:click="clear()">{{$t("buttons.reset")}}</button>
                  </div>
                  <div class="btn-group">
                    <button class="btn btn-default" @click="openAdd()">{{$t("buttons.add")}}</button>
                    <button class="btn btn-default" @click="openEdit()">{{$t("buttons.edit")}}</button>
                  </div>
                </div>
              </div>
            </div>
            <Datatable ref="datatable" :items="users" :columns="columns" :totalCount="totalCount" v-on:getItem="getDataTableUser"/>
            <Pagination :totalpage="totalpage" :curPage="currentPage" :totalCount="totalCount" :pagesize="currentpageList" v-on:getPage="getCurrentPage" />
          </div>
        </div>
      </div>
    </div>
  </div>


  <Modal v-bind:title='$t("Titles.addUser")' v-model="addUser" class-name="vertical-center-modal" width="900" >
        <div v-if="showlogicMessage">
          <Alert type="error">{{errors.logic}}</Alert>
        </div>
        <div slot="footer">
          <Button type="text" size="large" @click="cancelClick()">{{$t("buttons.cancel")}}</Button>
          <Button type="primary" size="large" @click="addSave()">{{$t("buttons.submit")}}</Button>
        </div>
        <Form ref="formadd" :model="formInfoadd" :label-width="100">
          <div class="col-md-6">
            <FormItem v-bind:label='$t("labels.name")' prop="fullname">
              <Input v-model="formInfoadd.fullname" v-bind:placeholder='$t("placeholders.searchEnter")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.mobile")' prop="tel" v-bind:error='errors.tel'>
              <Input v-model="formInfoadd.tel" v-bind:placeholder='$t("placeholders.enterMobile")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.email")' prop="email" v-bind:error='errors.email'>
              <Input v-model="formInfoadd.email" v-bind:placeholder='$t("placeholders.enterEmail")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.gender")' prop="sex">
              <RadioGroup v-model="formInfoadd.sex">
                <Radio label="0">{{$t("gender.male")}}</Radio>
                <Radio label="1">{{$t("gender.female")}}</Radio>
              </RadioGroup>
            </FormItem>
            <FormItem v-bind:label='$t("labels.organizationId")'>
              <Select v-model="formInfoadd.organizationId" style="width:260px">
                <Option v-for="(item,index) in companies" :value="item.id" :key="index">{{ item.name }}</Option>
              </Select>
            </FormItem>
          </div>
          <div class="col-md-6">
            <FormItem v-bind:label='$t("labels.loginname")' prop="username" v-bind:error='errors.username'>
              <Input v-model="formInfoadd.username" v-bind:placeholder='$t("placeholders.enterLoginname")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.password")' prop="password" v-bind:error='errors.password'>
              <Input type="password" v-model="formInfoadd.password"></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.auth")'>
              <Select multiple v-model="formRoles" style="width:260px">
                <Option v-for="(item,index) in groups" :value="item.id" :key="index">{{ item.name }}</Option>
              </Select>
            </FormItem>
          </div>
        <FormItem>
        </FormItem>
        </Form>
    </Modal>
    <Modal v-bind:title='$t("Titles.editUser")' v-model="editUser" class-name="vertical-center-modal" width="900">
        <div v-if="showlogicMessage">
          <Alert type="error">{{errors.logic}}</Alert>
        </div>
        <div slot="footer">
          <Button type="text" size="large" @click="editCancelClick()">{{$t("buttons.cancel")}}</Button>
          <Button type="primary" size="large" @click="editSave()">{{$t("buttons.submit")}}</Button>
        </div>
        <Form ref="formedit" :model="formInfoedit" :label-width="100">
          <div class="col-md-6">
            <FormItem v-bind:label='$t("labels.name")'>
              <Input v-model="formInfoedit.fullname" v-bind:placeholder='$t("placeholders.searchEnter")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.mobile")' prop="tel" v-bind:error='errors.tel'>
              <Input v-model="formInfoedit.tel" v-bind:placeholder='$t("placeholders.enterMobile")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.email")' prop="email" v-bind:error='errors.email'>
              <Input v-model="formInfoedit.email" v-bind:placeholder='$t("placeholders.enterEmail")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.gender")' prop="sex">
              <RadioGroup v-model="formInfoedit.sex">
                <Radio label="0">{{$t("gender.male")}}</Radio>
                <Radio label="1">{{$t("gender.female")}}</Radio>
              </RadioGroup>
            </FormItem>
            <FormItem v-bind:label='$t("labels.organizationId")'>
              <Select v-model="formInfoedit.organizationId" style="width:260px">
                <Option v-for="(item,index) in companies" :value="item.id" :key="index">{{ item.name }}</Option>
              </Select>
            </FormItem>
            <FormItem hidden>
              <Input v-model="formInfoedit.userid"></Input>
            </FormItem>
          </div>
          <div class="col-md-6">
            <FormItem v-bind:label='$t("labels.loginname")' prop="username" v-bind:error='errors.username'>
              <Input v-model="formInfoedit.username" v-bind:placeholder='$t("placeholders.enterLoginname")'></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.password")' prop="password" v-bind:error='errors.password'>
              <Input type="password" v-model="formInfoedit.password"></Input>
            </FormItem>
            <FormItem v-bind:label='$t("labels.auth")'>
              <Select multiple v-model="formRoles" style="width:260px">
                <Option v-for="(item,index) in groups" :value="item.id" :key="index">{{ item.name }}</Option>
              </Select>
            </FormItem>
          </div>
        <FormItem>
        </FormItem>
        </Form>
    </Modal>
</div>
</template>

<script>
import Pagination from '../../components/pagination'
import Datatable from '../../components/datatable'
import {
  selectUsers,
  addUser,
  editUser,
  getUserAuthOptions
} from '@/api/users'
import {
  getUserAuths
} from '@/api/auth'
export default {
  data() {
    return {
      currentIndex:-1,
      searchKey: '',
      addUser: false,
      editUser: false,
      currentUser:{},
      users: [],
      groups:[],
      companies:[],
      columns: [],
      currentpageList: '10',
      currentPage: 1,
      totalCount: -1,
      formInfoadd: {
                    fullname: '',
                    email: '',
                    tel: '',
                    sex: 1,
                    username:'',
                    password:'',
                    userRoles:[],
                    organizationId:''
      },
      formInfoedit: {
                    fullname: '',
                    email: '',
                    tel: '',
                    sex: 1,
                    username:'',
                    password:'',
                    pwdChanged:'0',
                    userRoles:[],
                    userid:'',
                    organizationId:''
      },
      formRoles:[],
      errors:{},
      showlogicMessage:false,
      prePasswd:'',
      flag:false
    }
  },
  created: async function() {
    var res = await selectUsers(this.currentPage, this.currentpageList, '')
    this.users = res.data.data.userInfoList
    this.currentUser = this.users[0];
    this.totalCount = res.data.data.totalCount
    this.columns = res.data.data.columns
  },
  components: {
    Datatable,
    Pagination
  },
  computed: {
    totalpage() {
      return Math.ceil(this.totalCount / this.currentpageList) || 0
    }
  },
  methods:{
    getDataTableUser(user){
      this.flag = true;
      this.currentUser = user;
    },
    getCurrentPage(page){
      this.currentPage = page;
    },
    async search(){
      let res = await selectUsers(1, 10, this.searchKey)
      this.users = res.data.data.userInfoList
      this.totalCount = res.data.data.totalCount
      this.columns = res.data.data.columns
      this.flag = false;
      this.$refs.datatable.deleteClass();
    },
    clear(){
      this.searchKey = "";
    },
    async openAdd(){
      this.$refs['formadd'].resetFields();
      var res = await getUserAuthOptions()
      this.groups = res.data.data.roles;
      this.companies = res.data.data.organizations;
      this.addUser = true;
    },
    async addSave(){
      this.formInfoadd.userRoles = [];
      for(var i=0;i<this.formRoles.length;i++){
        var userRoles = {roleId:''};
        userRoles.roleId = this.formRoles[i];
        this.formInfoadd.userRoles.push(userRoles);
      }
      var res = await addUser(this.formInfoadd)

      if (!res.data.meta.success) {
        this.errors = res.data.data;
        if(this.errors.logic !== undefined && this.errors.logic !==''){
          this.showlogicMessage = true;
        }else{
          this.showlogicMessage = false;
        }
      } else {
        this.showlogicMessage = false;
        let res = await selectUsers(1, 10, this.searchKey)
        this.users = res.data.data.userInfoList
        // this.currentUser = this.users[0];
        this.totalCount = res.data.data.totalCount
        // this.$refs.datatable.deleteClass();
        this.formInfoadd={
                      fullname: '',
                      email: '',
                      tel: '',
                      sex: '',
                      username:'',
                      password:'',
                      userRoles:[],
                      organizationId:''}
        this.formRoles = [];
        // this.flag = false;
        this.addUser = false;
      }
    },
    async editSave(){
      this.formInfoedit.userRoles = [];
      for(var i=0;i<this.formRoles.length;i++){
        var userRoles = {roleId:''};
        userRoles.roleId = this.formRoles[i];
        this.formInfoedit.userRoles.push(userRoles);
      }
      if(this.prePasswd !== this.formInfoedit.password){
        this.formInfoedit.pwdChanged = 1;
      }else{
        this.formInfoedit.pwdChanged = 0;
      }
      this.formInfoedit.userid = this.currentUser.userid

      var res = await editUser(this.formInfoedit)
      if (!res.data.meta.success) {
        this.errors = res.data.data;
        if(this.errors.logic !== undefined && this.errors.logic !==''){
          this.showlogicMessage = true;
        }else{
          this.showlogicMessage = false;
        }
      } else {
        this.showlogicMessage = false;
        this.$Modal.success({
                            title: this.$t("messages.successTitle"),
                            content: this.$t("messages.successMessage")
                        });
        let res = await selectUsers(1, 10, this.searchKey)
        this.users = res.data.data.userInfoList
        // this.resetCurrentUser();
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
        this.$refs.datatable.deleteClass();
        this.formRoles = [];
        this.formInfoedit.organizationId='';
        // this.flag = false;
        this.editUser= false;
      }
    },
    cancelClick(){
      // this.$refs['formadd'].resetFields();
      // this.$refs.datatable.deleteClass();
      this.errors = {};
      this.showlogicMessage = false;
      this.formRoles = [];
      this.formInfoadd.organizationId ='';
      // this.flag = false;
      this.addUser = false;
    },
    editCancelClick(){
      // this.$refs['formedit'].resetFields();
      // this.$refs.datatable.deleteClass();
      this.errors = {};
      this.formRoles = [];
      this.showlogicMessage = false;
      this.formInfoedit.organizationId ='';
      // this.flag = false;
      this.editUser = false;
    },
    async openEdit(){
      if(this.flag!==true){
        this.$Modal.warning({
                            title: this.$t("messages.warningTitle"),
                            content: this.$t("messages.warningSelectEdit")
                        });
        return;
      }
      this.$refs['formedit'].resetFields();
      var res = await getUserAuthOptions()

      this.groups = res.data.data.roles;
      this.companies = res.data.data.organizations;
      this.formInfoedit.fullname = this.currentUser.fullname;
      this.formInfoedit.email = this.currentUser.email;
      this.formInfoedit.tel = this.currentUser.tel;
      this.formInfoedit.sex = this.currentUser.sex;
      this.formInfoedit.username = this.currentUser.username;
      this.formInfoedit.password = this.currentUser.password;
      this.formInfoedit.organizationId = this.currentUser.organizationId;
      this.prePasswd = this.currentUser.password;
      if(this.currentUser.groupid !== undefined){
        var arr = this.currentUser.groupid.split(',');
        this.formRoles = [];
        for(var i=0;i<arr.length;i++){
          this.formRoles.push(arr[i]);
        }
      }

      this.editUser= true;
    },
    resetCurrentUser(){
      this.currentUser.fullname = this.formInfoedit.fullname;
      this.currentUser.email = this.formInfoedit.email;
      this.currentUser.tel = this.formInfoedit.tel;
      this.currentUser.sex = this.formInfoedit.sex;
      this.currentUser.username = this.formInfoedit.username;
      this.currentUser.password = this.formInfoedit.password;
      this.currentUser.organizationId = this.formInfoedit.organizationId;
      this.currentUser.groupid = this.formRoles.join(',');
    }
  }
}
</script>

<style scoped>
.searchClass {
  display: inline-block;
  width: auto;
  vertical-align: middle;
}
div.dataTables_filter {
  text-align: right;
}
.vertical-center-modal {
  display: flex;
  align-items: center;
  justify-content: center;

  .ivu-modal {
    top: 0;
  }
}
</style>
