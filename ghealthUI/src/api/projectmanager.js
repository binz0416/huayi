import http from '../utils/http'

export function getCustomerMealInfos(id) {
  const data = {
    apiflag: 'getMealInfos',
    customerId: id
  }
  return http.post('projectmanager/list', JSON.stringify(data))
}
export function getCustomers(currentPage, pagesize, name,time) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    customerCheckin:time,
    apiflag: 'getCustomers'
  }
  return http.post('projectmanager/list', JSON.stringify(data))
}
export function saveReport(data) {
  return http.post('projectmanager/edit', JSON.stringify(data))
}
