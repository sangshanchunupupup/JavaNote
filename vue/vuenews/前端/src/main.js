import Vue from 'vue'
import router from './router'
import axios from 'axios'
import layer from 'layui-layer'
import 'jquery'
import login from './login'
import Router from 'vue-router'

Vue.config.productionTip = false;
window.axios = axios;
window.layer = layer;

Vue.use(Router);

$(function () {
  const app = new Vue({
    el: '#app',
    components: {
      login
    },
    template: '<login/>',
    router
  })
});
