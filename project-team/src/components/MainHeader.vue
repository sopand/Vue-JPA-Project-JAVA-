<template>
	<div class="tag_box">
		<RouterLink to="/login" v-if="!$store.state.account.id">Login</RouterLink>
		<button type="button" @click="logout" v-else>Logout</button>
	</div>
	<div class="header_box">
		<LogoCom />
		<ul class="menu_list">
			<li>
				<RouterLink to="/board/01">Notice</RouterLink>
			</li>
			<li><RouterLink to="/board/02">Comunity</RouterLink></li>
			<li><RouterLink to="/board/03">Q&A</RouterLink></li>
			<li><RouterLink to="/proejct">Project</RouterLink></li>
			<li><RouterLink to="/study">Study</RouterLink></li>
		</ul>
	</div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import LogoCom from './LogoLayout.vue';
import store from '@/modules/loginStore';
import router from '@/router';
import axios from 'axios';
function logout() {
	axios
		.post('/api/member/logout')
		.then(({ data }) => {
			if (data.success) {
				sessionStorage.removeItem('id');
				store.commit('setAccount', 0);
				router.push({ path: '/login' });
			}
		})
		.catch(error => {
			console.error(error);
		});
}
</script>

<style scoped>
.header_box {
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	border-bottom: 1px solid #e2e2e2;
	padding: 20px 0 20px 0;
}
.tag_box {
	width: 100%;
	text-align: end;
	padding: 10px 150px 0 0;
	font-size: 16px;
}

.menu_list {
	display: flex;
	align-items: center;
	justify-content: space-evenly;
	width: 100%;
}

.menu_list li {
	list-style-type: none;
	font-size: 20px;
	text-align: center;
	width: 200px;
}
.menu_list li a,
.tag_box a,
button {
	font-weight: bold;
	color: black;
}
button {
	border: 0;
}
</style>
