import http from '../utils/http'

export function selectContactAll(currentPage, pagesize,name) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    name: name,
    apiflag: 'getEPContacts'
  }
  return http.post('epcustommanager/list', JSON.stringify(data))
}
export function saveEqContact(data) {
  const para = {
    epContactsDto: data,
    apiflag: 'contacts'
  }
  return http.post('epcustommanager/edit', JSON.stringify(para))
}
export function deleEqContact(data) {
  return http.post('epcustommanager/dele', JSON.stringify(data))
}
