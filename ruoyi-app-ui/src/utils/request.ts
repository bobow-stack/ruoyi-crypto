import axios from 'axios';
import { showToast } from 'vant';
import { getToken, removeToken } from '@/utils/auth';
import router from '@/router';
import type { ApiResp } from '@/types/api';

const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: 15000
});

request.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
    config.headers = config.headers || {};
    config.headers['App-Authorization'] = `Bearer ${token}`;
  }
  return config;
});

request.interceptors.response.use(
  (response) => {
    const res: ApiResp = response.data;
    if (res && res.code === 200) {
      return res;
    }
    const msg = res?.msg || 'Request failed';
    showToast(msg);
    if (res?.code === 401) {
      const redirect = router.currentRoute.value.fullPath;
      removeToken();
      router.replace(`/login?redirect=${encodeURIComponent(redirect)}`);
    }
    return Promise.reject(res);
  },
  (error) => {
    let msg = 'Network error, please try again';
    if (error.code === 'ECONNABORTED') {
      msg = 'Request timeout, please try again';
    } else if (error.response?.data?.msg) {
      msg = error.response.data.msg;
    }
    showToast(msg);
    return Promise.reject(error);
  }
);

export default request;
