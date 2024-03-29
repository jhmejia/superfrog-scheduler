
import { createRouter, createWebHistory } from 'vue-router'
// import PricingPage from '@/components/pricing-page/main-container.vue'
import AdminPage from '@/components/admin-page/main-parent.vue'
import RequestPage from '@/components/request-page/main-container.vue'
import PricingPage from '@/components/pricing-page/main-container.vue'
import LoginPage from '@/components/login-page/main-container.vue'
import SuperfrogPage from '@/components/superfrog-page/main-parent.vue'
import App from '@/App.vue'
import RangePage from '@/components/range-page/main-container.vue'
import ModifyPage from '@/components/modify-page/main-container.vue'

const routes = [
    { path: '/', component: App },

    { path: '/pricing', component: PricingPage },
    { path: '/admin', component: AdminPage},
    { path: '/superfrog', component: SuperfrogPage},

    { path: '/range', component: RangePage },
    { path: '/request', component: RequestPage },
    { path: '/modify-request', component: ModifyPage },
    { path: '/login', component: LoginPage },
    { path: '/:catchAll(.*)',
    component: App}
    
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router