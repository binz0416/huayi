import http from '../utils/http'

export function getCustomerMealInfos(id) {
  const data = {
    apiflag: 'getCustomerMeals',
    customerId: id
  }
  return http.post('setmeal/list', JSON.stringify(data))
}
export function getCustomers(currentPage, pagesize, name,time) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    customerCheckin:time,
    apiflag: 'getCustomers'
  }
  return http.post('setmeal/list', JSON.stringify(data))
}
