import lazyLoading from './lazyLoading'
export default (routers, data) => {
  initRouters(routers, data)
}

function initRouters(routers, data) {
  data.forEach((item) => {
    let router = Object.assign({}, item)
    router.component = lazyLoading(router.component)
    if (router.children !== undefined && router.children.length > 0) {
      router.children = []
      initRouters(router.children, item.children)
    }
    routers.push(router)

  })
}
