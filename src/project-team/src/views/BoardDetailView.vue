<template>
	<MainHeader />
	<main class="board_detail_con">
		<section class="board_detail_box">
			<div class="board_detail_title">
				<article>제목 : {{ viewData.data.title }}</article>
				<article>
					작성일 : {{ $formatDateTimeArray(viewData.data.createDate) }} 수정일 :
					{{ $formatDateTimeArray(viewData.data.updateDate) }}
				</article>
			</div>
			<article class="board_detail_name">
				작성자 : {{ viewData.data.memberName }}
			</article>
			<article
				class="board_detail_content"
				v-html="viewData.data.content"
			></article>
		</section>

		<section class="board_detail_button">
			<ButtonComponent
				:button_tag="'수정'"
				:child-width="'200px'"
			></ButtonComponent>
			<ButtonComponent
				:child-width="'200px'"
				:button_tag="'삭제'"
				:child-color="'red'"
			></ButtonComponent>
		</section>

		<section class="comment_save_box">
			<InputText
				:child-height="'100px'"
				:child-width="'70%'"
				:text_tag="'댓글'"
				v-model="comment.content"
			/>
			<ButtonComponent
				:button_tag="'댓글 등록'"
				child-height="100px"
				child-width="20%"
				@click="addComment"
			/>
		</section>

		<section class="comment_list_box">
			<aritlce>
				<div>
					<div>작성자</div>
					<div>작성일</div>
				</div>

				<div>내용</div>
			</aritlce>
		</section>
	</main>
	<MainFooter />
</template>

<script setup>
import MainHeader from '@/components/MainHeader.vue';
import MainFooter from '@/components/MainFooter.vue';
import ButtonComponent from '@/components/ButtonComponent.vue';
import InputText from '@/components/InputText.vue';
import { onMounted, reactive } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const comment = reactive({
	boardSid: '',
	content: '',
});

function addComment() {
	const login = sessionStorage.getItem('id');
	if (login == null || login == '') {
		alert('로그인이 필요합니다');
		return false;
	}
	axios
		.post('/api/comment/', comment)
		.then(({ data }) => {
			if (data.success) {
				alert(data.message);
			}
		})
		.catch(error => {
			alert(error.response.data.message);
		});
}

const route = useRoute();
const viewData = reactive({
	data: {
		boardSid: 0,
		title: '',
		content: '',
		memberName: '',
		memberSid: 0,
		createDate: [],
		updateDate: [],
	},
});

const detailData = async sid => {
	comment.boardSid = sid;
	try {
		const response = await axios.get(`/api/board/detail/${sid}`);
		viewData.data = response.data;
	} catch (error) {
		console.error('데이터를 가져오는 중 오류 발생:', error);
	}
};
onMounted(() => {
	detailData(route.params.board_sid);
});
</script>

<style scoped>
.comment_list_box,
.board_detail_con,
.board_detail_box {
	display: flex;
	align-items: center;
	justify-content: center;
}
.comment_save_box,
.board_detail_title {
	display: flex;
	align-items: center;
	justify-content: space-around;
}
.board_detail_button {
	margin-top: 30px;
}
.comment_list_box {
	margin-top: 50px;
	width: 1000px;
	border: 1px solid red;
}
.board_detail_con {
	flex-direction: column;
}
.comment_save_box {
	margin-top: 50px;
	width: 1000px;
}

.board_detail_title {
	width: 100%;
	padding: 30px 0;
	border-bottom: 1px solid #e2e2e2;
}
.board_detail_con {
	width: 100%;
	padding: 100px 0;
}
.board_detail_box {
	flex-direction: column;
	width: 1000px;
	border: 1px solid #e2e2e2;
	border-radius: 15px;
}
.board_detail_name {
	padding: 20px 0 20px 120px;
	text-align: start;
	width: 100%;
	border-bottom: 1px solid #e2e2e2;
}
.board_detail_content {
	padding: 15px;
}
.board_detail_content :deep(img) {
	width: 100%;
}
</style>
