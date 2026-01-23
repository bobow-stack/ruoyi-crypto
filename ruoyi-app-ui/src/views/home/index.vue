<template>
  <div class="page">
    <div class="card">
      <h2>Logged In</h2>
      <div class="line">Token prefix: {{ tokenPreview }}</div>
      <van-button type="danger" block @click="onLogout">Logout</van-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/store/user';

const router = useRouter();
const userStore = useUserStore();

const tokenPreview = computed(() => {
  const t = userStore.token || '';
  return t.slice(0, 10);
});

function onLogout() {
  userStore.logout();
  router.replace('/login');
}
</script>

<style scoped>
.page {
  padding: 24px;
}
.card {
  background: #fff;
  padding: 20px;
  border-radius: 12px;
}
.line {
  margin: 12px 0;
  color: #666;
}
</style>
