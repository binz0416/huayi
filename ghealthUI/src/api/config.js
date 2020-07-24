import http from '@/utils/http'

export function getConfig() {
	const data = {}
  return http.post('config/list',JSON.stringify(data))
}

export function saveConfig(data) {
  return http.post('config/edit',JSON.stringify(data))
}