import { createRouter, createWebHashHistory, type RouteRecordRaw } from 'vue-router';
import Login from '@/views/login/index.vue';
import Register from '@/views/register/index.vue';
import Home from '@/views/home/index.vue';
import Market from '@/views/market/index.vue';
import Trade from '@/views/trade/index.vue';
import Asset from '@/views/asset/index.vue';
import Profile from '@/views/profile/index.vue';
import { setupRouterGuard } from './guard';

const routes: RouteRecordRaw[] = [
  { path: '/', redirect: '/home' },
  { path: '/login', component: Login, meta: { requiresAuth: false } },
  { path: '/register', component: Register, meta: { requiresAuth: false } },
  { path: '/home', component: Home, meta: { requiresAuth: true, tab: 'home' } },
  { path: '/market', component: Market, meta: { requiresAuth: true, tab: 'market' } },
  { path: '/trade', component: Trade, meta: { requiresAuth: true, tab: 'trade' } },
  { path: '/asset', component: Asset, meta: { requiresAuth: true, tab: 'asset' } },
  { path: '/profile', component: Profile, meta: { requiresAuth: true, tab: 'profile' } }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

setupRouterGuard(router);

export default router;
