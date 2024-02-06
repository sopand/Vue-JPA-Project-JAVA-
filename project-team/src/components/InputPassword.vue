<template>
	<label class="input_tag">
		<span>{{ password_tag }}</span>
		<input
			type="password"
			id="password_input"
			:value="props.modelValue"
			@input="$emit('update:modelValue', $event.target.value)"
		/>
	</label>
</template>

<script setup>
const props = defineProps({
	modelValue: String,
	password_tag: String,
});
</script>
<style scoped>
.input_tag {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	height: 50px;
}
.input_tag span {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	width: 20%;
	padding-right: 15px;
	font-weight: bold;
}
.input_tag input {
	width: 60%;
	border: 1px solid #e2e2e2;
	border-radius: 10px;
	height: 40px;
	padding-left: 10px;
	font-size: 16px;
	font-weight: bold;
}
</style>
