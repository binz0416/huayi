const getters = {
  // user状态：是否登入等
  status: state => state.user.status,
  name: state => state.user.name,
  introduction: state => state.user.introduction,
  roles: state => state.user.roles,
  userID: state => state.user.userID,
  errMsg: state => state.user.errMsg
}
export default getters
