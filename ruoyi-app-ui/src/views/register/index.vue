<template>
  <div class="page">
    <div class="card">
      <h2>Register</h2>
      <van-field v-model="form.account" label="Account" placeholder="Enter account" />
      <van-field v-model="form.password" type="password" label="Password" placeholder="Enter password" />
      <van-field v-model="form.confirm" type="password" label="Confirm" placeholder="Confirm password" />
      <van-button type="primary" block @click="onSubmit">Register</van-button>
      <div class="link">
        <router-link to="/login">Already have an account? Login</router-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { showToast } from 'vant';
import { register } from '@/api/user';

const form = reactive({
  account: '',
  password: '',
  confirm: ''
});

const router = useRouter();

async function onSubmit() {
  if (!form.account || !form.password || !form.confirm) {
    showToast('Please fill all fields');
    return;
  }
  if (form.password !== form.confirm) {
    showToast('Passwords do not match');
    return;
  }
  try {
    await register({ account: form.account, password: form.password });
    showToast('Register success, please login');
    router.replace('/login');
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
