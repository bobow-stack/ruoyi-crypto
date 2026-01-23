import type { Router } from 'vue-router';
import { getToken, removeToken } from '@/utils/auth';
import { showToast } from 'vant';

export function setupRouterGuard(router: Router) {
  router.beforeEach((to) => {
    const token = getToken();
    if (to.meta?.requiresAuth && !token) {
      showToast('Please login first');
      return {
        path: '/login',
        query: { redirect: to.fullPath }
      };
    }
    return true;
  });

  router.onError(() => {
    removeToken();
    showToast('Router error, please login again');
  });
}
