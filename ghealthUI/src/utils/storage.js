'use strict'

export default {
  getSessionItem(keyVal) {
    return window.sessionStorage.getItem(keyVal)
  },
  setSessionItem(keyVal, item) {
    window.sessionStorage.setItem(keyVal, item)
  },
  removeSessionItem(keyVal) {
    window.sessionStorage.remove(keyVal)
  },
  clearSession() {
    window.sessionStorage.clear()
  }
}
