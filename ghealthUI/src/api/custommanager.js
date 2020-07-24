import http from '../utils/http'

export function selectCoutomAll(currentPage, pagesize,name) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    customerName: name,
    apiflag: 'customAll'
  }
  return http.post('custommanager/list', JSON.stringify(data))
}
