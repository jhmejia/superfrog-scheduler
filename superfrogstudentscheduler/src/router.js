
import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '@/components/HelloWorld.vue'
import RequestPage from '@/components/request-page/main-container.vue'
import App from '@/App.vue'

const routes = [
    { path: '/', component: App },
    { path: '/pricing', component: HelloWorld },
    { path: '/range', component: HelloWorld },
    { path: '/request', component: RequestPage },
    { path: '/modify-request', component: HelloWorld },
    { path: '/:catchAll(.*)',
    component: App}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router