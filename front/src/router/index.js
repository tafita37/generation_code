import AboutViewVue from '@/views/AboutView.vue';
import { createRouter, createWebHistory } from 'vue-router';
import Testss from '@/components/principal/Testss.vue';import Test from '@/components/principal/Test.vue';import Login from '@/components/principal/Login.vue';
import NewTestss from '@/components/principal/NewTestss.vue';import NewTest from '@/components/principal/NewTest.vue';import NewLogin from '@/components/principal/NewLogin.vue';
import ModifTestss from '@/components/principal/ModifTestss.vue';import ModifTest from '@/components/principal/ModifTest.vue';import ModifLogin from '@/components/principal/ModifLogin.vue';
import LoginLogin from '@/components/principal/LoginLogin.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [ { path: '/', name: 'loginLogin', component: LoginLogin },, { path: '/allTestss/:numPage', name: 'allTestss', component: Testss },{ path: '/allTest/:numPage', name: 'allTest', component: Test },{ path: '/allLogin/:numPage', name: 'allLogin', component: Login },, { path: '/newTestss', name: 'newTestss', component: NewTestss },{ path: '/newTest', name: 'newTest', component: NewTest },{ path: '/newLogin', name: 'newLogin', component: NewLogin },, { path: '/modifTestss', name: 'modifTestss', component: ModifTestss },{ path: '/modifTest', name: 'modifTest', component: ModifTest },{ path: '/modifLogin', name: 'modifLogin', component: ModifLogin }, { path: '/about', name: 'about', component: AboutViewVue }
  ]
})

export default router
