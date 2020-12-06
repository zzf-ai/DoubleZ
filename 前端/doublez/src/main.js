import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//导入element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'

//导入axios
import axios from 'axios'


Vue.config.productionTip = false
Vue.prototype.$axios = axios

Vue.use(ElementUI)

import "./axios"

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
