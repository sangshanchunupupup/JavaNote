import Vue from 'vue'
import Router from 'vue-router'
import login from '../login.vue'
import newsList from '../components/newsList.vue'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      component: newsList,
      path: '/newsList',
      name: 'newsList',
    }
  ],
  mode:"history"
})
