import http from '../utils/http'
import md5 from 'js-md5'

export function loginByUsername(userName, passWd) {
  const data = {
    userName: userName.trim(),
    passWd: md5(passWd)
  }
  return http.post('actlogin', JSON.stringify(data))
}
