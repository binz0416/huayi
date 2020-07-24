import http from '../utils/http'

export function selectUsers(currentPage, pagesize, name) {
  const data = {
    currentPage: currentPage,
    pagesize: pagesize,
    fullname: name,
    apiflag: 'selectUsers'
  }
  return http.post('users/list', JSON.stringify(data))
}

export function addUser(user) {
  return http.post('users/additional', JSON.stringify(user))
}

export function editUser(user) {
  return http.post('users/edit', JSON.stringify(user))
}

export function getUserAuthOptions() {
  const data = {
    apiflag: 'userAuthOptions'
  }
  return http.post('users/list', JSON.stringify(data))
}
