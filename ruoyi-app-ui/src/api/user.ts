import request from '@/utils/request';
import type { ApiResp } from '@/types/api';

const API = {
  login: '/app/auth/login',
  register: '/app/auth/register',
  profile: '/app/user/profile'
};

export type LoginReq = {
  account: string;
  password: string;
};

export type RegisterReq = {
  account: string;
  password: string;
};

export type LoginResp = {
  token: string;
};

export type ProfileResp = {
  id: number;
  account: string;
  nickname?: string;
};

export function login(data: LoginReq) {
  return request.post<ApiResp<LoginResp>>(API.login, data);
}

export function register(data: RegisterReq) {
  return request.post<ApiResp<any>>(API.register, data);
}

export function profile() {
  return request.get<ApiResp<ProfileResp>>(API.profile);
}
