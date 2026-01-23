<template>
  <div class="page">
    <div class="card">
      <h2>Login</h2>
      <van-field v-model="form.account" label="Account" placeholder="Enter account" />
      <van-field v-model="form.password" type="password" label="Password" placeholder="Enter password" />
      <van-button type="primary" block @click="onSubmit">Login</van-button>
      <div class="link">
        <router-link to="/register">No account? Register</router-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { showToast } from 'vant';
import { useUserStore } from '@/store/user';

const form = reactive({
  account: '',
  password: ''
});

const router = useRouter();
const route = useRoute();
const userStore = useUserStore();

async function onSubmit() {
  if (!form.account || !form.password) {
    showToast('Account or password is empty');
    return;
  }
  try {
    await userStore.login(form.account, form.password);
    const redirect = (route.query.redirect as string) || '/home';
    router.replace(redirect);
  } catch {
  }
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
.link {
  margin-top: 12px;
  text-align: center;
}
</style>
