<template>
  <div class="app-shell">
    <router-view />
    <van-tabbar v-if="showTabbar" v-model="active" route class="tabbar">
      <van-tabbar-item name="/home" to="/home" icon="home-o">首页</van-tabbar-item>
      <van-tabbar-item name="/market" to="/market" icon="chart-trending-o">行情</van-tabbar-item>
      <van-tabbar-item name="/trade" to="/trade" icon="exchange">交易</van-tabbar-item>
      <van-tabbar-item name="/asset" to="/asset" icon="balance-o">资产</van-tabbar-item>
      <van-tabbar-item name="/profile" to="/profile" icon="user-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const active = computed(() => route.path);
const showTabbar = computed(() => !['/login', '/register'].includes(route.path));
</script>

<style>
html, body, #app {
  height: 100%;
}
body {
  margin: 0;
  background: #0b0e11;
}
.app-shell {
  min-height: 100%;
  padding-bottom: 60px;
}
.tabbar {
  background: #0f1419;
  border-top: 1px solid #1f242b;
}
.van-tabbar-item--active {
  color: #f0b90b !important;
}
</style>
