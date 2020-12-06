import Vue from 'vue'
import VueRouter from 'vue-router'

import login from '../views/login'
import success from "../views/success";
import register from "../views/register";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    redirect:{name:'login'}
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/register',
    name: 'register',
    component: register
  },
  {
    path: '/success',
    name: 'success',
    component: success
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
