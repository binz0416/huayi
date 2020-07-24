import http from '../utils/http'
import axios from 'axios'
export function getReportByCustomerId(id, time) {
  const data = {
    apiflag: "getReport",
    customerId: id,
    checkinTime: time
  }
  return http.post('physicalreport/list', JSON.stringify(data))
}

export function saveReport(data) {
  return http.post('physicalreport/edit', JSON.stringify(data))
}

export function getCustomers(currentPage, pagesize, name, time) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    customerCheckin: time,
    apiflag: 'getCustomers'
  }
  return http.post('physicalreport/list', JSON.stringify(data))
}

export function reportUpload(file) {
  return http.post('physicalreport/upload',file)
}
