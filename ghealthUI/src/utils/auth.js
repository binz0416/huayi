import store from './storage'

const TokenKey = 'token'
const RoutersKey = 'routers'
const UserKey = 'user'

export default {
  getToken() {
    return store.getSessionItem(TokenKey)
  },
  setToken(token) {
    return store.setSessionItem(TokenKey, token)
  },
  removeToken() {
    return store.removeSessionItem(TokenKey)
  },
  getRouters() {
    return store.getSessionItem(RoutersKey)
  },
  setRouters(token) {
    return store.setSessionItem(RoutersKey, token)
  },
  removeRouters() {
    return store.removeSessionItem(RoutersKey)
  },
  getUser() {
    return store.getSessionItem(UserKey)
  },
  setUser(token) {
    return store.setSessionItem(UserKey, token)
  },
  removeUser() {
    return store.removeSessionItem(UserKey)
  }
}
