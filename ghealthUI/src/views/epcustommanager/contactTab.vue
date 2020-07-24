<template>
<div class="tab-pane" id="tab_2">
  <section class="content">
    <div class="row">
      <div class="col-xs-12">
        <div class="box box-primary">
          <div class="box-body">
            <div id="user_table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
              <HeadBar :options="options" :buttons="buttons" v-on:getparentpageList="getHeaderInfo" />
              <Datatable ref='datatable' :items="contacts" :columns="columns" :totalCount="totalCount" v-on:getItem="getDataTableItem" />
              <Pagination :totalpage="totalpage" :curPage="currentPage" :totalCount="totalCount" :pagesize="currentpageList" v-on:getPage="getCurrentPage" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <Modal v-bind:title='$t("Titles.addContact")' v-model="addContact" class-name="vertical-center-modal" width="900">
    <div v-if="showlogicMessage">
      <Alert type="error">{{errors.logic}}</Alert>
    </div>
    <div slot="footer">
      <Button type="text" size="large" @click="btnAddCancle()">{{$t("buttons.cancel")}}</Button>
      <Button type="primary" size="large" @click="btnAddSave()">{{$t("buttons.submit")}}</Button>
    </div>
    <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec">
      <span style="margin-top:12px;position:Absolute ">{{$t("Titles.baseInfo")}}</span>
    </div>
    <Form ref="formadd" :model="addInfo" :label-width="100" style="margin-top:10px">
      <div class="col-md-6" style="margin-top:5px">
        <!-- 联系人名称 -->
        <FormItem v-bind:label='$t("labels.fName")' prop="name" v-bind:error='errors.name'>
          <Input v-model="addInfo.name" v-bind:placeholder='$t("placeholders.searchEnter")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6" style="margin-top:5px">
        <!-- 联系人性别 -->
        <FormItem v-bind:label='$t("labels.gender")' prop="sex">
          <RadioGroup v-model="addInfo.sex">
            <Radio label="0">{{$t("gender.male")}}</Radio>
            <Radio label="1">{{$t("gender.female")}}</Radio>
          </RadioGroup>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 所属客户 -->
        <FormItem v-bind:label='$t("labels.ofCustomer")' prop="epcustomerId" v-bind:error='errors.epcustomerId'>
          <Select v-model="addInfo.epcustomerId" style="width:200px">
                <Option v-for="(item,index) in customers" :value="item.epcustomerId" :key="item.epcustomerId">{{ item.name }}</Option>
              </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人职位 -->
        <FormItem v-bind:label='$t("labels.fPosition")' prop="post">
          <Input v-model="addInfo.post" v-bind:placeholder='$t("placeholders.enterPosition")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人电话 -->
        <FormItem v-bind:label='$t("labels.fTelephone")' prop="telephone" v-bind:error='errors.telephone'>
          <Input v-model="addInfo.telephone" v-bind:placeholder='$t("placeholders.enterMobile")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人邮箱 -->
        <FormItem v-bind:label='$t("labels.fEmail")' prop="email" v-bind:error='errors.email'>
          <Input v-model="addInfo.email" v-bind:placeholder='$t("placeholders.enterEmail")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人QQ -->
        <FormItem v-bind:label='$t("labels.fQQ")' prop="qqNo">
          <Input v-model="addInfo.qqNo" v-bind:placeholder='$t("placeholders.enterQQ")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人邮编-->
        <FormItem v-bind:label='$t("labels.postCode")' prop="zipCode">
          <Input v-model="addInfo.zipCode" v-bind:placeholder='$t("placeholders.enterPCode")'></Input>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 联系人地址-->
        <FormItem v-bind:label='$t("labels.contactAddress")' prop="address">
          <Input v-model="addInfo.address" v-bind:placeholder='$t("placeholders.enterLoginname")'></Input>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 备注-->
        <FormItem v-bind:label='$t("labels.fRemark")' prop="description">
          <Input v-model="addInfo.description" v-bind:placeholder='$t("placeholders.enterCAddress")'></Input>
        </FormItem>
      </div>
    </Form>
  </Modal>
  <Modal v-bind:title='$t("Titles.editContact")' v-model="editContact" class-name="vertical-center-modal" width="900">
    <div v-if="showlogicMessage">
      <Alert type="error">{{errors.logic}}</Alert>
    </div>
    <div slot="footer">
      <Button type="text" size="large" @click="btnEditCancle()">{{$t("buttons.cancel")}}</Button>
      <Button type="primary" size="large" @click="btnEditSave()">{{$t("buttons.submit")}}</Button>
    </div>
    <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec">
      <span style="margin-top:12px;position:Absolute ">{{$t("labels.mainInfo")}}</span>
    </div>
    <Form ref="formedit" :model="editInfo" :label-width="100" style="margin-top:10px">
      <div class="col-md-6" style="margin-top:5px">
        <!-- 联系人名称 -->
        <FormItem v-bind:label='$t("labels.fName")' prop="name" v-bind:error='errors.name'>
          <Input v-model="editInfo.name" v-bind:placeholder='$t("placeholders.searchEnter")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6" style="margin-top:5px">
        <!-- 联系人性别 -->
        <FormItem v-bind:label='$t("labels.gender")' prop="sex">
          <RadioGroup v-model="editInfo.sex">
            <Radio label="0">{{$t("gender.male")}}</Radio>
            <Radio label="1">{{$t("gender.female")}}</Radio>
          </RadioGroup>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 所属客户 -->
        <FormItem v-bind:label='$t("labels.ofCustomer")' prop="epcustomerId">
          <Select v-model="editInfo.epcustomerId" style="width:200px">
                <Option v-for="(item,index) in customers" :value="item.epcustomerId" :key="item.epcustomerId">{{ item.name }}</Option>
                </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人职位 -->
        <FormItem v-bind:label='$t("labels.fPosition")' prop="post">
          <Input v-model="editInfo.post" v-bind:placeholder='$t("placeholders.enterPosition")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人电话 -->
        <FormItem v-bind:label='$t("labels.fTelephone")' prop="telephone" v-bind:error='errors.telephone'>
          <Input v-model="editInfo.telephone" v-bind:placeholder='$t("placeholders.enterMobile")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人邮箱 -->
        <FormItem v-bind:label='$t("labels.fEmail")' prop="email">
          <Input v-model="editInfo.email" v-bind:placeholder='$t("placeholders.enterEmail")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人QQ -->
        <FormItem v-bind:label='$t("labels.fQQ")' prop="qqNo">
          <Input v-model="editInfo.qqNo" v-bind:placeholder='$t("placeholders.enterQQ")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 联系人邮编-->
        <FormItem v-bind:label='$t("labels.postCode")' prop="zipCode">
          <Input v-model="editInfo.zipCode" v-bind:placeholder='$t("placeholders.enterPCode")'></Input>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 联系人地址-->
        <FormItem v-bind:label='$t("labels.contactAddress")' prop="address">
          <Input v-model="editInfo.address" v-bind:placeholder='$t("placeholders.enterCAddress")'></Input>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 备注-->
        <FormItem v-bind:label='$t("labels.fRemark")' prop="description">
          <Input v-model="editInfo.description"></Input>
        </FormItem>
      </div>
    </Form>
  </Modal>
  <Modal v-bind:title='$t("Titles.viewContact")' v-model="viewContact" class-name="vertical-center-modal" width="900">
    <div slot="footer">
      <Button type="text" size="large" @click="btnViewCancle()">{{$t("buttons.cancel")}}</Button>
    </div>
    <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec">
      <span style="margin-top:12px;position:Absolute ">{{$t("labels.customInfo")}}</span>
    </div>
    <div class="col-md-12" style="margin-top:15px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.fName")}}</td>
          <td class="value">{{ viewInfo.name }}</td>
          <td class="text">{{$t("labels.gender")}}</td>
          <td class="value">{{ viewInfo.sex }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.ofCustomer")}}</td>
          <td class="value">{{ viewInfo.epcustomerName }}</td>
          <td class="text">{{$t("labels.fPosition")}}</td>
          <td class="value">{{ viewInfo.post }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.fTelephone")}}</td>
          <td class="value">{{ viewInfo.telephone }}</td>
          <td class="text">{{$t("labels.fEmail")}}</td>
          <td class="value">{{ viewInfo.email }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.fQQ")}}</td>
          <td class="value">{{ viewInfo.qqNo }}</td>
          <td class="text">{{$t("labels.postCode")}}</td>
          <td class="value">{{ viewInfo.zipCode }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.createTime")}}</td>
          <td class="value">{{ viewInfo.createTimeStr }}</td>
          <td class="text">{{$t("labels.lastUpdateTime")}}</td>
          <td class="value">{{ viewInfo.updateTimeStr }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.contactAddress")}}</td>
          <td class="value">{{ viewInfo.address }}</td>
          <td class="text">{{$t("labels.aRemark")}}</td>
          <td class="value">{{ viewInfo.description }}</td>
        </tr>
      </table>
    </div>
  </Modal>
</div>
</template>
<script>
import HeadBar from '../../components/headerbar'
import Pagination from '../../components/pagination'
import Datatable from '../../components/datatable'
import {
  selectContactAll,
  saveEqContact,
  deleEqContact
} from '@/api/epcontactsmanager'
export default {
  data() {
    return {
      customers: [],
      contacts: [],
      columns: [],
      addContact: false,
      editContact: false,
      viewContact: false,
      currentpageList: '10',
      currentPage: 1,
      totalCount: -1,
      currentContact: {},
      options: {
        "searchbar": 1,
        "buttonbar": 1,
        "tips": this.$t("placeholders.searchEnter")
      },
      buttons: [{
          action: "add",
          actionName: "添加"
        },
        {
          action: "edit",
          actionName: "修改"
        },
        {
          action: "delete",
          actionName: "删除"
        },
        {
          action: "view",
          actionName: "查看"
        }
      ],
      searchkey: "",
      addInfo: {
        contactsId: '', //联系人id
        name: '', //联系人姓名
        sex: '', //联系人性别
        epcustomerId: '', //所属客户
        post: '', //职位
        telephone: '', //电话
        email: '', //邮箱
        qqNo: '', //QQ
        zipCode: '', //邮编
        address: '', //地址
        description: '',
        apiflag: 'contacts'
      },
      editInfo: {
        contactsId: '', //联系人id
        name: '', //联系人姓名
        sex: '', //联系人性别
        epcustomerId: '', //所属客户
        post: '', //职位
        telephone: '', //电话
        email: '', //邮箱
        qqNo: '', //QQ
        zipCode: '', //邮编
        address: '', //地址
        description: '',
        apiflag: 'contacts'
      },
      viewInfo: {
        contactsId: '', //联系人id
        name: '', //联系人姓名
        sex: '', //联系人性别
        epcustomerName: '', //所属客户
        post: '', //职位
        telephone: '', //电话
        email: '', //邮箱
        qqNo: '', //QQ
        zipCode: '', //邮编
        createTimeStr: '',
        updateTimeStr: '',
        address: '', //地址
        description: '' //备注
      },
      errors: {},
      showlogicMessage: false,
      flag: false
    }
  },
  created: function() {
    this.getContacts(this.searchkey)
  },
  methods: {
    async getHeaderInfo(page) {
      this.currentpageList = page.pageList;
      this.currentPage = 1;
      this.action = page.action;
      this.searchkey = page.searchkey;
      if (this.action === "search") {
        var res = await selectContactAll(this.currentPage, this.currentpageList, this.searchkey)
        this.contacts = res.data.data.epContactsList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
      } else if (this.action === "add") {
        this.btnAddOpen()
      } else if (this.action === "edit") {
        this.btnEditOpen()
      } else if (this.action === "delete") {
        this.btnDeleteClick()
      } else if (this.action === "view") {
        this.btnViewOpen()
      }
    },
    async btnAddOpen(page) {
      this.$refs['formadd'].resetFields();
      this.addInfo.ownerUserid = this.currentUserId
      this.addContact = true;
    },
    async btnAddSave() {
      var res = await saveEqContact(this.addInfo)

      if (!res.data.meta.success) {
        this.errors = res.data.data;
        if (this.errors.logic !== undefined && this.errors.logic !== '') {
          this.showlogicMessage = true;
        } else {
          this.showlogicMessage = false;
        }
      } else {
        this.showlogicMessage = false;
        var res = await selectContactAll(1, 10, this.searchkey)
        this.contacts = res.data.data.epContactsList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
        this.addInfo = {
          contactsId: '',
          name: '',
          sex: '',
          epcustomerId: '',
          post: '',
          telephone: '',
          email: '',
          qqNo: '',
          zipCode: '',
          address: '',
          description: '',
          apiflag: 'contacts'
        }
        this.addContact = false;
      }
    },
    btnAddCancle() {
      this.errors = {};
      this.showlogicMessage = false;
      this.addInfo = {
        contactsId: '',
        name: '',
        sex: '',
        epcustomerId: '',
        post: '',
        telephone: '',
        email: '',
        qqNo: '',
        zipCode: '',
        address: '',
        description: '',
        apiflag: 'contacts'
      }
      this.addContact = false;
    },
    btnEditOpen(page) {
      if (this.flag !== true) {
        this.$Modal.warning({
          title: this.$t("messages.warningTitle"),
          content: this.$t("messages.warningSelectEdit")
        });
        return;
      }
      this.$refs['formedit'].resetFields();
      // console.log(JSON.stringify(this.currentCustomer));
      this.editInfo.contactsId = this.currentContact.contactsId
      this.editInfo.name = this.currentContact.name
      this.editInfo.sex = this.currentContact.sex
      this.editInfo.epcustomerId = this.currentContact.epcustomerId
      this.editInfo.post = this.currentContact.post
      this.editInfo.telephone = this.currentContact.telephone
      this.editInfo.email = this.currentContact.email
      this.editInfo.qqNo = this.currentContact.qqNo
      this.editInfo.zipCode = this.currentContact.zipCode
      this.editInfo.address = this.currentContact.address
      this.editInfo.description = this.currentContact.description
      this.editContact = true;
    },
    async btnEditSave() {
      // console.log(JSON.stringify(this.editInfo))
      var res = await saveEqContact(this.editInfo)
      if (!res.data.meta.success) {
        this.errors = res.data.data;
        if (this.errors.logic !== undefined && this.errors.logic !== '') {
          this.showlogicMessage = true;
        } else {
          this.showlogicMessage = false;
        }
      } else {
        this.showlogicMessage = false;
        this.$Modal.success({
          title: this.$t("messages.successTitle"),
          content: this.$t("messages.successMessage")
        });
        var res = await selectContactAll(1, 10, this.searchkey)
        this.contacts = res.data.data.epContactsList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
        this.$refs.datatable.deleteClass();
        this.editContact = false;
      }
    },
    btnEditCancle() {
      this.errors = {};
      this.showlogicMessage = false;
      this.formRoles = [];
      this.organizationId = '';
      this.editContact = false;
    },
    btnViewOpen() {
      if (this.flag !== true) {
        this.$Modal.warning({
          title: this.$t("messages.warningTitle"),
          content: this.$t("messages.warningSelectEdit")
        });
        return;
      }
      this.contacts = this.currentContact.contactsList
      this.viewInfo.contactsId = this.currentContact.contactsId
      this.viewInfo.name = this.currentContact.name
      this.viewInfo.sex = this.currentContact.sexDsp
      this.viewInfo.epcustomerName = this.currentContact.epcustomerName
      this.viewInfo.post = this.currentContact.post
      this.viewInfo.telephone = this.currentContact.telephone
      this.viewInfo.email = this.currentContact.email
      this.viewInfo.qqNo = this.currentContact.qqNo
      this.viewInfo.zipCode = this.currentContact.zipCode
      this.viewInfo.createTimeStr = this.currentContact.createTimeStr
      this.viewInfo.updateTimeStr = this.currentContact.updateTimeStr
      this.viewInfo.address = this.currentContact.address
      this.viewInfo.description = this.currentContact.description
      this.viewContact = true;
    },
    btnViewCancle() {
      this.viewContact = false;
    },
    async btnDeleteClick() {
      if (this.flag !== true) {
        this.$Modal.warning({
          title: this.$t("messages.warningTitle"),
          content: this.$t("messages.warningSelectEdit")
        });

        return;
      }
      const data = {
        contactsId: this.currentContact.contactsId,
        apiflag: 'contacts'
      }
      var res = await deleEqContact(data)
      if (!res.data.meta.success) {
        var errors = '<p>' + this.$t("messages.handleError") + '</p>';
        for (var i = 0; i < res.data.data.length; i++) {
          errors += '<p>' + (i + 1) + ' ' + res.data.data[i].error + '</p>'
        }
        this.$Modal.error({
          title: this.$t("messages.errorTitle"),
          content: errors
        });
      } else {
        this.showlogicMessage = false;
        this.$Modal.success({
          title: this.$t("messages.successTitle"),
          content: this.$t("messages.successMessage")
        });
        var res = await selectContactAll(1, 10, this.searchkey)
        this.contacts = res.data.data.epContactsList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
      }
    },
    getCurrentPage(page) {
      this.currentPage = page;
      this.getContacts(this.searchkey)
    },
    getDataTableItem(item) {
      this.flag = true;
      this.currentContact = item;
    },
    async getContacts(paraName) {
      var res = await selectContactAll(this.currentPage, this.currentpageList, paraName)
      this.contacts = res.data.data.epContactsList
      this.customers = res.data.data.epCustomerList
      this.totalCount = res.data.data.totalCount
      this.columns = res.data.data.columns
    }
  },
  components: {
    HeadBar,
    Datatable,
    Pagination
  },
  computed: {
    totalpage() {
      return Math.ceil(this.totalCount / this.currentpageList) || 0
    }
  }
}
</script>

<style >
.ivu-modal-body {
  padding: 2px;
  font-size: 12px;
  line-height: 0.1;
}

.ivu-input {
  display: inline-block;
  width: 80%;
  height: 28px;
  line-height: 1.2;
  padding: 4px 7px;
  font-size: 12px;
  border: 1px solid #dddee1;
  border-radius: 4px;
  color: #495060;
  background-color: #fff;
  background-image: none;
  position: relative;
  cursor: text;
  -webkit-transition: border .2s ease-in-out, background .2s ease-in-out, -webkit-box-shadow .2s ease-in-out;
  transition: border .2s ease-in-out, background .2s ease-in-out, -webkit-box-shadow .2s ease-in-out;
  transition: border .2s ease-in-out, background .2s ease-in-out, box-shadow .2s ease-in-out;
  transition: border .2s ease-in-out, background .2s ease-in-out, box-shadow .2s ease-in-out, -webkit-box-shadow .2s ease-in-out;
}

.ivu-modal-header {
  border-bottom: 0px solid #e9eaec;
  padding: 14px 16px;
  line-height: 1;
}

.ivu-modal-footer {
  border-top: 0px solid #e9eaec;
  padding: 12px 18px 12px 18px;
  text-align: right;
}

.text {
  width: 60px;
  padding: 0px 10px 0px 5px;
  text-align: right;
}

.value {
  width: 150px;
  padding: 0px 0px 0px 5px;
  text-align: left;
}
</style>
