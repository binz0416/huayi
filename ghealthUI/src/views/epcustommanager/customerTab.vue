<template>
<div class="tab-pane active" id="tab_1">
  <section class="content">
    <div class="row">
      <div class="col-xs-12">
        <div class="box box-primary">
          <div class="box-body">
            <div id="user_table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
              <HeadBar :options="options" :buttons="buttons" v-on:getparentpageList="getHeaderInfo" />
              <Datatable ref='datatable' :items="customers" :columns="columns" :totalCount="totalCount" v-on:getItem="getDataTableItem" />
              <Pagination :totalpage="totalpage" :curPage="currentPage" :totalCount="totalCount" :pagesize="currentpageList" v-on:getPage="getCurrentPage" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <Modal v-bind:title='$t("Titles.addCustomer")' v-model="addCustomer" class-name="vertical-center-modal" width="900">
    <div v-if="showlogicMessage">
      <Alert type="error">{{errors.logic}}</Alert>
    </div>
    <div slot="footer">
      <Button type="text" size="large" @click="btnAddCancle()">{{$t("buttons.cancel")}}</Button>
      <Button type="primary" size="large" @click="btnAddSave()">{{$t("buttons.submit")}}</Button>
    </div>
    <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec">
      <span style="margin-top:12px;position:Absolute ">{{$t("labels.mainInfo")}}</span>
    </div>
    <Form ref="formadd" :model="addInfo" :label-width="100" style="margin-top:10px">
      <div class="col-md-6" style="margin-top:5px">
        <!-- 负责人 -->
        <FormItem v-bind:label='$t("labels.bUser")' prop="fullname">
          <Select v-model="addInfo.ownerUserid" style="width:200px">
                <Option v-for="(item,index) in userList" :value="item.userid" :key="item.userid">{{ item.username }}</Option>
              </Select>
        </FormItem>
      </div>
      <div class="col-md-6" style="margin-top:5px">
        <!-- 客户名称 -->
        <FormItem v-bind:label='$t("labels.customerName")' prop="name" v-bind:error='errors.name'>
          <Input v-model="addInfo.name" v-bind:placeholder='$t("placeholders.enterCName")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 客户行业 -->
        <FormItem v-bind:label='$t("labels.cIndustry")' prop="industry">
          <Select v-model="addInfo.industry" style="width:200px">
                <Option :value='$t("optionText.education")' :key="0"></Option>
                <Option :value='$t("optionText.business")' :key="1"></Option>
                <Option :value='$t("optionText.trade")' :key="2"></Option>
              </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 客户信息来源 -->
        <FormItem v-bind:label='$t("labels.cInfoSource")' prop="origin">
          <Select v-model="addInfo.origin" style="width:200px">
                <Option :value='$t("optionText.teleMarketing")' :key="0"></Option>
                <Option :value='$t("optionText.netMarketing")' :key="1"></Option>
              </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 公司性质 -->
        <FormItem v-bind:label='$t("labels.companyNature")' prop="ownership">
          <RadioGroup v-model="addInfo.ownership">
            <Radio :label='$t("companyNature.joint")'>{{$t("companyNature.joint")}}</Radio>
            <Radio :label='$t("companyNature.enterprise")'>{{$t("companyNature.enterprise")}}</Radio>
            <Radio :label='$t("companyNature.privately")'>{{$t("companyNature.privately")}}</Radio>
          </RadioGroup>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 邮编 -->
        <FormItem v-bind:label='$t("labels.postCode")' prop="zipCode">
          <Input v-model="addInfo.zipCode" v-bind:placeholder='$t("placeholders.enterPCode")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 年营业额 -->
        <FormItem v-bind:label='$t("labels.yearTurnover")' prop="annualRevenue">
          <Select v-model="addInfo.annualRevenue" style="width:200px">
                <Option :value='$t("optionText.oneHThousand")' :key="0"></Option>
                <Option :value='$t("optionText.twoHThousand")' :key="1"></Option>
                <Option :value='$t("optionText.fiveHThousand")' :key="2"></Option>
              </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 评分 -->
        <FormItem v-bind:label='$t("labels.score")' prop="rating">
          <RadioGroup v-model="addInfo.rating">
            <Radio :label='$t("score.oneS")'>{{$t("score.oneS")}}</Radio>
            <Radio :label='$t("score.twoS")'>{{$t("score.twoS")}}</Radio>
            <Radio :label='$t("score.threeS")'>{{$t("score.threeS")}}</Radio>
          </RadioGroup>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 客户地址 -->
        <FormItem v-bind:label='$t("labels.cAddress")' prop="address">
          <Input v-model="addInfo.address" v-bind:placeholder='$t("placeholders.enterCAddress")'></Input>
        </FormItem>
      </div>
      <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec">
        <span style="margin-top:12px;position:Absolute ">{{$t("labels.afdditionalInfo")}}</span>
      </div>
      <div class="col-md-12" style="margin-top:5px">
        <!--公司人数-->
        <FormItem v-bind:label='$t("labels.aMemberCount")' prop="noOfEmployees">
          <Select v-model="addInfo.noOfEmployees" style="width:200px">
                <Option :value='$t("optionText.fiveMember")'  :key="0"></Option>
                <Option :value='$t("optionText.twentyMember")'  :key="1"></Option>
                <Option :value='$t("optionText.fiftyMember")'  :key="2"></Option>
              </Select>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 备注-->
        <FormItem v-bind:label='$t("labels.aRemark")' prop="description">
          <Input v-model="addInfo.description"></Input>
        </FormItem>
      </div>
    </Form>
  </Modal>
  <Modal v-bind:title='$t("Titles.editCustomer")' v-model="editCustomer" class-name="vertical-center-modal" width="900">
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
        <!-- 负责人 -->
        <FormItem v-bind:label='$t("labels.bUser")' prop="fullname">
          <Select v-model="editInfo.ownerUserid" style="width:200px">
                  <Option v-for="(item,index) in userList" :value="item.userid" :key="item.userid">{{ item.username }}</Option>
                </Select>
        </FormItem>
      </div>
      <div class="col-md-6" style="margin-top:5px">
        <!-- 首要联系人 -->
        <FormItem v-bind:label='$t("labels.firstContact")' prop="fullname">
          <Select v-model="editInfo.contactsId" style="width:200px">
                  <Option v-for="(item,index) in contactList" :value="item.contactsId" :key="item.contactsId">{{ item.name }}</Option>
                </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 客户名称 -->
        <FormItem v-bind:label='$t("labels.customerName")' prop="name" v-bind:error='errors.name'>
          <Input v-model="editInfo.name" v-bind:placeholder='$t("placeholders.enterCName")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 客户行业 -->
        <FormItem v-bind:label='$t("labels.cIndustry")' prop="industry">
          <Select v-model="editInfo.industry" style="width:200px">
                  <Option :value='$t("optionText.education")' :key="0"></Option>
                  <Option :value='$t("optionText.business")' :key="1"></Option>
                  <Option :value='$t("optionText.trade")' :key="2"></Option>
                </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 客户信息来源 -->
        <FormItem v-bind:label='$t("labels.cInfoSource")' prop="origin">
          <Select v-model="editInfo.origin" style="width:200px">
                  <Option :value='$t("optionText.teleMarketing")' :key="0"></Option>
                  <Option :value='$t("optionText.netMarketing")' :key="1"></Option>
                </Select>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 公司性质 -->
        <FormItem v-bind:label='$t("labels.companyNature")' prop="ownership">
          <RadioGroup v-model="editInfo.ownership">
            <Radio :label='$t("companyNature.joint")'>{{$t("companyNature.joint")}}</Radio>
            <Radio :label='$t("companyNature.enterprise")'>{{$t("companyNature.enterprise")}}</Radio>
            <Radio :label='$t("companyNature.privately")'>{{$t("companyNature.privately")}}</Radio>
          </RadioGroup>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 邮编 -->
        <FormItem v-bind:label='$t("labels.postCode")' prop="zipCode">
          <Input v-model="editInfo.zipCode" v-bind:placeholder='$t("placeholders.enterPCode")'></Input>
        </FormItem>
      </div>
      <div class="col-md-6">
        <!-- 年营业额 -->
        <FormItem v-bind:label='$t("labels.yearTurnover")' prop="annualRevenue">
          <Select v-model="editInfo.annualRevenue" style="width:200px">
                  <Option :value='$t("optionText.oneHThousand")' :key="0"></Option>
                  <Option :value='$t("optionText.twoHThousand")' :key="1"></Option>
                  <Option :value='$t("optionText.fiveHThousand")' :key="2"></Option>
                </Select>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 评分 -->
        <FormItem v-bind:label='$t("labels.score")' prop="rating">
          <RadioGroup v-model="editInfo.rating">
            <Radio :label='$t("score.oneS")'>{{$t("score.oneS")}}</Radio>
            <Radio :label='$t("score.twoS")'>{{$t("score.twoS")}}</Radio>
            <Radio :label='$t("score.threeS")'>{{$t("score.threeS")}}</Radio>
          </RadioGroup>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 客户地址 -->
        <FormItem v-bind:label='$t("labels.cAddress")' prop="address">
          <Input v-model="editInfo.address" v-bind:placeholder='$t("placeholders.enterCAddress")'></Input>
        </FormItem>
      </div>
      <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec">
        <span style="margin-top:12px;position:Absolute ">{{$t("labels.afdditionalInfo")}}</span>
      </div>
      <div class="col-md-12" style="margin-top:5px">
        <!--公司人数-->
        <FormItem v-bind:label='$t("labels.aMemberCount")' prop="noOfEmployees">
          <Select v-model="editInfo.noOfEmployees" style="width:200px">
                  <Option :value='$t("optionText.fiveMember")'  :key="0"></Option>
                  <Option :value='$t("optionText.twentyMember")'  :key="1"></Option>
                  <Option :value='$t("optionText.fiftyMember")'  :key="2"></Option>
                </Select>
        </FormItem>
      </div>
      <div class="col-md-12">
        <!-- 备注-->
        <FormItem v-bind:label='$t("labels.aRemark")' prop="description">
          <Input v-model="editInfo.description"></Input>
        </FormItem>
      </div>
    </Form>
  </Modal>
  <Modal v-bind:title='$t("Titles.viewCustomer")' v-model="viewCustomer" class-name="vertical-center-modal" width="900">
    <div slot="footer">
      <Button type="text" size="large" @click="btnViewCancle()">{{$t("buttons.cancel")}}</Button>
    </div>
    <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec">
      <span style="margin-top:12px;position:Absolute ">{{$t("labels.customInfo")}}</span>
    </div>
    <div class="col-md-12" style="margin-top:15px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.createTime")}}</td>
          <td class="value">{{ viewInfo.createTimeStr }}</td>
          <td class="text">{{$t("labels.createUser")}}</td>
          <td class="value">{{ viewInfo.creatorUserName }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.bUser")}}</td>
          <td class="value">{{ viewInfo.ownerUserName }}</td>
          <td class="text">{{$t("labels.lastUpdateTime")}}</td>
          <td class="value">{{ viewInfo.updateTimeStr }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.firstContact")}}</td>
          <td class="value">{{ viewInfo.contactsName }}</td>
          <td class="text">{{$t("labels.customerName")}}</td>
          <td class="value">{{ viewInfo.name }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.cIndustry")}}</td>
          <td class="value">{{ viewInfo.industry }}</td>
          <td class="text">{{$t("labels.companyNature")}}</td>
          <td class="value">{{ viewInfo.ownership }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.postCode")}}</td>
          <td class="value">{{ viewInfo.zipCode }}</td>
          <td class="text">{{$t("labels.aMemberCount")}}</td>
          <td class="value">{{ viewInfo.noOfEmployees }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.yearTurnover")}}</td>
          <td class="value">{{ viewInfo.annualRevenue }}</td>
          <td class="text">{{$t("labels.score")}}</td>
          <td class="value">{{ viewInfo.rating }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="margin-top:25px">
      <table style="width:100%">
        <tr>
          <td class="text">{{$t("labels.cAddress")}}</td>
          <td class="value">{{ viewInfo.address }}</td>
          <td class="text">{{$t("labels.aRemark")}}</td>
          <td class="value">{{ viewInfo.description }}</td>
        </tr>
      </table>
    </div>
    <div class="col-md-12" style="height:25px;border-bottom:1px solid #e9eaec; margin-top:15px">
      <span style="margin-top:12px;position:Absolute ">{{$t("labels.contactInfo")}}</span>
    </div>
    <div class="col-md-12" style="margin-top:10px;">
      <!-- 联系人信息 -->
      <Datatable style="height:150px;overflow:scroll" ref='datatable1' :items="contacts" :columns="contactClolumns" :totalCount="totalCount" />
    </div>
  </Modal>
</div>
</template>
<script>
import HeadBar from '../../components/headerbar'
import Pagination from '../../components/pagination'
import Datatable from '../../components/datatable'
import {
  selectCoutomAll,
  saveEqCustomer,
  deleEqCustomer
} from '@/api/epcustommanager'
export default {
  data() {
    return {
      customers: [],
      contacts: [],
      columns: [],
      contactClolumns: [],
      userList: [],
      contactList: [],
      addCustomer: false,
      editCustomer: false,
      viewCustomer: false,
      currentpageList: '10',
      currentPage: 1,
      totalCount: -1,
      currentCustomer: {},
      currentUserId: 0,
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
        epcustomerId: '',
        ownerUserid: '',
        name: '',
        industry: '',
        origin: '',
        ownership: '',
        zipCode: '',
        annualRevenue: '',
        rating: '',
        address: '',
        contactsId: '',
        noOfEmployees: '',
        description: '',
        apiflag: 'custom'
      },
      editInfo: {
        epcustomerId: '', //客户id
        ownerUserid: '', //负责人id
        name: '', //客户姓名
        industry: '', //客户行业
        origin: '', //客户信息来源
        ownership: '', //客户公司性质
        zipCode: '', //邮编
        annualRevenue: '', //年营业额
        rating: '', //评分
        address: '', //地址
        contactsId: '', //联系人id
        noOfEmployees: '', //
        description: '', //
        apiflag: 'custom'
      },
      viewInfo: {
        createTimeStr: '',
        creatorUserName: '',
        ownerUserName: '',
        updateTimeStr: '',
        contactsName: '',
        name: '',
        industry: '',
        origin: '',
        ownership: '',
        zipCode: '', //邮编
        noOfEmployees: '', //员工数
        annualRevenue: '', //年营业额
        rating: '', //评分
        address: '', //地址
        description: ''
      },
      errors: {},
      showlogicMessage: false,
      flag: false
    }
  },
  created: function() {
    this.getCustomers(this.searchkey)
  },
  methods: {
    async getHeaderInfo(page) {
      this.currentpageList = page.pageList;
      this.currentPage = 1;
      this.action = page.action;
      this.searchkey = page.searchkey;
      if (this.action === "search") {
        var res = await selectCoutomAll(this.currentPage, this.currentpageList, this.searchkey)
        this.customers = res.data.data.epCustomerList
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
    async btnAddOpen() {
      this.$refs['formadd'].resetFields();
      this.addInfo.ownerUserid = this.currentUserId
      this.addCustomer = true;
    },
    async btnAddSave() {
      var res = await saveEqCustomer(this.addInfo)

      if (!res.data.meta.success) {
        this.errors = res.data.data;
        if (this.errors.logic !== undefined && this.errors.logic !== '') {
          this.showlogicMessage = true;
        } else {
          this.showlogicMessage = false;
        }
      } else {
        this.$Modal.success({
          title: this.$t("messages.successTitle"),
          content: this.$t("messages.successMessage")
        });
        this.showlogicMessage = false;
        var res = await selectCoutomAll(1, 10, this.searchkey)
        this.customers = res.data.data.epCustomerList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
        this.userList = res.data.data.userInfoList
        this.addInfo = {
          epcustomerId: '',
          ownerUserid: '',
          name: '',
          industry: '',
          origin: '',
          ownership: '',
          zipCode: '',
          annualRevenue: '',
          rating: '',
          address: '',
          contactsId: '',
          noOfEmployees: '',
          description: '',
          apiflag: 'custom'
        }
        this.addCustomer = false;
      }
    },
    btnAddCancle() {
      this.errors = {};
      this.showlogicMessage = false;
      this.addInfo = {
        epcustomerId: '',
        ownerUserid: '',
        name: '',
        industry: '',
        origin: '',
        ownership: '',
        zipCode: '',
        annualRevenue: '',
        rating: '',
        address: '',
        contactsId: '',
        noOfEmployees: '',
        description: '',
        apiflag: 'custom'
      }
      this.addCustomer = false;
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
      this.editInfo.epcustomerId = this.currentCustomer.epcustomerId
      this.editInfo.contactsId = this.currentCustomer.contactsId
      this.editInfo.ownerUserid = this.currentCustomer.ownerUserid
      this.editInfo.name = this.currentCustomer.name
      this.editInfo.industry = this.currentCustomer.industry
      this.editInfo.origin = this.currentCustomer.origin
      this.editInfo.ownership = this.currentCustomer.ownership
      this.editInfo.zipCode = this.currentCustomer.zipCode
      this.editInfo.annualRevenue = this.currentCustomer.annualRevenue
      this.editInfo.rating = this.currentCustomer.rating
      this.editInfo.address = this.currentCustomer.address
      this.editInfo.noOfEmployees = this.currentCustomer.noOfEmployees
      this.editInfo.description = this.currentCustomer.description
      this.contactList = this.currentCustomer.contactsList
      this.editCustomer = true;
    },
    async btnEditSave() {
      // console.log(JSON.stringify(this.editInfo))
      var res = await saveEqCustomer(this.editInfo)
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
        var res = await selectCoutomAll(1, 10, this.searchkey)
        this.customers = res.data.data.epCustomerList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
        this.$refs.datatable.deleteClass();
        this.userList = res.data.data.userInfoList
        this.currentUserId = res.data.data.currentUserid
        this.editCustomer = false;
      }
    },
    btnEditCancle() {
      this.errors = {};
      this.showlogicMessage = false;
      this.formRoles = [];
      this.organizationId = '';
      this.editCustomer = false;
    },
    btnViewOpen() {
      if (this.flag !== true) {
        this.$Modal.warning({
          title: this.$t("messages.warningTitle"),
          content: this.$t("messages.warningSelectEdit")
        });
        return;
      }
      this.contacts = this.currentCustomer.contactsList
      this.viewInfo.createTimeStr = this.currentCustomer.createTimeStr
      this.viewInfo.creatorUserName = this.currentCustomer.creatorUserName
      this.viewInfo.ownerUserName = this.currentCustomer.ownerUserName
      this.viewInfo.updateTimeStr = this.currentCustomer.updateTimeStr
      this.viewInfo.contactsName = this.currentCustomer.contactsName
      this.viewInfo.name = this.currentCustomer.name
      this.viewInfo.industry = this.currentCustomer.industry
      this.viewInfo.origin = this.currentCustomer.origin
      this.viewInfo.ownership = this.currentCustomer.ownership
      this.viewInfo.zipCode = this.currentCustomer.zipCode
      this.viewInfo.noOfEmployees = this.currentCustomer.noOfEmployees
      this.viewInfo.annualRevenue = this.currentCustomer.annualRevenue
      this.viewInfo.rating = this.currentCustomer.rating
      this.viewInfo.address = this.currentCustomer.address
      this.viewInfo.description = this.currentCustomer.description
      this.viewCustomer = true;
    },
    btnViewCancle() {
      this.viewCustomer = false;
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
        epcustomerId: this.currentCustomer.epcustomerId,
        apiflag: 'custom'
      }
      var res = await deleEqCustomer(data)
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
        var res = await selectCoutomAll(1, 10, this.searchkey)
        this.customers = res.data.data.epCustomerList
        this.totalCount = res.data.data.totalCount
        this.columns = res.data.data.columns
        this.userList = res.data.data.userInfoList
      }
    },
    getCurrentPage(page) {
      this.currentPage = page;
      this.getCustomers(this.searchkey)
    },
    getDataTableItem(item) {
      this.flag = true;
      this.currentCustomer = item;
    },
    async getCustomers(paraName) {
      var res = await selectCoutomAll(this.currentPage, this.currentpageList, paraName)
      this.customers = res.data.data.epCustomerList
      this.totalCount = res.data.data.totalCount
      this.columns = res.data.data.columns
      this.contactClolumns = res.data.data.cxcontactscolumns
      this.userList = res.data.data.userInfoList
      this.currentUserId = res.data.data.currentUserid = res.data.data.currentUserid
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
  width: 100%;
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
