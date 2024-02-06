<template>
	<HeaderCom />
	<div class="login_con">
		<div class="login_box">
			<LogoCom />
			<InputText v-model="member.email" :text_tag="'아이디'" />
			<InputPassword v-model="member.password" :password_tag="'비밀번호'" />
			<PrimaryButton @click="login" :button_tag="'로그인'" />
			<RouterLink to="/member/join">회원가입</RouterLink>
		</div>
	</div>
	<FooterCom />
</template>

<script setup>
import { reactive } from 'vue';
import HeaderCom from '@/components/MainHeader.vue';
import FooterCom from '@/components/MainFooter.vue';
import LogoCom from '@/components/LogoLayout.vue';
import InputText from '@/components/InputText.vue';
import InputPassword from '@/components/InputPassword.vue';
import PrimaryButton from '@/components/ButtonComponent.vue';
import { RouterLink } from 'vue-router';
import store from '@/modules/loginStore';
import axios from 'axios';
const reg_chk = /^\s+|\s+$/g;
const member = reactive({
	email: '',
	password: '',
});
function login() {
	if (member.email.replace(reg_chk, '') == '') {
		alert('이메일을 입력해주세요');
		return;
	}

	if (member.password.replace(reg_chk, '') == '') {
		alert('비밀번호를 입력해주세요');
		return;
	}

	axios
		.post('/api/member/login', member)
		.then(({ data }) => {
			if (data.success) {
				alert(data.message);
				store.commit('setAccount', data.data);
				sessionStorage.setItem('id', data.data);
			}
		})
		.catch(error => {
			alert(error.response.data.message);
		});
}
</script>
<style>
.login_con {
	width: 100%;
	padding: 150px 0;
	display: flex;
	align-items: center;
	justify-content: center;
}
.login_box {
	width: 450px;
	border: 1px solid #e2e2e2;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 40px 0;
}

.login_box a {
	width: 350px;
	height: 40px;
	border-radius: 10px;
	color: white;
	font-size: 18px;
	font-weight: bold;
	display: flex;
	justify-content: center;
	align-items: center;
	border: 1px solid #e2e2e2;
	background-color: #000000;
}
</style>
