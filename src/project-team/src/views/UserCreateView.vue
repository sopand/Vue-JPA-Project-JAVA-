<template>
	<HeaderCom />
	<div class="join_con">
		<h1>Get Team 간단 회원가입</h1>
		<div class="join_box">
			<InputText v-model="member.email" :text_tag="'아이디'" />
			<InputPass v-model="member.password" :password_tag="'비밀번호'" />
			<InputText v-model="member.name" :text_tag="'별명,성명'" />
			<PrimaryButton @click="join" :button_tag="'회원가입'" />
		</div>
		<button></button>
	</div>
	<FooterCom />
</template>

<script setup>
import HeaderCom from '@/components/MainHeader.vue';
import FooterCom from '@/components/MainFooter.vue';
import InputText from '@/components/InputText.vue';
import InputPass from '@/components/InputPassword.vue';
import PrimaryButton from '@/components/ButtonComponent.vue';
import { reactive } from 'vue';
import router from '@/router';
import axios from 'axios';

const reg_chk = /^\s+|\s+$/g;
const member = reactive({
	email: '',
	password: '',
	name: '',
});

function join() {
	if (member.email.replace(reg_chk, '') == '') {
		alert('이메일을 입력해주세요');
		return;
	}

	if (member.password.replace(reg_chk, '') == '') {
		alert('비밀번호를 입력해주세요');
		return;
	}
	if (member.name.replace(reg_chk, '') == '') {
		alert('별명 또는 성명을 입력해주세요');
		return;
	}
	axios
		.post('/api/member/join', member)
		.then(({ data }) => {
			if (data.success) {
				alert(data.message);
				router.push({ path: '/login' });
			}
		})
		.catch(error => {
			alert(error.response.data.message);
		});
}
</script>

<style scoped>
.join_con {
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 100px 0;
}
.join_box {
	text-align: center;
	width: 700px;
}
</style>
