'use strict'

import axios from 'axios'
import qs from 'qs'
import auth from './auth'
import router from '@/router'
import config from './config'
import iView from 'iview'
import VueI18n from 'vue-i18n'

axios.defaults.baseURL = config.apiBaseURL
axios.defaults.timeout = config.apiTimeout

axios.interceptors.request.use(config => {
  // loading
  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  return response
}, error => {
  if (error.response) {
    const i18n = new VueI18n({
      locale: 'zh', // 语言标识
      messages: {
        'zh': require('../assets/js/locale/zh'),
        'en': require('../assets/js/locale/en')
      }
    })
    switch (error.response.status) {
      // 网络异常的情况
      case 401:
        iView.Modal.error({
          title: i18n.t("messages.errorTitle"),
          content: i18n.t("messages.networkError")
        });
        window.sessionStorage.clear()
        router.replace({
          path: '/login'
        })
      case 500:
        iView.Modal.error({
          title: i18n.t("messages.errorTitle"),
          content: error.response.data.split(':')[1]
        });
        if ('E0000' === error.response.data.split(':')[0]) {
          window.sessionStorage.clear()
          router.replace({
            path: '/login'
          })
        }
    }
  }
  console.log(router)
  return Promise.reject(error)
})

export default {
  post(url, data) {
    let headers = {
      'Content-Type': 'application/json;charset=utf-8'
    }
    if ("physicalreport/upload" === url) {
      headers = {
        'Content-Type': 'multipart/form-data;charset=utf-8'
      }
      return axios({
        method: 'post',
        url,
        data: data,
        dataType: 'form-data',
        headers: headers
      })
    } else {
      // 非LOGIN
      if ('actlogin' !== url) {
        headers = {
          'Content-Type': 'application/json;charset=utf-8',
          'Authorization': auth.getToken(),
          'LoginUser': JSON.parse(auth.getUser()).username
        }
      }

      return axios({
        method: 'post',
        url,
        data: data,
        dataType: 'json',
        headers: headers
      })
    }


  },
  get(url, params) {
    return axios({
      method: 'get',
      url,
      params
    })
  }
}
