import {loginByUsername} from '@/api/login'
import auth from '@/utils/auth'

const user = {
  state: {
    userID: '',
    status: '',
    name: '',
    introduction: '',
    roles: []
  },

  mutations: {
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_USERID: (state, userID) => {
      state.userID = userID
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    }
  },

  actions: {
    // 用户名登录
    LoginByUsername({commit}, userInfo) {
      return new Promise((resolve, reject) => {
        loginByUsername(userInfo.userName, userInfo.passWd).then(res => {
          let loginInfo = res.data
          if (loginInfo.meta.success === true) {
            auth.setToken(loginInfo.data.token)
            auth.setRouters(JSON.stringify(loginInfo.data.routers))
            let userInfo = loginInfo.data.UserInfo
            auth.setUser(JSON.stringify(userInfo))
            commit('SET_USERID', userInfo.username)
            commit('SET_NAME', userInfo.fullname)
          } else {
            reject(loginInfo.meta.message)
          }
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default user
