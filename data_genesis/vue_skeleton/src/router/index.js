import AboutViewVue from '@/views/AboutView.vue';
import { createRouter, createWebHistory } from 'vue-router';
[routerComponentImportList]
[componentImportInsert]
[componentImportUpdate]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [ [routerLinkList], [routerLinkInsert], [routerLinkUpdate] { path: '/about', name: 'about', component: AboutViewVue }
  ]
})

export default router
