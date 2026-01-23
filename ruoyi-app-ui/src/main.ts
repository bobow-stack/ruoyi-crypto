import { createApp } from 'vue';
import { createPinia } from 'pinia';
import {
  Button,
  Cell,
  CellGroup,
  Field,
  Form,
  Icon,
  NavBar
} from 'vant';
import App from './App.vue';
import router from './router';

import 'vant/lib/index.css';

const app = createApp(App);
app.use(createPinia());
app.use(router);
app.use(Button);
app.use(Cell);
app.use(CellGroup);
app.use(Field);
app.use(Form);
app.use(Icon);
app.use(NavBar);
app.mount('#app');
