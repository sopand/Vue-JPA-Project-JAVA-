import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '@/views/LoginView.vue';
import UserCreateView from '@/views/UserCreateView.vue';
import loginStore from '@/modules/loginStore';
const routes = [
	{
		path: '/',
		name: 'home',
		component: HomeView,
	},
	{
		path: '/login',
		name: 'login',
		component: LoginView,
	},
	{
		path: '/member/join',
		name: 'memberJoin',
		component: UserCreateView,
		//meta: { auth: true },
	},
	{
		path: '/board/add',
		name: 'boardAdd',
		component: () => import('@/views/BoardCreateView.vue'),
	},
	{
		path: '/board/:category',
		name: 'noticeList',
		component: () => import('@/views/BoardListView.vue'),
	},
	{
		path: '/board/detail/:board_sid',
		name: 'boardDetail',
		component: () => import('@/views/BoardDetailView.vue'),
	},
];

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes,
});

router.beforeEach(async (to, from, next) => {
	const memberSid = loginStore.state.account.id;
	if (to.matched.some(r => r.meta.auth) && memberSid == 0) {
		alert('로그인이 필요한 페이지입니다!');
		next('/login');
	} else {
		next();
	}
});

export default router;
