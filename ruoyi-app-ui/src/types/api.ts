export type ApiResp<T = any> = {
  code: number;
  msg: string;
  data: T;
};
