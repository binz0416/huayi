import http from '../utils/http'

export function selectCoutomAll(currentPage, pagesize,name) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    name: name,
    apiflag: 'getepCustomAll'
  }
  return http.post('epcustommanager/list', JSON.stringify(data))
}
export function saveEqCustomer(data) {
  const para = {
    epCustomerDto: data,
    apiflag: 'custom'
  }
  return http.post('epcustommanager/edit', JSON.stringify(para))
}
export function deleEqCustomer(data) {
  return http.post('epcustommanager/dele', JSON.stringify(data))
}
