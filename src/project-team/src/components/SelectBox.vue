<template>
	<div class="select_con">
		<label>{{ select_tag }}</label>
		<select v-model="selectedOption" @change="handleChange">
			<option :value="null" disabled selected>선택해주세요</option>
			<option v-for="listitem in list" :key="listitem" :value="listitem.value">
				{{ listitem.name }}
			</option>
		</select>
	</div>
</template>

<script setup>
import { ref } from 'vue';

defineProps({
	list: [],
	select_tag: String,
});
const emit = defineEmits(['change']);
const selectedOption = ref(null);
const handleChange = () => {
	emit('change', selectedOption.value);
};
</script>

<style scoped>
.select_con {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	height: 50px;
}
.select_con label {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	width: 20%;
	padding-right: 15px;
	font-weight: bold;
}
.select_con select {
	width: 60%;
	border: 1px solid #e2e2e2;
	border-radius: 10px;
	height: 40px;
	padding-left: 10px;
	font-size: 16px;
	font-weight: bold;
}
</style>
