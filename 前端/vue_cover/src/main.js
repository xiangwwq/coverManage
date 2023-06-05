import Vue from 'vue'
import App from './App.vue'
import router from "@/router";
import  '@/assets/css/global.css'
import ElementUI, {Message} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";

import ECharts from 'vue-echarts'

import 'echarts'


axios.defaults.baseURL="http://localhost:3000"
//配置请求拦截器 加上负载token
axios.interceptors.request.use(config=>{
    config.headers.Authorization=window.sessionStorage.getItem("uid")
    // console.log(config)
    return config;
})
Vue.config.productionTip = false
Vue.component("ECharts",ECharts)
Vue.use(ElementUI)
Vue.prototype.$http=axios
// Vue.prototype.$message=Message

new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
