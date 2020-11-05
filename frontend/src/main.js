import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import  VueSweetalert2  from  'vue-sweetalert2' ;
import VueCookies from "vue-cookies";

Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)
Vue.use(VueSweetalert2);
Vue.use(VueCookies);

Vue.config.productionTip = false
Vue.$cookies.config("2h");

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
