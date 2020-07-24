import http from '@/utils/http'

export function getCustomers(currentPage, pagesize, name, time) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    customerCheckin: time,
    apiflag: 'getCustomers'
  }
  return http.post('toxindetection/list', JSON.stringify(data))
}

export function getToxinReport(customerId, checkinTime) {
  const data = {
    customerId: customerId,
    apiflag: 'getToxinReport'
  }
  return http.post('toxindetection/list', JSON.stringify(data))
}

export function saveReport(data) {
  return http.post('toxindetection/edit', JSON.stringify(data))
}
