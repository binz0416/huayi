import http from '../utils/http'
export function getReportByCustomerId(id, time) {
  const data = {
    apiflag: "getReport",
    customerId: id,
    checkinTime: time
  }
  return http.post('dataunscramble/list', JSON.stringify(data))
}

// export function saveReport(data) {
//   return http.post('dataunscramble/edit', JSON.stringify(data))
// }

export function getCustomers(currentPage, pagesize, name, time) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    customerCheckin: time,
    apiflag: 'getCustomers'
  }
  return http.post('dataunscramble/list', JSON.stringify(data))
}

export function getEcgData(id) {
  const data = {
    apiflag: "getEcgData",
    customerId: id
  }
  return http.post('dataunscramble/list', JSON.stringify(data))
}

export function edit(archivesDetailId,archivesId,inspectionTime,type,scId,detailAdvise) {
  const data = {
    archivesDetailId: archivesDetailId,
    archivesId: archivesId,
    inspectionTime: inspectionTime,
    type: type,
    scId: scId,
    advise: detailAdvise
  }
  return http.post('dataunscramble/edit', JSON.stringify(data))
  // type值：
  // 1： 太空舱
  // 2： 穿戴式
  // 3： 毒素检测
  // 4： 超声报告
  // 5：血检报告
  // 6：尿检报告
  // 7：综合报告
}
