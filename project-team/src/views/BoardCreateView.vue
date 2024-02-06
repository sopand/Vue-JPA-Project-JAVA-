<template>
	<HeaderCom />
	<div class="board_con">
		<div class="board_box">
			<InputText v-model="board.title" :text_tag="'제목'" />
			<SelectBox
				:list="selectList"
				:select_tag="'카테고리'"
				@change="handleSelectChange"
			></SelectBox>
			<TextEditor v-on:update:value="handleTextChange" :label="'내용'" />
			<PrimaryButton @click="addBoard" button_tag="등록" />
		</div>
	</div>

	<FooterCom />
</template>

<script setup>
import HeaderCom from '@/components/MainHeader.vue';
import FooterCom from '@/components/MainFooter.vue';
import TextEditor from '@/components/TextEditor.vue';
import InputText from '@/components/InputText.vue';
import SelectBox from '@/components/SelectBox.vue';
import PrimaryButton from '@/components/ButtonComponent.vue';
import { reactive } from 'vue';
import axios from 'axios';

function addBoard() {
	axios
		.post('/api/board/create', board)
		.then(({ data }) => {
			if (data.success) {
				alert(data.message);
			}
		})
		.catch(error => {
			alert(error.response.data.message);
		});
}

function handleTextChange(value) {
	board.content = value;
}

function handleSelectChange(value) {
	board.category = value;
}

const selectList = [
	{
		name: '공지사항',
		value: '01',
	},
	{
		name: '커뮤니티',
		value: '02',
	},
	{
		name: 'Q&A',
		value: '03',
	},
];
const board = reactive({
	title: '',
	content: '',
	category: '',
});
</script>

<style scoped>
.board_con {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	padding: 100px 0;
}
.board_box {
	width: 800px;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
}
.board_con button {
	margin: 100px 0;
}
</style>
