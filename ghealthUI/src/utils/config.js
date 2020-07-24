// 生产环境
const apiBaseURL = 'https://www.ghsmg.com/ghealth/';
const apiBaseUrlForPDF = 'https://www.ghsmg.com/';
// 本地调试
// const apiBaseURL = 'http://127.0.0.1:8888/ghealth/';
// const apiBaseUrlForPDF = 'http://127.0.0.1:8888/';
// 环境配置 - timeout
const apiTimeout = 30000;
// 环境配置 - LOADING 等待时间
const loadingTime = 2000;

export default {
  apiBaseURL,
  apiTimeout,
  loadingTime,
  apiBaseUrlForPDF
};
