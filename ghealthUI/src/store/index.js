import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import user from './modules/user'

Vue.use(Vuex)
const main = {
	state:{
		mainFlag:0
	},
	mutations:{
		invert(state,n){
			state.mainFlag = n
		}
	}
}
const store = new Vuex.Store({
  modules: {
    user,
    main
  },
  getters
})

export default store
