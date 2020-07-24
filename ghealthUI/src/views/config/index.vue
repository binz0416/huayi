<template>
  <div>
	<section class="content">
		<div class="col-xs-12">
      <div class="box box-primary">
      	<div class="box-body">
          <div class="row" align="right">
              <div class="col-sm-12">
                <div class="dataTables_filter" id="searchDiv">
                  <div class="btn-group">
                    <button class="btn btn-primary" @click="openAdd()">{{$t("buttons.add")}}</button>
                    <button class="btn btn-default" @click="openEdit()">{{$t("buttons.edit")}}</button>
                  </div>
                </div>
              </div>
            </div>
          <Datatable ref="datatable" :items="formInfo" :columns="columns" :totalCount="totalCount" v-on:getItem="getCurrentConfig"/>
        </div>
      </div>
    </div>

    <Modal v-bind:title='$t("Titles.manageConfig")' v-model="addConfigFlag" class-name="vertical-center-modal">
      <div slot="footer">
          <Button type="text" size="large" @click="configCancleClick()">{{$t("buttons.cancel")}}</Button>
          <Button type="primary" size="large" @click="saveInput()">{{$t("buttons.submit")}}</Button>
      </div>
      <Form ref="formadd" :model="formConfigAdd" :label-width="100">
        <FormItem v-bind:label='$t("labels.configName")' prop="configName" v-bind:error="errors.roleName">
          <Input v-model="formConfigAdd.configName" v-bind:placeholder='$t("placeholders.enterConfigName")'></Input>
        </FormItem>
        <FormItem v-bind:label='$t("labels.configValue")' prop="configValue" v-bind:error="errors.roleName">
          <Input v-model="formConfigAdd.configValue" v-bind:placeholder='$t("placeholders.enterConfigValue")'></Input>
        </FormItem>
        <FormItem v-bind:label='$t("labels.remark")' prop="remark" v-bind:error="errors.roleName">
          <Input v-model="formConfigAdd.remark" v-bind:placeholder='$t("placeholders.enterConfigRemark")'></Input>
        </FormItem>
      </Form>
    </Modal>
	</section>
  </div>
</template>


<script>
import Datatable from '../../components/datatable'
import {
  getConfig,
  saveConfig
} from '@/api/config'
export default{
	data() {
		return{
      formInfo:[],
      columns:[],
      totalCount:1,
      addConfigFlag:false,
      formConfigAdd:{configId:'',configName:'',configValue:'',remark:''},
      currentConfig:{configId:'',configName:'',configValue:'',remark:''},
			errors:'',
      flag:false
		}
	},
	methods:{
		async ajaxGetConfig(){
      this.currentConfig={configId:'',configName:'',configValue:'',remark:''};
			var res = await getConfig()
			this.formInfo = res.data.data.config;
      this.totalCount = res.data.data.totalCount;	
      this.columns = res.data.data.columns;	
		},
    openAdd(){
      this.$refs['formadd'].resetFields();
      this.addConfigFlag = true;
    },
    openEdit(){
      if(this.currentConfig.configId===''){
        this.$Modal.warning({
                            title: this.$t("messages.warningTitle"),
                            content: this.$t("messages.warningSelectEdit")
                        });
      }else{
        this.formConfigAdd.configId = this.currentConfig.configId;
        this.formConfigAdd.configName= this.currentConfig.configName;
        this.formConfigAdd.configValue = this.currentConfig.configValue;
        this.formConfigAdd.remark = this.currentConfig.remark;
        this.addConfigFlag = true;
      }
    },
    configCancleClick(){
      this.$refs['formadd'].resetFields();
      this.addConfigFlag = false;
    },
		async saveInput(){
			let data={
				configId:this.formConfigAdd.configId,
				configName:this.formConfigAdd.configName,
        configValue:this.formConfigAdd.configValue,
        remark:this.formConfigAdd.remark
			}
			var res = await saveConfig(data)
			if (!res.data.meta.success) {
        		var errors = '<p>'+this.$t("messages.handleError")+'</p>';
        		for (var i = 0; i < res.data.data.length; i++) {
          			errors += '<p>' + (i + 1) + ' ' + res.data.data[i].error + '</p>'
        		}
        		this.$Modal.error({
          			title: this.$t("messages.errorTitle"),
          			content: errors
        		});
      		} else {
            this.addConfigFlag = false;
        		this.$Modal.success({
          			title: this.$t("messages.successTitle"),
          			content: this.$t("messages.successMessage")
        		});
            this.ajaxGetConfig()
            this.$refs.datatable.deleteClass();
      		}
		},
    getCurrentConfig(item){
      this.currentConfig = item;
    }
	},
	created: function() {
    	this.ajaxGetConfig()
  },
  components: {
    Datatable
  }
}
</script>
<style>
	
</style>

