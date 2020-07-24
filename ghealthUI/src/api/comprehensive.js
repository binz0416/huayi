import http from '@/utils/http'

export function getCustomers(currentPage, pagesize, name, time) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    customerCheckin: time,
    apiflag: 'getCustomers'
  }
  return http.post('comprehensive/list', JSON.stringify(data))
}

export function getComprehensiveReport(customerId, checkinTime) {
  const data = {
    customerId: customerId,
    apiflag: ''
  }
  return http.post('comprehensive/list', JSON.stringify(data))
}

export function saveReport(data) {
  return http.post('comprehensive/edit', JSON.stringify(data))
}
