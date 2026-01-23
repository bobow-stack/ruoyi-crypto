import { defineStore } from 'pinia';
import { getToken, setToken, removeToken } from '@/utils/auth';
import { login as loginApi, profile as profileApi } from '@/api/user';

export type UserInfo = {
  id?: number;
  account?: string;
  nickname?: string;
};

export const useUserStore = defineStore('user', {
  state: () => ({
    token: getToken(),
    userInfo: {} as UserInfo
  }),
  actions: {
    async login(account: string, password: string) {
      const res = await loginApi({ account, password });
      const token = res.data?.token || (res as any).token || '';
      setToken(token);
      this.token = token;
      return token;
    },
    async fetchProfile() {
      const res = await profileApi();
      this.userInfo = res.data || {};
      return this.userInfo;
    },
    logout() {
      removeToken();
      this.token = '';
      this.userInfo = {};
    }
  }
});
