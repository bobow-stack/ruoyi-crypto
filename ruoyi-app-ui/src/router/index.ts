import { createRouter, createWebHashHistory, type RouteRecordRaw } from 'vue-router';
import Login from '@/views/login/index.vue';
import Register from '@/views/register/index.vue';
import Home from '@/views/home/index.vue';
import { setupRouterGuard } from './guard';

const routes: RouteRecordRaw[] = [
  { path: '/', redirect: '/home' },
  { path: '/login', component: Login, meta: { requiresAuth: false } },
  { path: '/register', component: Register, meta: { requiresAuth: false } },
  { path: '/home', component: Home, meta: { requiresAuth: true } }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

setupRouterGuard(router);

export default router;
