import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import AccountView from "@/views/AccountView.vue";
import AuthVeiw from "@/views/AuthVeiw.vue";
import AuthRegView from "@/views/AuthRegView.vue";
import PersonalView from "@/views/PersonalView.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/', component: HomeView},
        {path: '/profile', component: AccountView},
        {path: '/auth', component: AuthVeiw},
        {path: '/auth/reg', component: AuthRegView},
        {path: '/profile/personal', component: PersonalView}
    ]
})

export default router
