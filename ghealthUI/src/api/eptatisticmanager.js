import http from '../utils/http'

export function getTatisticInfo(data) {
  return http.post('epcustommanager/list', JSON.stringify(data))
}
