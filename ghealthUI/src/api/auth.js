import http from '../utils/http'

export function getUserAuths(roleId) {
  const data = {
    roleId: roleId,
    apiflag: 'init'
  }
  return http.post('auth/list', JSON.stringify(data))
}
// export function getAuths(roleId) {
//   const data = {
//     roleId: roleId,
//     apiflag: 'init'
//   }
//   return http.post('auth/list', JSON.stringify(data))
// }
export function addAuth(role) {
  return http.post('auth/additional', JSON.stringify(role))
}
export function editAuth(role) {
  return http.post('auth/edit', JSON.stringify(role))
}
