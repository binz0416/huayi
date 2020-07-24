import http from '../utils/http'

export function initWearable(id,time,arId) {
  const data = {
    customerId: id,
    checkinTime: time,
    archivesId: arId,
    apiflag:"init",
  }
  return http.post('archivesmanager/list', JSON.stringify(data))
}
