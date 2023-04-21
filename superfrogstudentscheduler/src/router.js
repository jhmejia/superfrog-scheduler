
import { createRouter, createWebHistory } from 'vue-router'
import RequestPage from '@/components/request-page/main-container.vue'
import PricingPage from '@/components/pricing-page/main-container.vue'
import App from '@/App.vue'

const routes = [
    { path: '/', component: App },
    { path: '/pricing', component: PricingPage},
    { path: '/range', component: RequestPage },
    { path: '/request', component: RequestPage },
    { path: '/modify-request', component: RequestPage },
    { path: '/:catchAll(.*)',
    component: App}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router