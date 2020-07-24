import http from '@/utils/http'

export function getCustomerArchives(id) {
  const data = {
    customerId: id,
    apiflag: "getCustomerArchives",
  }
  return http.post('archivesmanager/list', JSON.stringify(data))
}
export function getEcgData(id) {
  const data = {
    apiflag: "getEcgData",
    customerId: id
  }
  return http.post('archivesmanager/list', JSON.stringify(data))
}
export function getCustomers(currentPage, pagesize, name, time) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    customerCheckin: time,
    apiflag: 'getCustomers'
  }
  return http.post('archivesmanager/list', JSON.stringify(data))
}
export function getReport(id) {
  const data = {
    customerId: id
  }
  return http.post('archivesmanager/edit',JSON.stringify(data))
}
